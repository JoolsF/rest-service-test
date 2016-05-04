package model

import slick.driver.MySQLDriver.api._

//import slick.driver.MySQLDriver.api._
/**
  * http://sysgears.com/articles/building-rest-service-with-scala/
  * http://slick.lightbend.com/doc/2.0.0-M3/lifted-embedding.html
  */

/**
  * @param id
  * @param firstName
  * @param lastName
  * @param birthday
  */
case class Customer( id: Option[Long],
                     firstName: String,
                     lastName:  String,
                     birthday: Option[java.util.Date])

class Customers(tag: Tag) extends Table[Customer](tag, "customers") {

  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def firstName = column[String]("first_name")
  def lastName = column[String]("last_name")
  def birthday = column[Option[java.util.Date]]("birthday") // can't add O.Nullable here, using option instead

  //http://stackoverflow.com/questions/25158212/scala-problems-with-slick-2-when-case-class-and-object-name-are-same
  def * = Customer.apply _

  /**
   * An implicit dateTypeMapper was added due to default date mapping limitations. Slick supports only java.sql._ dates
   * out of the box, but java.util.Date type is used to define birthday property of Customer objects.
   */
  implicit val dateTypeMapper = MappedColumnType.base[java.util.Date, java.sql.Date] (
    ud => new java.sql.Date(ud.getTime),
    sd => new java.util.Date(sd.getTime)
  )

  /**
   * findById is a Slick's Query Template - parametrized query. A template works like a function that takes some
   * parameters and returns a Query for them except that template is more efficient, because it doesn't
   * require complete query recompilation at each run.
   */
  //TODO fix
//  val findById = for {
//    id <- Parameters[Long]
//    c <- this if c.id is id
//  } yield c

}