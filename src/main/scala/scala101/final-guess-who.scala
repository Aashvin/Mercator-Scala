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
        CharacterString((Random.alphanumeric take 10).mkString),
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
        if(name.value.isEmpty) {
            return 2
        }

        val coords = this.findCharacterByName(name)

        if(coords == (-1, -1)) {
            return 2
        }

        if(!this.state(coords._1)(coords._2).inGame) {
            return 1
        }

        this.state(coords._1)(coords._2).inGame = false
        0
    }

    private def removeCharacterByTrait(character: Character, characterTraitType: String, guess: CharacterTraitDataType, equality: Boolean): Character = {
        val characterTrait: CharacterTraitDataType = characterTraitType match {
            case "hair" => character.hairColour
            case "eye" => character.eyeColour
            case "glasses" => character.hasGlasses
        }

        // Remove the correct character from the game based on whether the guess was correct or not
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

    def show(): Unit = {
        print("\n\n\n")
        print(this.state.map(_.map(e =>
            if(e.inGame) {
                e.hasGlasses.value
            } else {
                " " * e.name.value.length
            }).mkString("     ")).mkString("\n"))
        println()
    }
}

class Game(rows: Int, columns: Int) {
    private val hairColours: Array[String] = Array("Black", "Brunette", "Blonde", "Ginger", "White", "Blue", "Pink")
    private val eyeColours: Array[String] = Array("Black", "Brown", "Hazel", "Green", "Grey", "Blue")
    private val board: Board = new Board(rows, columns, hairColours, eyeColours)
    private val chosenCharacter: Character = board.state(Random.nextInt(board.rows))(Random.nextInt(board.columns))
    private var numGuesses: Int = 0

    private def getGuessType: Int = {
        var guessType: Int = 0
        while(guessType < 1 || guessType > 4) {
            println("1. Guess by name\n2. Guess by hair colour\n3. Guess by eye colour\n4. Guess by glasses presence")
            print("Please enter the number corresponding to the option: ")

            try {
                guessType = scala.io.StdIn.readLine().toInt
            } catch {
                case _: NumberFormatException => println("That was not a number!")
            }

        }
        guessType
    }

    private def guessByName(): Unit = {
        print("Please enter a name to guess: ")
        val guess = CharacterString(scala.io.StdIn.readLine())
        val removeCharacterResult = board.removeCharacterByName(guess)

        if (removeCharacterResult != 2) {
            if (removeCharacterResult == 1) {
                println("You've used another guess on a character that has already been guessed!")
            }
        } else {
            println("This character does not exist in this game!")
        }
    }

    private def guessByColourTrait(characteristic: String): Unit = {
        val coloursList: Array[String] = characteristic match {
            case "hair" => hairColours
            case "eye" => eyeColours
        }
        println(s"Please enter a $characteristic colour out of: " + coloursList.mkString(", "))
        val guess = CharacterString(scala.io.StdIn.readLine())

        val removeCharacterResult: Int = characteristic match {
            case "hair" => board.removeCharacterByHairColour(chosenCharacter, guess)
            case "eye" => board.removeCharacterByEyeColour(chosenCharacter, guess)
        }
        if (removeCharacterResult == 0) {
            println(s"You guessed the correct $characteristic colour!")
        } else if (removeCharacterResult == 1) {
            println(s"The character does not have that $characteristic colour.")
        }
    }

    private def guessByGlasses(): Unit = {
        print("1. Has glasses\n2. Does not have glasses\nPlease enter the number corresponding to your choice: ")
        val guess = scala.io.StdIn.readLine()
        val removeCharacterResult = board.removeCharacterByGlasses(chosenCharacter, CharacterBoolean(guess == "1"))
        if (removeCharacterResult == 0) {
            println("You guessed correctly - the character " + (if (guess == "1") "has" else "does not have") + " glasses")
        } else if (removeCharacterResult == 1)  {
            println("You guessed incorrectly - the character " + (if (guess == "1") "does not have" else "has") + " glasses")
        }
    }

    def play(): Unit = {
        var guessType: Int = 0

        println(chosenCharacter.hasGlasses.value)
        while(board.state.flatten.count(_.inGame) > 0 && chosenCharacter.inGame) {
            board.show()

            println("Number of guesses: " + numGuesses + "\n")

            guessType = this.getGuessType
            if(guessType == 1) {
                this.guessByName()
            } else if(guessType == 2) {
                this.guessByColourTrait("hair")
            } else if (guessType == 3) {
                this.guessByColourTrait("eye")
            } else if (guessType == 4) {
                this.guessByGlasses()
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
