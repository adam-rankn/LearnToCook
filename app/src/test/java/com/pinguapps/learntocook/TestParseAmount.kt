package com.pinguapps.learntocook

import com.pinguapps.learntocook.util.parseAmount
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class TestParseAmount {

    @Test
    fun testNoAmount() {
        val parsed = parseAmount("pounds","0",1)
        assertEquals("", parsed)

        val parsed2 = parseAmount("","0",9)
        assertEquals("", parsed2)
    }

    @Test
    fun testNoUnit() {
        val parsed = parseAmount("","5",1)
        assertEquals("5", parsed)

        val parsed2 = parseAmount("","3",3)
        assertEquals("9", parsed2)
    }

    @Test
    fun testFractionScale() {
        val parsed = parseAmount("cup","1/4",2)
        assertEquals("1/2", parsed)

        val parsed2 = parseAmount("cup","2/4",1)
        assertEquals("1/2", parsed2)

        val parsed3 = parseAmount("tsp","3/4",2)
        assertEquals("1 1/2", parsed3)

        val parsed4 = parseAmount("tsp","1/4",9)
        assertEquals("2 1/4", parsed4)

        val parsed5 = parseAmount("tsp","3/2",1)
        assertEquals("1 1/2", parsed5)

        val parsed6 = parseAmount("tsp","1/2",2)
        assertEquals("1", parsed6)

        val parsed7 = parseAmount("tsp","1/2",4)
        assertEquals("2", parsed7)

        val parsed8 = parseAmount("tsp","1/3",3)
        assertEquals("1", parsed8)

        val parsed9 = parseAmount("tsp","207/391",3)
        assertEquals("1 10/17", parsed9)
    }

    @Test
    fun testRange() {
        val parsed = parseAmount("pounds","3-4",2)
        assertEquals("6-8", parsed)

        val parsed2 = parseAmount("","1-2",2)
        assertEquals("2-4", parsed2)

        val parsed3 = parseAmount("","12-16",2)
        assertEquals("24-32", parsed3)
    }

    @Test
    fun testRangeAndFraction() {

        val parsed1 = parseAmount("cups","1/2-3/4",2)
        assertEquals("1-1 1/2", parsed1)
    }
}