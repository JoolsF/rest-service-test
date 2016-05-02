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

class Customers(tag: Tag) extends Table[Customer](tag, "customers"){

  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def firstName = column[String]("first_name")
  def lastName = column[String]("last_name")
  //def birthday = column[java.util.Date]("birthday", O.Nullable) // can't add O.Nullable here

  //http://stackoverflow.com/questions/25158212/scala-problems-with-slick-2-when-case-class-and-object-name-are-same
  def * = Customer.apply _


}

