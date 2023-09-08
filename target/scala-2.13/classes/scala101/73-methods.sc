/** Methods exercise from slide 73
 */

def nameLength(firstName: String, surname: String): Int = {
    if(firstName.length == surname.length) {
        0
    } else {
        firstName.length.max(surname.length)
    }
}

nameLength("aaaa", "bbb")