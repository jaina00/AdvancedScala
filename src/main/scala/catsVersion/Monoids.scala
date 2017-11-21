package catsVersion

import cats.implicits._
import cats.kernel._

object Monoids extends App {
  val resStr = Monoid[String].combineAll(List("a", "b", "cc"))
  println(resStr)
  //abcc

  val sets = List(Set(10, 20), Set(30, 20))
  val mSets = Monoid[Set[Int]].combineAll(sets)
  println(mSets)
  //Set(10, 20, 30)

  val scores = List(Map("Joe" -> 12, "Kate" -> 5), Map("Joe" -> 10), Map("Joe" -> 10), Map("Mary" -> 10))
  val totals = Monoid[Map[String, Int]].combineAll(scores)
  println(totals)
  //Map(Joe -> 32, Kate -> 5, Mary -> 10)

}