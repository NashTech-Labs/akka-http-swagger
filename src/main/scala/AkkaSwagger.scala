

/**
  * Created by knoldus on 9/1/17.
  */

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Route
import akka.stream.ActorMaterializer
import scala.concurrent.ExecutionContext.Implicits.global
import scala.io.StdIn


object AkkaSwagger extends PingHttpService{
  def main(args: Array[String]) {

    implicit val actorSystem = ActorSystem("akka-system")
    implicit val flowMaterializer = ActorMaterializer()

    val interface = "localhost"
    val port = 8080

    def route: Route = putRoute ~postRoute ~ webSocketRoute ~ readRoute ~ new SwaggerDocService(actorSystem).routes

    val binding = Http().bindAndHandle(route, interface, port)
    println(s"Server is now online at http://$interface:$port\nPress RETURN to stop...")
    StdIn.readLine()

    binding.flatMap(_.unbind()).onComplete(_ => actorSystem.shutdown())
    println("Server is down...")

  }



}

