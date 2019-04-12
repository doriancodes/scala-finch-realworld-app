package authentication

import java.time.Instant

import pdi.jwt.{JwtAlgorithm, JwtCirce, JwtClaim}

import scala.util.Random

trait JWTUtils {

  val a = java.time.OffsetDateTime.now()

  val claim = JwtClaim(
    expiration = Some(Instant.now.plusSeconds(3600).getEpochSecond), //one hour
    issuedAt = Some(Instant.now.getEpochSecond))

  lazy val key = Random.alphanumeric.take(16).mkString("")

  val algo = JwtAlgorithm.HS256

  def generateToken: String = JwtCirce.encode(claim, key, algo)

}
