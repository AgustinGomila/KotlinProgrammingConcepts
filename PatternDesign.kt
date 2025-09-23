/**
 * **CATEGORÍAS DE PATRONES**
 *
 * 1. Creacionales: Patrones para creación de objetos
 * 2. Estructurales: Patrones para composición de objetos
 * 3. Comportamiento: Patrones para comunicación entre objetos
 * 4. Arquitectónicos: Patrones para estructura de sistemas
 */
object DesignPatternsCatalog {

    // ==================================================
    // PATRONES CREACIONALES
    // ==================================================
    object CreationalPatterns {
        /**
         * **Singleton**: Garantiza una única instancia global
         */
        object Singleton {
            private var instance: Singleton? = null

            fun getInstance(): Singleton {
                return instance ?: synchronized(this) {
                    instance ?: Singleton().also { instance = it }
                }
            }
        }

        /**
         * **Factory**: Encapsula creación de objetos relacionados
         */
        object Factory {
            interface Vehicle {
                fun drive()
            }

            class Car : Vehicle {
                override fun drive() = println("Driving car")
            }

            class Truck : Vehicle {
                override fun drive() = println("Driving truck")
            }

            object VehicleFactory {
                fun createVehicle(type: String): Vehicle = when (type) {
                    "car" -> Car()
                    "truck" -> Truck()
                    else -> throw IllegalArgumentException("Invalid type")
                }
            }
        }

        /**
         * **Builder**: Construye objetos complejos paso a paso
         */
        object Builder {
            data class Computer(
                val cpu: String,
                val ram: Int,
                val storage: Int,
                val gpu: String?
            )

            class ComputerBuilder {
                private var cpu: String = "i5"
                private var ram: Int = 8
                private var storage: Int = 256
                private var gpu: String? = null

                fun setCpu(cpu: String) = apply { this.cpu = cpu }
                fun setRam(ram: Int) = apply { this.ram = ram }
                fun setStorage(storage: Int) = apply { this.storage = storage }
                fun setGpu(gpu: String) = apply { this.gpu = gpu }
                fun build() = Computer(cpu, ram, storage, gpu)
            }
        }

        /**
         * **Prototype**: Crea nuevos objetos clonando existentes
         */
        object Prototype {
            abstract class Shape : Cloneable {
                var id: String? = null
                var type: String? = null

                abstract fun draw()

                public override fun clone(): Any {
                    return super.clone()
                }
            }

            class Rectangle : Shape() {
                override fun draw() = println("Drawing Rectangle")
            }
        }

        /**
         * **ObjectPool**: Reutiliza objetos costosos en creación
         */
        object ObjectPool {
            class DatabaseConnection {
                init {
                    println("Creating DB connection (expensive operation)")
                }

                fun query(sql: String) = println("Executing: $sql")
            }

            class ConnectionPool(private val size: Int) {
                private val available = mutableListOf<DatabaseConnection>()
                private val inUse = mutableListOf<DatabaseConnection>()

                init {
                    repeat(size) { available.add(DatabaseConnection()) }
                }

                fun acquire(): DatabaseConnection {
                    if (available.isEmpty()) {
                        println("Creating extra connection")
                        return DatabaseConnection()
                    }
                    val conn = available.removeAt(0)
                    inUse.add(conn)
                    return conn
                }

                fun release(conn: DatabaseConnection) {
                    inUse.remove(conn)
                    if (available.size < size) available.add(conn)
                }
            }
        }

        /**
         * **AbstractFactory**: Crea familias de objetos relacionados
         */
        object AbstractFactory {
            interface Button {
                fun render()
            }

            interface Checkbox {
                fun render()
            }

            // Windows Family
            class WinButton : Button {
                override fun render() = println("Windows button")
            }

            class WinCheckbox : Checkbox {
                override fun render() = println("Windows checkbox")
            }

            // MacOS Family
            class MacButton : Button {
                override fun render() = println("macOS button")
            }

            class MacCheckbox : Checkbox {
                override fun render() = println("macOS checkbox")
            }

            interface GUIFactory {
                fun createButton(): Button
                fun createCheckbox(): Checkbox
            }

            class WinFactory : GUIFactory {
                override fun createButton() = WinButton()
                override fun createCheckbox() = WinCheckbox()
            }

