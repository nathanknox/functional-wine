package domain

import zio.Chunk
import domain.BottleLayout.Bottle
import zio.schema.Schema
import zio.schema.DeriveSchema
import zio.schema.DeriveGen
import zio.test.{Gen, Sized}

sealed trait WineInfo
object WineInfo {
  case object WineInfoPlaceholder extends WineInfo
}
sealed trait ShelfDescription
object ShelfDescription {
  case object ShelfDescriptionPlaceholder extends ShelfDescription
}

sealed trait BottleLayout
object BottleLayout {
  implicit val schema: Schema[BottleLayout] = DeriveSchema.gen
  val gen: Gen[Sized, BottleLayout]         = DeriveGen.gen

  case object None                                                             extends BottleLayout
  final case class SideBySide(left: BottleLayout, right: BottleLayout)         extends BottleLayout
  final case class Stacked(top: BottleLayout, bottom: BottleLayout)            extends BottleLayout
  final case class FrontToBack(front: BottleLayout, back: BottleLayout)        extends BottleLayout
  final case class Shelf(contents: Chunk[BottleLayout], details: ShelfDetails) extends BottleLayout
  final case class Bottle(details: BottleDetails)                              extends BottleLayout
  object Bottle {
    implicit val schema: Schema[Bottle] = DeriveSchema.gen
    val gen                             = DeriveGen.gen
  }
}

sealed trait BottleDetails
object BottleDetails {
  implicit val schema: Schema[BottleDetails] = DeriveSchema.gen
  val gen                                    = DeriveGen.gen
  case object BottlePlaceholder extends BottleDetails
}

final case class ShelfDetails(capapcity: Int, description: Option[ShelfDescription] = None)
object ShelfDetails {
  implicit val schema: Schema[ShelfDetails] = DeriveSchema.gen
}

final case class WineLabel(info: WineInfo)
object WineLabel {
  implicit val schema: Schema[WineLabel] = DeriveSchema.gen
}
