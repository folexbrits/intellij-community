// BIND_TO org.jetbrains.B
package org.jetbrains

class A {
    fun foo() { }
}

class B {
    fun foo() { }
}

fun foo() {
    val x = <caret>org.jetbrains.A::foo
}