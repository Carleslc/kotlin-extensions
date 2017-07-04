# KotlinExtensions
Utility extensions, properties and useful methods to boost your programming with Kotlin.

## How to install

### Maven
Add the following repository to your `pom.xml`:
```
<repositories>
    <repository>
        <id>central</id>
        <url>http://34.210.110.148:8081/artifactory/libs-release</url>
    </repository>
</repositories>
```

Add the following dependency to your `pom.xml`:
```
<dependencies>
    <dependency>
        <groupId>me.carleslc</groupId>
        <artifactId>kotlin-extensions</artifactId>
        <version>0.1</version>
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
dependencies {
    compile(group: 'me.carleslc', name: 'kotlin-extensions', version: '0.1')
}
```

### Other

1. `git clone https://github.com/Carleslc/kotlin-extensions.git`
2. `cd kotlin-extensions`
3. Build the KotlinExtensions with `mvn clean install`. This also adds this project to your local Maven repository.
4. Add the `kotlin-extensions-0.1.jar` from `target` folder as a dependency of your project.

If you wish you can use these steps to add the dependency using Maven without accessing to the remote repository.

## How to use

In order to use any of the extensions or methods of KotlinExtensions you only need to import them.

### Import a single extension
`import me.carleslc.kotlin.extensions.standard.UtilsExtensions.times`

### Import all extensions of a package
`import me.carleslc.kotlin.extensions.standard.*`

## Packages available

### Arrays
Extensions for arrays, a syntactic-sugar way to create literal arrays like `A[1, 2, 3]` instead typical `arrayOf(1, 2, 3)`
and methods to work with 2D arrays and matrices (2D arrays with fixed columns size) easier.

### Collections
Extensions for collections and other global methods to create collections.

### HTML
Extensions to generate HTML in a more readable way.

### Map
Extensions for maps and tuples (pairs).

### Number
Extensions for numbers and conversions.

### Strings
Extensions for strings.

### Time
Global methods to format time easily.

### Standard
General purpose extensions and global methods that do not match any other package category (e.g. looping with `10 times { /* code */ }` as an alias for `repeat(10) { /* code */ }`).
