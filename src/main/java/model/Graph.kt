package model

interface Graph {
    val size: Int
    val edges: Set<Pair<Int, Int>>
}