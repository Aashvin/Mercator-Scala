case class ChocolateBar (name: String, filling: Option[String]) {
    def getFilling(): String = {
        filling.getOrElse("No filling")
    }
}