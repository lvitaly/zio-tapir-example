package com.lvitaly.api.repository

import com.augustnagro.magnum.magzio.TransactorZIO
import com.lvitaly.api.model.RepositoryError
import zio.{IO, URLayer, ZIO, ZLayer}

type Repository = BookRepository

val live: URLayer[TransactorZIO, Repository] =
  BookRepository.layer
