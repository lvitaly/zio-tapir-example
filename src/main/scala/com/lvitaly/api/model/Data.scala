package com.lvitaly.api.model

import zio.json.*

case class Author(name: String) derives JsonCodec

case class Book(title: String, year: Int, author: Author) derives JsonCodec
