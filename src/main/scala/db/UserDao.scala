package db

import cats.effect.IO
import doobie._
import doobie.implicits._
import cats._
import cats.effect._
import cats.implicits._
import models.User

trait UserDao extends DbConnection {

  def insertUpdateUser(email: String,
                       token: String,
                       username: String,
                       bio: Option[String],
                       image: Option[String]): IO[User] =
    sql"insert into users (email, token, username, bio, image) values ($email, $token, $username, $bio, $image)".update.run
      .transact(xa).map( _ => User(email, token, username, bio, image))

}
