/** Inheritance exercise from slide 37
 */

class Artillery(val numGuns: Int, val range: Float)

class RenownedDesigner(val name: String, val location: String)

abstract class Boat (
    val length: Int,
    val width: Int,
    val topSpeed: Int,
)

trait SailBoat {
    val numSails: Int
    val hasOars: Boolean
    val canTrack: Boolean
}

trait MotorBoat {
    val engineSize: Float
    val fuelType: String
}

class LuxurySailBoat(val hasJacuzzi: Boolean, val hasBooze: Boolean)
    extends Boat(length = 5, width = 2, topSpeed = 3) with SailBoat {

    override val numSails: Int = 2
    override val hasOars: Boolean = false
    override val canTrack: Boolean = true
}

class PirateShip(val numGangPlanks: Int)
    extends Boat(length = 7, width = 3, topSpeed = 5) with SailBoat {

    override val numSails: Int = 3
    override val hasOars: Boolean = true
    override val canTrack: Boolean = false
}

class WarShip(val country: String)
    extends Boat(length = 10, width = 5, topSpeed = 4) with MotorBoat {

    override val engineSize: Float = 2.5f   // Use the f for float otherwise will default to Double
    override val fuelType: String = "Diesel"
}

class PacerBoat(val sponsor: String, val quarterMileTime: Double)
    extends Boat(length = 10, width = 5, topSpeed = 4) with MotorBoat {

    override val engineSize: Float = 1.5f   // Use the f for float otherwise will default to Double
    override val fuelType: String = "Diesel"
}