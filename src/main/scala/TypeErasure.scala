import scala.reflect.ClassTag

object TypeErasure extends App {
  //Error:(5, 50) cannot find class tag for element type T
  //def createArray[T](length: Int, element :T ) = new Array[T](length)

  def createArray[T](length: Int, element :T )(implicit tag: ClassTag[T]) = new Array[T](length)
  createArray(5,10.0).foreach(println(_))

  def createArray2[T: ClassTag](length: Int, element :T ) = new Array[T](length)
  createArray2(5,1).foreach(println(_))

}
