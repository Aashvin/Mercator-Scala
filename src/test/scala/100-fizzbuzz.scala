import org.scalatest._
import scala101.FizzBuzz

class TestFizzBuzz extends flatspec.AnyFlatSpec {
    val fizzBuzz = new FizzBuzz()       // Construct new FizzBuzz object for use in unit tests

    "exclusive multiples of 3" should "output fizz" in {
        assert(fizzBuzz.fizzBuzz(num = 6) === "fizz")
    }

    "exclusive multiples of 5" should "output buzz" in {
        assert(fizzBuzz.fizzBuzz(num = 10) === "buzz")
    }

    "multiples of both 3 and 5" should "output fizzbuzz" in {
        assert(fizzBuzz.fizzBuzz(num = 15) === "fizzbuzz")
    }

    "numbers that are not multiples of 3 or 5" should "output a string of that number" in {
        assert(fizzBuzz.fizzBuzz(num = 7) === "7")
    }
}