            class MacFactory : GUIFactory {
                override fun createButton() = MacButton()
                override fun createCheckbox() = MacCheckbox()
            }
        }

        /**
         * **DependencyInjection**: Inyecta dependencias externamente
         */
        object DependencyInjection {
            interface Logger {
                fun log(message: String)
            }

            class ConsoleLogger : Logger {
                override fun log(message: String) = println(message)
            }

            class UserService(private val logger: Logger) {
                fun register(user: String) {
                    logger.log("Registering user: $user")
                }
            }
        }
    }

    // ==================================================
    // PATRONES ESTRUCTURALES
    // ==================================================
    object StructuralPatterns {
        /**
         * **Decorator**: Añade funcionalidades dinámicamente
         */
        object Decorator {
            interface Coffee {
                fun cost(): Double
            }

            class SimpleCoffee : Coffee {
                override fun cost() = 2.0
            }

            class MilkDecorator(private val coffee: Coffee) : Coffee {
                override fun cost() = coffee.cost() + 0.5
            }

            class SugarDecorator(private val coffee: Coffee) : Coffee {
                override fun cost() = coffee.cost() + 0.2
            }
        }

        /**
         * **Adapter**: Convierte interfaces incompatibles
         */
        object Adapter {
            interface ModernPrinter {
                fun printDocument(content: String)
            }

            class LegacyPrinter {
                fun print(text: String) = println("Legacy printing: $text")
            }

            class PrinterAdapter(private val legacyPrinter: LegacyPrinter) : ModernPrinter {
                override fun printDocument(content: String) {
                    legacyPrinter.print(content)
                }
            }
        }

        /**
         * **Facade**: Simplifica interfaces complejas
         */
        object Facade {
            class CPU {
                fun process() = println("Processing data")
            }

            class Memory {
                fun load() = println("Loading memory")
            }

            class HardDrive {
                fun read() = println("Reading disk")
            }

            class ComputerFacade {
                private val cpu = CPU()
                private val memory = Memory()
                private val hdd = HardDrive()

                fun start() {
                    memory.load()
                    hdd.read()
                    cpu.process()
                }
            }
        }

        /**
         * **Proxy**: Controla acceso a objetos
         */
        object Proxy {
            interface Image {
                fun display()
            }

            class RealImage(private val filename: String) : Image {
                init {
                    loadFromDisk()
                }

                private fun loadFromDisk() = println("Loading $filename")
                override fun display() = println("Displaying $filename")
            }

            class ImageProxy(private val filename: String) : Image {
                private var realImage: RealImage? = null

                override fun display() {
                    if (realImage == null) realImage = RealImage(filename)
                    realImage!!.display()
                }
            }
        }

        /**
         * **Bridge**: Separa abstracción de implementación
         */
        object Bridge {
            interface Renderer {
                fun renderCircle(radius: Int)
            }

            class VectorRenderer : Renderer {
                override fun renderCircle(radius: Int) =
                    println("Drawing circle of radius $radius with vectors")
            }

            class RasterRenderer : Renderer {
                override fun renderCircle(radius: Int) =
                    println("Drawing circle of radius $radius with pixels")
            }

            abstract class Shape(protected val renderer: Renderer) {
                abstract fun draw()
            }

            class Circle(private val radius: Int, renderer: Renderer) : Shape(renderer) {
                override fun draw() = renderer.renderCircle(radius)
            }
        }

        /**
         * **Module**: Organiza código en módulos
         */
        object Module {
            object MathUtils {
                fun sum(a: Int, b: Int) = a + b
                fun factorial(n: Int): Int = if (n <= 1) 1 else n * factorial(n - 1)
            }

            object StringUtils {
                fun reverse(s: String) = s.reversed()
                fun isPalindrome(s: String) = s.equals(s.reversed(), ignoreCase = true)
            }
        }

        /**
         * **Composite**: Trata objetos individuales y compuestos uniformemente
         */
        object Composite {
            interface Graphic {
                fun draw()
            }

            class Circle : Graphic {
                override fun draw() = println("Drawing circle")
            }

            class CompositeGraphic : Graphic {
                private val children = mutableListOf<Graphic>()

                fun add(graphic: Graphic) = children.add(graphic)
                fun remove(graphic: Graphic) = children.remove(graphic)

                override fun draw() {
                    println("Composite:")
                    children.forEach { it.draw() }
                }
            }
        }

