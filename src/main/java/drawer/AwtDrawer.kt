package drawer

import java.awt.Frame
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.Shape
import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent
import java.awt.geom.Ellipse2D
import java.awt.geom.Line2D
import kotlin.system.exitProcess

class AwtDrawer(private val size: Pair<Int, Int>) : DrawingApi {
    private val shapes: MutableList<Shape> = mutableListOf()

    override fun getDrawingAreaWidth() = size.first
    override fun getDrawingAreaHeight() = size.second


    override fun drawCircle(x: Double, y: Double, radius: Double) {
        shapes.add(Ellipse2D.Double(x - radius, y - radius, 2 * radius, 2 * radius))
    }

    override fun drawLine(x1: Double, y1: Double, x2: Double, y2: Double) {
        shapes.add(Line2D.Double(x1, y1, x2, y2))
    }

    override fun show() {
        val frame = AwtFrame(shapes)
        frame.setSize(getDrawingAreaWidth(), getDrawingAreaHeight())
        frame.setUp()
        frame.isVisible = true
    }

    private fun Frame.setUp() {
        addWindowListener(object : WindowAdapter() {
            override fun windowClosing(e: WindowEvent?) {
                exitProcess(0)
            }

            override fun windowActivated(e: WindowEvent?) {
                println("LOG: Opened AwtDrawer")
            }
        })
    }

    class AwtFrame(private val shapes: List<Shape>) : Frame() {
        override fun paint(g: Graphics?) {
            val graphics2D = g as Graphics2D
            shapes.filterIsInstance<Ellipse2D>().forEach {
                graphics2D.fill(it)
            }
            shapes.filterIsInstance<Line2D>().forEach {
                graphics2D.draw(it)
            }
        }
    }
}