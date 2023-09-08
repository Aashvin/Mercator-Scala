package scala101

import scala.util.Random

// Custom classes help with reusable functions
abstract class CharacterTraitDataType(val value: Any)
final case class CharacterString(override val value: String) extends CharacterTraitDataType
final case class CharacterBoolean(override val value: Boolean) extends CharacterTraitDataType

class Character(
               val name: CharacterString,
               val hairColour: CharacterString,
               val hasGlasses: CharacterBoolean,
               val eyeColour: CharacterString,
               ) {
    var inGame: Boolean = true
}

class Board(val rows: Int, val columns: Int, hairColours: Array[String], eyeColours: Array[String]) {
    var state: Array[Array[Character]] = Array.ofDim[Character](rows, columns)      // The state of the board

    // Populate the board with random characters
    this.state = this.state.map(_.map(_ => new Character(
        CharacterString((Random.alphanumeric take 10).mkString.toLowerCase().capitalize),
        CharacterString(hairColours(Random.nextInt(hairColours.length))),
        CharacterBoolean(Random.nextBoolean()),
        CharacterString(eyeColours(Random.nextInt(eyeColours.length)))
    )))

    // Get board position of a character by name
    private def findCharacterByName(name: CharacterString): (Int, Int) = {
        for {
            i <- this.state.indices
            j <- this.state(0).indices
            if this.state(i)(j).name.value == name.value
        } return (i, j)

        (-1, -1)
    }

    def removeCharacterByName(name: CharacterString): Int = {
        val coordinates = this.findCharacterByName(name)

        if(coordinates == (-1, -1)) return 2

        if(!this.state(coordinates._1)(coordinates._2).inGame) return 1

        this.state(coordinates._1)(coordinates._2).inGame = false
        0
    }

    private def removeCharacterByTrait(character: Character, characterTraitType: String, guess: CharacterTraitDataType, equality: Boolean): Character = {
        val characterTrait: CharacterTraitDataType = characterTraitType match {
            case "hair" => character.hairColour
            case "eye" => character.eyeColour
            case "glasses" => character.hasGlasses
        }

        // Remove the character from the game based on whether the guess was correct or not
        val comparison = (characterTrait: CharacterTraitDataType, guess: CharacterTraitDataType) =>
            if (equality) {characterTrait.value == guess.value} else {characterTrait.value != guess.value}

        if (comparison(characterTrait, guess) && character.inGame) {character.inGame = !character.inGame}

        character
    }

    def removeCharacterByHairColour(chosenCharacter: Character, hairColour: CharacterString): Int = {
        this.state = this.state.map(_.map(e =>
            removeCharacterByTrait(e, "hair", hairColour, equality = !(hairColour.value == chosenCharacter.hairColour.value))
        ))

        if (hairColour.value == chosenCharacter.hairColour.value) 0 else 1
    }

    def removeCharacterByEyeColour(chosenCharacter: Character, eyeColour: CharacterString): Int = {
        this.state = this.state.map(_.map(e =>
            removeCharacterByTrait(e, "eye", eyeColour, equality = !(eyeColour.value == chosenCharacter.eyeColour.value))
        ))

        if (eyeColour.value == chosenCharacter.eyeColour.value) 0 else 1
    }

    def removeCharacterByGlasses(chosenCharacter: Character, glasses: CharacterBoolean): Int = {
        this.state = this.state.map(_.map(e =>
            removeCharacterByTrait(e, "glasses", glasses, equality = !(glasses.value == chosenCharacter.hasGlasses.value))
        ))

        if (glasses.value == chosenCharacter.hasGlasses.value) 0 else 1
    }

    private def getCharacteristic(character: Character, characteristic: String): String = {
        val value = characteristic match {
            case "name" => character.name.value
            case "hair" => character.hairColour.value
            case "eye" => character.eyeColour.value
            case "glasses" => character.hasGlasses.value
        }
        value.toString
    }

    // Print the board state
    def show(characteristic: String): Unit = {
        print("\n\n\n")
        print(this.state.map(_.map(e =>
            if(e.inGame) {
                getCharacteristic(e, characteristic) + " " * (10 - getCharacteristic(e, characteristic).length)
            } else {
                Console.RED + getCharacteristic(e, characteristic) + " " * (10 - getCharacteristic(e, characteristic).length) + Console.RESET
            }).mkString("     ")).mkString("\n"))
        println()
    }
}

