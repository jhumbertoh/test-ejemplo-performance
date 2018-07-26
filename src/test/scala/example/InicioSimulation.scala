package example



import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.protocol.HttpProtocolBuilder
import io.gatling.core.structure.ScenarioBuilder


class InicioSimulation extends Simulation {

  private val baseUrl = "https://www.tudominiourl.com"
  private val uri = "https://www.tudominiourl.com/api/v1/comics"
  private val contentType = "application/json"
  private val endpoint = "/api/v1/comics"
  private val requestCount = 5



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