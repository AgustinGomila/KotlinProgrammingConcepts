@file:Suppress("unused")

import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.BlockingQueue


/**
 * **Patrón Creacional [Singleton]**: Es uno de los patrones más utilizados y ampliamente conocidos, ya que es muy común necesitar una única instancia global de una clase en todo el sistema.
 *
 * **Patrón Creacional [Factory]**: Es otro patrón muy común que se utiliza para encapsular la creación de objetos y proporcionar una interfaz común para crear diferentes tipos de objetos.
 *
 * **Patrón Creacional [Builder]**: Es comúnmente utilizado cuando se necesita construir objetos complejos paso a paso y separar la construcción de la representación final del objeto.
 *
 * **Patrón Creacional [Prototype]**: Es utilizado cuando se necesita crear objetos duplicados o clonar objetos existentes sin depender de sus clases concretas.
 *
 * **Patrón Creacional [ObjectPool]**: Permite crear un grupo (pool) de objetos previamente inicializados y disponibles para ser reutilizados, en lugar de crear y destruir objetos continuamente.
 *
 * **Patrón Creacional [AbstractFactory]**: Proporciona una interfaz para crear familias de objetos relacionados sin especificar sus clases concretas.
 *
 * **Patrón Creacional [DependencyInjection]**: Se utiliza para manejar las dependencias entre componentes de un sistema.
 * En lugar de que un componente cree o busque sus dependencias, estas se "inyectan" desde el exterior.
 *
 * **Patrón Estructural [Decorator]**: Es frecuentemente utilizado para agregar responsabilidades adicionales a objetos individuales de manera dinámica, sin modificar su estructura.
 *
 * **Patrón Estructural [Adapter]**: Es útil para permitir que clases con interfaces incompatibles trabajen juntas, proporcionando una interfaz que el cliente espera.
 *
 * **Patrón Estructural [Facade]**: Es útil cuando tienes un sistema complejo con muchas partes internas y deseas proporcionar una interfaz simplificada y unificada para interactuar con ese sistema.
 *
 * **Patrón Estructural [Proxy]**: Es utilizado para controlar el acceso a un objeto y agregar funcionalidad adicional antes o después de acceder al objeto real.
 *
 * **Patrón Estructural [Bridge]**: Se utiliza para separar una abstracción de su implementación, permitiendo que ambas varíen de manera independiente.
 *
 * **Patrón Estructural [Module]**: Se centra en dividir un sistema en partes interconectadas y desacopladas llamadas módulos, donde cada módulo tiene su propia responsabilidad y actúa como una entidad independiente.
 *
 * **Patrón Estructural [Composite]**: Es utilizado cuando se necesita tratar tanto objetos individuales como grupos de objetos de manera uniforme, simplificando la manipulación de estructuras jerárquicas.
 *
 * **Patrón Estructural [Flyweight]**: Es menos común en comparación con los anteriores, ya que su utilidad se ve más claramente en situaciones con gran cantidad de objetos similares para reducir la sobrecarga de memoria.
 *
 * **Patrón de Comportamiento [Observer]**: Es muy utilizado en situaciones donde hay objetos que necesitan estar sincronizados y actualizados automáticamente cuando otros objetos cambian su estado.
 *
 * **Patrón de Comportamiento [Strategy]**: Es aplicado cuando se necesita definir una familia de algoritmos intercambiables y permitir que el cliente seleccione la implementación concreta en tiempo de ejecución.
 *
 * **Patrón de Comportamiento [Command]**: Se utiliza para encapsular una solicitud como un objeto, permitiendo parametrizar los clientes con diferentes solicitudes, encolar o registrar las solicitudes y realizar operaciones reversibles.
 *
 * **Patrón de Comportamiento [ChainOfResponsibility]**: Es comúnmente utilizado cuando hay una serie de objetos que pueden manejar una solicitud y se desea evitar el acoplamiento directo entre el remitente de la solicitud y los receptores.
 *
 * **Patrón de Comportamiento [Interpreter]**: Se utiliza para definir una gramática para interpretar oraciones o expresiones de un lenguaje específico.
 *
 * **Patrón de Comportamiento [TemplateMethod]**: Define el esqueleto de un algoritmo en un método, pero delega algunos pasos a las subclases.
 * Permite que las subclases redefinan ciertos pasos del algoritmo sin cambiar su estructura general.
 *
 * **Patrón de Comportamiento [IteratorPattern]**: Se utiliza para proporcionar una forma de acceder secuencialmente a los elementos de una colección sin exponer su representación interna.
 *
 * **Patrón de Comportamiento [Memento]**: Se utiliza para capturar y restaurar el estado interno de un objeto sin violar su encapsulación.
 *
 * **Patrón de Comportamiento [Mediator]**: Se utiliza para reducir las dependencias entre objetos, permitiendo que los objetos se comuniquen entre sí a través de un objeto mediador central.
 *
 * **Patrón de Comportamiento [Visitor]**: Se utiliza para agregar operaciones adicionales a objetos sin modificar su estructura.
 *
 * **Patrón de Comportamiento [State]**: Permite que un objeto altere su comportamiento cuando su estado interno cambia.
 */
data class DesignPatterns(val name: String)


