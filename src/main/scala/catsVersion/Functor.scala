package catsVersion

import cats.Functor
import cats.implicits.{catsStdInstancesForFuture, catsStdInstancesForOption}
import cats.implicits.catsStdInstancesForList
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}

trait Functor[F[_]] {
  def map[A, B](fa: F[A])(f: A => B): F[B]
}

object FunctorExample extends App {

  //Normal map method
  val maybeName = Option("Joe")
  println(Functor[Option].map(maybeName)(_.length))



  //Lift - transform a function A => B into a function F[A] => F[B]
  def greet(name: String): String = s"Hello $name!"
  println(greet("abhishek"))

  val name = Some("abhishek") //val name = None
  val lifted = Functor[Option].lift(greet)
  println(lifted(name))

  val liftedFuture = Functor[Future].lift(greet)
  val futureName = Future.successful("tony")
  println(Await.result(liftedFuture(futureName), Duration.Inf))



  //fproduct - combines F[A] with results of applying a function A => B by producing a pair F[(A, B)]
  val users = List("Joe", "Kate")
  println(Functor[List].fproduct(users)(greet).toMap)


  //compose - given 2 functors F[_] and G[_] we can create a new functor F[G[_]]
  val listOptionFunctor = Functor[List].compose(Functor[Option])
  val optUsers = List(Some("Joe"), None, Some("Kate"))
  println(listOptionFunctor.map(optUsers)(greet))

  println(optUsers.map(x => x.map(greet)))
}







