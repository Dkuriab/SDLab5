import drawer.AwtDrawer
import drawer.DrawingApi
import drawer.PiccoloDrawer
import graphdrawer.GraphDrawer
import model.Graph
import model.GraphImpl
import org.apache.commons.cli.Options
import org.apache.commons.cli.PosixParser
import java.io.File
import java.lang.IllegalArgumentException
import java.nio.file.Files
import kotlin.reflect.KClass
import kotlin.streams.toList

enum class GraphType {
    Matrix,
    Edges
}

fun main(vararg args: String) {
    val options = Options()
    options.addOption("f", "file", true, "file") // input
    options.addOption("g", "graphType", true, "graph type") // matrix / edges
    options.addOption("d", "drawingApi", true, "drawing api") // awt / pic

    val commandLineParser = PosixParser()
    val commandLine = commandLineParser.parse(options, args)
    val canvasSize = Pair(1000, 700)
    lateinit var graphType: GraphType
    lateinit var drawer: DrawingApi
    lateinit var graph: Graph
    lateinit var fileLines: List<String>

    for (option in commandLine.options) {
        println("${ option.opt }   -   ${ option.value }")
    }

    for (option in commandLine.options) {
        when (option.opt) {
            "d" -> {
                drawer = when (option.value) {
                    "awt" -> AwtDrawer(canvasSize)
                    "pic" -> PiccoloDrawer(canvasSize)
                    else -> throw IllegalArgumentException("${option.value} drawer not yet implemented")
                }
            }
            "g" -> {
                graphType = when(option.value) {
                    "matrix" -> GraphType.Matrix
                    "edges" -> GraphType.Edges
                    else -> throw IllegalArgumentException("${option.value} graphs not yet implemented")
                }
            }
            "f" -> {
                fileLines = Files.lines(File(option.value).toPath()).toList()
            }
        }
    }

    graph = when(graphType) {
        GraphType.Matrix -> GraphImpl(
            readMatrix(fileLines)
        )
        GraphType.Edges -> {
            val params = readEdgesAndSize(fileLines)
            GraphImpl(
                params.first,
                params.second
            )
        }
    }

    val graphDrawer = GraphDrawer(drawer)

    graphDrawer.draw(graph)
}

fun readMatrix(fileLines: List<String>): List<List<Boolean>> {
    val matrix = mutableListOf<List<Boolean>>()

    for (line in fileLines) {
        matrix.add(line.split(' ').map { it == "1" })
    }

    return matrix
}

fun readEdgesAndSize(fileLines: List<String>): Pair<Int, MutableSet<Pair<Int, Int>>> {
    val size = fileLines[0].toInt()
    val edges = mutableSetOf<Pair<Int, Int>>()

    for (i in 1 .. fileLines.lastIndex) {
        val nodes = fileLines[i].split(' ').map { it.toInt() }
        edges.add(Pair(nodes[0], nodes[1]))
    }

    return Pair(size, edges)
}