/**
 * **Singleton**
 *
 * El patrón Singleton **se utiliza para asegurar que una clase tenga solo una instancia y proporcionar un punto de acceso global a esa instancia**.
 * El patrón Singleton es útil en situaciones donde necesitas una única instancia de una clase que debe ser compartida por todo el sistema, como configuraciones globales, conexiones a bases de datos, registros, entre otros.
 */
object Singleton {
    object Singleton {
        fun doSomething() {
            println("Singleton: Doing something...")
        }
    }
}


/**
 * **Factory**
 *
 * El patrón Factory **se utiliza para encapsular la creación de objetos, proporcionando una interfaz común para crear diferentes tipos de objetos sin exponer la lógica de creación**.
 * El patrón Factory es útil cuando necesitas crear objetos de una clase específica, pero no conoces la implementación concreta que debes instanciar hasta tiempo de ejecución. Esto permite el uso de una interfaz común para la creación de objetos, lo que facilita el intercambio de implementaciones y mejora la flexibilidad del código.
 */
object Factory {
    interface Animal {
        fun speak()
    }

    class Dog : Animal {
        override fun speak() {
            println("Dog: Woof!")
        }
    }

    class Cat : Animal {
        override fun speak() {
            println("Cat: Meow!")
        }
    }

    object AnimalFactory {
        fun createAnimal(animalType: String): Animal {
            return when (animalType) {
                "dog" -> Dog()
                "cat" -> Cat()
                else -> throw IllegalArgumentException("Invalid animal type")
            }
        }
    }
}


/**
 * **Abstract factory**
 *
 * El patrón Abstract Factory es un patrón creacional que **proporciona una interfaz para crear familias de objetos relacionados sin especificar sus clases concretas**.
 * Este patrón abstrae la creación de objetos y permite que una fábrica concreta produzca objetos que se ajusten a una cierta jerarquía o interfaz común.
 * El patrón Abstract Factory es útil cuando se necesita crear familias de objetos relacionados o productos que deben ser utilizados juntos de manera coherente.
 * Es especialmente útil cuando un sistema debe ser independiente de cómo se crean, compuestos y representan sus objetos.
 */
object AbstractFactory {
    // Abstract Factory
    interface FactionFactory {
        fun createBuilding(): Building
        fun createUnit(): Unit
    }

    // Concrete Factory for Humans
    class HumanFactionFactory : FactionFactory {
        override fun createBuilding(): Building {
            return HumanBuilding()
        }

        override fun createUnit(): Unit {
            return HumanUnit()
        }
    }

    // Concrete Factory for Orcs
    class OrcFactionFactory : FactionFactory {
        override fun createBuilding(): Building {
            return OrcBuilding()
        }

        override fun createUnit(): Unit {
            return OrcUnit()
        }
    }

    // Abstract Product: Building
    interface Building {
        fun produceUnit()
    }

    // Concrete Product: Human Building
    class HumanBuilding : Building {
        override fun produceUnit() {
            println("Human Building produces Human Unit.")
        }
    }

    // Concrete Product: Orc Building
    class OrcBuilding : Building {
        override fun produceUnit() {
            println("Orc Building produces Orc Unit.")
        }
    }

    // Abstract Product: Unit
    interface Unit {
        fun attack()
    }

    // Concrete Product: Human Unit
    class HumanUnit : Unit {
        override fun attack() {
            println("Human Unit attacks with a sword.")
        }
    }

    // Concrete Product: Orc Unit
    class OrcUnit : Unit {
        override fun attack() {
            println("Orc Unit attacks with an axe.")
        }
    }

    // Client (Game)
    fun main() {
        // Selección de la facción
        val factionFactory: FactionFactory = HumanFactionFactory()

        // Creación de los edificios y unidades
        val building: Building = factionFactory.createBuilding()
        val unit: Unit = factionFactory.createUnit()

        // Interacción con los objetos creados
        building.produceUnit()
        unit.attack()
    }
}


/**
 * **Observer**
 *
 * El patrón Observer **se utiliza para establecer una relación de uno a muchos objetos, de modo que cuando un objeto cambia su estado, todos sus observadores sean notificados y actualizados automáticamente**.
 * El patrón Observer es útil cuando necesitas mantener una sincronización entre objetos dependientes y asegurar que los cambios en uno se reflejen en otros. Es ampliamente utilizado en situaciones como eventos y notificaciones, interfaces gráficas de usuario y cualquier escenario donde un objeto necesita informar a múltiples observadores sobre cambios de estado.
 */
object Observer {
    interface Observer {
        fun update(message: String)
    }

    class ConcreteObserver(private val name: String) : Observer {
        override fun update(message: String) {
            println("$name received message: $message")
        }
    }

    class Subject {
        private val observers = mutableListOf<Observer>()

        fun addObserver(observer: Observer) {
            observers.add(observer)
        }

        fun removeObserver(observer: Observer) {
            observers.remove(observer)
        }

        fun notifyObservers(message: String) {
            for (observer in observers) {
                observer.update(message)
            }
        }
    }
}


/**
 * **Builder**
 *
 * El patrón Builder **se utiliza para construir objetos complejos paso a paso, separando la construcción de la representación final del objeto**.
 * El patrón Builder es útil cuando tienes objetos con muchos atributos configurables y opciones diversas de inicialización, evitando tener múltiples constructores con combinaciones de argumentos diferentes. Es especialmente útil cuando deseas mantener la inmutabilidad del objeto final y permitir una construcción más legible y flexible.
 */
