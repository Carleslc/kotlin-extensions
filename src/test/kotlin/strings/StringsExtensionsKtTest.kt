package strings

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test


class StringsExtensionsKtTest {

    @Test
    fun joinWith() {

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