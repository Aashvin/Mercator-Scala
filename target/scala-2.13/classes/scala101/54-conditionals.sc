/** Group conditionals exercise from slide 54
 */

var grade: String = "F"
val score: Int = 89

if(score >= 90) {
    grade = "A"
} else if(score >= 80) {
    grade = "B"
} else if(score >= 70) {
    grade = "C"
} else if(score >= 60) {
    grade = "D"
} else if(score >= 50) {
    grade = "E"
}

grade

