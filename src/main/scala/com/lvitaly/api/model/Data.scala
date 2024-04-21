package com.lvitaly.api.model

import zio.json.*

case class Author(name: String) derives JsonEncoder, JsonDecoder

case class Book(title: String, year: Int, author: Author) derives JsonEncoder, JsonDecoder
