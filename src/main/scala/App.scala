import cats.effect.{ContextShift, IO}
import com.twitter.finagle.Service
import com.twitter.finagle.http.{Request, Response}
import io.finch.{Application, BadRequest, Bootstrap, Endpoint, Ok}
import io.circe.generic.auto._
import io.finch.circe._

class App(implicit S: ContextShift[IO]) extends Endpoint.Module[IO] {

  case class Message(message: String)

  def getMessage(): IO[Message] = {
    IO.pure(Message("Hello, world!"))
  }

  val helloWorld: Endpoint[IO, Message] = get("hello") {
    getMessage().map(Ok)
  }

  final def toService: Service[Request, Response] =
    Bootstrap
      .serve[Application.Json](helloWorld)
      .toService

}
