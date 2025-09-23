/**
 * Archivo que explica patrones arquitectónicos y principios clave en desarrollo de software.
 *
 * Este archivo contiene explicaciones y ejemplos simplificados de patrones como MVC, MVVM, SOLID,
 * Repository y Clean Architecture, etc. utilizando Kotlin. Los ejemplos son intencionalmente básicos
 * para ilustrar los conceptos fundamentales sin dependencias externas.
 */

package com.example.architecturalpatterns

/**
 * ## Patrón MVC (Model-View-Controller)
 *
 * **Separación de responsabilidades en tres componentes:**
 * - **Modelo**: Maneja los datos y lógica de negocio.
 * - **Vista**: Muestra la información y captura la interacción del usuario.
 * - **Controlador**: Actúa como intermediario entre Modelo y Vista.
 *
 * ### Ejemplo en Kotlin
 */
class MVCExample {
    // Modelo
    data class User(val name: String, val email: String)

    // Vista
    interface UserView {
        fun displayUser(user: User)
    }

    // Controlador
    class UserController(private val view: UserView) {
        private var user: User? = null
        fun setUser(name: String, email: String) {
            user = User(name, email)
            user?.let { view.displayUser(it) }
        }
    }

    fun runExample() {
        val view = object : UserView {
            override fun displayUser(user: User) {
                println("MVC - Usuario: ${user.name}, Email: ${user.email}")
            }
        }
        UserController(view).setUser("Carlos", "carlos@example.com")
    }
}

/**
 * ## Patrón MVVM (Model-View-ViewModel)
 *
 * **Componentes clave:**
 * - **Modelo**: Representa los datos y lógica de negocio.
 * - **Vista**: Muestra los datos (en Android: XML/Compose).
 * - **ViewModel**: Proporciona datos a la Vista y maneja la lógica de presentación.
 *
 * ### Ventajas
 * - Desacoplamiento entre Vista y lógica
 * - Mejor testabilidad
 * - Soporte para observables (ej: LiveData en Android)
 *
 * ### Ejemplo simplificado
 */
class MVVMExample {
    // Modelo
    data class User(val name: String, var email: String)

    // Vista
    interface UserView {
        fun updateUI(user: User)
    }

    // ViewModel
    class UserViewModel {
        private var user: User? = null
        private var view: UserView? = null
        fun bindView(view: UserView) {
            this.view = view; user?.let { view.updateUI(it) }
        }

        fun setUser(name: String, email: String) {
            user = User(name, email)
            user?.let { view?.updateUI(it) }
        }
    }

    fun runExample() {
        val view = object : UserView {
            override fun updateUI(user: User) {
                println("MVVM - Vista actualizada: ${user.name}, ${user.email}")
            }
        }
        UserViewModel().apply {
            bindView(view)
            setUser("Laura", "laura@example.com")
        }
    }
}

/**
 * ## Principios SOLID
 *
 * **Conjunto de 5 principios para diseño de software mantenible:**
 *
 * ### 1. Single Responsibility Principle (SRP)
 * > "Una clase debe tener una sola razón para cambiar"
 *
 * ### 2. Open/Closed Principle (OCP)
 * > "Abierto para extensión, cerrado para modificación"
 *
 * ### 3. Liskov Substitution Principle (LSP)
 * > "Los objetos de una subclase deben poder sustituir a los de la superclase"
 *
 * ### 4. Interface Segregation Principle (ISP)
 * > "Mejor varias interfaces específicas que una general"
 *
 * ### 5. Dependency Inversion Principle (DIP)
 * > "Depende de abstracciones, no de implementaciones concretas"
 */

/**
 * ### Ejemplo SRP: Separación de responsabilidades
 */
class SRPExample {
    data class User(val name: String, val email: String)
    class UserRepository {
        fun save(user: User) {
            println("SRP - Guardando usuario ${user.name} en BD")
        }
    }
}

/**
 * ### Ejemplo OCP: Extensión sin modificación
 */
class OCPExample {
    // Interfaz para algoritmos
    interface PaymentStrategy {
        fun pay(amount: Double)
    }

    // Nuevas estrategias se agregan sin modificar código existente
    class CreditCardPayment : PaymentStrategy {
        override fun pay(amount: Double) {
            println("OCP - Pago con tarjeta: $$amount")
        }
    }

    class PayPalPayment : PaymentStrategy {
        override fun pay(amount: Double) {
            println("OCP - Pago con PayPal: $$amount")
        }
    }
}

/**
 * ## Patrón Repository
 *
 * **Propósito:**
 * - Abstrae la fuente de datos (BD, API, etc.)
 * - Centraliza la lógica de acceso a datos
 * - Facilita pruebas y cambios en la capa de datos
 *
 * ### Ejemplo básico
 */
class RepositoryExample {
    data class User(val name: String, val email: String)
    interface UserRepository {
        fun getUser(id: Int): User
        fun saveUser(user: User)
    }

    class DbUserRepository : UserRepository {
        override fun getUser(id: Int): User {
            return User("Usuario $id", "user$id@example.com")
        }

        override fun saveUser(user: User) {
            println("Repository - Guardando ${user.name} en BD")
        }
    }

    fun runExample() {
        val repo = DbUserRepository()
        val user = repo.getUser(1)
        println("Repository - Obtenido: ${user.name}")
        repo.saveUser(user)
    }
}

