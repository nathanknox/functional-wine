package domain

import zio.test._
import zio.test.TestAspect._
import zio.test.Assertion._
import zio._

object WineSpec extends ZIOSpecDefault {
  override def spec = suite("WineSpec")(
    test("exampleTest") {
      val program = for {
        result <- ZIO.fail(new RuntimeException("NOT YET IMPLEMENTED"))
      } yield assertTrue(1 == 1)

      program
    } // @@ failing
  ).provide()
}
