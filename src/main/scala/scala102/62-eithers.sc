object EitherStyle {
    def parse(s: String): Either[NumberFormatException, Int] =
        if (s.matches("-?[0-9]+")) Right(s.toInt)
        else Left(new NumberFormatException(s"$s is not a valid integer"))
}

// Guess: 23 - true
EitherStyle.parse("23").isRight

// Guess: NumberFormatException: twenty is not a valid integer - true
EitherStyle.parse("twenty").isLeft

// Guess: type mismatch with function definition
EitherStyle.parse(100).isLeft

// Guess: 100 - false
EitherStyle.parse(100.toString).isLeft

// Guess NumberFormatException: 21.5 is not a valid integer - false
EitherStyle.parse("21.5").isRight
