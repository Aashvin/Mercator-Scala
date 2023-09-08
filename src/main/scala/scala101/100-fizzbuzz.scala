package scala101

class FizzBuzz() {
    def fizzBuzz(num: Int): String = {
        if (num % 3 == 0 && num % 5 == 0) {
            "fizzbuzz"
        } else if (num % 3 == 0) {
            "fizz"
        } else if (num % 5 == 0) {
            "buzz"
        } else {
            num.toString
        }
    }
}