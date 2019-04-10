package models

case class User(
               email: String,
               token: String,
               username: String,
               bio: Option[String],
               image: Option[String]
               )
