import Main.flag
import cats.effect.IO
import com.twitter.app.Flag
import com.twitter.finagle.Http
import com.twitter.server.TwitterServer
import com.twitter.util.Await
import com.twitter.logging.Logger
import scala.concurrent.ExecutionContext
import scala.util.Properties

object Main extends TwitterServer {

  private val log = Logger.get(getClass)

  private val port8081 = Properties.envOrElse("PORT", "8081").toInt

  implicit val ctx = IO.contextShift(ExecutionContext.global)

  def main(): Unit = {
    log.info(s"Serving the application on port ${port8081}")

    val app = new App()
    Await.ready(Http.server.serve(":" + port8081, app.toService))

  }
}
