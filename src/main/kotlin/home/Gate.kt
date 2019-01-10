package home

object Gate : GameServer(port = 12121) {

  override val role: GameRole = GameRole.gate


  fun start() {
    startZookeeper()
  }

  fun close() {
    closeZookeeper()
  }

  fun init() {
    logger.info("gate started!")
  }

}