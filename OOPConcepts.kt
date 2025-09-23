/**
 * - **Imperativo:** Se basa en una secuencia de instrucciones que alteran el estado del programa. **Lenguaje:** C.
 * - **Procedural:** Un subparadigma del imperativo que organiza el código en procedimientos o funciones. **Lenguajes:** C, Pascal, Go.
 * - **Orientado a Objetos (POO):** Organiza el diseño alrededor de objetos que contienen datos y comportamiento. **Lenguajes:** Java, C#, Python, Ruby.
 * - **Funcional:** Trata la computación como la evaluación de funciones matemáticas, evitando el estado mutable. **Lenguajes:** Haskell, Erlang, Scala, Elixir.
 * - **Declarativo:** Expresa la lógica de la computación sin describir su flujo de control (se centra en el "qué", no en el "cómo"). **Lenguaje:** SQL (el ejemplo más puro).
 * - **Lógico:** Se basa en la lógica formal para expresar relaciones y reglas. El programa encuentra la solución. **Lenguaje:** Prolog.
 * - **Dirigido por Eventos:** El flujo del programa está determinado por eventos (ej., clics de ratón, mensajes). **Lenguajes:** JavaScript (en el navegador), Python (con asyncio).
 * **Nota:** La mayoría de los lenguajes modernos son **multi-paradigma** (como Python, JavaScript, Scala o Go), pero suelen estar asociados a uno o dos paradigmas predominantes.
 */

/**
 * **Conceptos Fundamentales de POO**:
 *
 * - **[Abstraction]**: Permite simplificar y representar objetos esenciales.
 * - **[Encapsulation]**: Oculta detalles internos exponiendo solo interfaz necesaria.
 * - **[Inheritance]**: Permite heredar propiedades y comportamientos entre clases.
 * - **[Polymorphism]**: Permite comportamientos diferentes mediante interfaz común.
 */
object OOPConcepts {
    /**
     * **Abstraction**
     *
     * Simplifica objetos del mundo real enfocándose en características esenciales,
     * ocultando detalles complejos mediante clases abstractas e interfaces.
     */
    object Abstraction {
        abstract class Shape {
            abstract fun area(): Double
            abstract fun perimeter(): Double
        }

        class Circle(private val radius: Double) : Shape() {
            override fun area() = Math.PI * radius * radius
            override fun perimeter() = 2 * Math.PI * radius
        }

        class Rectangle(
            private val width: Double,
            private val height: Double
        ) : Shape() {
            override fun area() = width * height
            override fun perimeter() = 2 * (width + height)
        }
    }

    /**
     * **Encapsulation**
     *
     * Protege el estado interno mediante acceso controlado,
     * previniendo modificación directa y garantizando integridad.
     */
    object Encapsulation {
        class BankAccount {
            private var balance: Double = 0.0
            private val transactionHistory = mutableListOf<String>()

            fun deposit(amount: Double) {
                if (amount > 0) {
                    balance += amount
                    logTransaction("Deposit: +$amount")
                }
            }

            fun withdraw(amount: Double) {
                if (amount > 0 && balance >= amount) {
                    balance -= amount
                    logTransaction("Withdrawal: -$amount")
                }
            }

            fun currentBalance() = balance

            private fun logTransaction(description: String) {
                transactionHistory.add("$description | Balance: $balance")
            }
        }
    }

    /**
     * **Inheritance**
     *
     * Establece relaciones jerárquicas permitiendo reutilización de código
     * y especialización mediante superclases y subclases.
     */
    object Inheritance {
        open class Vehicle(
            private val brand: String,
            private val model: String
        ) {
            open fun startEngine() = println("$brand $model engine started")
            open fun stopEngine() = println("$brand $model engine stopped")
        }

        class ElectricCar(
            brand: String,
            model: String,
            private val batteryCapacity: Int
        ) : Vehicle(brand, model) {
            override fun startEngine() {
                println("Activating electric systems...")
                super.startEngine()
            }

            fun chargeBattery() = println("Charging $batteryCapacity kWh battery")
        }
    }

    /**
     * **Polymorphism**
     *
     * Permite tratar objetos diversos uniformemente mediante interfaz común,
     * habilitando comportamientos específicos en tiempo de ejecución.
     */
    object Polymorphism {
        interface PaymentMethod {
            fun processPayment(amount: Double): Boolean
        }

        class CreditCard : PaymentMethod {
            override fun processPayment(amount: Double): Boolean {
                println("Processing credit card payment: $amount")
                return true
            }
        }

        class CryptoWallet : PaymentMethod {
            override fun processPayment(amount: Double): Boolean {
                println("Processing crypto payment: $amount BTC")
                return true
            }
        }

        class PaymentProcessor {
            fun acceptPayment(paymentMethod: PaymentMethod, amount: Double) {
                paymentMethod.processPayment(amount)
            }
        }
    }
}