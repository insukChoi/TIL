package `2_structuralPatterns`

class Squad(val units: List<Trooper>) : Trooper {
    constructor(vararg units: Trooper) : this(units.toList())

    override fun move(x: Long, y: Long) {
        for (u in units) {
            u.move(x, y)
        }
    }

    override fun attackRebel(x: Long, y: Long) {
        for (u in units) {
            u.attackRebel(x, y)
        }
    }
}

val bobaFett = StormTrooper(Rifle(), RegularLegs())
val squad = Squad(bobaFett.copy(), bobaFett.copy(), bobaFett.copy())

val squad2 = Squad(Squad(), Squad())


