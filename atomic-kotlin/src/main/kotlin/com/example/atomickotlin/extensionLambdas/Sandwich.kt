package com.example.atomickotlin.extensionLambdas

import com.example.atomickotlin.atomicTest.eq

open class Recipe : ArrayList<RecipeUnit>()

open class RecipeUnit {
    override fun toString(): String = "${this::class.simpleName}"
}

open class Operation : RecipeUnit()
class Toast : Operation()
class Grill : Operation()
class Cut : Operation()

open class Ingredient: RecipeUnit()
class Bread : Ingredient()
class PeanutButter : Ingredient()
class GrapeJelly : Ingredient()
class Ham : Ingredient()
class Swiss : Ingredient()
class Mustard : Ingredient()

open class Sandwich : Recipe() {
    fun action(op: Operation): Sandwich {
        add(op)
        return this
    }
    fun toast() = action(Toast())
    fun grill() = action(Grill())
    fun cut() = action(Cut())
}

fun sandwich(
    fillings: Sandwich.() -> Unit
): Sandwich {
    val sandwich = Sandwich()
    sandwich.add(Bread())
    sandwich.toast()
    sandwich.fillings()
    sandwich.cut()
    return sandwich
}

fun main() {
    val pbj = sandwich {
        add(PeanutButter())
        add(GrapeJelly())
    }
    val hamAndSwiss = sandwich {
        add(Ham())
        add(Swiss())
        add(Mustard())
        grill()
    }

    pbj eq "[Bread, Toast, PeanutButter, GrapeJelly, Cut]"
    hamAndSwiss eq "[Bread, Toast, Ham, Swiss, Mustard, Grill, Cut]"
}