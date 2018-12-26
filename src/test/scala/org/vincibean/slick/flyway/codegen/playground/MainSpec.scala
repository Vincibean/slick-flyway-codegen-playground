package org.vincibean.slick.flyway.codegen.playground

import java.time.LocalDate

import org.specs2.Specification
import org.specs2.concurrent.ExecutionEnv
import org.specs2.specification.core.SpecStructure
import slick.basic.DatabaseConfig
import slick.jdbc.JdbcProfile

import scala.concurrent.Future
import scala.concurrent.duration._
import scala.util.Try

class MainSpec(implicit ee: ExecutionEnv) extends Specification {
  private val dbConfig = DatabaseConfig.forConfig[JdbcProfile]("database")
  private val db = dbConfig.db

  import dbConfig.profile.api._

  override def is: SpecStructure =
    s2"""
        Auto generating code with Slick and Flyway should
          provide the right number of flights $s1
          provide the right number of planes $s2
          provide the right number of rows in the view $s3
          provide a view with valid IDs $s4
          provide a view with valid data $s5
      """

  private def s1 = {
    db.run(Tables.Flights.result).map(_.length) must beEqualTo(151102).await(5, 5.seconds)
  }

  private def s2 = {
    db.run(Tables.Planes.result).map(_.length) must beEqualTo(5029).await(5, 5.seconds)
  }

  private def s3 = {
    db.run(Tables.FlightsPlanes.result).map(_.length) must beEqualTo(100).await(5, 5.seconds)
  }

  private def s4 = {
    val res = db.run(Tables.FlightsPlanes.take(1).map(_.tailnum).result.headOption).map(_.flatten)
    res must beSome { x: String =>
      x must not beEmpty
    }.await(5, 5.seconds)
  }

  private def s5 = {
    def isoDateFormat(year: Int, month: Int, dayOfMonth: Int) = {
      Seq(year, f"$month%02d", f"$dayOfMonth%02d").mkString("-")
    }

    val futureDateString = db.run {
      Tables.FlightsPlanes
        .take(1)
        .map(x => (x.year, x.month, x.dayofmonth))
        .result.headOption
        .collect {
          case Some((Some(year), Some(month), Some(dayOfMonth))) => isoDateFormat(year, month, dayOfMonth)
        }
    }

    val res = for {
      dateString <- futureDateString
      localDate <- Future.fromTry(Try(LocalDate.parse(dateString)))
      today = LocalDate.now()
    } yield localDate isBefore today

    res must beTrue.await(5, 5.seconds)
  }

}
