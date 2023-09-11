def areaOfCircle(radius: Double): Double = {
    3.14 * scala.math.pow(radius, 2)
}

def circumference(radius: Double): Double = {
    2 * 3.14 * radius
}

def circleOperation(radii: List[Double], operation: Double => Double): List[Double] = {
    radii.map(operation(_))
}

circleOperation(List(1, 2, 3, 4), areaOfCircle)