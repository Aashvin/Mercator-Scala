object EitherStyle {
    def parse(s: String): Either[NumberFormatException, Int] =
        if (s.matches("-?[0-9]+")) Right(s.toInt)
        else Left(new NumberFormatException(s"$s is not a valid integer"))
}

// Guess: 23
EitherStyle.parse("23").isRight

// Guess: NumberFormatException - twenty is not a valid integer
EitherStyle.parse("twenty").isLeft

// Guess: 
EitherStyle.parse(100).isLeft
EitherStyle.parse(100.toString).isLeft
EitherStyle.parse("21.5").isRight
