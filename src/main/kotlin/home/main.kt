package home

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


fun main(args: Array<String>) {
//  Gate.init()
//  Gate.start()
//  Gate.close()


  GlobalScope.launch {
    doSomething()
  }
  println("Hello,") // main thread continues while coroutine is delayed
  Thread.sleep(2000L) // block main thread for 2 seconds to keep JVM alive

}

suspend fun doSomething() {
  delay(1000L)
  println("World!")
}
