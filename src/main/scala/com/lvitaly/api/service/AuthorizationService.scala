package com.lvitaly.api
package service

import model.*
import zio.*

trait AuthorizationService:
  def authorize(token: MaybeToken, requiredRoles: Set[UserRole]): AuthorizationService.AuthIO

object AuthorizationService:
  type AuthIO = IO[AuthorizationError, AuthUserSession]

  val live: URLayer[AppConfig, AuthorizationService] = ZLayer {
    ZIO.serviceWith[AppConfig] { config => (token: MaybeToken, requiredRoles: Set[UserRole]) =>
      if token.value == config.auth.token
      then ZIO.succeed(AuthUserSession(ValidToken(token.value), "The Dude"))
      else ZIO.fail(AuthorizationError("Token is not valid!"))
    }
  }
