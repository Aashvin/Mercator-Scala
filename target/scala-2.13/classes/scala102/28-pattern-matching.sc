def isACapital(city: String): String = {
    val capitals = List("London", "Edinburgh", "Cardiff", "Belfast")

    capitals.contains(city) match {
        case true => city.toUpperCase()
        case false => s"$city isn't a UK capital"
    }
}

abstract class Animal {
    val name: String
    val age: Int
}

case class Dog() extends Animal {
    override val name: String = "Dog"
    override val age: Int = 10
}

case class Cat() extends Animal {
    override val name: String = "Cat"
    override val age: Int = 4
}

def checkAnimal(animal: Animal): String = {
    animal match {
        case Dog() => "Dog"
        case Cat() => "Kitty"
        case _ => "Other"
    }
}

def checkSam(animal: Animal): String = {
    animal.name match {
        case "Sam" => s"Sam's age is ${animal.age}"
        case _ => s"${animal.name} is not Sam"
    }
}

def checkAge(animal: Animal): String = {
    animal.age > 10 match {
        case true => s"${animal.name} is ${animal.age} years old"
        case _ => s"${animal.name} is young"
    }
}