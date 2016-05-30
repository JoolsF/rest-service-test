package rest

import dao.PersonDAO
import model._
import akka.actor.Actor
import akka.event.slf4j.SLF4JLogging
import java.util.Date
import java.text.{ParseException, SimpleDateFormat}
import net.liftweb.json.Serialization._
import net.liftweb.json.{DateFormat, Formats}
import spray.http._
import spray.httpx.unmarshalling._
import spray.routing._

/**
  * Rest Service Actor
  */
class RestServiceActor extends Actor with RestService{

  implicit def actorRefFactory = context

//  def receive = runRoute(rest)

  def receive = ???
}



trait RestService extends HttpService with SLF4JLogging {

  val personService = new PersonDAO

  implicit val executionContext = actorRefFactory.dispatcher

  implicit val liftJsonFormats = new Formats{
    val dateFormat = new DateFormat {
      val sdf = new SimpleDateFormat("yyyy-MM-dd")

      def parse(s: String): Option[Date] = try {
        Some(sdf.parse(s))
      } catch {
        case e: Exception => None
      }

      def format(d: Date): String = sdf.format(d)
    }
  }

  implicit val string2Date = new FromStringDeserializer[Date] {
    def apply(value: String) = {
      val sdf = new SimpleDateFormat("yyyy-MM-dd")
      try Right(sdf.parse(value))
      catch {
        case e: ParseException => {
          Left(MalformedContent("'%s' is not a valid Date value" format (value), e))
        }
      }
    }
  }

  implicit val customRejectionHandler = RejectionHandler {
    case rejections => mapHttpResponse {
      response =>
        response.withEntity(HttpEntity(ContentType(MediaTypes.`application/json`),
          write(Map("error" -> response.entity.asString))))
    } {
      RejectionHandler.Default(rejections)
    }
  }



}