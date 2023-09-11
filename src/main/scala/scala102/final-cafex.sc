sealed trait ItemType
object Food extends ItemType
object Drink extends ItemType

sealed trait Temperature
object Hot extends Temperature
object Cold extends Temperature

trait MenuItem {
    val name: String
    val itemType: ItemType
    val temperature: Temperature
    val price: BigDecimal
    val isPremium: Boolean
}

class Customer (
    val loyalty: Boolean,
    val numStars: Int
)

def produceBill(items: List[MenuItem], customer: Customer): BigDecimal = {
    var totalBill: BigDecimal = 0

    val baseBill: BigDecimal = if (customer.loyalty && customer.numStars >= 3) {
        items.map(e => {
            if (!e.isPremium) {
                e.price -= e.price * (0.025 * customer.numStars.min(8))
            }
            e
        }).map(_.price).sum
    } else items.map(_.price).sum

    if (!items.map(_.itemType).contains(Food)) {
        totalBill = baseBill
    } else {
        if (items.exists(_.isPremium)) totalBill = baseBill + (baseBill * 0.25).min(40)
        items.map(_.temperature).contains(Hot) match {
            case false => totalBill = baseBill * 1.1
            case true => totalBill = baseBill + (baseBill * 0.2).min(20)
        }
    }

    totalBill.setScale(2, BigDecimal.RoundingMode.HALF_UP)
}

val food1 = new MenuItem {
    override val name: String = "Sandwich"
    override val itemType: ItemType = Food
    override val temperature: Temperature = Hot
    override val price: BigDecimal = 1.05
    override val isPremium: Boolean = false
}
val food2 = new MenuItem {
    override val name: String = "Yes"
    override val itemType: ItemType = Drink
    override val temperature: Temperature = Cold
    override val price: BigDecimal = 2.03
    override val isPremium: Boolean = false
}

produceBill(List(food1, food2))