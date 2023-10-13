package com.lvitaly.api.model

case class MaybeToken(value: String) extends AnyVal
case class ValidToken(value: String) extends AnyVal
case class AuthUserSession(token: ValidToken, originalUsername: String)
case class UserRole(value: String)   extends AnyVal
