/**
 * **Patrones Creacionales**: Patrones que se enfocan en mecanismos de creación de objetos.
 */
object CreationalPatterns {
    /**
     * **Singleton**: Asegura que una clase tenga solo una instancia y proporciona un punto de acceso global.
     */
    object Singleton {
        fun doSomething() {
            println("Singleton: Doing something...")
        }
    }

    /**
     * **Factory**: Encapsula la creación de objetos proporcionando una interfaz común.
     */
    object Factory {
        interface Animal {
            fun speak()
        }

        class Dog : Animal {
            override fun speak() = println("Dog: Woof!")
        }

        class Cat : Animal {
            override fun speak() = println("Cat: Meow!")
        }

        object AnimalFactory {
            fun createAnimal(animalType: String): Animal = when (animalType) {
                "dog" -> Dog()
                "cat" -> Cat()
                else -> throw IllegalArgumentException("Invalid animal type")
            }
        }
    }

    /**
     * **Builder**: Construye objetos complejos paso a paso separando construcción de representación.
     */
    object Builder {
        class Product private constructor(
            val param1: String,
            val param2: Int,
            val param3: Boolean
        ) {
            class Builder(
                private var param1: String,
                private var param2: Int = 0,
                private var param3: Boolean = false
            ) {
                fun setParam2(value: Int) = apply { param2 = value }
                fun setParam3(value: Boolean) = apply { param3 = value }
                fun build() = Product(param1, param2, param3)
            }
        }
    }

    /**
     * **Prototype**: Crea objetos duplicados clonando instancias existentes.
     */
    object Prototype {
        data class Person(val name: String, val age: Int) {
            fun clone() = copy()
        }
    }

    /**
     * **ObjectPool**: Reutiliza objetos preinicializados en lugar de crearlos continuamente.
     */
    object ObjectPool {
        class Connection {
            init {
                println("Creating connection")
            }

            fun executeQuery(query: String) = println("Executing: $query")
            fun close() = println("Closing connection")
        }

        class ConnectionPool(private val maxSize: Int) {
            private val pool = mutableListOf<Connection>()

            init {
                repeat(maxSize) { pool.add(Connection()) }
            }

            fun acquire(): Connection? = if (pool.isNotEmpty()) pool.removeAt(0) else null
            fun release(conn: Connection) {
                if (pool.size < maxSize) pool.add(conn)
            }
        }
    }

    /**
     * **AbstractFactory**: Crea familias de objetos relacionados sin especificar clases concretas.
     */
    object AbstractFactory {
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

        interface Button {
            fun paint()
        }

        interface Checkbox {
            fun paint()
        }

        class WinButton : Button {
            override fun paint() = println("Windows button")
        }

        class MacButton : Button {
            override fun paint() = println("MacOS button")
        }

        class WinCheckbox : Checkbox {
            override fun paint() = println("Windows checkbox")
        }

        class MacCheckbox : Checkbox {
            override fun paint() = println("MacOS checkbox")
        }
    }

    /**
     * **DependencyInjection**: Inyecta dependencias externamente en lugar de crearlas internamente.
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
                logger.log("Registering: $user")
            }
        }
    }
}

/**
 * **Patrones Estructurales**: Patrones que tratan sobre composición de clases y objetos.
 */
object StructuralPatterns {
    /**
     * **Decorator**: Añade funcionalidades adicionales a objetos dinámicamente.
     */
    object Decorator {
        interface Coffee {
            fun cost(): Double
        }

        class SimpleCoffee : Coffee {
            override fun cost() = 2.0
        }

        class MilkDecorator(private val coffee: Coffee) : Coffee {
            override fun cost() = coffee.cost() + 1.0
        }
    }

    /**
     * **Adapter**: Permite colaboración entre interfaces incompatibles.
     */
    object Adapter {
        interface MediaPlayer {
            fun play(file: String)
        }

        class LegacyPlayer {
            fun playFile(file: String) = println("Playing: $file")
        }

        class PlayerAdapter : MediaPlayer {
            private val legacyPlayer = LegacyPlayer()
            override fun play(file: String) = legacyPlayer.playFile(file)
        }
    }

    /**
     * **Facade**: Proporciona una interfaz simplificada a un subsistema complejo.
     */
    object Facade {
        class CPU {
            fun start() = println("CPU started")
        }

        class Memory {
            fun load() = println("Memory loaded")
        }

        class ComputerFacade {
            private val cpu = CPU()
            private val memory = Memory()

            fun start() {
                cpu.start()
                memory.load()
            }
        }
    }

    /**
     * **Proxy**: Controla el acceso a un objeto actuando como intermediario.
     */
    object Proxy {
        interface Image {
            fun display()
        }

        class RealImage(private val file: String) : Image {
            init {
                loadFromDisk()
            }

            private fun loadFromDisk() = println("Loading: $file")
            override fun display() = println("Displaying: $file")
        }

        class ProxyImage(private val file: String) : Image {
            private var realImage: RealImage? = null
            override fun display() {
                if (realImage == null) realImage = RealImage(file)
                realImage?.display()
            }
        }
    }

    /**
     * **Bridge**: Separa abstracción de implementación permitiendo variación independiente.
     */
    object Bridge {
        interface Renderer {
            fun renderShape()
        }

        class VectorRenderer : Renderer {
            override fun renderShape() = println("Rendering vector")
        }

        class RasterRenderer : Renderer {
            override fun renderShape() = println("Rendering raster")
        }

        abstract class Shape(protected val renderer: Renderer) {
            abstract fun draw()
        }

