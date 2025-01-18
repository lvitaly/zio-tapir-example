package com.lvitaly.api.endpoints.v1

import com.lvitaly.api.model.*
import com.lvitaly.api.service.*
import zio.*

private trait Resolvers:

  private type Books = List[Book]

  protected def getBooks: ZIO[BookService, ApiError, Books] =
    ZIO.serviceWithZIO[BookService](_.getBooks)
  
  protected def getBooks(year: Int): ZIO[BookService, ApiError, Books] =
    getBooks.map(_.filter(_.height == year))

end Resolvers
