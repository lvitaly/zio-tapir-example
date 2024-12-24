package com.lvitaly.api.endpoints

import com.lvitaly.api.model.{*, given}
import com.lvitaly.api.{AppEnv, ZIOAppRuntime, appEnv}
import sttp.client3.testing.SttpBackendStub
import sttp.client3.ziojson.*
import sttp.client3.{UriContext, basicRequest}
import sttp.tapir.server.stub.TapirStubInterpreter
import sttp.tapir.ztapir.RIOMonadError
import zio.test.Assertion.*
import zio.test.{TestEnvironment, ZIOSpecDefault, assertZIO, testEnvironment}
import zio.{ZIO, ZLayer}

object EndpointsSpec extends ZIOSpecDefault with ZIOAppRuntime:

  override val bootstrap: ZLayer[Any, Any, TestEnvironment] =
    slf4jLogging >>> typesafeConfig >>> testEnvironment

  def spec = suite("Endpoints spec") {
    // given
    val backendStub = TapirStubInterpreter(SttpBackendStub(new RIOMonadError[AppEnv]))
      .whenServerEndpointsRunLogic(all)
      .backend()

    test("list all available books") {
      // when
      val response = basicRequest
        .get(uri"http://test.com/api/v1/books/all")
        .auth
        .bearer("test-token")
        .response(asJson[List[Book]])
        .send(backendStub)

      // then
      assertZIO(response.map(_.body))(isRight(isNonEmpty))
    } +
      test("list available books by year") {
        // when
        val response = basicRequest
          .get(uri"http://test.com/api/v1/books?year=1888")
          .response(asJson[List[Book]])
          .send(backendStub)

        // then
        assertZIO(response.map(_.body))(isRight(isEmpty || assertion("should contain year 1888")(_.exists(_.year == 1888))))
      }
  }.provide(appEnv)
