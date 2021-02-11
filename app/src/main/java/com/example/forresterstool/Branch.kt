package com.example.forresterstool

import kotlin.math.PI

fun radius(circumference: Float) = circumference / (2 * PI)
fun cylinderVolume(circumference: Float, length: Float): Double {
    return radius(circumference) * radius(circumference) * PI * length
}
fun coneTrunkVolume(circ1: Float, circ2: Float, length: Float): Double {
    var r1 = radius(circ1)
    var r2 = radius(circ2)
    return PI * length * (r1 * r1 + r2 * r2 + r1 * r2) / 3
}


class Branch(var type: Boolean, var circumference1: Float, var circumference2: Float, var length: Float) {
    fun getVolume(): Float {
        if(type == CYLINDER)
            return cylinderVolume(circumference1, length).toFloat()
        else
            return coneTrunkVolume(circumference1, circumference2, length).toFloat()
    }
    fun getType() = if(!type) "Cylinder" else "Cone Trunk"
}

var branches = mutableListOf<Branch>()
