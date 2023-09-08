case class Dog(name: String, breed: String, Age: Int)

case class Kennel(name: String, dogs: List[Dog])

val dog1 = Dog("Fido", "Golden Retriever", 4)
val dog2 = Dog("Rover", "Poodle", 2)
val dog3 = Dog("Dog", "Samoyed", 3)
val dog4 = Dog("Jim", "Chihuahua", 9)

val kennel = Kennel("Aashvin's Kennel", List(dog1, dog2, dog3, dog4))

