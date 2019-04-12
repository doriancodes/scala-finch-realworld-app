package models

import java.time.OffsetDateTime

case class User(
    email: String,
    token: String,
    username: String,
    bio: Option[String],
    image: Option[String]
)