        /**
         * **Flyweight**: Comparte objetos para reducir memoria
         */
        object Flyweight {
            data class TreeType(val name: String, val color: String)

            class TreeFactory {
                private val treeTypes = mutableMapOf<String, TreeType>()

                fun getTreeType(name: String, color: String): TreeType {
                    return treeTypes.getOrPut("$name-$color") { TreeType(name, color) }
                }
            }

            class Tree(
                private val x: Int,
                private val y: Int,
                private val type: TreeType
            ) {
                fun draw() = println("Drawing ${type.name} at ($x, $y)")
            }
        }
    }

    // ==================================================
    // PATRONES DE COMPORTAMIENTO
    // ==================================================
    object BehavioralPatterns {
        /**
         * **Observer**: Notifica cambios a dependientes
         */
        object Observer {
            interface EventListener {
                fun update(event: String)
            }

            class EventManager {
                private val listeners = mutableMapOf<String, MutableList<EventListener>>()

                fun subscribe(eventType: String, listener: EventListener) {
                    listeners.getOrPut(eventType) { mutableListOf() }.add(listener)
                }

                fun notify(eventType: String, data: String) {
                    listeners[eventType]?.forEach { it.update(data) }
                }
            }
        }

        /**
         * **Strategy**: Algoritmos intercambiables
         */
        object Strategy {
            interface Compression {
                fun compress(file: String)
            }

            class ZipCompression : Compression {
                override fun compress(file: String) = println("Compressing $file to ZIP")
            }

            class RarCompression : Compression {
                override fun compress(file: String) = println("Compressing $file to RAR")
            }

            class Compressor(private var strategy: Compression) {
                fun setStrategy(strategy: Compression) {
                    this.strategy = strategy
                }

                fun compressFile(file: String) = strategy.compress(file)
            }
        }

        /**
         * **Command**: Encapsula solicitudes como objetos
         */
        object Command {
            interface Command {
                fun execute()
            }

            class Light {
                fun on() = println("Light on")
            }

            class LightOnCommand(private val light: Light) : Command {
                override fun execute() = light.on()
            }

            class RemoteControl {
                fun submit(command: Command) = command.execute()
            }
        }

        /**
         * **ChainOfResponsibility**: Encadena manejadores
         */
        object ChainOfResponsibility {
            abstract class Handler {
                var next: Handler? = null
                abstract fun handle(request: Int)
                protected fun forward(request: Int) {
                    next?.handle(request)
                }
            }

            class PositiveHandler : Handler() {
                override fun handle(request: Int) {
                    if (request > 0) println("Positive handler: $request")
                    else forward(request)
                }
            }
        }

        /**
         * **Interpreter**: Evalúa expresiones en lenguaje
         */
        object Interpreter {
            interface Expression {
                fun interpret(): Int
            }

            class Number(private val value: Int) : Expression {
                override fun interpret() = value
            }

            class Add(private val left: Expression, private val right: Expression) : Expression {
                override fun interpret() = left.interpret() + right.interpret()
            }
        }

        /**
         * **TemplateMethod**: Define esqueleto de algoritmo
         */
        object TemplateMethod {
            abstract class Game {
                abstract fun initialize()
                abstract fun startPlay()
                abstract fun endPlay()

                fun play() {
                    initialize()
                    startPlay()
                    endPlay()
                }
            }

            class Chess : Game() {
                override fun initialize() = println("Chess initialized")
                override fun startPlay() = println("Chess started")
                override fun endPlay() = println("Chess finished")
            }
        }

        /**
         * **Iterator**: Acceso secuencial a colecciones
         */
        object IteratorPattern {
            class BookCollection(private val books: List<String>) {
                fun createIterator() = BookIterator(books)
            }

            class BookIterator(private val books: List<String>) : Iterator<String> {
                private var index = 0
                override fun hasNext() = index < books.size
                override fun next() = books[index++]
            }
        }

        /**
         * **Memento**: Captura y restaura estado interno
         */
        object Memento {
            class Editor {
                private var content = ""

                fun write(text: String) {
                    content += text
                }

                fun getContent() = content

                fun save() = EditorMemento(content)
                fun restore(memento: EditorMemento) {
                    content = memento.getState()
                }
            }

            class EditorMemento(private val state: String) {
                fun getState() = state
            }
        }

