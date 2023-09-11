final case class UserNotFoundError(private val message: String = "", private val cause: Throwable = None.orNull) extends Exception(message, cause)

def userExists(username: String, password: String): Boolean = {
    (username, password) match {
        case ("Boris Johnson", "pword123") => true
        case ("Barack Obama", "merica") => true
        case _ => false
    }
}

def checkLogin(username: String, password: String): Either[UserNotFoundError, Boolean] = {
    if (userExists(username, password)) Right(true) else Left(UserNotFoundError("This user does not exist, or password is incorrect."))
}