/**
 * ## Clean Architecture
 *
 * **Estructura en capas:**
 * ```
 * 1. Entidades (Dominio)      <- Reglas de negocio
 * 2. Casos de uso             <- Flujo específico de la aplicación
 * 3. Adaptadores (UI, DB)     <- Herramientas e interfaces
 * 4. Frameworks               <- Bibliotecas externas (ej: Android SDK)
 * ```
 *
 * ### Características clave
 * - Independencia de frameworks
 * - Testabilidad
 * - Separación de preocupaciones
 *
 * ### Ejemplo conceptual (simplificado)
 */
class CleanArchitectureExample {
    // Entidad (Dominio)
    data class User(val id: Int, val name: String)
    interface UserRepository {
        fun getUser(id: Int): User
    }

    // Caso de uso
    class GetUserUseCase(private val repo: UserRepository) {
        fun execute(id: Int): User = repo.getUser(id)
    }
}

/**
 * ## Patrón MVP (Model-View-Presenter)
 *
 * **Características clave:**
 * - La Vista es completamente pasiva y delega toda la lógica al Presenter
 * - El Presenter actúa como intermediario y contiene toda la lógica de presentación
 * - El Modelo mantiene la lógica de negocio y datos
 *
 * **Ventajas:**
 * - Mayor testabilidad (el Presenter se puede testear sin dependencias de UI)
 * - Desacoplamiento total entre vista y lógica
 */
class MVPExample {
    data class User(val id: Int, val name: String, val email: String)
    interface UserView {
        fun showUser(user: User)
        fun showError(message: String)
    }

    class UserRepository {
        fun getUserById(id: Int): User? {
            return if (id > 0) User(id, "User$id", "user$id@example.com") else null
        }
    }

    class UserPresenter(private val view: UserView, private val repository: UserRepository) {
        fun loadUser(userId: Int) {
            val user = repository.getUserById(userId)
            if (user != null) {
                view.showUser(user)
            } else {
                view.showError("User not found")
            }
        }
    }

    fun runExample() {
        val repository = UserRepository()
        val view = object : UserView {
            override fun showUser(user: User) {
                println("MVP - User loaded: ${user.name} (${user.email})")
            }

            override fun showError(message: String) {
                println("MVP - Error: $message")
            }
        }
        UserPresenter(view, repository).loadUser(1)
        UserPresenter(view, repository).loadUser(-1)
    }
}

/**
 * ## Arquitectura Hexagonal (Ports and Adapters)
 *
 * **Conceptos fundamentales:**
 * - El núcleo de dominio está aislado de tecnologías externas
 * - Los "puertos" definen contratos para interacciones
 * - Los "adaptadores" implementan los puertos para tecnologías específicas
 *
 * **Estructura:**
 * 1. Dominio (entidades y reglas de negocio)
 * 2. Puertos (interfaces de dominio)
 * 3. Adaptadores (implementaciones concretas)
 */
class HexagonalArchitectureExample {
    data class Product(val id: Int, var name: String, var price: Double)
    interface ProductRepositoryPort {
        fun save(product: Product)
        fun findById(id: Int): Product?
    }

    class InventoryManagement {
        private val repository: ProductRepositoryPort

        constructor(repository: ProductRepositoryPort) {
            this.repository = repository
        }

        fun updatePrice(productId: Int, newPrice: Double) {
            val product = repository.findById(productId)
            product?.price = newPrice
            product?.let { repository.save(it) }
        }
    }

    class DatabaseAdapter : ProductRepositoryPort {
        private val products = mutableMapOf<Int, Product>()
        override fun save(product: Product) {
            products[product.id] = product
        }

        override fun findById(id: Int): Product? {
            return products[id]
        }
    }

    fun runExample() {
        val dbAdapter = DatabaseAdapter()
        val inventory = InventoryManagement(dbAdapter)

        val product = Product(100, "Laptop", 999.99)
        dbAdapter.save(product)

        inventory.updatePrice(100, 899.99)
        println("Hexagonal - Updated price: ${dbAdapter.findById(100)?.price}")
    }
}

/**
 * ## Patrón CQRS (Command Query Responsibility Segregation)
 *
 * **Principio fundamental:**
 * - Separar operaciones de escritura (comandos) de operaciones de lectura (consultas)
 * - Permitir diferentes modelos para leer y escribir datos
 *
 * **Beneficios:**
 * - Optimización independiente de lectura/escritura
 * - Mejor escalabilidad en sistemas complejos
 * - Simplificación de lógica de dominio
 */
class CQRSExample {
    data class Account(val id: Int, val owner: String, var balance: Double)
    interface CommandHandler<T> {
        fun handle(command: T)
    }

    interface QueryHandler<T, R> {
        fun query(query: T): R
    }

    data class CreateAccountCommand(val id: Int, val owner: String, val initialBalance: Double)
    data class GetAccountQuery(val id: Int)
    class AccountCommandHandler : CommandHandler<CreateAccountCommand> {
        private val accounts = mutableMapOf<Int, Account>()
        override fun handle(command: CreateAccountCommand) {
            accounts[command.id] = Account(
                command.id,
                command.owner,
                command.initialBalance
            )
        }
    }

    class AccountQueryHandler : QueryHandler<GetAccountQuery, Account?> {
        private val accounts = mutableMapOf<Int, Account>()
        override fun query(query: GetAccountQuery): Account? {
            return accounts[query.id]
        }
    }

    fun runExample() {
        val commandHandler = AccountCommandHandler()
        val queryHandler = AccountQueryHandler()

        // Ejecutar comando (escribir)
        commandHandler.handle(CreateAccountCommand(200, "Ana", 1000.0))

        // Ejecutar query (leer)
        val account = queryHandler.query(GetAccountQuery(200))
        println("CQRS - Account balance: ${account?.balance}")
    }
}