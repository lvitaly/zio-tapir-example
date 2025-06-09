package com.lvitaly.api.repository

import com.augustnagro.magnum.magzio.TransactorZIO
import com.augustnagro.magnum.*
import com.lvitaly.api.model.{Book, RepositoryError}
import zio.{IO, URLayer, ZLayer, ZIO}

trait BookRepository:
  def getBooks: IO[RepositoryError, Vector[Book]]
  def getBooks(height: Int): IO[RepositoryError, Vector[Book]]

object BookRepository:
  val layer: URLayer[TransactorZIO, BookRepository] = ZLayer.derive[BookRepositoryLive]

final class BookRepositoryLive(tx: TransactorZIO) extends BookRepository:
  def getBooks: IO[RepositoryError, Vector[Book]] =
    tx.connect {
      sql"select * from book".query[Book].run()
    }.mapError(e => RepositoryError(e.getMessage))

  def getBooks(height: Int): IO[RepositoryError, Vector[Book]] =
    tx.connect {
      sql"select * from book where height = $height".query[Book].run()
    }.mapError(e => RepositoryError(e.getMessage))
