import scala.util.Random

def getCharacterQuote(character: Option[String]): String = {
    character match {
        case "Spongebob" => "Spongebob quote"
        case "Patrick" => "Patrick quote"
        case None => "Default quote"
    }
}

def getPhilosophyQuote(quote: Option[String]): String = {
    val randomQuotes: List[String] = List(
        "The unexamined life is not worth living",
        "Whereof one cannot speak, thereof one must be silent",
        "Entities should not be multiplied unnecessarily",
        "The life of man (in a state of nature) is solitary, poor, nasty, brutish, and short"
    )

    quote match {
        case None => randomQuotes(Random.nextInt(randomQuotes.length))
        case Some(value) => value
    }
}

def quoter(quoteFunction: Option[String] => String, quoteFunctionInput: Option[String]): Unit = {
    quoteFunction(quoteFunctionInput)
}