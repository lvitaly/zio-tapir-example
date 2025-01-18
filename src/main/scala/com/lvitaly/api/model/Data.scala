package com.lvitaly.api.model

import zio.json.*

case class Author(name: String) derives JsonCodec

case class Book(
    id: Long,
    title: String,
    author: Option[String],
    genre: String,
    subGenre: String,
    height: Int,
    publisher: Option[String]
) derives JsonCodec
