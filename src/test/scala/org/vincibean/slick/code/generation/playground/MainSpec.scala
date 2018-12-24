package org.vincibean.slick.code.generation.playground

import models.Tables
import org.specs2.Specification
import org.specs2.concurrent.ExecutionEnv
import org.specs2.specification.core.SpecStructure
import slick.basic.DatabaseConfig
import slick.jdbc.JdbcProfile

import scala.concurrent.duration._

class MainSpec(implicit ee: ExecutionEnv) extends Specification {
  private val dbConfig = DatabaseConfig.forConfig[JdbcProfile]("database")
  private val db = dbConfig.db
  import dbConfig.profile.api._

  override def is: SpecStructure =
    s2"""
        Flyway must
          provide the right number of flights $s1
          provide the right number of planes $s2
      """

  private def s1 = {
    db.run(Tables.Flights.result).map(_.length) must beEqualTo(151102).await(3, 5.seconds)
  }

  private def s2 = {
    db.run(Tables.Planes.result).map(_.length) must beEqualTo(5029).await(3, 5.seconds)
  }

}