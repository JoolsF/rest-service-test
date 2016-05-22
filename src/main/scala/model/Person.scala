package model

import slick.driver.MySQLDriver.api._

/**
  * http://sysgears.com/articles/building-rest-service-with-scala/
  * http://slick.lightbend.com/doc/2.0.0-M3/lifted-embedding.html
  */
final case class Person (
  id: Long,
  firstName: String,
  lastName: String) {

  def testData = Seq(
    Person(1, "John",   "Lennon"),
    Person(2, "Ringo",  "Starr"),
    Person(3, "Paul",   "McCartney"),
    Person(4, "George", "Harrison")
  )
}

final class PersonTable(tag: Tag) extends Table[Person](tag, "person"){

  def id      = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def firstName  = column[String]("firstName")
  def lastName = column[String]("lastName")

  def * = (id, firstName, lastName) <> (Person.tupled, Person.unapply)

  // val findById = for {
  //   id <- Parameters[Long]
  // } yield id

}
