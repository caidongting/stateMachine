package home

import org.slf4j.Logger
import org.slf4j.LoggerFactory

enum class GameRole {
  gate,
  world,
  data,
}


abstract class GameServer(val port: Int) {

  abstract val role: GameRole

  val logger: Logger = LoggerFactory.getLogger(javaClass)

  /** actorFactory*/

  /** uid*/

  /** hibernate*/

  /** netty actor session*/
  private val netty: NettyTcpServer = NettyTcpServer(port = 8081)

  /** zookeeper*/
  private val znode: Znode = Znode()

  fun startNetwork() {
    netty.run()
  }

  fun closeNetwork() {

  }

  fun startZookeeper() {
    znode.start()
    startNetwork()
  }

  fun closeZookeeper() {
    znode.close()
    closeNetwork()
  }

}

