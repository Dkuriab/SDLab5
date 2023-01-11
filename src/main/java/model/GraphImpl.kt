package model

class GraphImpl: Graph {
    override val size: Int
    override val edges: MutableSet<Pair<Int, Int>>

    constructor(matrix: List<List<Boolean>>) {
        size = matrix.size
        edges = mutableSetOf()
        for (i in matrix.indices) {
            for (j in 0 until i) {
                if (matrix[i][j]) {
                    edges.add(Pair(i, j))
                }
            }
        }
    }

    constructor(size: Int, edges: Set<Pair<Int, Int>>) {
        this.size = size
        this.edges = edges.toMutableSet()
    }
}