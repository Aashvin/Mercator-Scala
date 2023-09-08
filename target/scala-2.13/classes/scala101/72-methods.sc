/** Group methods exercise from slide 72
 */

def getBigVal(input1: Int, input2: Int): String = {
    if(input1 > input2) {
        "first"
    } else if(input1 < input2) {
        "second"
    } else {
        "same"
    }
}

getBigVal(2, 2)