package dao

import slick.driver.MySQLDriver.api._
import model.PersonTable
import slick.jdbc.meta.MTable

import scala.concurrent.Future
import scala.concurrent.Await
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

/**
 *
 */
class PersonDAO {
  //Init database instance
  val db = Database.forConfig("db")

   /* Slick allows us to define and compose queries in advance of running them against the database.
    We start by defining a TableQuery object that represents a simple SELECT * style query
    on our message table: */
    val people = TableQuery[PersonTable]

    // Create tables if do not exist http://stackoverflow.com/questions/30036964/check-table-existance-in-slick-3-0
    // TODO - implement below in non-blocking way
    val tables = Await.result(db.run(MTable.getTables), 1.seconds).toList
    if(tables.isEmpty) {
      val action: DBIO[Unit] = people.schema.create
      val future = db.run(action)
      val result = Await.result(future, 5 seconds)
      println("created table")
    }

}