        class Circle(renderer: Renderer) : Shape(renderer) {
            override fun draw() {
                print("Drawing circle: ")
                renderer.renderShape()
            }
        }
    }

    /**
     * **Module**: Organiza código en módulos independientes con responsabilidades claras.
     */
    object Module {
        // Módulo de autenticación
        object AuthModule {
            fun login(user: String) = println("User $user logged in")
            fun logout(user: String) = println("User $user logged out")
        }

        // Módulo de base de datos
        object DatabaseModule {
            fun query(sql: String) = println("Executing: $sql")
        }
    }

    /**
     * **Composite**: Trata objetos individuales y composiciones de manera uniforme.
     */
    object Composite {
        interface Graphic {
            fun draw()
        }

        class Circle : Graphic {
            override fun draw() = println("Drawing circle")
        }

        class GraphicGroup : Graphic {
            private val graphics = mutableListOf<Graphic>()
            fun add(graphic: Graphic) = graphics.add(graphic)
            override fun draw() = graphics.forEach { it.draw() }
        }
    }

    /**
     * **Flyweight**: Reduce memoria compartiendo estados intrínsecos entre objetos.
     */
    object Flyweight {
        data class TreeType(val name: String, val color: String)

        class TreeFactory {
            private val treeTypes = mutableMapOf<String, TreeType>()
            fun getTreeType(name: String, color: String): TreeType {
                return treeTypes.getOrPut("$name-$color") { TreeType(name, color) }
            }
        }

        class Tree(private val x: Int, private val y: Int, private val type: TreeType) {
            fun draw() = println("Drawing $type at ($x, $y)")
        }
    }
}

/**
 * **Patrones de Comportamiento**: Patrones que tratan sobre comunicación entre objetos.
 */
object BehavioralPatterns {
    /**
     * **Observer**: Notifica automáticamente a objetos dependientes de cambios de estado.
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
     * **Strategy**: Define algoritmos intercambiables que pueden seleccionarse en tiempo de ejecución.
     */
    object Strategy {
        interface CompressionStrategy {
            fun compress(file: String)
        }

        class ZipStrategy : CompressionStrategy {
            override fun compress(file: String) = println("Zipping: $file")
        }

        class RarStrategy : CompressionStrategy {
            override fun compress(file: String) = println("RAR-ing: $file")
        }

        class Compressor(private var strategy: CompressionStrategy) {
            fun setStrategy(newStrategy: CompressionStrategy) {
                strategy = newStrategy
            }

            fun executeCompression(file: String) = strategy.compress(file)
        }
    }

    /**
     * **Command**: Encapsula solicitudes como objetos permitiendo parametrizar operaciones.
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
     * **ChainOfResponsibility**: Pasa solicitudes por una cadena de manejadores potenciales.
     */
    object ChainOfResponsibility {
        abstract class Handler {
            var next: Handler? = null
            abstract fun handle(request: Int)
            protected fun forward(request: Int) {
                next?.handle(request)
            }
        }

        class NegativeHandler : Handler() {
            override fun handle(request: Int) {
                if (request < 0) println("Handled negative: $request")
                else forward(request)
            }
        }
    }

    /**
     * **Interpreter**: Define una gramática para interpretar expresiones en un lenguaje.
     */
    object Interpreter {
        interface Expression {
            fun interpret(): Boolean
        }

        class TerminalExpression(private val data: String) : Expression {
            override fun interpret() = data.contains("hello")
        }

        class OrExpression(private val expr1: Expression, private val expr2: Expression) : Expression {
            override fun interpret() = expr1.interpret() || expr2.interpret()
        }
    }

    /**
     * **TemplateMethod**: Define el esqueleto de un algoritmo delegando pasos a subclases.
     */
    object TemplateMethod {
        abstract class Game {
            abstract fun initialize()
            abstract fun startPlay()
            fun play() {
                initialize()
                startPlay()
            }
        }

        class Chess : Game() {
            override fun initialize() = println("Setting up chess")
            override fun startPlay() = println("Starting chess game")
        }
    }

    /**
     * **Iterator**: Proporciona acceso secuencial a elementos de una colección.
     */
    object IteratorPattern {
        class NameIterator(private val names: Array<String>) : Iterator<String> {
            private var index = 0
            override fun hasNext() = index < names.size
            override fun next() = names[index++]
        }
    }

    /**
     * **Memento**: Captura y restaura el estado interno de un objeto sin violar encapsulación.
     */
    object Memento {
        class EditorMemento(private val content: String) {
            fun getSavedContent() = content
        }

        class TextEditor {
            private var content = ""
            fun write(text: String) {
                content += text
            }

            fun save() = EditorMemento(content)
            fun restore(memento: EditorMemento) {
                content = memento.getSavedContent()
            }
        }
    }

    /**
     * **Mediator**: Centraliza comunicación compleja entre objetos.
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
     * **Visitor**: Añade nuevas operaciones a objetos sin modificar sus clases.
     */
    object Visitor {
        interface ReportVisitor {
            fun visit(contract: FixedPriceContract)
        }

        class CostReportVisitor : ReportVisitor {
            override fun visit(contract: FixedPriceContract) = println("Fixed cost: ${contract.costPerYear}")
        }

        class FixedPriceContract(val costPerYear: Long)
    }

    /**
     * **State**: Permite que un objeto altere su comportamiento al cambiar su estado interno.
     */
    object State {
        interface State {
            fun handle(context: Context)
        }

        class Context(var state: State) {
            fun request() = state.handle(this)
        }

        class ConcreteStateA : State {
            override fun handle(context: Context) {
                println("Handling in State A")
                context.state = ConcreteStateB()
            }
        }
    }
}