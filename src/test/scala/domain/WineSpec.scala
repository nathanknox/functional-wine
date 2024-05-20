package domain

import zio.test._
import zio.test.TestAspect._
import zio.test.Assertion._
import zio.ZIO
import zio.Chunk
import domain.BottleLayout._
import zio.Duration
import zio.durationInt

object WineSpec extends ZIOSpecDefault {
  val testIntGen          = Gen.int(0, 100)
  val testBottleLayoutGen = BottleLayout.gen
  val testBottleGen       = Bottle.gen
  override def spec = suite("WineSpec")(
    test("a Shelf can hold a Bottle") {
      val bottle: Bottle = Bottle(BottleDetails.BottlePlaceholder)
      val shelf: Shelf   = Shelf(Chunk(bottle), ShelfDetails(capacity = 1))
      assertTrue(true)
    } @@ timed,
    test("can Gen some Ints") {
      check(testIntGen) { n =>
        assertTrue {
          //println(s"an INT: $n")
          n + n == 2 * n
        }
      }
    } @@ timed, // @@ samples(5),
    test("can Gen some Bottles") {
      check(testBottleGen) { bottle =>
        println(s"a Bottle: ${bottle.toString()}")
        assertTrue(true)
      }
    } @@ samples(5) @@ timed,
    test("can Gen some BottleLayouts") {
      check(testBottleLayoutGen) { bottleLayout =>
        println(s"a BottleLayout: ${bottleLayout.toString()}")
        assertTrue(true)
      }
    } @@ ignore, // @@ samples(5) @@ timeout(Duration.fromSeconds(5)) @@ timed,
    test("can debug a custom Generator") {
      val program = for {
        _ <- Bottle.gen.runCollectN(2).debug
      } yield assertTrue(true)

      program
    } @@ timed,
    test("exampleTest") {
      val program = for {
        result <- ZIO.fail(new RuntimeException("NOT YET IMPLEMENTED"))
      } yield assertTrue(1 == 1)

      program
    } @@ timed @@ failing
  )
}
