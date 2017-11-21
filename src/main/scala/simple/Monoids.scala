package simple

import scala.annotation.implicitNotFound

@implicitNotFound(msg = "Cannot find Moniod type class for ${A}")
trait Monoids[A] {
  def compose(a: A, b: A): A
  def empty: A
}

object Monoids {
  implicit val stringMoniod = new Monoids[String] {
    override def compose(a: String, b: String): String = a + b
    override def empty: String = ""
  }

  implicit val intMoniod = new Monoids[Int] {
    override def compose(a: Int, b: Int): Int = a * b
    override def empty: Int = 1
  }

  implicit val longMoniod = new Monoids[Long] {
    override def compose(a: Long, b: Long): Long = a + b
    override def empty: Long = 0L
  }

  def compose[A: Monoids](a: A, b: A): A = implicitly[Monoids[A]].compose(a, b)
  def empty[A: Monoids]: A = implicitly[Monoids[A]].empty

  def combineAll[A](lst: List[A])(implicit m: Monoids[A]) =
    lst.foldLeft(m.empty)((a, b) => m.compose(a, b))
}

object MoniodExampple extends App {
  println(Monoids.compose("ab", "cd"))
  println(Monoids.compose(1, 2))
  println(Monoids.combineAll(List(1,2,3,4,5)))
  println(Monoids.combineAll(List(1L,2L,3L,4L,5L)))
}
