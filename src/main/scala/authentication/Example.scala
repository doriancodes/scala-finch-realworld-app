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

}
