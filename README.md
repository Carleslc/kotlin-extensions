# KotlinExtensions
Utility extensions, properties and useful methods to boost your programming with Kotlin.

Additionally `funktionale-all` module from [funKTionale](https://github.com/MarioAriasC/funKTionale) is included.

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

Add the following dependencies to your `pom.xml`:
```
<dependencies>
    <dependency>
        <groupId>me.carleslc</groupId>
        <artifactId>kotlin-extensions</artifactId>
        <version>0.2</version>
    </dependency>
    <dependency>
        <groupId>org.funktionale</groupId>
        <artifactId>funktionale-all</artifactId>
        <version>1.1</version>
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
    compile(group: 'me.carleslc', name: 'kotlin-extensions', version: '0.2')
    compile(group: 'org.funktionale', name: 'funktionale-all', version: '1.1')
}
```

### Manual

1. `git clone https://github.com/Carleslc/kotlin-extensions.git`
2. `cd kotlin-extensions`
3. Build the KotlinExtensions with `mvn clean install`. This also adds this project to your local Maven repository.
4. Add the `kotlin-extensions-0.2.jar` from `target` folder as a dependency of your project.
5. `git clone https://github.com/MarioAriasC/funKTionale`
6. cd `funKTionale/funktionale-all`
7. Build the functionale-all with `mvn clean install`. This also adds this project to your local Maven repository.
8. Add the `funktionale-all-1.1.jar` from `target` folder as a dependency of your project.

If you wish you can use these steps to add the dependency using Maven without accessing to the remote repository.

## How to use

In order to use any of the extensions or methods of KotlinExtensions you only need to import them.

### Import a single extension
`import me.carleslc.kotlin.extensions.standard.UtilsExtensions.times`

### Import all extensions of a package
`import me.carleslc.kotlin.extensions.standard.*`

## Packages available

### funKTionale
[funKTionale](https://github.com/MarioAriasC/funKTionale) library is included, so you can use any methods of that library.

```
import org.funktionale.collections.*
import org.funktionale.complement.*
import org.funktionale.composition.*
import org.funktionale.currying.*
import org.funktionale.either.*
import org.funktionale.memoization.*
import org.funktionale.option.*
import org.funktionale.pairing.*
import org.funktionale.partials.*
import org.funktionale.pipe.*
import org.funktionale.reverse.*
import org.funktionale.state.*
import org.funktionale.tries.*
import org.funktionale.utils.*
import org.funktionale.validation.*
```

### Arrays
`import me.carleslc.kotlin.extensions.arrays.*`

Extensions for arrays, a syntactic-sugar way to create literal arrays like `A[1, 2, 3]` instead typical `arrayOf(1, 2, 3)`
and methods to work with 2D arrays and matrices (2D arrays with fixed columns size) easier.

### Collections
`import me.carleslc.kotlin.extensions.collections.*`

Extensions for collections and other global methods to create collections.

### HTML
`import me.carleslc.kotlin.extensions.html.*`

Extensions to generate HTML in a more readable way.

### Map
```
import me.carleslc.kotlin.extensions.map.*
import me.carleslc.kotlin.extensions.map.TupleExtensions.*
import me.carleslc.kotlin.extensions.map.MapExtensions.*
```

Extensions for maps and tuples (pairs).

### Number
`import me.carleslc.kotlin.extensions.number.*`

Extensions for numbers and conversions.

### Strings
`import me.carleslc.kotlin.extensions.strings.*`

Extensions for strings.

### Time
`import me.carleslc.kotlin.extensions.time.*`

Global methods to format time easily.

### Standard
`import me.carleslc.kotlin.extensions.standard.*`

General purpose extensions and global methods that do not match any other package category (_e.g._ looping with `10 times { /* code */ }` as an alias for `repeat(10) { /* code */ }`).
