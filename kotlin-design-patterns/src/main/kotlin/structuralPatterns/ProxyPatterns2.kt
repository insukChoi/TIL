package structuralPatterns

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

@Suppress("UNCHECKED_CAST")
abstract class MapProxy: ReadWriteProperty<MapProxy, Any?> {
    val map = HashMap<String, Any?>()

    override fun getValue(thisRef: MapProxy, property: KProperty<*>): Any? = map[property.name]
    override fun setValue(thisRef: MapProxy, property: KProperty<*>, value: Any?) {
        map[property.name] = value
    }

    protected val long: ReadWriteProperty<MapProxy, Long> get() = this as ReadWriteProperty<MapProxy, Long>
    protected val boolean: ReadWriteProperty<MapProxy, Boolean> get() = this as ReadWriteProperty<MapProxy, Boolean>
    protected val string: ReadWriteProperty<MapProxy, String> get() = this as ReadWriteProperty<MapProxy, String>
}

class Member: MapProxy() {
    var name by string
    var age by long
    var isMale by boolean
}

fun main() {
    val member = Member().apply {
        name = "insuk"
        age = 20
        isMale = true
    }

    val member2 = Member().apply {
        name = "heera"
        age = 18
        isMale = false
    }

    println(member.map)
    println(member2.map)
}