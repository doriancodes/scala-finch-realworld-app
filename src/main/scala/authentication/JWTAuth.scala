package authentication

import cats.effect.IO
import io.circe._
import io.finch._
import io.finch.Endpoint.{cookieOption, headerOption, path}
import pdi.jwt._
import pdi.jwt.algorithms.JwtHmacAlgorithm
import io.circe.parser._

import scala.util.{Failure, Success}

sealed trait JwtAuth {
  val key: String
  val algorithm: JwtHmacAlgorithm

  protected def by(e: Endpoint[IO, Option[String]]): Endpoint[IO, JwtClaim] =
    e.map(_.map(JwtCirce.decode(_, key, Seq(algorithm)))).mapOutput {
      case Some(result) =>
        result match {
          case Success(x) => Ok(x)
          case Failure(_) => Unauthorized(JwtAuthFailed)
        }
      case None => Unauthorized(HeaderMissing)
    }

  protected def byAs[A: Decoder](
      e: Endpoint[IO, Option[String]]): Endpoint[IO, A] =
    by(e).mapOutput { claim =>
      decode[A](claim.content) match {
        case Right(x) => Ok(x)
        case Left(er) => BadRequest(er)
      }
    }
}

object JwtAuthFailed extends Exception {
  override def getMessage: String = "JWT is invalid"
}

object HeaderMissing extends Exception {
  override def getMessage: String = "Authentication header was not present"
}

final case class HeaderJwtAuth(key: String,
                               algorithm: JwtHmacAlgorithm,
                               headerName: String)
    extends JwtAuth {
  def auth: Endpoint[IO, JwtClaim] = by(headerOption(headerName))
  def authAs[A: Decoder] = byAs(headerOption(headerName))
}

final case class UrlJwtAuth(key: String, algorithm: JwtHmacAlgorithm)
    extends JwtAuth {
  def auth: Endpoint[IO, JwtClaim] = by(path[IO, String].map(Option.apply))
  def authAs[A: Decoder]: Endpoint[IO, A] =
    byAs(path[IO, String].map(Option.apply))
}

final case class CookieJwtAuth(key: String,
                               algorithm: JwtHmacAlgorithm,
                               cookieName: String)
    extends JwtAuth {
  def auth: Endpoint[IO, JwtClaim] =
    by(cookieOption[IO](cookieName).map(_.map(_.value)))

  def authAs[A: Decoder] =
    byAs(cookieOption[IO](cookieName).map(_.map(_.value)))

}
