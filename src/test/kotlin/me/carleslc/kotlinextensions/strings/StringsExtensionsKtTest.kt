package me.carleslc.kotlinextensions.strings

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import strings.*
import java.util.*

class StringsExtensionsKtTest {

    companion object {
        @BeforeAll
        @JvmStatic
        fun setStandardLocaleFormat() {
            Locale.setDefault(Locale.Category.FORMAT, Locale.ENGLISH)
        }
    }

    @Test
    fun toFixedDoubleTest() {
        val expected1 = "0.34"
        val result1 = 0.341.toFixed(2)
        Assertions.assertEquals(expected1, result1)

        val expected2 = "0.34100"
        val result2 = 0.341.toFixed(5)
        Assertions.assertEquals(expected2, result2)
    }

    @Test
    fun toFixedFloatTest() {
        val expected1 = "0.34"
        val result1 = (0.341f).toFixed(2)
        Assertions.assertEquals(expected1, result1)

        val expected2 = "0.34100"
        val result2 = (0.341f).toFixed(5)
        Assertions.assertEquals(expected2, result2)
    }

    @Test
    fun toStringTransformTest() {
        val expected = "0KOTLIN"
        val result = Pair("kotlin", 0).toStringTransform { it.second.toString() + it.first.uppercase() }
        Assertions.assertEquals(expected, result)
    }

    @Test
    fun `toStringNullableTransformTest for null string`() {
        val expected = "this_string_is_null"
        val input: String? = null
        val result = input.toStringTransform("this_string_is_null") { it.uppercase() }
        Assertions.assertEquals(expected, result)
    }

    @Test
    fun `toStringNullableTransformTest for non-null string`() {
        val expected = "NORMAL"
        val input = "normal"
        val result = input.toStringTransform("this_string_is_null") { it.uppercase() }
        Assertions.assertEquals(expected, result)
    }


    @Test
    fun pluralizeTest() {
        Assertions.assertEquals("people", "person".pluralize())
        Assertions.assertEquals("apples", "apple".pluralize())
        Assertions.assertEquals("cars", "car".pluralize())
        Assertions.assertEquals("geese", "goose".pluralize())
        Assertions.assertEquals("octopi", "octopus".pluralize(2))
        Assertions.assertEquals("apples", "apple".pluralize(2))
    }

    @Test
    fun singularizeTest() {
        Assertions.assertEquals("person", "people".singularize())
        Assertions.assertEquals("apple", "apples".singularize())
        Assertions.assertEquals("car", "cars".singularize())
        Assertions.assertEquals("goose", "geese".singularize())
        Assertions.assertEquals("octopi", "octopus".singularize(2))
        Assertions.assertEquals("apples", "apple".singularize(2))
    }

    @Test
    fun isNotNullOrBlankTest() {
        Assertions.assertFalse(null.isNotNullOrBlank())
        Assertions.assertFalse("".isNotNullOrBlank())
    }

    @Test
    fun timesTest() {
        val input = "kotlin"
        val times = 5
        Assertions.assertTrue(times times input == input times times)
    }

    @Test
    fun timesIntTest() {
        val input = "kotlin"
        val expected = input.repeat(5)
        val result = 5 times "kotlin"
        Assertions.assertEquals(expected, result)
    }

    @Test
    fun timesStringTest() {
        val input = "kotlin"
        val times = 5
        val expected = input.repeat(times)
        val result = "kotlin" times times
        Assertions.assertEquals(expected, result)
    }

    @Test
    fun splitLines() {
        val input =
            "Kotlin is a cross-platform, statically typed, general-purpose high-level programming language with type inference."
        val expected = input.split("\n")
        val result = input.splitLines()
        Assertions.assertIterableEquals(expected, result)
    }

    @Test
    fun wrapTest() {
        val expected = "1000"
        val result = 1000.wrap()
        val result2 = 1000.wrapString()
        Assertions.assertEquals(expected, result)
        Assertions.assertEquals(expected, result2)
    }


    @Test
    fun concatTest() {

        val expected = "javakotlinpythonrust"
        val result = concat("java", "kotlin", "python", "rust")

        Assertions.assertEquals(expected, result)

    }

    @Test
    fun joinTest() {

        val expected = "java kotlin python rust"
        val result = join("java", "kotlin", "python", "rust")

        Assertions.assertEquals(expected, result)
    }


    @Test
    fun joinWithTest() {

        val expected = "java0kotlin0python"
        val result = joinWith("0", "java", "kotlin", "python")

        Assertions.assertEquals(expected, result)
    }

    @Test
    fun replaceTest() {

        val expected = "this is java, hello from java"
        val input = "this is kotlin, hello from kotlin"
        val result = input.replace(true, Pair("kotlin", "java"))

        Assertions.assertEquals(expected, result)

    }

    @Test
    fun removeTest() {

        val expected1 = " world"
        val result1 = "hello world".remove("hello")

        val expected2 = "hello "
        val result2 = "hello world".remove("world")

        Assertions.assertEquals(expected1, result1)
        Assertions.assertEquals(expected2, result2)


    }

    @Test
    fun capitalizeFirstCharTest() {

        val expected = "Petro Canada"

        val result1 = "petro canada".capitalizeFirstChar()
        val result2 = "PETRO CANADA".capitalizeFirstChar()
        val result3 = "PEtRO CAnaDA".capitalizeFirstChar()
        val result4 = "pEtRO cAnaDA".capitalizeFirstChar()

        Assertions.assertEquals(expected, result1)
        Assertions.assertEquals(expected, result2)
        Assertions.assertEquals(expected, result3)
        Assertions.assertEquals(expected, result4)

    }

    @Test
    fun removeNonAlphaTest() {
        val expected = ""
        val input = """()`~!@#%^&*-+=|\{}[];"'<>,.?/_:0123456789"""
        val result = input.removeNonAlpha()
        Assertions.assertEquals(expected, result)
    }

    @Test
    fun uniquifyWordsTest() {

        val input = "java kotlin java c++ python python rust"
        val expected = "java kotlin c++ python rust"
        val result = input.uniquifyWords()

        Assertions.assertEquals(expected, result)

    }

}
