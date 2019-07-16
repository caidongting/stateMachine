import org.junit.Test

class AddTest {

  @Test
  fun exec() {
    val x = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 15)
    var result = bitSum(x.sum())
    do {
      val _result = result
      result = bitSum(result)
    } while (_result != result)
    println("result = $result")
  }

}


fun bitSum(x: Int): Int {
  var y = x
  var sum = 0
  while (y != 0) {
    sum += y % 10
    y /= 10
  }
  return sum
}