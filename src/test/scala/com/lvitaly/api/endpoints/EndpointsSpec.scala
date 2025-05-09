package com.lvitaly.api.endpoints

import com.lvitaly.api.model.{*, given}
import com.lvitaly.api.{AppEnv, ZIOAppRuntime, appEnv}
import sttp.client4.testing.StreamBackendStub
import sttp.client4.ziojson.asJson
import sttp.client4.{UriContext, basicRequest}
import sttp.tapir.server.stub4.TapirStreamStubInterpreter
import sttp.tapir.ztapir.RIOMonadError
import zio.test.Assertion.*
import zio.test.{TestEnvironment, ZIOSpecDefault, assertZIO, testEnvironment}
import zio.{ZIO, ZLayer}

object EndpointsSpec extends ZIOSpecDefault with ZIOAppRuntime:

  override val bootstrap: ZLayer[Any, Any, TestEnvironment] =
    slf4jLogging >>> typesafeConfig >>> testEnvironment

  def spec = suite("Endpoints spec") {
    // given
    val backendStub = TapirStreamStubInterpreter(StreamBackendStub(new RIOMonadError[AppEnv]))
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
      test("list available books by height") {
        val height = 211

        // when
        val response = basicRequest
          .get(uri"http://test.com/api/v1/books?height=$height")
          .response(asJson[List[Book]])
          .send(backendStub)

        // then
        assertZIO(response.map(_.body))(isRight(assertion(s"should contain height $height")(_.exists(_.height == height))))
      }
  }.provide(appEnv)

end EndpointsSpec
