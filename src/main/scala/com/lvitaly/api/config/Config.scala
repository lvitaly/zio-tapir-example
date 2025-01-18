package com.lvitaly.api.config

import com.lvitaly.api.model.AppConfig
import zio.*

val live: Layer[Config.Error, AppConfig] =
  ZLayer {
    ZIO.config[AppConfig].tap(c => ZIO.logDebug(s"AppConfig: $c"))
  }
