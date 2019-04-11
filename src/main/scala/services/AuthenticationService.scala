package services

import authentication.JWTUtils
import cats.effect.IO
import db.UserDao
import models.{InputUser, User}

class AuthenticationService extends JWTUtils with UserDao {

  def register(inputUser: InputUser): IO[User] = {

    insertUpdateUser(
      inputUser.user.email,
      generateToken,
      inputUser.user.username,
      None,
      None
    )

  }

}
