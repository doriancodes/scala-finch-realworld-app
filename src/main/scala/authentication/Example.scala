package authentication

import java.time.Instant
import pdi.jwt.{JwtCirce, JwtAlgorithm, JwtClaim}

trait Example {

  //TODO GET RID OF

  val claim = JwtClaim(
    expiration = Some(Instant.now.plusSeconds(157784760).getEpochSecond),
    issuedAt = Some(Instant.now.getEpochSecond))

  val key = "secretKey"

  val algo = JwtAlgorithm.HS256

  val token = JwtCirce.encode(claim, key, algo)

  JwtCirce.decodeJson(token, key, Seq(JwtAlgorithm.HS256))

  JwtCirce.decode(token, key, Seq(JwtAlgorithm.HS256))

  /*
  val claim = JwtClaim(
  expiration = Some(Instant.now.plusSeconds(157784760).getEpochSecond),
  issuedAt = Some(Instant.now.getEpochSecond))

val claim2 = JwtClaim(
  expiration = Some(Instant.now.plusSeconds(5).getEpochSecond),
  issuedAt = Some(Instant.now.getEpochSecond))

val key = "secretKey"

val algo = JwtAlgorithm.HS256

val a = JwtCirce.encode(claim, key, algo)

val b = JwtCirce.encode(claim, "another", algo)

val c = JwtCirce.encode(claim2, "ahahjhkhs", algo)

val token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1NTQ5OTA0NjksImlhdCI6MTU1NDk5MDQ2NH0.F9I12-8WStC5tcYQ1gL9XQcaCm7dhnCg7jlkqzTpejw"

JwtCirce.decode(token,"ahahjhkhs", Seq(JwtAlgorithm.HS256))

JwtCirce.decode(a,key, Seq(JwtAlg
 */

}
