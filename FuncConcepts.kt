// Programación Funcional en Kotlin - Ejemplos Básicos

fun main() {
    println("===== FUNCIONES DE ORDEN SUPERIOR =====")
    higherOrderFunctions()

    println("\n===== OPERACIONES DE COLECCIÓN =====")
    collectionOperations()

    println("\n===== COMPOSICIÓN Y OTROS =====")
    compositionAndMore()
}

// 1. Funciones de Orden Superior (Higher-Order Functions)
// ======================================================
// Funciones que reciben otras funciones como parámetros o devuelven funciones

fun higherOrderFunctions() {
    // Función que toma otra función como parámetro
    fun operate(a: Int, b: Int, operation: (Int, Int) -> Int): Int {
        return operation(a, b)
    }

    // Lambda: función anónima (suma)
    val sum: (Int, Int) -> Int = { x, y -> x + y }

    // Función nombrada
    fun multiply(x: Int, y: Int) = x * y

    println("Suma: ${operate(5, 3, sum)}")          // Usando lambda
    println("Multiplicación: ${operate(5, 3, ::multiply)}") // Referencia a función
    println(
        "Potencia: ${
            operate(2, 4) { a, b ->
                (1..b).fold(1) { acc, _ -> acc * a }
            }
        }"
    ) // Lambda inline
}

// 2. Operaciones con Colecciones
// ================================
// Transformaciones funcionales sobre colecciones

fun collectionOperations() {
    val numbers = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9)

    // Map: transforma cada elemento
    val squares = numbers.map { it * it }
    println("Cuadrados: $squares")

    // Filter: selecciona elementos
    val evens = numbers.filter { it % 2 == 0 }
    println("Pares: $evens")

    // Reduce: acumula valores
    val sum = numbers.reduce { acc, num -> acc + num }
    println("Suma total: $sum")

    // Fold: reduce con valor inicial
    val factorial = (1..5).fold(1) { acc, i -> acc * i }
    println("Factorial de 5: $factorial")

    // Chaining: encadenamiento
    val result = numbers
        .filter { it > 4 }
        .map { it * 2 }
        .take(3)
    println("Filtrado >4, duplicado y tomar 3: $result")
}

// 3. Composición y otros conceptos
// =================================
fun compositionAndMore() {
    // Función de extensión
    fun String.addExclamation() = "$this!"
    println("Hola".addExclamation())

    // Inmutabilidad
    val immutableList = listOf("A", "B", "C")
    // immutableList.add("D") // Error: colección inmutable

    // Composición de funciones
    fun addTwo(x: Int) = x + 2
    fun triple(x: Int) = x * 3

    val addThenTriple = ::addTwo andThen ::triple
    println("Composición: 5 +2 *3 = ${addThenTriple(5)}")

    // Secuencias (lazy evaluation)
    val lazyResult = generateSequence(1) { it + 1 }
        .map { it * it }
        .take(5)
        .toList()
    println("Secuencia perezosa: $lazyResult")
}

// Operador para composición de funciones
infix fun <A, B, C> ((A) -> B).andThen(next: (B) -> C): (A) -> C =
    { input: A -> next(this(input)) }

/**
 * ===== FUNCIONES DE ORDEN SUPERIOR =====
 * Suma: 8
 * Multiplicación: 15
 * Potencia: 16
 *
 * ===== OPERACIONES DE COLECCIÓN =====
 * Cuadrados: [1, 4, 9, 16, 25, 36, 49, 64, 81]
 * Pares: [2, 4, 6, 8]
 * Suma total: 45
 * Factorial de 5: 120
 * Filtrado >4, duplicado y tomar 3: [10, 12, 14]
 *
 * ===== COMPOSICIÓN Y OTROS =====
 * Hola!
 * Composición: 5 +2 *3 = 21
 * Secuencia perezosa: [1, 4, 9, 16, 25]
 */