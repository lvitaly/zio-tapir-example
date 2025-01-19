package com.lvitaly.api.service

import com.lvitaly.api.model.{ApiError, Book}
import com.lvitaly.api.repository.BookRepository
import zio.{IO, Task, URLayer, ZIO, ZLayer}

trait BookService:
  def getBooks: IO[ApiError, List[Book]]
  def getBooks(height: Int): IO[ApiError, List[Book]]

object BookService:
  val layer: URLayer[BookRepository, BookService] = ZLayer.derive[BookServiceLive]

final class BookServiceLive(repository: BookRepository) extends BookService:
  def getBooks: IO[ApiError, List[Book]]              = repository.getBooks.map(_.toList)
  def getBooks(height: Int): IO[ApiError, List[Book]] = repository.getBooks(height).map(_.toList)
