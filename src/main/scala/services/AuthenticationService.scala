package services

import java.time.OffsetDateTime

import authentication.JWTUtils
import cats.effect.IO
import db.UserDao
import models.{InputUser, User}
import org.mindrot.jbcrypt.BCrypt

class AuthenticationService extends JWTUtils with UserDao {

  def register(inputUser: InputUser): IO[User] = {

    for {
      _ <- insertUpdateAuthUser(
        inputUser.user.email,
        BCrypt.hashpw(inputUser.user.password, BCrypt.gensalt(12)),
        OffsetDateTime.now(),
        OffsetDateTime.now())
      newUser <- insertUpdateUser(
        inputUser.user.email,
        inputUser.user.username,
        None,
        None,
        OffsetDateTime.now(),
        OffsetDateTime.now()
      )
    } yield
      User(
        newUser.email,
        generateToken,
        newUser.username,
        newUser.bio,
        newUser.image
      )

    /*
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
      ))*/

  }

}
