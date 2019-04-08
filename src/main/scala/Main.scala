import Main.flag
import cats.effect.IO
import com.twitter.app.Flag
import com.twitter.finagle.Http
import com.twitter.server.TwitterServer
import com.twitter.util.Await

import scala.concurrent.ExecutionContext
import scala.util.Properties

object Main extends TwitterServer {

  private val port8081 = Properties.envOrElse("PORT", "8081").toInt
  private val port: Flag[Int] =
    flag("port", port8081, "TCP port for HTTP server")
  implicit val ctx = IO.contextShift(ExecutionContext.global)

  def main(): Unit = {
    //  private val logging = Logger("finch-example-new")
    //   logging.info(s"Serving the application on port ${port()}")
    println(s"Serving the application on port ${port()}")

    val app = new App()
    Await.ready(Http.server.serve(":" + port8081, app.toService))

  }
}
