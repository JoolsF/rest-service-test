package dao

import config.Configuration
import slick.driver.MySQLDriver.api._
import model.{Person, PersonTable}
import slick.jdbc.meta.MTable

import scala.concurrent.Future
import scala.concurrent.Await
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.Failure


class PersonDAO extends Configuration {
  // 1 Init DB instance
  val db = Database.forConfig("db")


  /* 2 Create Table Query object
   * Slick allows us to define and compose queries in advance of running them against the database.
   * We start by defining a TableQuery object that represents a simple SELECT * style query
   * on our message table:
   */
  val people = TableQuery[PersonTable]


  // 3 Create tables if they do not exist
  // http://stackoverflow.com/questions/30036964/check-table-existance-in-slick-3-0
  val tables = exec(MTable.getTables).toList
  if (tables.isEmpty) {
    exec(people.schema.create)
    println("created table")

    // 3.1 Insert test data in empty table
    def freshTestData = Seq(
      Person(firstName = "Julian", lastName = "Fenner"),
      Person(firstName = "Linda", lastName = "Florence"),
      Person(firstName = "David", lastName = "Fenner"),
      Person(firstName = "Andrea", lastName = "Fenner"),
      Person(firstName = "Graciela", lastName = "Fenner")
    )

    exec(people ++= freshTestData)
  }

  /**
    * Exec helper function
    */
  // TODO - implement below in non-blocking way
  // The best approach is simply to transform the Future query result
  // to a Future of an HTTP response and send that to the client.
  def exec[T](action: DBIO[T]): T = Await.result(db.run(action), 2 seconds)

  /**
    * Save person entity into database
    */
  def create(person: Person) = exec(people += person)

  //def delete(id: Long) = people.filter(_.id === id).delete.statements

  def getAll = exec(people.result)

  def getPerson(id: Int) = getAll.filter(_.id == id)


}