object Builder {
    class Product private constructor(
        val param1: String, val param2: Int, val param3: Boolean
    ) {
        data class Builder(
            var param1: String, var param2: Int = 0, var param3: Boolean = false
        ) {
            fun build() = Product(param1, param2, param3)
        }

        companion object {
            fun create(param1: String) = Builder(param1)
        }
    }
}


/**
 * **Decorator**
 *
 * El patrón Decorator **se utiliza para agregar responsabilidades adicionales a objetos individuales de manera dinámica**. Este patrón es útil cuando quieres extender las funcionalidades de objetos existentes sin modificar su estructura original o cuando necesitas muchas variantes de un objeto con diferentes combinaciones de características.
 */
object Decorator {
    interface Coffee {
        fun cost(): Double
    }

    class SimpleCoffee : Coffee {
        override fun cost(): Double {
            return 2.0
        }
    }

    class MilkDecorator(private val coffee: Coffee) : Coffee by coffee {
        override fun cost(): Double {
            return coffee.cost() + 1.0
        }
    }

    class SugarDecorator(private val coffee: Coffee) : Coffee by coffee {
        override fun cost(): Double {
            return coffee.cost() + 0.5
        }
    }

    fun main() {
        val simpleCoffee = SimpleCoffee()
        println("Cost of Simple Coffee: $${simpleCoffee.cost()}")

        val coffeeWithMilk = MilkDecorator(simpleCoffee)
        println("Cost of Coffee with Milk: $${coffeeWithMilk.cost()}")

        val coffeeWithMilkAndSugar = SugarDecorator(coffeeWithMilk)
        println("Cost of Coffee with Milk and Sugar: $${coffeeWithMilkAndSugar.cost()}")
    }
}


/**
 * **Adapter**
 *
 * El patrón Adapter **se utiliza para permitir que clases con interfaces incompatibles trabajen juntas**. Este patrón es útil cuando tienes una clase existente con una interfaz que no coincide con la interfaz requerida por el cliente, y deseas que la clase existente funcione con el cliente sin modificar su código fuente.
 */
object Adapter {
    interface MediaPlayer {
        fun play(fileName: String)
    }

    class AudioPlayer : MediaPlayer {
        override fun play(fileName: String) {
            println("Playing audio file: $fileName")
        }
    }

    class VideoPlayer {
        fun start(fileName: String) {
            println("Starting video file: $fileName")
        }

        fun stop() {
            println("Stopping video playback.")
        }
    }

    // Adaptador para VideoPlayer
    class VideoPlayerAdapter(private val videoPlayer: VideoPlayer) : MediaPlayer {
        override fun play(fileName: String) {
            videoPlayer.start(fileName)
        }
    }

    fun main() {
        val audioPlayer = AudioPlayer()
        audioPlayer.play("song.mp3")

        val videoPlayer = VideoPlayer()
        val videoAdapter = VideoPlayerAdapter(videoPlayer)
        videoAdapter.play("movie.mp4")
    }
}


/**
 * **Facade**
 *
 * El patrón Facade **permite proporcionar una interfaz simplificada para un subsistema complejo**, ocultando la complejidad detrás de una única clase. En este ejemplo, crearemos un Facade para un sistema de reproducción multimedia que consta de múltiples componentes.
 * El patrón Facade se utiliza para proporcionar una interfaz unificada y simplificada para un subsistema complejo. Este patrón es útil cuando tienes un sistema con muchas partes internas que interactúan entre sí y deseas ocultar su complejidad detrás de una sola clase.
 */
object Facade {
    // Subsistema complejo
    class AudioPlayer {
        fun load(fileName: String) {
            println("Loading audio file: $fileName")
        }

        fun play() {
            println("Playing audio...")
        }

        fun stop() {
            println("Stopping audio playback.")
        }

        fun release() {
            println("Releasing audio resources.")
        }
    }

    class VideoPlayer {
        fun load(fileName: String) {
            println("Loading video file: $fileName")
        }

        fun play() {
            println("Playing video...")
        }

        fun stop() {
            println("Stopping video playback.")
        }

        fun release() {
            println("Releasing video resources.")
        }
    }

    // Facade para simplificar el subsistema
    class MultimediaPlayer(private val audioPlayer: AudioPlayer, private val videoPlayer: VideoPlayer) {
        fun playAudio(fileName: String) {
            audioPlayer.load(fileName)
            audioPlayer.play()
        }

        fun playVideo(fileName: String) {
            videoPlayer.load(fileName)
            videoPlayer.play()
        }

        fun stopAudio() {
            audioPlayer.stop()
        }

        fun stopVideo() {
            videoPlayer.stop()
        }

        fun releaseResources() {
            audioPlayer.release()
            videoPlayer.release()
        }
    }

    // Uso del Facade
    fun main() {
        val audioPlayer = AudioPlayer()
        val videoPlayer = VideoPlayer()
        val multimediaPlayer = MultimediaPlayer(audioPlayer, videoPlayer)

        multimediaPlayer.playAudio("song.mp3")
        multimediaPlayer.playVideo("movie.mp4")

        // Detener solo el audio
        multimediaPlayer.stopAudio()

        multimediaPlayer.releaseResources()
    }
}


