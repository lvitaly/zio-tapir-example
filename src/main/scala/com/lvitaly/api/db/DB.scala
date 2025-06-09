package com.lvitaly.api.db

import com.lvitaly.api.model.{AppConfig, DbConfig}
import com.zaxxer.hikari.{HikariConfig, HikariDataSource}
import zio.{RLayer, URIO, ZIO, ZLayer}
import com.augustnagro.magnum.magzio.TransactorZIO
import zio.{URLayer, ZLayer}

import javax.sql.DataSource

private val config: URIO[AppConfig, HikariConfig] =
  ZIO.serviceWith[AppConfig] { cfg =>
    val DbConfig(url, username, password) = cfg.db

    val hc = new HikariConfig()
    hc.setJdbcUrl(url)
    hc.setUsername(username)
    hc.setPassword(password)
    hc
  }

private val dataSource: RLayer[AppConfig, DataSource] =
  ZLayer.scoped {
    ZIO.acquireRelease {
      config.flatMap(cfg => ZIO.attempt(new HikariDataSource(cfg)))
    } { ds =>
      ZIO.attempt(ds.close()).orDie
    }
  }
  
val live: RLayer[AppConfig, TransactorZIO] = dataSource >>> TransactorZIO.layer
