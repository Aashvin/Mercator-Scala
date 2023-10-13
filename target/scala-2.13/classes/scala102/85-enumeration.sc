object NintendoCharacters extends Enumeration {
    val Mario = Value("mario")
    val Link = Value("link")
    val Kirby = Value("kirby")
}

println(NintendoCharacters.values.mkString(", "))

sealed abstract class NintendoCharacterTrait(val next: Option[NintendoCharacterTrait])
case object Mario extends NintendoCharacterTrait(Some(Link))
case object Link extends NintendoCharacterTrait(Some(Kirby))
case object Kirby extends NintendoCharacterTrait(None)

var first: Option[NintendoCharacterTrait] = Some(Mario)
var allChars: List[NintendoCharacterTrait] = List()
while(first.isDefined) {
    println(first)
    first = first.get.next
}
allChars

//def printNintendo(character: Option[NintendoCharacterTrait]): List[NintendoCharacterTrait] = {
////    character match {
////        case Some(x) => List(x) + printNintendo(x.next)
////        case None =>
////    }
//    if(character.get.next.isDefined) {
//        List(character) :+ printNintendo(character.get.next)
//    }
//
//}