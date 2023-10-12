package com.lvitaly.api.endpoints.v1

import com.lvitaly.api.model.*
import zio.*

private trait Resolvers {
  type Books = List[Book]
  
  protected def getBooks: URIO[AppConfig, Books] =
    ZIO.serviceWith[AppConfig](_.books)
  
  protected def getBooks(year: Int): URIO[AppConfig, Books] =
    getBooks.map(_.filter(_.year == year))
}
