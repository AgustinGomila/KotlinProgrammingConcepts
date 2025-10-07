# Kotlin Programming Concepts

Un proyecto sencillo que muestra ejemplos prácticos de conceptos fundamentales de programación en Kotlin.

## Contenido

Este repositorio contiene tres archivos principales que explican diferentes paradigmas y patrones:

### 📚 Programación Orientada a Objetos

- Abstracción
- Encapsulamiento
- Herencia
- Polimorfismo

### 💻 Programación Funcional

- Funciones de orden superior
- Operaciones con colecciones (map, filter, reduce)
- Composición de funciones
- Inmutabilidad

### 🧩 Patrones de Diseño

- **Creacionales**: Singleton, Factory, Builder, etc.
- **Estructurales**: Decorator, Adapter, Facade, etc.
- **Comportamiento**: Observer, Strategy, Command, etc.
- **Arquitectónicos**: Repository, CQRS, etc.

## 🦁 vs ☕ Cuadro Comparativo: Kotlin vs Java

| **Característica**             | **Kotlin**                                                                                                           | **Java**                                                                              |
|--------------------------------|----------------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------|
| **Null Safety**                | Sistema de tipos integrado: `String?` para nulos, operadores `?.` y `?:` para manejo seguro.                         | Anotaciones (`@Nullable`) o `Optional`; no integrado en el sistema de tipos.          |
| **Sintaxis Concisa**           | Clases de datos con `data class`, inferencia de tipos, menos *boilerplate*. Ej: `data class User(val name: String)`. | Clases POJO verbosas con getters/setters manuales. Requiere bibliotecas como Lombok.  |
| **Programación Funcional**     | Soporte nativo para lambdas y higher-order functions. Ej: `{ x: Int -> x * 2 }`.                                     | Lambdas desde Java 8, pero con sintaxis más compleja: `(int x) -> { return x * 2; }`. |
| **Funciones de Extensión**     | Permite añadir métodos a clases existentes sin herencia: `fun String.addExclamation() = this + "!"`.                 | No soportado; se usan clases utilitarias estáticas.                                   |
| **Casts Inteligentes**         | Conversión automática tras verificación de tipo: `if (obj is String) obj.length`.                                    | Requiere *casting* explícito: `if (obj instanceof String) ((String)obj).length`.      |
| **Argumentos por Defecto**     | Valores predeterminados en parámetros: `fun save(user: User, log: Boolean = true)`.                                  | Requiere sobrecarga de métodos para simularlo.                                        |
| **Programación Asíncrona**     | Coroutines (estructuras ligeras, sin *callback hell*): `launch { ... }`.                                             | *Threads*, `CompletableFuture` o librerías externas (más complejo).                   |
| **Clases de Datos**            | `data class` genera automáticamente `equals()`, `hashCode()`, `toString()`.                                          | Implementación manual o dependencia de Lombok.                                        |
| **Excepciones Revisadas**      | Todas las excepciones son no revisadas (no obliga a manejar `try-catch`).                                            | Excepciones revisadas obligan a manejar o declarar con `throws`.                      |
| **Propiedades**                | Soporte nativo con `var`/`val` (getters/setters implícitos). Ej: `var name: String`.                                 | Uso exclusivo de métodos `getX()`/`setX()` para simular propiedades.                  |
| **Miembros Estáticos**         | `companion object` (flexible y compatible con inyección de dependencias).                                            | Palabra clave `static` (menos flexible en patrones como DI).                          |
| **Inferencia de Tipos**        | Amplia inferencia: `val list = listOf("a", "b")` (tipo `List<String>`).                                              | Inferencia limitada: `var list = new ArrayList<String>()` (solo en Java 10+).         |
| **Nulabilidad en Colecciones** | Tipos explícitos: `List<String>` (no nulo) vs `List<String?>` (nulo permitido).                                      | Colecciones pueden contener `null` sin distinción de tipo (ej: `List<String>`).       |

## Uso

El proyecto es puramente descriptivo y educativo. Solo contiene código de ejemplo con comentarios explicativos. Puedes
explorar los archivos para entender cómo se implementan estos conceptos en Kotlin.

¡Espero que te sea útil para aprender y consultar estos fundamentos de programación!