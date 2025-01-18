package com.lvitaly.api.endpoints

import com.lvitaly.api.model.*
import sttp.tapir.ztapir.*
import sttp.tapir.generic.auto.*
import sttp.tapir.json.zio.*

package object v1 extends Resolvers:
  private val apiV1 = api / "v1"

  private val books =
    endpoint.get
      .in(apiV1 / "books")
      .out(jsonBody[List[Book]])
      .errorJsonOut

  private val booksAll: ApiEndpoint =
    books
      .in("all")
      .securityBearer()
      .serverLogic(session => unit => getBooks)

  private val booksByHeight: ApiEndpoint =
    books
      .in(query[Int]("height"))
      .zServerLogic(height => getBooks(height))

  val endpoints: List[ApiEndpoint] =
    List(
      booksAll,
      booksByHeight
    )

end v1
