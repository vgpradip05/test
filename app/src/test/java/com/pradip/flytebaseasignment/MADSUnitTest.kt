package com.pradip.flytebaseasignment

import com.pradip.flytebaseasignment.business.MADSCalculator
import org.junit.Assert
import org.junit.Test

class MADSUnitTest {
    @Test
    fun evaluateTheExpression() {// desired result will be achieved
        val result = MADSCalculator.evaluate("50 + 20 / 10")
        Assert.assertEquals(7, result)

        val result2 = MADSCalculator.evaluate("50 / 20 + 5")
        Assert.assertEquals(2, result2)

        val result3 = MADSCalculator.evaluate("25 - 2 * 10")
        Assert.assertEquals(5, result3)

        val result4 = MADSCalculator.evaluate("10 / 2 - 20")
        Assert.assertEquals(-15, result4)

        val result5 = MADSCalculator.evaluate("10 - 2 - 3")
        Assert.assertEquals(5, result5)

        val result6 = MADSCalculator.evaluate("10 / 2 / 5")
        Assert.assertEquals(1, result6)

        val result7 = MADSCalculator.evaluate("10 / 2 / 4 + 1")
        Assert.assertEquals(1, result7)
    }

    @Test
    fun evaluateTheExpressionWithoutSpaces() {// result will not be as expected
        val result = MADSCalculator.evaluate("50+20/10")
        Assert.assertEquals(7, result)
    }

    @Test
    fun validateMyInput() {// validate user input
        val result = isValidInput("50+20/10")
        Assert.assertEquals(false, result)

        val result2 = isValidInput("50 + 20 / 10")
        Assert.assertEquals(true, result2)
    }

    private fun isValidInput(text: String?): Boolean {
        val str = "+-/*"
        return if (!text.isNullOrEmpty()) {
            val countOp = text.count { str.contains(it) }
            val countSp = text.count { it == ' ' }
            val countDig = text.count { it.isDigit() }
            countOp * 2 == countSp && countDig > 1
        } else
            false

    }
}