val everybody = Seq("elem1", "elem2", "elem3")

val colourMap = Map(1 -> "red", 2 -> "yellow", 3 -> "blue", 4 -> "refrigerator")
println(colourMap(3))

def addOneToSequence(seq: Seq[Int]): Seq[Int] = {
    seq.map(_ + 1)
}
var addSequence = Seq(1, 2, 4, 6, 9)
println(addSequence)
addSequence = addOneToSequence(addSequence)
println(addSequence)

def removeEvenNumbers(seq: Seq[Int]): Seq[Int] = {
    seq.filter(_ % 2 == 1)
}
var filteredSequence = removeEvenNumbers(addSequence)
println(filteredSequence)

def hasT(seq: Seq[String]): Boolean = {
    seq.exists(_.contains("t"))     // Case sensitive
}
println(hasT(everybody))
var seqWithT = Seq("this", "sequence", "has", "one")
println(hasT(seqWithT))