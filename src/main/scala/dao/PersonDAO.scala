package dao

import slick.driver.MySQLDriver.api._
import model.PersonTable
import scala.concurrent.Future
import scala.concurrent.Await
import scala.concurrent.duration._

/**
 *
 */
class PersonDAO {
  val db = Database.forConfig("db")

  val people = TableQuery[PersonTable]

  val action: DBIO[Unit] = people.schema.create

  val future = db.run(action)

  val result = Await.result(future, 2 seconds)

println("running")

}
