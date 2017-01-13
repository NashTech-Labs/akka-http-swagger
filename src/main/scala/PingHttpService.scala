

import javax.ws.rs.Path

import akka.http.scaladsl.model.ws.{Message, TextMessage}
import akka.http.scaladsl.server.{Directives, Route}
import akka.stream.scaladsl.Flow
import io.swagger.annotations.{ApiImplicitParams, ApiOperation, ApiResponses, _}


@Api(value = "/ping", description = "Operations about get.", produces = "application/json", consumes = "application/json")
@Path("/ping")
trait PingHttpService extends Directives {

  @ApiOperation(value = "Find a ping", notes = "Returns a pong", httpMethod = "GET", response = classOf[String])
  @ApiImplicitParams(Array(
    new ApiImplicitParam(name = "Authorization", value = "Authorization", required = true,
      dataType = "string", paramType = "header")))
  @ApiResponses(Array(
    new ApiResponse(code = 404, message = "Pong not found"),
    new ApiResponse(code = 200, message = "Pong found"),
    new ApiResponse(code = 400, message = "Invalid Ping supplied")))
  def readRoute = path("ping") {
    complete("pong")
  }

  @Path("/postPing")
  @ApiOperation(value = "Find a ping", notes = "Returns a pong", httpMethod = "POST",response = classOf[String])
  @ApiImplicitParams(Array(
    new ApiImplicitParam(name = "Authorization", value = "Authorization", required = true,
      dataType = "string", paramType = "header"),
    new ApiImplicitParam(name = "data", value = "\"data\" to sum", required = true,
      dataType = "string", paramType = "form"),
    new ApiImplicitParam(name = "file", required = true,
      dataType = "file", paramType = "form")
    ))
  @ApiResponses(Array(
    new ApiResponse(code = 404, message = "websocket not found"),
    new ApiResponse(code = 200, message = "websocket found"),
    new ApiResponse(code = 400, message = "Invalid websocket supplied")))
   def postRoute = path("postPing") {
    complete("post pong")
   }

  def webSocketRoute: Route = path("websocket") {
    handleWebSocketMessages(broadcast)
  }

  def broadcast: Flow[Message, Message, Any] = {
    Flow[Message].mapConcat {
      case tm: TextMessage =>
        TextMessage(tm.textStream) :: Nil
    }
  }


  @Path("/putRoute")
  @ApiOperation(value = "Find a ping", notes = "Returns a pong", httpMethod = "PUT",response = classOf[String])
  @ApiImplicitParams(Array(
    new ApiImplicitParam(name = "Authorization", value = "Authorization", required = true,
      dataType = "string", paramType = "header"),
    new ApiImplicitParam(name = "data", value = "data", required = true,
      dataType = "string", paramType = "form"),
    new ApiImplicitParam(name = "file", value = "file", required = true,
      dataType = "file", paramType = "form")
  ))
  @ApiResponses(Array(
    new ApiResponse(code = 404, message = "websocket not found"),
    new ApiResponse(code = 200, message = "websocket found"),
    new ApiResponse(code = 400, message = "Invalid websocket supplied")))
  def putRoute = path("put") {
    complete("put pong")
  }
}