/**
 * **Proxy**
 *
 * El patrón Proxy **se utiliza para controlar el acceso a un objeto, actuando como un sustituto o representante del objeto original**. Este patrón es útil en situaciones donde deseas agregar una capa de protección o funcionalidad adicional al objeto real, como restringir el acceso o cargar el objeto solo cuando sea necesario.
 */
object Proxy {
    interface Image {
        fun display()
    }

    class RealImage(private val fileName: String) : Image {
        init {
            loadFromDisk(fileName)
        }

        private fun loadFromDisk(fileName: String) {
            println("Loading $fileName")
        }

        override fun display() {
            println("Displaying $fileName")
        }
    }

    class ProxyImage(private val fileName: String) : Image by RealImage(fileName) {
        override fun display() {
            println("Proxy: Preparing to display $fileName")
            // RealImage is automatically delegated and invoked
        }
    }

    fun main() {
        val image = ProxyImage("test.jpg")
        image.display() // RealImage is only loaded and displayed when necessary
    }
}


/**
 * **Strategy**
 *
 * El patrón Strategy **se utiliza para definir una familia de algoritmos intercambiables** y permitir que el cliente seleccione la implementación concreta en tiempo de ejecución.
 * El patrón Strategy es útil cuando tienes diferentes algoritmos o estrategias que pueden ser utilizados para resolver un problema específico. El cliente puede elegir dinámicamente la estrategia que mejor se adapte a sus necesidades, lo que permite una mayor flexibilidad y reutilización de código en función de diferentes situaciones o requisitos cambiantes.
 */
object Strategy {
    interface PaymentStrategy {
        fun pay(amount: Double)
    }

    class CreditCardPayment : PaymentStrategy {
        override fun pay(amount: Double) {
            println("Paid $amount via Credit Card.")
        }
    }

    class PayPalPayment : PaymentStrategy {
        override fun pay(amount: Double) {
            println("Paid $amount via PayPal.")
        }
    }

    class PaymentProcessor(private val paymentStrategy: PaymentStrategy) {
        fun processPayment(amount: Double) {
            paymentStrategy.pay(amount)
        }
    }
}


/**
 * **Composite**
 *
 * El patrón Composite **se utiliza para representar objetos compuestos en una estructura jerárquica de árbol**. Este patrón es útil cuando deseas tratar objetos individuales y composiciones de objetos de manera uniforme, permitiendo que el cliente maneje objetos individuales y agrupados de la misma manera.
 */
object Composite {
    interface Component {
        fun showPrice()
    }

    class Leaf(private val name: String, private val price: Double) : Component {
        override fun showPrice() {
            println("$name: $price")
        }
    }

    class Composite(private val name: String) : Component {
        private val components = mutableListOf<Component>()

        fun add(component: Component) {
            components.add(component)
        }

        fun remove(component: Component) {
            components.remove(component)
        }

        override fun showPrice() {
            println(name)
            for (component in components) {
                component.showPrice()
            }
        }
    }

    fun main() {
        val root = Composite("Electronics")

        val mobiles = Composite("Mobiles")
        mobiles.add(Leaf("iPhone", 1000.0))
        mobiles.add(Leaf("Google Pixel", 900.0))

        val laptops = Composite("Laptops")
        laptops.add(Leaf("MacBook", 2000.0))
        laptops.add(Leaf("Dell", 1500.0))

        root.add(mobiles)
        root.add(laptops)

        root.showPrice()
    }
}


/**
 * **Dependency injection**
 *
 * La Inyección de Dependencias es un patrón de diseño que **se utiliza para manejar las dependencias entre componentes de un sistema**.
 * En lugar de que un componente cree o busque sus dependencias, estas se "inyectan" desde el exterior.
 * La Inyección de Dependencias es ampliamente utilizada en aplicaciones de software para lograr un diseño más flexible y desacoplado.
 * Permite que los componentes dependan de abstracciones en lugar de implementaciones concretas, lo que facilita el reemplazo de implementaciones y mejora la capacidad de realizar pruebas unitarias.
 */
object DependencyInjection {
    // Interfaz Printer
    interface Printer {
        fun print(text: String)
    }

    // Implementaciones concretas de Printer
    class ConsolePrinter : Printer {
        override fun print(text: String) {
            println("Console: $text")
        }
    }

    class FilePrinter : Printer {
        override fun print(text: String) {
            // Simulamos escribir en un archivo
            println("File: $text")
        }
    }

    // Clase Report con Inyección de Dependencias
    class Report(private val printer: Printer) {
        fun generateAndPrint(content: String) {
            // Generar el contenido del informe
            val reportContent = "Report Content: $content"

            // Imprimir utilizando el printer inyectado
            printer.print(reportContent)
        }
    }

    // Cliente
    fun main() {
        // Creamos una instancia de ConsolePrinter y otra de FilePrinter
        val consolePrinter = ConsolePrinter()
        val filePrinter = FilePrinter()

        // Creamos instancias de Report con diferentes printers inyectados
        val report1 = Report(consolePrinter)
        val report2 = Report(filePrinter)

        // Generamos y mostramos los informes
        report1.generateAndPrint("Report 1 content")
        report2.generateAndPrint("Report 2 content")
    }

}


