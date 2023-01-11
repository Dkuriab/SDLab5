package graphdrawer

import drawer.DrawingApi
import model.Graph
import java.awt.geom.Point2D
import kotlin.math.cos
import kotlin.math.sin

class GraphDrawer(private val drawingApi: DrawingApi) {
    companion object {
        const val POINT_RADIUS = 20.0
    }

    fun draw(graph: Graph) {
        val width = drawingApi.getDrawingAreaWidth()
        val height = drawingApi.getDrawingAreaHeight()

        val center = Point2D.Double(width / 2.0, height / 2.0)
        val radius = minOf(width, height) / 2.0 - POINT_RADIUS * 2
        val pointsCoordinates = mutableListOf<Pair<Double, Double>>()

        for (i in 0 until graph.size) {
            val angle = 2 * Math.PI * i / graph.size
            val point = Pair(center.getX() + radius * cos(angle), center.getY() + radius * sin(angle));
            pointsCoordinates.add(point)

            drawingApi.drawCircle(point.first, point.second, POINT_RADIUS)
        }

        for (edge in graph.edges) {
            val startPoint = pointsCoordinates[edge.first]
            val endPoint = pointsCoordinates[edge.second]

            drawingApi.drawLine(
                x1 = startPoint.first,
                y1 = startPoint.second,
                x2 = endPoint.first,
                y2 = endPoint.second
            )
        }

        drawingApi.show()
    }
}