println(raw"\n\n\n\n\n\n\n\nsameline\n\n")

case class Person(name: String, age: Double, favRegex: String)

val person1 = Person("Gary", 50.12345678, "^[\\]{5, 10}$")
println(s"Name: ${person1.name}\n" + f"Age: ${person1.age}%.2f\n" + raw"Favourite regex: ${person1.favRegex}")