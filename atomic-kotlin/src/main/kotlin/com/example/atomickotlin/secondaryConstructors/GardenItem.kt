package com.example.atomickotlin.secondaryConstructors

import com.example.atomickotlin.atomicTest.eq

enum class Material {
    Ceramic, Metal, Plastic
}

class GardenItem(val name: String) {
    var material: Material = Material.Plastic
    constructor(
        name: String, material: Material
    ) : this(name) {
        this.material = material
    }
    constructor(
        material: Material
    ) : this("Strange Thing", material)

    override fun toString(): String = "$material $name"
}

fun main() {
    GardenItem("Elf").material eq Material.Plastic
    GardenItem("Snowman").name eq "Snowman"
    GardenItem("Gazing Bail", Material.Metal) eq "Metal Gazing Bail"
    GardenItem(material = Material.Ceramic) eq "Ceramic Strange Thing"
}