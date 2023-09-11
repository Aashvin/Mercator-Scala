def bestComputer(computerType: String): String = {
    val uppercaseComputerType = computerType.map(_.toUpper)
    s"$uppercaseComputerType ARE THE BEST"
}
bestComputer("macbooks")

def doubleSum(numberList: List[String]): Int = {
    numberList.map(_.toInt * 2).sum
}
doubleSum(List("1", "2", "3"))