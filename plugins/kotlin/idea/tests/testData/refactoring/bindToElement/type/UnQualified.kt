// BIND_TO org.jetbrains.B
package org.jetbrains

interface A { }

class B : A { }

fun foo() {
    val x: <caret>A = B()
}