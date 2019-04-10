package authentication

import java.time.Instant

import pdi.jwt.{JwtAlgorithm, JwtCirce, JwtClaim}

import scala.util.Random

trait JWTUtils {

  val claim = JwtClaim(
    expiration = Some(Instant.now.plusSeconds(157784760).getEpochSecond),
    issuedAt = Some(Instant.now.getEpochSecond))

  val key = Random.alphanumeric.take(16).mkString("")

  val algo = JwtAlgorithm.HS256

  def generateToken: String = JwtCirce.encode(claim, key, algo)

}
