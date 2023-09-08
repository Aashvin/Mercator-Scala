/** Conditionals exercise from slide 55
 */

val allRatings: List[String] = List("U", "PG", "12A", "15", "18")

val age: Int = 4

if(age < 4) {
    "Can't watch any films yet!"
} else if(age < 8) {
    allRatings.slice(0, 1)
} else if(age < 12) {
    allRatings.slice(0, 2)
} else if(age < 15) {
    allRatings.slice(0, 3)
} else if(age < 18) {
    allRatings.slice(0, 4)
} else {
    allRatings
}