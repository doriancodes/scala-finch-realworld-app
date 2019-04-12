package db

import cats.effect.IO
import doobie.util.transactor.Transactor

import scala.concurrent.ExecutionContext

trait DbConnection {



  implicit val cs = IO.contextShift(ExecutionContext.global)

  val xa = Transactor.fromDriverManager[IO](
    "org.postgresql.Driver", // driver classname
    "jdbc:postgresql:conduit", // connect URL (driver-specific)
    "postgres", // user
    "password" // password
  )

  val y = xa.yolo

}