/**
 * **Chain of responsibility**
 *
 * El patrón Chain of Responsibility **se utiliza para construir una cadena de objetos receptores**, donde cada objeto en la cadena tiene la oportunidad de manejar una solicitud o pasarla al siguiente objeto en la cadena.
 * Este patrón es útil cuando tienes múltiples objetos que pueden manejar una solicitud, y deseas evitar el acoplamiento entre el remitente de la solicitud y el receptor.
 */
object ChainOfResponsibility {
    abstract class Handler(var successor: Handler? = null) {
        abstract fun handleRequest(request: String)

        fun nextHandler(request: String) {
            successor?.handleRequest(request)
        }
    }

    class ConcreteHandlerA(successor: Handler? = null) : Handler(successor) {
        override fun handleRequest(request: String) {
            if (request == "A") {
                println("ConcreteHandlerA: Handling request $request")
            } else {
                nextHandler(request)
            }
        }
    }

    class ConcreteHandlerB(successor: Handler? = null) : Handler(successor) {
        override fun handleRequest(request: String) {
            if (request == "B") {
                println("ConcreteHandlerB: Handling request $request")
            } else {
                nextHandler(request)
            }
        }
    }

    class ConcreteHandlerC(successor: Handler? = null) : Handler(successor) {
        override fun handleRequest(request: String) {
            if (request == "C") {
                println("ConcreteHandlerC: Handling request $request")
            } else {
                println("No handler can process the request $request")
            }
        }
    }

    fun main() {
        val handlerA = ConcreteHandlerA()
        val handlerB = ConcreteHandlerB()
        val handlerC = ConcreteHandlerC()

        handlerA.successor = handlerB
        handlerB.successor = handlerC

        handlerA.handleRequest("A") // Output: ConcreteHandlerA: Handling request A
        handlerA.handleRequest("B") // Output: ConcreteHandlerB: Handling request B
        handlerA.handleRequest("C") // Output: ConcreteHandlerC: Handling request C
        handlerA.handleRequest("D") // Output: No handler can process the request D
    }
}


/**
 * **Prototype**
 *
 * El patrón Prototype **se utiliza para crear objetos duplicados o clonar objetos existentes** sin depender de sus clases concretas. Este patrón es útil cuando necesitas crear múltiples instancias de un objeto con valores iniciales similares o cuando la creación de un objeto es costosa y deseas evitar repetir el proceso de inicialización.
 */
object Prototype {
    data class Person(val name: String, val age: Int) {
        fun clone(): Person {
            return this.copy()
        }
    }

    fun main() {
        val originalPerson = Person("John", 30)
        val clonedPerson = originalPerson.clone()

        println("Original Person: $originalPerson")
        println("Cloned Person: $clonedPerson")
    }
}


/**
 * **Flyweight**
 *
 * El patrón Flyweight **se utiliza para compartir eficientemente objetos pequeños** y reducir la sobrecarga de memoria, almacenando datos comunes externamente y pasando datos únicos como información interna de los objetos.
 * El patrón Flyweight es útil cuando tienes una gran cantidad de objetos similares y la duplicación de datos entre esos objetos consume mucha memoria. Este patrón se aplica especialmente en situaciones donde se requiere una gran cantidad de objetos con el mismo estado intrínseco.
 */
object Flyweight {
    // Clase Flyweight que representa la representación compartida de caracteres
    class Character(private val char: Char) {
        fun display(position: Int) {
            println("Character '$char' at position $position")
        }
    }

    // Clase FlyweightFactory que administra los caracteres compartidos
    class CharacterFactory {
        private val characters = mutableMapOf<Char, Character>()

        fun getCharacter(char: Char): Character {
            return characters.getOrPut(char) { Character(char) }
        }
    }

    // Cliente que crea y muestra caracteres
    fun main() {
        val characterFactory = CharacterFactory()

        val text = "Hello, World!"
        var position = 0

        for (char in text) {
            val character = characterFactory.getCharacter(char)
            character.display(position)
            position++
        }
    }
}


/**
 * **Command**
 *
 * El patrón Command **se utiliza para encapsular una solicitud como un objeto**, permitiendo parametrizar los clientes con diferentes solicitudes, encolar o registrar las solicitudes y realizar operaciones reversibles.
 * El patrón Command es útil cuando se necesita desacoplar el emisor de una solicitud del receptor, o cuando se requiere realizar operaciones en colas, realizar deshacer/rehacer y otras funcionalidades relacionadas con la invocación de comandos.
 *
 */
object Command {
    // Interfaz de Comando
    interface Command {
        fun execute()
    }

    // Comando concreto para encender la luz
    class LightOnCommand(private val light: Light) : Command {
        override fun execute() {
            light.turnOn()
        }
    }

    // Comando concreto para apagar la luz
    class LightOffCommand(private val light: Light) : Command {
        override fun execute() {
            light.turnOff()
        }
    }

    // Clase Light
    class Light {
        fun turnOn() {
            println("Light is ON")
        }

        fun turnOff() {
            println("Light is OFF")
        }
    }

    // Invocador (Invoker)
    class RemoteControl {
        private val commands = mutableMapOf<String, Command>()

        fun setCommand(button: String, command: Command) {
            commands[button] = command
        }

        fun pressButton(button: String) {
            commands[button]?.execute()
        }
    }

