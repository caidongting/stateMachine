package home

import java.util.*

enum class Card(val index: Int) {

  // 万
  m_1(1),
  m_2(2),
  m_3(3),
  m_4(4),
  m_5(5), m_0(5),
  m_6(6),
  m_7(7),
  m_8(8),
  m_9(9),

  // 饼\筒
  p_1(11),
  p_2(12),
  p_3(13),
  p_4(14),
  p_5(15), p_0(15),
  p_6(16),
  p_7(17),
  p_8(18),
  p_9(19),

  // 索\刻\条
  s_1(21),
  s_2(22),
  s_3(23),
  s_4(24),
  s_5(25), s_0(25),
  s_6(26),
  s_7(27),
  s_8(28),
  s_9(29),

  // 风牌 东 南 西 北
  z_1(31),
  z_2(32),
  z_3(33),
  z_4(34),

  // 三元牌 白 发 中
  z_5(45),
  z_6(46),
  z_7(47),
  ;

}

val meta_s: ArrayList<Card> = arrayListOf(Card.m_0, Card.p_0, Card.s_0)
val doraMap = mapOf(
    Card.m_5 to Card.m_0,
    Card.p_5 to Card.p_0,
    Card.s_5 to Card.s_0
)


//
data class S_abc(val a: Card, val b: Card, val c: Card) {
  val mem get() = listOf(a, b, c)
}

data class S_aaa(val a: Card) {
  val mem get() = listOf(a, a, a)
}

data class S_cc(val c: Card) {
  val mem get() = listOf(c, c)
}

// 牌实体
class Entity {

  // 暗牌
  var x: List<Card> = listOf()
    private set


  // 副露
  var abc = listOf<S_abc>()
    private set

  var aaa = listOf<S_aaa>()
    private set

  val y: List<Card> get() = abc.flatMap { it.mem } + aaa.flatMap { it.mem }


  // 来牌
  var z: Card? = null
    private set


  val shouyi get() = x + y

  fun seq(): List<Card> {
    return z?.let { shouyi + it } ?: shouyi
  }

  fun sort() {
    x.sortedBy { it.index }
  }

  fun analysis(): List<AnalysisEntity> {
    TODO()
  }

}

// 分析后得到的手牌
class AnalysisEntity(
    val abc: List<S_abc>,
    val aaa: List<S_aaa>,
    val _abc: List<S_abc>,
    val _aaa: List<S_aaa>,
    val cc: List<S_cc>
) {

  val shouyi: List<Card>
    get() = (abc + _abc).flatMap(S_abc::mem) +
        (aaa + _aaa).flatMap(S_aaa::mem) +
        cc.flatMap(S_cc::mem)

}


// 字牌
val 字牌 = arrayOf(Card.z_1, Card.z_2, Card.z_3, Card.z_4, Card.z_5, Card.z_6, Card.z_7)

// 幺九牌
val 幺九牌 = 字牌 + listOf(Card.m_1, Card.m_9, Card.p_1, Card.p_9, Card.s_1, Card.s_9)

abstract class 役 {
  abstract fun check(entity: Entity): Boolean
}

object 段幺九 : 役() {

  override fun check(entity: Entity): Boolean {
    return entity.seq().none { it in 幺九牌 }
  }

}

object 字一色 : 役() {

  override fun check(entity: Entity): Boolean {
    return entity.seq().all { it in 字牌 }
  }
}

object ad : 役() {
  override fun check(entity: Entity): Boolean {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }
}


// 牌堆
class CardStack {

  val stack = listOf<Card>(
      Card.p_1, Card.p_2, Card.p_3, Card.p_4, Card.p_6, Card.p_7, Card.p_8, Card.p_9,
      Card.p_1, Card.p_2, Card.p_3, Card.p_4, Card.p_6, Card.p_7, Card.p_8, Card.p_9,
      Card.p_1, Card.p_2, Card.p_3, Card.p_4, Card.p_6, Card.p_7, Card.p_8, Card.p_9,
      Card.p_1, Card.p_2, Card.p_3, Card.p_4, Card.p_6, Card.p_7, Card.p_8, Card.p_9,

      Card.m_1, Card.m_2, Card.m_3, Card.m_4, Card.m_6, Card.m_7, Card.m_8, Card.m_9,
      Card.m_1, Card.m_2, Card.m_3, Card.m_4, Card.m_6, Card.m_7, Card.m_8, Card.m_9,
      Card.m_1, Card.m_2, Card.m_3, Card.m_4, Card.m_6, Card.m_7, Card.m_8, Card.m_9,
      Card.m_1, Card.m_2, Card.m_3, Card.m_4, Card.m_6, Card.m_7, Card.m_8, Card.m_9,

      Card.s_1, Card.s_2, Card.s_3, Card.s_4, Card.s_6, Card.s_7, Card.s_8, Card.s_9,
      Card.s_1, Card.s_2, Card.s_3, Card.s_4, Card.s_6, Card.s_7, Card.s_8, Card.s_9,
      Card.s_1, Card.s_2, Card.s_3, Card.s_4, Card.s_6, Card.s_7, Card.s_8, Card.s_9,
      Card.s_1, Card.s_2, Card.s_3, Card.s_4, Card.s_6, Card.s_7, Card.s_8, Card.s_9,

      Card.z_1, Card.z_2, Card.z_3, Card.z_4, Card.z_5, Card.z_6, Card.z_7,
      Card.z_1, Card.z_2, Card.z_3, Card.z_4, Card.z_5, Card.z_6, Card.z_7,
      Card.z_1, Card.z_2, Card.z_3, Card.z_4, Card.z_5, Card.z_6, Card.z_7,
      Card.z_1, Card.z_2, Card.z_3, Card.z_4, Card.z_5, Card.z_6, Card.z_7,

      Card.p_5, Card.p_5, Card.p_5, Card.p_0,
      Card.m_5, Card.m_5, Card.m_5, Card.m_0,
      Card.s_5, Card.s_5, Card.s_5, Card.s_0

  )
}

// 用户
class User {

}

enum class Position {
  东,
  南,
  西,
  北
}

// 整个对局状态
enum class State {
  // 初始化
  INITIALIZING,

  // 开始
  STATING,
  // 进行中
  RUNNING,
  // 结算
  ENDING,

  // 最终结算
  FINAL,
  ;
}

// 操作
enum class Action {
  吃,
  碰,
  杠,
  立直,
  和,
  自摸,
  流局,
  摸,
  切,
  跳过,
  ;
}

// 玩家状态
enum class PlayerState {
  // 激活状态
  ACTIVING,
  // 等待状态
  WAITING,
  ;
}


// 玩家
class Player(
    //点数
    val point: Int,
    //方位
    var position: Position,
    // 手牌
    var entity: Entity,
    // 状态
    var state: PlayerState
) {


}

// 一个半庄/半半庄
class Majsoul(
    var state: State,
    var players: List<Player>
) {

  private val stack = CardStack()

  fun runOnce() {
//    val values = Card.values()
    val stack: List<Card> = listOf()
//    repeat(4) { stack += values }
    // 包含所有牌谱
    val nstack = stack.shuffled()


  }

}



