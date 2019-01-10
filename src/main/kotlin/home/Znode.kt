package home

import org.apache.curator.framework.CuratorFramework
import org.apache.curator.framework.CuratorFrameworkFactory
import org.apache.curator.retry.ExponentialBackoffRetry
import org.slf4j.Logger
import org.slf4j.LoggerFactory

const val zooKeeperPath = "192.168.199.240:2181,192.168.199.240:2182,192.168.199.240:2183"

class Znode {

  private lateinit var curatorFramework: CuratorFramework

  val logger: Logger = LoggerFactory.getLogger(javaClass)

  fun start() {
    curatorFramework = CuratorFrameworkFactory.builder()
       .connectString(zooKeeperPath)
       .sessionTimeoutMs(4000)
       .retryPolicy(ExponentialBackoffRetry(1000, 3))
       .build()
    curatorFramework.start()
    logger.debug("Znode stated!")
  }

  fun close() {
    curatorFramework.close()
    logger.debug("Znode closed!")
  }
}