        /**
         * **Mediator**: Centraliza comunicación compleja
         */
        object Mediator {
            class ChatUser(private val mediator: ChatMediator, val name: String) {
                fun send(message: String) = mediator.sendMessage(this, message)
                fun receive(message: String) = println("$name received: $message")
            }

            class ChatMediator {
                private val users = mutableListOf<ChatUser>()
                fun addUser(user: ChatUser) = users.add(user)
                fun sendMessage(sender: ChatUser, message: String) {
                    users.filter { it != sender }.forEach { it.receive("${sender.name}: $message") }
                }
            }
        }

        /**
         * **Visitor**: Añade operaciones sin modificar clases
         */
        object Visitor {
            interface ReportVisitor<T> {
                fun visit(element: Element): T
            }

            interface Element {
                fun <T> accept(visitor: ReportVisitor<T>): T
            }

            class FixedPriceContract(val costPerYear: Long) : Element {
                override fun <T> accept(visitor: ReportVisitor<T>) = visitor.visit(this)
            }

            class CostReportVisitor : ReportVisitor<String> {
                override fun visit(element: FixedPriceContract) = "Cost: ${element.costPerYear}"
            }
        }

        /**
         * **State**: Cambia comportamiento con estado interno
         */
        object State {
            interface State {
                fun handle(context: Context)
            }

            class Context(var state: State) {
                fun request() = state.handle(this)
            }

            class StateA : State {
                override fun handle(context: Context) {
                    println("Handling in State A")
                    context.state = StateB()
                }
            }

            class StateB : State {
                override fun handle(context: Context) {
                    println("Handling in State B")
                    context.state = StateA()
                }
            }
        }

        /**
         * **NullObject**: Objeto predeterminado para evitar nulos
         */
        object NullObject {
            interface Logger {
                fun log(message: String)
            }

            class ConsoleLogger : Logger {
                override fun log(message: String) = println("LOG: $message")
            }

            class NullLogger : Logger {
                override fun log(message: String) { /* No hace nada */
                }
            }

            class Service(private val logger: Logger) {
                fun doOperation() {
                    logger.log("Operation started")
                    // ... lógica
                }
            }
        }
    }

    // ==================================================
    // PATRONES ARQUITECTÓNICOS
    // ==================================================
    object ArchitecturalPatterns {
        /**
         * **Repository**: Abstracción de acceso a datos
         */
        object RepositoryPattern {
            interface UserRepository {
                fun findById(id: Int): User?
                fun save(user: User)
            }

            class InMemoryUserRepository : UserRepository {
                private val users = mutableMapOf<Int, User>()

                override fun findById(id: Int) = users[id]
                override fun save(user: User) {
                    users[user.id] = user
                }
            }

            data class User(val id: Int, val name: String, val email: String)

            class UserService(private val userRepository: UserRepository) {
                fun getUserEmail(id: Int) = userRepository.findById(id)?.email
            }
        }

        /**
         * **CQRS**: Separa comandos (escritura) de queries (lectura)
         */
        object CQRS {
            // Commands
            interface CommandHandler<TCommand> {
                fun handle(command: TCommand)
            }

            class CreateUserCommand(val name: String, val email: String)
            class CreateUserHandler : CommandHandler<CreateUserCommand> {
                override fun handle(command: CreateUserCommand) {
                    println("Creating user: ${command.name}")
                    // Lógica de creación
                }
            }

            // Queries
            interface QueryHandler<TQuery, TResult> {
                fun handle(query: TQuery): TResult
            }

            class GetUserEmailQuery(val userId: Int)
            class GetUserEmailHandler : QueryHandler<GetUserEmailQuery, String?> {
                override fun handle(query: GetUserEmailQuery): String? {
                    // Lógica para obtener email
                    return "user${query.userId}@example.com"
                }
            }

            // Bus
            class CommandQueryBus {
                private val handlers = mutableMapOf<Class<*>, Any>()

                fun <TCommand> registerHandler(
                    commandType: Class<TCommand>,
                    handler: CommandHandler<TCommand>
                ) {
                    handlers[commandType] = handler
                }

                fun <TCommand> send(command: TCommand) {
                    val handler = handlers[command!!::class.java] as CommandHandler<TCommand>
                    handler.handle(command)
                }
            }
        }
    }
}