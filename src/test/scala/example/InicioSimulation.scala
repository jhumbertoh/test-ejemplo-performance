package example



import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.protocol.HttpProtocolBuilder
import io.gatling.core.structure.ScenarioBuilder


class InicioSimulation extends Simulation {

  private val baseUrl = "https://gateway.marvel.com"
  private val contentType = "application/json"
  private val endpoint = "/v1/public/characters?ts=1&apikey=YOURAPIKEY&hash=YOURHASH"
  private val requestCount = 50

  val httpProtocol: HttpProtocolBuilder = http
    .baseURL(baseUrl)
    .inferHtmlResources()
    .acceptHeader("*/*")
    .contentTypeHeader(contentType)
    .userAgentHeader("curl/7.54.0")

  val headers_0 = Map("Expect" -> "100-continue")

  val scn: ScenarioBuilder = scenario("MyScenario")
    .exec(http("request_0")
      .get(endpoint)
      .headers(headers_0)
      .check(status.is(200)))

  setUp(scn.inject(atOnceUsers(requestCount))).protocols(httpProtocol)

}