package home


fun main() {
  Gate.init()
  Gate.start()
//  Gate.close()

  println("Hello,") // main thread continues while coroutine is delayed
  Thread.sleep(2000L) // block main thread for 2 seconds to keep JVM alive

}