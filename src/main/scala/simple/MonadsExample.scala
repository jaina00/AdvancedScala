package simple

trait Monad[M[_]] {
  def pure[A](x: A): M[A]
  def map[A, B](m: M[A])(f: A => B): M[B]
  def flatMap[A, B](m: M[A])(f: A => M[B]): M[B]
}

sealed trait MayBe[A] {
  def map[B](f: A => B): MayBe[B]

  def flatMap[B](f: A => MayBe[B]): MayBe[B]
}

case class SomeValue[A](a: A) extends MayBe[A] {
  override def map[B](f: A => B) = new SomeValue[B](f(a))

  override def flatMap[B](f: A => MayBe[B]) = f(a)
}

case class NoValue[A]() extends MayBe[A] {
  override def map[B](f: A => B) = new NoValue()

  override def flatMap[B](f: A => MayBe[B]) = NoValue()
}


object MonadsExample extends App {

  def doSome(foo: MayBe[Int], bar: MayBe[Int]) = for {
    x <- foo
    y <- bar
  } yield (x + y)


  println(doSome(SomeValue(10), SomeValue(20)))
  println(doSome(SomeValue(10), NoValue()))

}