package com.lvitaly.api.endpoints

import com.lvitaly.api.Layers.AppEnv
import com.lvitaly.api.model.auth.*
import com.lvitaly.api.model.*
import com.lvitaly.api.service.AuthorizationService
import sttp.tapir.*
import sttp.tapir.ztapir.*
import zio.ZIO

import scala.language.implicitConversions

private[endpoints] trait ApiSecurity:

  extension [I, A, R](endpoint: PublicEndpoint[I, ApiError, A, R])
    def securityBearer(roles: UserRole*): ZPartialServerEndpoint[AppEnv, MaybeToken, AuthUserSession, I, ApiError, A, R] =
      endpoint
        .securityIn(sttp.tapir.auth.bearer[MaybeToken]())
        .zServerSecurityLogic[AppEnv, AuthUserSession] { token =>
          ZIO.serviceWithZIO[AuthorizationService](_.authorize(token, roles.toSet))
        }
