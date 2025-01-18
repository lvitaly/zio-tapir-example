package com.lvitaly.api.model

import zio.config.magnolia.*
import zio.Config

case class AppConfig(
    auth: AppAuthConfig,
    db: DbConfig
) derives Config

case class AppAuthConfig(
    token: String
)

case class DbConfig(
    url: String,
    username: String,
    password: String
)
