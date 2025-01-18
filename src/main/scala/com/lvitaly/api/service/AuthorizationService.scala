package com.lvitaly.api.service

import com.lvitaly.api.model.*
import zio.*

trait AuthorizationService:
  def authorize(token: MaybeToken, requiredRoles: Set[UserRole]): IO[AuthorizationError, AuthUserSession]

object AuthorizationService:
  val layer: URLayer[AppConfig, AuthorizationService] = ZLayer {
    ZIO.serviceWith[AppConfig] { config => (token: MaybeToken, requiredRoles: Set[UserRole]) =>
      if token.value == config.auth.token
      then ZIO.succeed(AuthUserSession(ValidToken(token.value), "The Dude"))
      else ZIO.fail(AuthorizationError("Token is not valid!"))
    }
  }
