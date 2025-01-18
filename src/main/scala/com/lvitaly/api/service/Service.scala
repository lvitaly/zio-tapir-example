package com.lvitaly.api.service

import com.lvitaly.api.model.*
import com.lvitaly.api.repository.BookRepository
import zio.*

type Service = AuthorizationService & BookService

val live: URLayer[AppConfig & BookRepository, Service] =
  AuthorizationService.layer ++ BookService.layer