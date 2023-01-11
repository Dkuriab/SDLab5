package drawer

interface DrawingApi {
    fun getDrawingAreaWidth(): Int
    fun getDrawingAreaHeight(): Int
    fun drawCircle(x: Double, y: Double, radius: Double)
    fun drawLine(x1: Double, y1: Double, x2: Double, y2: Double)
    fun show()
}


