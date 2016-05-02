package boot

import akka.actor.{ActorSystem, Props}
import akka.io.IO
import config.Configuration


/**
  * Trait App is mixed to turn Boot object into executable program whereas Configuration trait
  * provide access to the startup settings like host name and port number to run on.
  *
  * https://github.com/oermolaev/simple-scala-rest-example
  * http://sysgears.com/articles/building-rest-service-with-scala/
  *
  */


import spray.can.Http

object Boot extends App with Configuration {

  // create an actor system for application
  implicit val system = ActorSystem("rest-service-example")

  // create and start rest service actor
//  val restService = system.actorOf(Props[RestServiceActor], "rest-endpoint")

  // start HTTP server with rest service actor as a handler
//  IO(Http) ! Http.Bind(restService, serviceHost, servicePort)
}
