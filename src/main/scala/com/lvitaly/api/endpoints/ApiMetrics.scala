package com.lvitaly.api.endpoints

import com.lvitaly.api.Layers.AppEnv
import sttp.tapir.server.metrics.prometheus.PrometheusMetrics
import zio.RIO

trait ApiMetrics:
  private type ApiIO[A] = RIO[AppEnv, A]

  val prometheusMetrics: PrometheusMetrics[ApiIO] = PrometheusMetrics.default[ApiIO]()
  val metrics: AppEndpoint                        = prometheusMetrics.metricsEndpoint
