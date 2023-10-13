val flavour: String = "Chocolate"

val benAndJerrysFlavour: String = flavour match {
    case "Caramel" => "Caramel Chew Chew"
    case "Chocolate" => "Chocolate Fudge Brownie"
    case "Cookie" => "Cookie Dough"
    case _ => flavour
}

print(benAndJerrysFlavour)


val pizzaMeasurement: Int = 7

val pizzaSize: String = pizzaMeasurement match {
    case 7 => "Personal"
    case 9 => "Small"
    case 11 => "Medium"
    case 14 => "Large"
    case _ => "Medium"
}

case class Pizza(size: String, crust: String)

val pizza = Pizza("Large", "Stuffed")

val pizzaCost: Double = pizza.size match {
    case "Personal" => 5.99
    case "Small" => 10.99
    case "Medium" =>
        12.99 + (pizza.crust match {
            case "Stuffed" => 2.99
            case _ => 0
        })
    case "Large" =>
        14.99 + (pizza.crust match {
            case "Stuffed" => 2.99
            case _ => 0
        })
}

println(pizzaCost)