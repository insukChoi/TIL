package com.example.functional.ch1

import kotlin.reflect.KClass

interface CreditCard

class Charge<PRODUCT:Product>(val cc: CreditCard, val qty: Int, val product: KClass<PRODUCT>)

class Receipt<PRODUCT:Product>(val qty: Int, val product: KClass<PRODUCT>)

interface Product {
    val price: Double
    val factory: () -> Product
}

class Americano: Product {
    override val price: Double = 1000.0
    override val factory: () -> Americano
        get() = ::Americano
}

class CafeLatte: Product {
    override val price: Double = 2000.0
    override val factory: () -> CafeLatte
        get() = ::CafeLatte
}