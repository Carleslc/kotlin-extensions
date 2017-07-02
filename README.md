# KotlinExtensions
Utility extensions, properties and useful methods to boost your programming with Kotlin.

## How to install

### Maven
Add the following repository to your `pom.xml`:
```
<repositories>
    <repository>
        <id>central</id>
        <name>ip-172-31-10-140-releases</name>
        <url>http://34.210.110.148:8081/artifactory/libs-release</url>
    </repository>
</repositories>
```

Add the following dependency to your `pom.xml`:
```
<dependencies>
    <dependency>
        <groupId>me.carleslc.kotlinextensions</groupId>
        <artifactId>KotlinExtensions</artifactId>
        <version>0.1</version>
        <scope>compile</scope>
    </dependency>
</dependencies>
```

### Gradle

Add the following repository to your `build.gradle`:

```
repositories {
    url 'http://34.210.110.148:8081/artifactory/libs-release'
}
```

Add the following dependency to your `build.gradle`:
```
compile(group: 'me.carleslc.kotlinextensions', name: 'KotlinExtensions', version: '0.1')
```

### Other

1. `git clone https://github.com/Carleslc/KotlinExtensions.git`
2. `cd KotlinExtensions`
3. Build the KotlinExtensions with `mvn clean install`. This also adds this project to your local Maven repository.
4. Add the `KotlinExtensions-0.1.jar` from `target` folder as a dependency of your project.

If you wish you can use these steps to add the dependency using Maven without accessing to the remote repository.

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
Extensions to generate HTML in a more readable way.

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
