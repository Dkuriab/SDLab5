package drawer

import org.piccolo2d.PCanvas
import org.piccolo2d.extras.PFrame
import org.piccolo2d.nodes.PPath
import org.piccolo2d.nodes.PText
import java.awt.Color
import javax.swing.JFrame


class PiccoloDrawer(private val size: Pair<Int, Int>) : DrawingApi {
    private val shapes: MutableList<PPath> = mutableListOf()

    override fun getDrawingAreaWidth() = size.first
    override fun getDrawingAreaHeight() = size.second


    override fun drawCircle(x: Double, y: Double, radius: Double) {
        val ellipse = PPath.createEllipse(x - radius, y - radius, 2 * radius, 2 * radius)
        ellipse.paint = Color.BLACK

        shapes.add(ellipse)
    }

    override fun drawLine(x1: Double, y1: Double, x2: Double, y2: Double) {
        shapes.add(PPath.createLine(x1, y1, x2, y2))
    }

    override fun show() {
        val frame = PFrame()

        frame.canvas.layer.addChildren(shapes)
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        frame.setSize(getDrawingAreaWidth(), getDrawingAreaHeight())
        frame.isVisible = true
    }
}