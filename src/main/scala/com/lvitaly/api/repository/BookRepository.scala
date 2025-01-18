package com.lvitaly.api.repository

import com.augustnagro.magnum.magzio.*
import com.lvitaly.api.model.{Book, RepositoryError}
import zio.{IO, Task, URLayer, ZLayer, ZIO}

trait BookRepository:
  def getBooks: IO[RepositoryError, Vector[Book]]
  def getBooks(height: Int): IO[RepositoryError, Vector[Book]]

object BookRepository:
  val layer: URLayer[Transactor, BookRepository] =
    ZLayer {
        ZIO.serviceWith[Transactor](BookRepositoryLive(_))
    }

class BookRepositoryLive(tx: Transactor) extends BookRepository:
  def getBooks: IO[RepositoryError, Vector[Book]] =
    tx.connect {
      sql"select * from book".query[Book].run()
    }.mapError(e => RepositoryError(e.getMessage))

  def getBooks(height: Int): IO[RepositoryError, Vector[Book]] =
    tx.connect {
      sql"select * from book where height = $height".query[Book].run()
    }.mapError(e => RepositoryError(e.getMessage))