    // Cliente
    fun main() {
        val light = Light()

        val lightOnCommand = LightOnCommand(light)
        val lightOffCommand = LightOffCommand(light)

        val remoteControl = RemoteControl()
        remoteControl.setCommand("ON", lightOnCommand)
        remoteControl.setCommand("OFF", lightOffCommand)

        remoteControl.pressButton("ON") // Output: Light is ON
        remoteControl.pressButton("OFF") // Output: Light is OFF
    }
}


/**
 * **Interpreter**
 *
 * El patrón Interpreter **se utiliza para definir una gramática para interpretar oraciones o expresiones de un lenguaje específico**.
 * El patrón Interpreter es útil cuando necesitas crear un intérprete para un lenguaje específico o realizar operaciones complejas de análisis y evaluación de expresiones.
 *
 */
object Interpreter {
    // Contexto
    class Context(var data: Map<String, Int>)

    // Expresión
    interface Expression {
        fun interpret(context: Context): Int
    }

    // Expresiones terminales
    class NumberExpression(private val number: Int) : Expression {
        override fun interpret(context: Context): Int {
            return number
        }
    }

    class VariableExpression(private val variableName: String) : Expression {
        override fun interpret(context: Context): Int {
            return context.data[variableName] ?: throw IllegalArgumentException("Variable not found")
        }
    }

    // Expresiones no terminales
    class AddExpression(private val left: Expression, private val right: Expression) : Expression {
        override fun interpret(context: Context): Int {
            return left.interpret(context) + right.interpret(context)
        }
    }

    class SubtractExpression(private val left: Expression, private val right: Expression) : Expression {
        override fun interpret(context: Context): Int {
            return left.interpret(context) - right.interpret(context)
        }
    }

    // Cliente
    fun main() {
        val context = Context(mapOf("x" to 10, "y" to 5))

        val expression = AddExpression(
            SubtractExpression(NumberExpression(20), VariableExpression("x")), VariableExpression("y")
        )

        val result = expression.interpret(context)
        println("Result: $result") // Output: Result: -5
    }
}


/**
 * **Iterator**
 *
 * El patrón Iterator **se utiliza para proporcionar una forma de acceder secuencialmente a los elementos de una colección sin exponer su representación interna**.
 * El patrón Iterator es útil cuando necesitas recorrer los elementos de una colección de manera uniforme, independientemente de su estructura interna, o cuando deseas ocultar la estructura de datos subyacente.
 *
 */
object IteratorPattern {
    // Colección a recorrer
    class MyCollection<T> : Iterable<T> {
        private val items = mutableListOf<T>()

        fun add(item: T) {
            items.add(item)
        }

        override fun iterator(): Iterator<T> {
            return items.iterator()
        }
    }

    // Cliente
    fun main() {
        val collection = MyCollection<Int>()
        collection.add(1)
        collection.add(2)
        collection.add(3)

        for (item in collection) {
            println(item) // Output: 1, 2, 3
        }
    }
}


/**
 * **Memento**
 *
 * El patrón Memento **se utiliza para capturar y restaurar el estado interno de un objeto sin violar su encapsulación**.
 * El patrón Memento es útil cuando necesitas guardar y restaurar el estado de un objeto de manera eficiente, como en casos de deshacer/rehacer o recuperación de estados anteriores.
 *
 */
object Memento {
    // Clase Origen (Originator)
    data class Originator(var state: String) {
        fun saveStateToMemento(): Memento {
            return Memento(state)
        }

        fun restoreStateFromMemento(memento: Memento) {
            state = memento.state
        }
    }

    // Clase Memento
    data class Memento(val state: String)

    // Clase Caretaker
    class Caretaker {
        private val mementos = mutableListOf<Memento>()

        fun addMemento(memento: Memento) {
            mementos.add(memento)
        }

        fun getMemento(index: Int): Memento? {
            return mementos.getOrNull(index)
        }
    }

    // Cliente
    fun main() {
        val originator = Originator("State1")
        val caretaker = Caretaker()

        caretaker.addMemento(originator.saveStateToMemento())
        originator.state = "State2"

        caretaker.addMemento(originator.saveStateToMemento())
        originator.state = "State3"

        val savedState1 = caretaker.getMemento(0)
        val savedState2 = caretaker.getMemento(1)

        println(savedState1?.state) // Output: State1
        println(savedState2?.state) // Output: State2
    }
}


/**
 * **Mediator**
 *
 * El patrón Mediator **se utiliza para reducir las dependencias entre objetos, permitiendo que los objetos se comuniquen entre sí a través de un objeto mediador central**.
 * El patrón Mediator es útil cuando tienes un conjunto de objetos con muchas interacciones complejas y deseas evitar el acoplamiento directo entre ellos, promoviendo un diseño más flexible y mantenible.
 *
 */
object Mediator {
    // Mediator
    interface Mediator {
        fun sendMessage(user: User, message: String)
    }

    // Usuario (Colleague)
    class User(val name: String, private val mediator: Mediator) {
        fun sendMessage(message: String) {
            mediator.sendMessage(this, message)
        }

        fun receiveMessage(from: String, message: String) {
            println("[$name] Message from $from: $message")
        }
    }

    // Implementación concreta del Mediator
    class ChatMediator : Mediator {
        private val users = mutableListOf<User>()

        fun addUser(user: User) {
            users.add(user)
        }

