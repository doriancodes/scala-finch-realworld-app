package services

import java.time.OffsetDateTime

import authentication.JWTUtils
import cats.effect.IO
import db.UserDao
import models.{InputUser, User}

class AuthenticationService extends JWTUtils with UserDao {

  def register(inputUser: InputUser): IO[User] = {

    insertUpdateUser(
      inputUser.user.email,
      inputUser.user.username,
      None,
      None,
      OffsetDateTime.now(),
      OffsetDateTime.now()
    ).map(
      newUser =>
        User(
          newUser.email,
          generateToken,
          newUser.username,
          newUser.bio,
          newUser.image
      ))

  }

}
