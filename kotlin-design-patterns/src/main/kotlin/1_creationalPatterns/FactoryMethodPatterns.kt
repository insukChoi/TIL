package `1_creationalPatterns`

fun main() {
    val notations = listOf("pa8", "qc3")
    val pieces = mutableListOf<ChessPiece>()
    for (n in notations) {
        pieces.add(createPiece(n))
    }
    println(pieces)
}

interface ChessPiece {
    val file: Char
    val rank: Char
}

data class Pawn(
    override val file: Char,
    override val rank: Char
) : ChessPiece

data class Queen(
    override val file: Char,
    override val rank: Char
) : ChessPiece

// 팩토리 메서드 패턴
fun createPiece(notation: String): ChessPiece {
    val (type, file, rank) = notation.toCharArray()
    return when (type) {
        'p' -> Pawn(file, rank)
        'q' -> Queen(file, rank)
        else -> throw RuntimeException("Unknown chess type $type")
    }
}