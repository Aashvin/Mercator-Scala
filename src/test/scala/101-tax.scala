import org.scalatest._
import scala101.Tax

class TestTax extends flatspec.AnyFlatSpec {
    val tax = new Tax()       // Construct new Tax object for use in unit tests

    "income less than or equal to 10000" should "be taxed at 10%" in {
        assert(tax.calculateTax(income = 10000) === 10)
    }

    "income more than 10000 and less than or equal to 50000" should "be taxed at 15%" in {
        assert(tax.calculateTax(income = 50000) === 15)
    }

    "income more than 50000 and less than or equal to 100000" should "be taxed at 20%" in {
        assert(tax.calculateTax(income = 100000) === 20)
    }

    "income more than 100000" should "be taxed at 40%" in {
        assert(tax.calculateTax(income = 100001) === 40)
    }
}