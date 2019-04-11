package models

case class InputUser(
    user: UserCredentials
)

case class UserCredentials(
    username: String,
    email: String,
    password: String
)
