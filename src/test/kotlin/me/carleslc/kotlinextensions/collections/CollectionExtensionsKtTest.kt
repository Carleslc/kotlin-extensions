package me.carleslc.kotlinextensions.collections

import me.carleslc.kotlinextensions.number.fitsInLong
import me.carleslc.kotlinextensions.ranges.size
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.math.BigInteger

class CollectionExtensionsKtTest {

    @Test
    fun `isBlankTest for non-blank collection`() {
        val input = listOf(4, 5, 1, 5, 6).isBlank()
        Assertions.assertFalse(input)
    }

    @Test
    fun `isBlankTest for blank collection`() {
        val input: List<Int>? = null
        Assertions.assertTrue(input.isBlank())
    }

    @Test
    fun anyNullTest() {
        val input1 = listOf(1, 2, null, 3, null, 4, 5)
        val input2 = listOf(1, 2, 3)
        val input3 = listOf<Int?>(null)
        val input4 = listOf<Int>()
        Assertions.assertTrue(input1.anyNull())
        Assertions.assertFalse(input2.anyNull())
        Assertions.assertTrue(input3.anyNull())
        Assertions.assertFalse(input4.anyNull())
    }

    @Test
    fun allNullTest() {
        val input1 = listOf(1, 2, 3)
        val input2 = listOf<Int?>(null, null, null)
        val input3 = listOf(1, null, 2, null)
        Assertions.assertFalse(input1.allNull())
        Assertions.assertTrue(input2.allNull())
        Assertions.assertFalse(input3.allNull())
    }

    @Test
    fun countNulls() {
        val input1 = listOf(null)
        val input2 = listOf(null, null, null)
        val input3 = listOf<Int?>()
        Assertions.assertEquals(1, input1.countNulls())
        Assertions.assertEquals(3, input2.countNulls())
        Assertions.assertEquals(0, input3.countNulls())
    }

    @Test
    fun countNonNulls() {
        val input1 = listOf(null, 1, 2, null, 3)
        val input2 = listOf(1, 2, 3, 4)
        val input3 = listOf(null, null, null)
        val input4 = listOf<Int?>()
        Assertions.assertEquals(3, input1.countNonNulls())
        Assertions.assertEquals(4, input2.countNonNulls())
        Assertions.assertEquals(0, input3.countNonNulls())
        Assertions.assertEquals(0, input4.countNonNulls())
    }

    @Test
    fun trimNulls() {
        val input1 = listOf(1, null, 2, null, 3, null)
        val expected1 = listOf(1,2,3)
        Assertions.assertIterableEquals(expected1, input1.trimNulls())

        val input2 = listOf(null,null,null)
        val expected2 = emptyList<Int>()
        Assertions.assertIterableEquals(expected2, input2.trimNulls())

        val input3 = listOf(1,2,3)
        val expected3 = listOf(1,2,3)
        Assertions.assertIterableEquals(expected3, input3.trimNulls())

        val input4 = emptyList<Int>()
        val expected4 = emptyList<Int>()
        Assertions.assertIterableEquals(expected4, input4.trimNulls())
    }

    @Test
    fun trimNullsToMutableListTest() {
        val input = listOf(1,null,2,null)
        val result = input.trimNullsToMutableList()
        val expected = listOf(1,2)
        Assertions.assertInstanceOf(MutableList::class.java, result)
        Assertions.assertIterableEquals(expected, result)
    }

    @Test
    fun longRangeBoundsTest() {
        val maxRange = Long.MIN_VALUE..Long.MAX_VALUE
        val size = maxRange.size
        val expected = BigInteger.valueOf(2).pow(64)
        Assertions.assertEquals(expected, size)
        Assertions.assertFalse(size.fitsInLong())
    }
}
