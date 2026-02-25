# KotlinExtensions
[![](https://jitpack.io/v/Carleslc/kotlin-extensions.svg)](https://jitpack.io/#Carleslc/kotlin-extensions)

Utility extensions, properties and useful methods to boost your programming with Kotlin.

[![ko-fi](https://www.ko-fi.com/img/githubbutton_sm.svg)](https://ko-fi.com/carleslc)

## How to install

### Maven
Add the following repository to your `pom.xml`:
```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```

Add the following dependencies to your `pom.xml`:
```xml
<dependencies>
    <dependency>
    	<groupId>me.carleslc</groupId>
    	<artifactId>kotlin-extensions</artifactId>
    	<version>0.8.1</version>
    </dependency>
</dependencies>
```

If you need more powerful extensions and tools check the [optional libraries section](https://github.com/Carleslc/kotlin-extensions#optional-libraries).

### Gradle

Add the following repository to your `build.gradle`:

```groovy
repositories {
    maven { url 'https://jitpack.io' }
}
```

Add the following dependency to your `build.gradle`:
```groovy
dependencies {
    compile 'me.carleslc:kotlin-extensions:0.8.1'
}
```

If you need more powerful extensions and tools check the [optional libraries section](https://github.com/Carleslc/kotlin-extensions#optional-libraries).

### Manual

1. `git clone https://github.com/Carleslc/kotlin-extensions.git`
2. `cd kotlin-extensions`
3. Build the KotlinExtensions with `mvn clean install`. This also adds this project to your local Maven repository.

If you wish, you can use these steps to add the dependency using Maven or Gradle without accessing to the `jitpack.io` remote repository.

Otherwise, you will need to add the `kotlin-extensions-0.8.jar` from generated `target` folder as external dependency of your project.

If you need more powerful extensions and tools check the [optional libraries section](https://github.com/Carleslc/kotlin-extensions#optional-libraries).

## How to use

In order to use any of the extensions or methods of KotlinExtensions you only need to import them.

### Import a single extension
`import main.kotlin.extensions.standard.times`

### Import all extensions of a package
`import main.kotlin.extensions.standard.*`

## Packages available

### Arrays
`import main.kotlin.extensions.arrays.*`

Extensions for arrays, a syntactic-sugar way to create literal arrays like `A[1, 2, 3]` instead typical `arrayOf(1, 2, 3)`
and methods to work with 2D arrays and matrices (2D arrays with fixed columns size) easier.

### Bytes
`import main.kotlin.extensions.bytes.*`

Extensions for memory size conversions _(from bits to pebibytes and petabytes)_.

Inspired from [ktunits](https://github.com/sargunster/ktunits) library.

### Collections
`import main.kotlin.extensions.collections.*`

Extensions for collections, syntactic-sugar for list and set literals with `L[values]` or `S[values]` and other global methods to create collections.

### Conversions
`import main.kotlin.extensions.conversions.*`

Extensions for conversions between basic types.

### HTML
`import main.kotlin.extensions.html.*`

Extensions to generate HTML in a more readable way.

### Map
`import main.kotlin.extensions.map.*`

Extensions for maps and sintactic-sugar for Map literals with `M[pairs]`.

### Number
`import main.kotlin.extensions.number.*`

Extensions for numbers and math operations.

### Strings
`import main.kotlin.extensions.strings.*`

Extensions for strings.

`pluralize` and `singularize` methods are extracted from [kotlin-pluralizer](https://github.com/cesarferreira/kotlin-pluralizer).

### Time
`import main.kotlin.extensions.time.*`

Extensions for timing, format and manage time or dates easily.

Inspired from [kxdate](https://github.com/yole/kxdate) and [khronos](https://github.com/hotchemi/khronos) libraries.

### Tuples
`import main.kotlin.extensions.tuples.*`

Extensions for pairs and triples.

### Preconditions
`import main.kotlin.extensions.preconditions.*`

Extensions to ensure preconditions easily.

### Standard
`import main.kotlin.extensions.standard.*`

General purpose extensions and global methods that do not match any other package category (_e.g._ looping with `10 times { code }` or `10 * { code }`).

## Contribute

_Do you want to contribute to this project? Great!_

Simply fork and do a pull request. Then if it makes sense I will merge it creating a new version of this project.

Follow this rules when doing a pull request in order to be accepted:

1. Match the code style of this project (default in _IntelliJ_). You can do `⌘ + Option + L` in Mac with _IntelliJ_ IDE to reformat your code. 
2. Inline your extensions when possible. Include `@file:Suppress("NOTHING_TO_INLINE")` to suppress warnings at top of your file.
3. Ensure that your extensions/methods are general enough to be used in many different contexts.
4. Be descriptive in the title and description of your pull request.
5. Unit Test your code.

```sh
# sdk install maven

# Run tests
mvn test
```

## Optional Libraries

### [Arrow](https://github.com/arrow-kt/arrow)

This library adds functional stuff to boost your programming with Kotlin. It is a perfect library to use together with KotlinExtensions.

We've already included `arrow.core` in KotlinExtensions.

### [Guava](https://github.com/google/guava)

This library from Google includes a lot of useful classes and methods to extend the language. It is written in Java but it can be used from Kotlin with no problem.

It is already included in KotlinExtensions, available for Android use.

### [Android KTX](https://developer.android.com/kotlin/ktx) and [Splitties](https://github.com/LouisCAD/Splitties)

If you need extensions and useful tools for Android these libraries are recommended for your Android development.
