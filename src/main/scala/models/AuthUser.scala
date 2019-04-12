package models

import java.time.OffsetDateTime

case class AuthUser(
    email: String,
    password: String,
    createdAt: OffsetDateTime,
    updatedAt: OffsetDateTime
)