        override fun sendMessage(user: User, message: String) {
            users.filter { it != user }.forEach { it.receiveMessage(user.name, message) }
        }
    }

    // Cliente
    fun main() {
        val mediator = ChatMediator()

        val user1 = User("John", mediator)
        val user2 = User("Alice", mediator)
        val user3 = User("Bob", mediator)

        mediator.addUser(user1)
        mediator.addUser(user2)
        mediator.addUser(user3)

        user1.sendMessage("Hello everyone!") // Output: [John] Message from Alice: Hello everyone!
        //         [John] Message from Bob: Hello everyone!
        user2.sendMessage("Hi, John!")       // Output: [Alice] Message from John: Hi, John!
        user3.sendMessage("Hey there!")      // Output: [Bob] Message from John: Hey there!
    }
}


/**
 * **Visitor**
 *
 * El patrón Visitor **se utiliza para agregar operaciones adicionales a objetos sin modificar su estructura**.
 * El patrón Visitor es útil cuando tienes una estructura de objetos compleja y deseas realizar operaciones específicas en cada uno de ellos sin alterar su implementación, como en casos de operaciones de exportación, impresión, etc.
 *
 */
object Visitor {
    // Elemento a visitar
    interface Element {
        fun accept(visitor: Visitor)
    }

    // Elementos concretos
    class ConcreteElementA : Element {
        override fun accept(visitor: Visitor) {
            visitor.visit(this)
        }

        fun operationA() {
            println("ConcreteElementA operation")
        }
    }

    class ConcreteElementB : Element {
        override fun accept(visitor: Visitor) {
            visitor.visit(this)
        }

        fun operationB() {
            println("ConcreteElementB operation")
        }
    }

    // Visitante
    interface Visitor {
        fun visit(elementA: ConcreteElementA)
        fun visit(elementB: ConcreteElementB)
    }

    // Implementación concreta del Visitante
    class ConcreteVisitor : Visitor {
        override fun visit(elementA: ConcreteElementA) {
            elementA.operationA()
        }

        override fun visit(elementB: ConcreteElementB) {
            elementB.operationB()
        }
    }

    // Cliente
    fun main() {
        val elements = listOf(ConcreteElementA(), ConcreteElementB())
        val visitor = ConcreteVisitor()

        elements.forEach { it.accept(visitor) }
        // Output: ConcreteElementA operation
        //         ConcreteElementB operation
    }
}


/**
 * **Object pool**
 *
 * En este patrón, se crea un grupo (pool) de **objetos previamente inicializados y disponibles para ser reutilizados**, en lugar de crear y destruir objetos continuamente.
 * Esto puede ser útil cuando la creación de objetos es costosa y se necesita mantener un número limitado de instancias activas.
 */
object ObjectPool {
    // Clase Connection
    class Connection {
        init {
            // Simulamos la inicialización de la conexión (por ejemplo, establecer la conexión con una base de datos)
            println("Creating connection: $this")
        }

        fun executeQuery(query: String) {
            println("Executing query '$query' using connection: $this")
        }

        fun close() {
            // Simulamos el cierre de la conexión (por ejemplo, cerrar la conexión con la base de datos)
            println("Closing connection: $this")
        }
    }

    // Object Pool para Connection
    class ConnectionPool(private val maxConnections: Int) {
        private val availableConnections: BlockingQueue<Connection> = ArrayBlockingQueue(maxConnections)

        init {
            // Inicializar las conexiones y agregarlas al pool
            repeat(maxConnections) {
                val connection = Connection()
                availableConnections.add(connection)
            }
        }

        fun getConnection(): Connection? {
            return try {
                availableConnections.take()
            } catch (ex: InterruptedException) {
                null
            }
        }

        fun releaseConnection(connection: Connection) {
            availableConnections.add(connection)
        }
    }

    // Cliente
    fun main() {
        val maxConnections = 3
        val connectionPool = ConnectionPool(maxConnections)

        val connection1 = connectionPool.getConnection()
        connection1?.executeQuery("SELECT * FROM users")
        connection1?.close()
        connectionPool.releaseConnection(connection1 ?: return)

        val connection2 = connectionPool.getConnection()
        connection2?.executeQuery("UPDATE products SET price = 20.0")
        connection2?.close()
        connectionPool.releaseConnection(connection2 ?: return)

        val connection3 = connectionPool.getConnection()
        connection3?.executeQuery("INSERT INTO orders (user_id, product_id) VALUES (1, 5)")
        connection3?.close()
        connectionPool.releaseConnection(connection3 ?: return)

        val connection4 = connectionPool.getConnection()
        connection4?.executeQuery("DELETE FROM customers WHERE id = 10")
        connection4?.close()
        connectionPool.releaseConnection(connection4 ?: return)
    }
}


/**
 * **Bridge**
 *
 * El patrón Bridge **se utiliza para separar una abstracción de su implementación**, permitiendo que ambas varíen de manera independiente.
 * Esto promueve un diseño flexible y extensible.
 * El patrón Bridge es útil cuando se tiene una jerarquía de clases con múltiples dimensiones de variación, y se desea evitar una explosión combinatoria de clases.
 * Permite agregar nuevos tipos de formas y colores sin afectar a las clases existentes.
 */
object Bridge {
    // Abstracción
    abstract class Shape(private val color: Color) {
        abstract fun draw()

