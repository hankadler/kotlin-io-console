# kotlin-io-console

Functions for command-line I/O.

## Table of Contents
- [Setup](#setup)
- [Examples](#examples)
- [License](#license)

## Setup

Add to build.gradle.kts:

```kotlin
repositories {
    maven {
        setUrl("https://dl.bintray.com/hankadler/kotlin-io")
    }
}

dependencies {
    implementation("com.hankadler.io:console:0.1.0")
}
```

## Examples

Source:
```kotlin
// ConsoleExample.kt

import com.hankadler.io.Console

fun main() {
    val name = Console.ask("What's your name? ", default = "John Smith")
    val color = Console.select(
        "\nTell me $name, how's the sky right now? ", collection=listOf("Blue", "Gray", "Indigo", "Black"),
        default="Blue")

    val remark = when (color) {
        "Blue" -> "You should go out and soak in some sun!"
        "Gray" -> "Are you enjoying the cool breeze?"
        "Indigo" -> "Sunsets are short lived, perhaps therein lies its beauty..."
        else -> "Good night."
    }

    println("\n\t$remark")
}
```

Run:
```
What's your name? Hank

Tell me Hank, how's the sky right now? 
    1. Blue
    2. Gray
    3. Indigo
    4. Black
Choice: 3

    Sunsets are short lived, perhaps therein lies its beauty...
```

## License
[MIT](LICENSE) © Hank Adler
