import org.scalatest._
import scala101.TwoNumbers


class TestTwoNumbers extends flatspec.AnyFlatSpec {
    "add" should "add numbers" in {
        val nums = new TwoNumbers(1, 2)
        assert(nums.add === 3)
    }
}