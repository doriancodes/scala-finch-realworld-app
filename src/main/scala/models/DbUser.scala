package models

import java.time.OffsetDateTime

case class DbUser(
    email: String,
    username: String,
    bio: Option[String],
    image: Option[String],
    createdAt: OffsetDateTime,
    updatedAt: OffsetDateTime
)
