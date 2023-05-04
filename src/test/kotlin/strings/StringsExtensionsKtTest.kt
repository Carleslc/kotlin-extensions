package strings

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test


class StringsExtensionsKtTest {

    @Test
    fun uniquifyWordsTest() {

        val input = "java kotlin java c++ python python rust"
        val expected = "java kotlin c++ python rust"
        val actual = input.uniquifyWords()

        Assertions.assertEquals(expected, actual)

    }
}