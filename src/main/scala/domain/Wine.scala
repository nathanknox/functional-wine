package domain

import zio.Chunk
import domain.BottleLayout.Bottle

type WineInfo
type ShelfDescription = String

sealed trait BottleDetails
object BottleDetails {
  case object None extends BottleDetails
}
sealed trait BottleLayout
object BottleLayout {
  case object None                                                             extends BottleLayout
  final case class SideBySide(left: BottleLayout, right: BottleLayout)         extends BottleLayout
  final case class Stacked(top: BottleLayout, bottom: BottleLayout)            extends BottleLayout
  final case class FrontToBack(front: BottleLayout, back: BottleLayout)        extends BottleLayout
  final case class Shelf(contents: Chunk[BottleLayout], details: ShelfDetails) extends BottleLayout
  final case class Bottle(details: BottleDetails)                              extends BottleLayout
}

final case class ShelfDetails(capapcity: Int, description: Option[ShelfDescription] = None)

final case class WineLabel(info: WineInfo)
