# KotlinExtensions
Utility extensions, properties and useful methods to boost your programming with Kotlin.

## How to install

First steps:

1. `git clone https://github.com/Carleslc/KotlinExtensions.git`
2. `cd KotlinExtensions`
2. Build the KotlinExtensions with `mvn clean install` to add this project to your local Maven repository

Then you can add KotlinExtensions to your project:

### Maven
Add the following dependency to your `pom.xml`
```
<dependency>
    <groupId>me.carleslc.kotlinextensions</groupId>
    <artifactId>KotlinExtensions</artifactId>
    <version>0.1</version>
    <scope>compile</scope>
</dependency>
```

### Other
Add the `KotlinExtensions-0.1.jar` from `target` folder as a dependency of your project

## How to use

In order to use any of the extensions or methods of KotlinExtensions you only need to import them.

### Import a single method
`import me.carleslc.extensions.number.even`

### Import all methods of a package
`import me.carleslc.extensions.number.*`

## Packages available

### Any
Extensions for Any objects.

### Arrays
Extensions for arrays, a syntactic-sugar way to create literal arrays like `A[1, 2, 3]` instead typical `arrayOf(1, 2, 3)`
and methods to work with 2D arrays and matrices (2D arrays with fixed columns size) easier.

### Collections
Extensions for collections and other static methods to create collections.

### Functions
Extensions for lambda functions and other static methods to work with functions (e.g. function composition).

### HTML
Extensions to generate HTML more readable.

### Map
Extensions for maps.

### Number
Extensions for numbers and conversions.

### Time
Static methods to format time easily.

### Tuples
Extensions for tuples (pairs).

### Utils
General purpose extensions and static methods that do not match any other package category (e.g. loops with `Int.times`).
