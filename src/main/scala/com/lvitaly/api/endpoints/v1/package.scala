package com.lvitaly.api.endpoints

import com.lvitaly.api.model.*
import sttp.tapir.ztapir.*
import sttp.tapir.generic.auto.*
import sttp.tapir.json.zio.*

package object v1 extends Resolvers:
  private val apiV1 = api / "v1"

  private val books =
    endpoint.get
      .in("books")
      .out(jsonBody[List[Book]])
      .errorJsonOut

  private val booksAll: AppEndpoint =
    books
      .in("all")
      .securityBearer()
      .serverLogic(session => unit => getBooks)

  private val booksByYear: AppEndpoint =
    books
      .in(query[Int]("year"))
      .zServerLogic(year => getBooks(year))

  val endpoints: List[AppEndpoint] =
    List(
      booksAll,
      booksByYear
    )
