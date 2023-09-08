case class Dog(name: String, breed: String, Age: Int)
case class Cat(name: String, breed: String, age: Int, spiciness: Int, hasHair: Boolean)
case class Bird(name: String, breed: String, age: Int, wingspan: Int, canFly: Boolean)

case class Kennel(name: String, dogs: List[Dog], cats: List[Cat], birds: List[Bird])

val dog1 = Dog("Fido", "Golden Retriever", 4)
val dog2 = Dog("Rover", "Poodle", 2)
val dog3 = Dog("Dog", "Samoyed", 3)
val dog4 = Dog("Jim", "Chihuahua", 9)

val cat1 = Cat("Mo", "Sphinx", 3, 10, hasHair = false)
val bird1 = Bird("Bob", "Parrot", 2, 20, canFly = true)

val kennel = Kennel("Aashvin's Kennel", List(dog1, dog2, dog3, dog4), List(cat1), List(bird1))

val newKennel = kennel.copy(name = "Aashvin's New and Improved Kennel")