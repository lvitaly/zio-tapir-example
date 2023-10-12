package com.lvitaly.api.model

import zio.http.Server

case class AppConfig(
    server: Server.Config,
    auth: AppAuthConfig,
    books: List[Book]
)

case class AppAuthConfig(
    token: String
)
