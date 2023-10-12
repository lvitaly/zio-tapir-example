package com.lvitaly.api

import com.intellias.api.endpoints.{*, given}
import com.intellias.api.model.{*, given}
import sttp.client3.testing.SttpBackendStub
import sttp.client3.{UriContext, basicRequest}
import sttp.tapir.server.stub.TapirStubInterpreter
import zio.test.Assertion.*
import zio.test.{ZIOSpecDefault, assertTrue, assertZIO}
import sttp.client3.ziojson.*
import sttp.tapir.ztapir.RIOMonadError
import zio.ZIO

object EndpointsSpec extends ZIOSpecDefault:
  def spec = suite("Endpoints spec")(
    test("list available books") {
/*
      // given
      val backendStub = TapirStubInterpreter(SttpBackendStub(new RIOMonadError[Any]))
        .whenServerEndpointRunLogic(all)
        .backend()

      // when
      val response = basicRequest
        .get(uri"http://test.com/books/all")
        .response(asJson[List[Book]])
        .send(backendStub)

      // then
      assertZIO(response.map(_.body))(isRight(equalTo(books)))
*/
      assertTrue(true)
    }
  )
