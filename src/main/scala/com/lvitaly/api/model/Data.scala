package com.lvitaly.api.model

import zio.json.*

case class Author(name: String)
object Author:
  given encoder: JsonEncoder[Author] = DeriveJsonEncoder.gen[Author]
  given decoder: JsonDecoder[Author] = DeriveJsonDecoder.gen[Author]

case class Book(title: String, year: Int, author: Author)
object Book:
  given encoder: JsonEncoder[Book] = DeriveJsonEncoder.gen[Book]
  given decoder: JsonDecoder[Book] = DeriveJsonDecoder.gen[Book]
