package db

import java.sql.Timestamp
import java.time.{LocalDateTime, OffsetDateTime, ZoneId, ZoneOffset}

import doobie.util.{Get, Put}

object OffsetDateTimeModule {

  def toTimestamp(o: OffsetDateTime): Timestamp = {
    Timestamp.valueOf(LocalDateTime.ofInstant(o.toInstant(), ZoneOffset.UTC))
  }

  def fromTimestamp(ts: Timestamp): OffsetDateTime = {
    OffsetDateTime.of(ts.toLocalDateTime, ZoneOffset.UTC)
  }

  implicit val natGet: Get[OffsetDateTime] = Get[Timestamp].map(fromTimestamp)
  implicit val natPut: Put[OffsetDateTime] =
    Put[Timestamp].contramap(toTimestamp)

}
