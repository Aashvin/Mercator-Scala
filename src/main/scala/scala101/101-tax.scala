package scala101

class Tax() {
    def calculateTax(income: Int): Int = {
        if(income <= 10000) {
            10
        } else if(income <= 50000) {
            15
        } else if(income <= 100000) {
            20
        } else {
            40
        }
    }
}