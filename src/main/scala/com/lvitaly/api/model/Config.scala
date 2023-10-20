package com.lvitaly.api.model

case class AppConfig(
    auth: AppAuthConfig,
    books: List[Book]
)

case class AppAuthConfig(
    token: String
)
