package com.lvitaly.api
package service

import model.*
import zio.config.*
import zio.config.magnolia.deriveConfig
import zio.config.typesafe.TypesafeConfigProvider
import zio.http.Server
import zio.{Config, Layer, ZIO, ZLayer}

object ConfigService:
  private val config: Config[AppConfig] = {
    deriveConfig[AppAuthConfig].nested("auth") ++
    deriveConfig[List[Book]].nested("books")
  }.to[AppConfig]

  val live: Layer[Config.Error, AppConfig] =
    ZLayer {
      ZIO.config(config).tap(c => ZIO.logDebug(s"AppConfig: $c"))
    }
