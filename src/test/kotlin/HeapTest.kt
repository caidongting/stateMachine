import home.NettyTcpServer
import org.junit.Test

class HeapTest {

  @Test
  fun testNetty() {
    NettyTcpServer(port = 12121).run()
  }

  @Test
  fun test() {
    val k = 12
    val arrN: IntArray = intArrayOf(5, 3, 7, 1, 8, 2, 9, 4, 7, 2, 6, 6)
    val arr: List<Data> = arrN.map(::Data)
    val root = createHeap(arr.subList(0, k), HeapMode.big)
    println(root)
  }

}

data class Data(val data: Int) : Comparable<Data> {
  override fun compareTo(other: Data): Int {
    return data - other.data
  }
}

data class Node(
   val n: Data,
   var l: Node? = null,
   var r: Node? = null
) : Comparable<Node> {

  override fun compareTo(other: Node): Int {
    return n.compareTo(other.n)
  }
}

// 堆类型
enum class HeapMode {
  little,
  big
}


fun Node.up(root: Node, mode: HeapMode): Boolean {
  return when (mode) {
    HeapMode.little -> this <= root
    HeapMode.big -> this >= root
  }
}

fun whenUp(root: Node, nt: Node, mode: HeapMode): Node {
  val l = root.l
  val r = root.r
  when {
    l == null -> {  // 左旋
      root.r = null
      nt.l = root
      nt.r = r
    }
    r == null -> { // 右旋
      root.l = null
      nt.l = l
      nt.r = root
    }
    else -> if (l.up(r, mode)) {
      root.l = null
      nt.l = l
      nt.r = root
    } else {
      root.r = null
      nt.l = root
      nt.r = r
    }
  }
  return nt
}

fun whenFall(root: Node, nt: Node, mode: HeapMode): Node {
  val l = root.l
  val r = root.r
  when {
    l == null -> root.l = nt
    r == null -> root.r = nt
    else -> if (l.up(r, mode)) root.r = insert(root.r, nt, mode)
    else root.l = insert(root.l, nt, mode)
  }
  return root
}

// insert
fun insert(root: Node?, nt: Node, mode: HeapMode): Node {
  return when {
    //  创建根节点
    root == null -> nt
    // 数据上升
    nt.up(root, mode) -> whenUp(root, nt, mode)
    // 数据下降
    else -> whenFall(root, nt, mode)
  }
}

/**
 * create heap with specific mode.
 *
 * @param arr data of heap.
 * @param mode the mode of heap created.
 * @return root node of heap.
 */
fun createHeap(arr: Collection<Data>, mode: HeapMode): Node? {
  if (arr.isEmpty()) return null
  var root: Node? = null
  arr.forEach { data -> root = insert(root, Node(data), mode) }
  return root
}