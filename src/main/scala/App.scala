import cats.effect.{ContextShift, IO}
import com.twitter.finagle.Service
import com.twitter.finagle.http.{Request, Response}
import io.finch.{Application, BadRequest, Bootstrap, Endpoint, Ok}
import io.circe.generic.auto._
import io.finch.circe._
import models.{InputUser, User}
import services.AuthenticationService

class App(implicit S: ContextShift[IO]) extends Endpoint.Module[IO] {

  val authenticationService = new AuthenticationService

  case class Message(message: String)

  def getMessage(): IO[Message] = {
    IO.pure(Message("Hello, world!"))
  }

  val helloWorld: Endpoint[IO, Message] = get("hello") {
    getMessage().map(Ok)
  }

  val register: Endpoint[IO, User] = post("api" :: "users" :: jsonBody[InputUser]) {
    user: InputUser =>
      authenticationService.register(user).map(Ok)
  }

  val api = (helloWorld :+: register).handle {
    case e: Exception => BadRequest(e)
  }

  final def toService: Service[Request, Response] =
    Bootstrap
      .serve[Application.Json](api)
      .toService

}
