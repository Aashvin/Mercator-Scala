case class Dog(name: String, age: Int, spotColour: Option[String]) {
    def getSpotColourPatternMatching(): String = {
        spotColour match {
            case None => "No spots"
            case _ => spotColour.get
        }
    }

    def getSpotColourOptional(): String = {
        spotColour.getOrElse("No spots")
    }
}

def multiplyByTwo(num: Option[Int]): Option[Int] = {
    num match {
        case None => None
        case _ => Some(num.get * 2)
    }
}