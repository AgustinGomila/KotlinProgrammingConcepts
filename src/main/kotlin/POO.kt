@file:Suppress("unused")

/**
 * **POO [Abstraction]**: Permite simplificar y representar objetos.
 *
 * **POO [Encapsulation]**: Permite ocultar los detalles internos de una clase y exponer solo la interfaz necesaria para interactuar con ella.
 *
 * **POO [Heritability]**: Permite que una clase herede propiedades y comportamientos de otra clase.
 *
 * **POO [Polymorphism]**: Permite que los objetos de diferentes clases se comporten de manera similar.
 */
data class POO(val name: String)


/**
 * **Abstraction**
 *
 * La abstracción se refiere a simplificar y representar objetos del mundo real en su forma esencial. En programación, esto implica definir características y comportamientos esenciales de un objeto, mientras se ocultan los detalles complejos.
 */
object Abstraction {
    abstract class Shape {
        abstract fun calculateArea(): Double
    }

    class Circle(val radius: Double) : Shape() {
        override fun calculateArea(): Double {
            return Math.PI * radius * radius
        }
    }

    class Rectangle(val width: Double, val height: Double) : Shape() {
        override fun calculateArea(): Double {
            return width * height
        }
    }
}

/**
 * **Encapsulation**
 *
 * El encapsulamiento implica ocultar los detalles internos de una clase y exponer solo la interfaz necesaria para interactuar con ella.
 */
object Encapsulation {
    class BankAccount(private var balance: Double) {
        fun deposit(amount: Double) {
            if (amount > 0) {
                balance += amount
            }
        }

        fun withdraw(amount: Double) {
            if (amount > 0 && amount <= balance) {
                balance -= amount
            }
        }

        fun getBalance(): Double {
            return balance
        }
    }
}

/**
 * **Heritability**
 *
 * La herencia permite que una clase herede propiedades y comportamientos de otra clase. En Kotlin, todas las clases heredan implícitamente de la clase Any.
 */
object Heritability {
    open class Animal(val name: String) {
        open fun makeSound() {
            println("Animal makes a sound")
        }
    }

    class Dog(name: String) : Animal(name) {
        override fun makeSound() {
            println("Dog barks")
        }
    }
}

/**
 * **Polymorphism**
 *
 * El polimorfismo permite que los objetos de diferentes clases se comporten de manera similar a través de interfaces y herencia.
 */
object Polymorphism {
    fun animalSound(animal: Heritability.Animal) {
        animal.makeSound()
    }

    init {
        val animal: Heritability.Animal = Heritability.Dog("Buddy")
        animalSound(animal)  // Imprime "Dog barks"
    }
}
