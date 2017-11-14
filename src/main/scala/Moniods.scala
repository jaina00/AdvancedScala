import annotation.implicitNotFound

@implicitNotFound(msg = "Cannot find Moniod type class for ${A}")
trait Moniods[A] {
  def compose(a: A, b: A): A
  def empty: A
}

object Moniods {
  implicit val stringMoniod = new Moniods[String] {
    override def compose(a: String, b: String): String = a + b
    override def empty: String = ""
  }

  implicit val intMoniod = new Moniods[Int] {
    override def compose(a: Int, b: Int): Int = a * b
    override def empty: Int = 1
  }

  implicit val longMoniod = new Moniods[Long] {
    override def compose(a: Long, b: Long): Long = a + b
    override def empty: Long = 0L
  }

  def compose[A: Moniods](a: A, b: A): A = implicitly[Moniods[A]].compose(a, b)
  def empty[A: Moniods]: A = implicitly[Moniods[A]].empty

  def combineAll[A](lst: List[A])(implicit m: Moniods[A]) =
    lst.foldLeft(m.empty)((a, b) => m.compose(a, b))
}

object MoniodExampple extends App {
  println(Moniods.compose("ab", "cd"))
  println(Moniods.compose(1, 2))
  println(Moniods.combineAll(List(1,2,3,4,5)))
  println(Moniods.combineAll(List(1L,2L,3L,4L,5L)))
}
