package com.lvitaly.api
package endpoints

import sttp.tapir.server.metrics.prometheus.PrometheusMetrics
import zio.RIO

private type ApiIO[A] = RIO[AppEnv, A]

val prometheusMetrics: PrometheusMetrics[ApiIO] = PrometheusMetrics.default[ApiIO]()
private val metrics: ApiEndpoint                = prometheusMetrics.metricsEndpoint