        fun applyColor() {
            color.applyColor()
        }
    }

    // Implementación de Color
    interface Color {
        fun applyColor()
    }

    // Implementaciones concretas de Color
    class RedColor : Color {
        override fun applyColor() {
            println("Applying red color")
        }
    }

    class BlueColor : Color {
        override fun applyColor() {
            println("Applying blue color")
        }
    }

    // Formas concretas
    class Circle(private val color: Color) : Shape(color) {
        override fun draw() {
            println("Drawing Circle")
        }
    }

    class Square(private val color: Color) : Shape(color) {
        override fun draw() {
            println("Drawing Square")
        }
    }

    // Cliente
    fun main() {
        val redCircle = Circle(RedColor())
        val blueSquare = Square(BlueColor())

        redCircle.draw()    // Output: Drawing Circle
        redCircle.applyColor()    // Output: Applying red color

        blueSquare.draw()    // Output: Drawing Square
        blueSquare.applyColor()    // Output: Applying blue color
    }
}


/**
 * **Module**
 *
 * El patrón Module **se centra en dividir un sistema en partes interconectadas y desacopladas** llamadas módulos, donde cada módulo tiene su propia responsabilidad y actúa como una entidad independiente.
 * El patrón Module es ampliamente utilizado en sistemas de gran escala, donde se busca una estructura modular y un diseño flexible.
 * Permite la gestión más sencilla de componentes individuales y facilita la colaboración de múltiples equipos de desarrollo.
 */
object Module {
    // Interfaz de alto nivel
    interface PaymentProcessor {
        fun pay(amount: Double)
    }

    // Implementación de bajo nivel
    class CreditCardPayment : PaymentProcessor {
        override fun pay(amount: Double) {
            println("Paid $amount using Credit Card.")
        }
    }

    class PayPalPayment : PaymentProcessor {
        override fun pay(amount: Double) {
            println("Paid $amount using PayPal.")
        }
    }

    // Cliente
    fun main() {
        val paymentProcessor: PaymentProcessor = CreditCardPayment()
        paymentProcessor.pay(100.0) // Output: Paid 100.0 using Credit Card.

        val anotherPaymentProcessor: PaymentProcessor = PayPalPayment()
        anotherPaymentProcessor.pay(50.0) // Output: Paid 50.0 using PayPal.
    }
}


/**
 * **State**
 *
 * El patrón State **permite que un objeto altere su comportamiento cuando su estado interno cambia**.
 * Permite que el objeto parezca cambiar de clase en tiempo de ejecución.
 * El patrón State se utiliza cuando un objeto tiene comportamientos diferentes según su estado y cuando estos comportamientos cambian con frecuencia.
 * Es especialmente útil para evitar condicionales excesivos y mejorar la cohesión entre los comportamientos relacionados.
 */
object State {
    // Interfaz de Estado
    interface State {
        fun doAction(context: Context)
    }

    // Estados concretos
    class StateA : State {
        override fun doAction(context: Context) {
            println("State A: Action performed.")
            context.state = StateB()
        }
    }

    class StateB : State {
        override fun doAction(context: Context) {
            println("State B: Action performed.")
            context.state = StateA()
        }
    }

    // Clase de contexto
    class Context(var state: State) {
        fun request() {
            state.doAction(this)
        }
    }

    // Cliente
    fun main() {
        val context = Context(StateA())

        context.request() // Output: State A: Action performed.
        context.request() // Output: State B: Action performed.
        context.request() // Output: State A: Action performed.
    }
}


/**
 * **Template Method**
 *
 * El patrón Template Method **define el esqueleto de un algoritmo en un método, pero delega algunos pasos a las subclases**.
 * Permite que las subclases redefinan ciertos pasos del algoritmo sin cambiar su estructura general.
 * El patrón Template Method es útil cuando se necesita definir el flujo general de un algoritmo en una clase base, pero se permite que las subclases personalicen ciertos pasos específicos.
 * Es ampliamente utilizado en marcos de desarrollo y bibliotecas donde el comportamiento general es el mismo, pero detalles específicos varían.
 */
object TemplateMethod {
    // Clase abstracta con Template Method
    abstract class Game {
        abstract fun initialize()
        abstract fun startPlay()
        abstract fun endPlay()

        // Template Method
        fun play() {
            initialize()
            startPlay()
            endPlay()
        }
    }

    // Juego concreto
    class Football : Game() {
        override fun initialize() {
            println("Football Game Initialized! Start playing.")
        }

        override fun startPlay() {
            println("Football Game Started. Enjoy the game!")
        }

        override fun endPlay() {
            println("Football Game Finished!")
        }
    }

    // Juego concreto
    class Cricket : Game() {
        override fun initialize() {
            println("Cricket Game Initialized! Start playing.")
        }

        override fun startPlay() {
            println("Cricket Game Started. Enjoy the game!")
        }

        override fun endPlay() {
            println("Cricket Game Finished!")
        }
    }

    // Cliente
    fun main() {
        val football = Football()
        football.play()
        // Output:
        // Football Game Initialized! Start playing.
        // Football Game Started. Enjoy the game!
        // Football Game Finished!

        val cricket = Cricket()
        cricket.play()
        // Output:
        // Cricket Game Initialized! Start playing.
        // Cricket Game Started. Enjoy the game!
        // Cricket Game Finished!
    }
}