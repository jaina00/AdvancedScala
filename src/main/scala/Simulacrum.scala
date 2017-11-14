import simulacrum._

@typeclass trait InfoPrinter[T] {
  def toInfo(value: T): String
}

case class User(name: String, age: Int)

object User {
  implicit val userPrinter = new InfoPrinter[User] {
    override def toInfo(value: User): String =
      s"[User] (${value.name}, ${value.age})"
  }
}


object Simulacrum extends App {
  import InfoPrinter.ops._

  val user = User("Joe", 42)
  println(user.toInfo)

}