class Game(rows: Int, columns: Int) {
    // Available pool of colours to choose from for each relevant character trait
    private val hairColours: Array[String] = Array("Black", "Brunette", "Blonde", "Ginger", "White", "Blue", "Pink")
    private val eyeColours: Array[String] = Array("Black", "Brown", "Hazel", "Green", "Grey", "Blue")

    // Create new board and choose a random character
    private val board: Board = new Board(rows, columns, hairColours, eyeColours)
    private val chosenCharacter: Character = board.state(Random.nextInt(board.rows))(Random.nextInt(board.columns))
    private var numGuesses: Int = 0

    private def getGuessType: Int = {
        var guessType: Int = 0
        while(guessType < 1 || guessType > 4) {
            println("1. Guess by name\n2. Guess by hair colour\n3. Guess by eye colour\n4. Guess by glasses presence")
            print("Please enter the number corresponding to the option: ")

            try guessType = scala.io.StdIn.readLine().toInt
            catch {
                case _: NumberFormatException => println("That was not a number!")
            }
        }
        guessType
    }

    private def guessByName(): Unit = {
        board.show("name")
        print("Please enter a name to guess: ")
        val guess = CharacterString(scala.io.StdIn.readLine().toLowerCase().capitalize)
        val removeCharacterResult = board.removeCharacterByName(guess)

        println(removeCharacterResult match {
            case 0 => if (guess.value == chosenCharacter.name.value) "You got the right character!" else "You got the wrong character!"
            case 1 => s"${guess.value} has already been guessed!"
            case 2 => s"${guess.value} doesn't exist in the game!"
        })
    }

    private def guessByColourTrait(characteristic: String): Unit = {
        board.show(characteristic)

        // Get the correct characteristic
        print(s"Please enter a $characteristic colour: ")
        val guess = CharacterString(scala.io.StdIn.readLine().toLowerCase().capitalize)

        val removeCharacterResult: Int = characteristic match {
            case "hair" => board.removeCharacterByHairColour(chosenCharacter, guess)
            case "eye" => board.removeCharacterByEyeColour(chosenCharacter, guess)
        }

        println(removeCharacterResult match {
            case 0 => s"${guess.value.toLowerCase().capitalize} is the correct $characteristic colour!"
            case 1 => s"The character does not have the ${guess.value.toLowerCase()} $characteristic colour."
        })
    }

    private def guessByGlasses(): Unit = {
        board.show("glasses")

        print("1. Has glasses\n2. Does not have glasses\nPlease enter the number corresponding to your choice: ")
        val guess = scala.io.StdIn.readLine()
        val removeCharacterResult = board.removeCharacterByGlasses(chosenCharacter, CharacterBoolean(guess == "1"))

        println(removeCharacterResult match {
            case 0 => "You guessed correctly - the character " + (if (guess == "1") "has" else "does not have") + " glasses"
            case 1 => "You guessed incorrectly - the character " + (if (guess == "1") "does not have" else "has") + " glasses"
        })
    }

    def play(): Unit = {
        println("You're playing Guess Who! Make guesses based on criteria to figure out the chosen character.")
        println("Characters in red have been confirmed to no longer be in the game, although you can still guess them if you so wish.")
        println("Guess the name of the chosen character to win.")
//        println(chosenCharacter.name.value)

        while(board.state.flatten.count(_.inGame) > 0 && chosenCharacter.inGame) {
            board.show("name")
            println("Number of guesses: " + numGuesses + "\n")

            this.getGuessType match {
                case 1 => this.guessByName()
                case 2 => this.guessByColourTrait("hair")
                case 3 => this.guessByColourTrait("eye")
                case 4 => this.guessByGlasses()
            }
            numGuesses += 1
        }
        println("You managed to guess " + chosenCharacter.name.value +" in " + numGuesses + " guesses!")
    }
}

object Main {
    def main(args: Array[String]): Unit = {
        val game = new Game(2, 2)
        game.play()
    }
}
