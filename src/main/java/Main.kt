import drawer.AwtDrawer
import drawer.PiccoloDrawer
import graphdrawer.GraphDrawer
import model.Graph
import model.GraphImpl

val matrixGraph: Graph = GraphImpl(
    listOf(
        listOf(true, true, true, true, true, true, true, true),
        listOf(true, true, true, true, true, true, true, true),
        listOf(true, true, true, true, true, true, true, true),
        listOf(true, false, true, true, true, true, true, true),
        listOf(true, true, true, true, true, true, true, true),
        listOf(true, true, true, true, true, true, true, true),
        listOf(true, true, true, true, true, true, true, true),
        listOf(false, true, true, true, true, true, true, true),
    )
)

val edgesGraph: Graph = GraphImpl(
    9,
    setOf(
        Pair(0, 8),
        Pair(8, 1),
        Pair(1, 7),
        Pair(7, 2),
        Pair(2, 6),
        Pair(6, 3),
        Pair(3, 5),
        Pair(5, 4),
    )
)


fun main() {
    val canvasSize = Pair(1000, 700)
    val awtDrawer = AwtDrawer(canvasSize)
    val pDrawer = PiccoloDrawer(canvasSize)

    val graphDrawer = GraphDrawer(pDrawer)

    graphDrawer.draw(edgesGraph)
}