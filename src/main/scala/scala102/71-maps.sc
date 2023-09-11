def twelveMultiply(number: Option[Int]): Int = {
//    Some(number).map(_.getOrElse(1) * 12).get
    number.getOrElse(1) * 12
}
print(twelveMultiply(Some(4)))

val scores = Seq(None, Some("A"), Some("B"), Some("C"), None, Some("F"))
scores.filter(_.isDefined)
scores.map(_.getOrElse("F"))
