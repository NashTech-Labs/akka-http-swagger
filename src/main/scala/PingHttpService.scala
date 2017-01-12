

import javax.ws.rs.Path

import akka.http.scaladsl.server.Directives
import io.swagger.annotations.{ Api, ApiOperation, ApiResponse, ApiResponses }


@Api(value = "/ping", description = "Operations about get.", produces = "application/json", consumes = "application/json")
@Path("/ping")
trait PingHttpService extends Directives {

    @ApiOperation(value = "Find a ping", notes = "Returns a pong", httpMethod = "GET", response = classOf[String])
    @ApiResponses(Array(
    new ApiResponse(code = 404, message = "Pong not found"),
    new ApiResponse(code = 200, message = "Pong found"),
    new ApiResponse(code = 400, message = "Invalid Ping supplied")))
    def readRoute = path("ping") {
      complete("pong")
    }

}
