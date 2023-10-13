package com.lvitaly.api
package endpoints

import model.*
import service.AuthorizationService
import sttp.tapir.*
import sttp.tapir.ztapir.*
import zio.ZIO

extension [I, A, R](endpoint: PublicEndpoint[I, ApiError, A, R])
  def securityBearer(roles: UserRole*): ZPartialServerEndpoint[AppEnv, MaybeToken, AuthUserSession, I, ApiError, A, R] =
    endpoint
      .securityIn(sttp.tapir.auth.bearer[MaybeToken]())
      .zServerSecurityLogic[AppEnv, AuthUserSession] { token =>
        ZIO.serviceWithZIO[AuthorizationService](_.authorize(token, roles.toSet))
      }
