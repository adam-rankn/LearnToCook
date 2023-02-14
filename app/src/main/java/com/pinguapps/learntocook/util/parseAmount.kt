package com.pinguapps.learntocook.util

fun parseAmount(unit: String, amount: String, scale: Int = 1, metric: Boolean = false): String {

    var parsedString = ""

    if (amount == "0"){
        return ""
    }

    val scaledAmount = scaleAmount(amount,scale)

    parsedString = scaledAmount

    // todo metric/imperial
    // todo simplify units
    // todo rudimentary plural fixing


    // check units and convert

    return parsedString
}

private fun scaleAmount(amount: String, scale: Int = 1): String {
    var scaledAmountString = ""
    if (amount.contains("-")){
        //range
        val lower = amount.substringBeforeLast("-")
        val upper = amount.substringAfterLast("-")

        val scaledLower = scaleAmount(lower,scale)
        val scaledUpper = scaleAmount(upper,scale)

        scaledAmountString = "$scaledLower-$scaledUpper"
    }

    else if (amount.contains("/")){
        // fractional
        val denominator = amount.substringAfterLast("/").toInt()
        val numerator = amount.substringBeforeLast("/").toInt()
        val scaledNumerator = numerator * scale

        //simplify fractions
        val gcd = gcd(scaledNumerator,denominator)
        val simpleNumerator = scaledNumerator.div(gcd)
        val simpleDenominator = denominator.div(gcd)

        // convert from improper to mixed fraction
        if (simpleNumerator > simpleDenominator){
            val properNumerator = simpleNumerator.floorDiv(simpleDenominator)
            val modulo = simpleNumerator.mod(simpleDenominator)
            if (modulo != 0) {
                scaledAmountString = "$properNumerator $modulo/$simpleDenominator"
            }
            else {
                scaledAmountString = "$properNumerator"
            }

        }
        else if (simpleNumerator == simpleDenominator) {
            scaledAmountString = "1"
        }
        else {
            scaledAmountString = "$simpleNumerator/$simpleDenominator"
        }
    }

    else {
        val scaledAmount = amount.toInt() * scale
        scaledAmountString = "$scaledAmount"
    }

    return scaledAmountString
}

private fun gcd(numerator: Int, denominator: Int): Int {

    var gcd = 1

    var i = 1
    while (i <= numerator && i <= denominator) {
        if (numerator % i == 0 && denominator % i == 0)
            gcd = i
        ++i
    }

    return gcd
}

private fun simplifyConvert(amount: String, unit: String): String {

    var convertedString = ""
    if (unit == "tsp") {
        val tbsp = amount.toInt().mod(3)
        if (tbsp == 0) {
            convertedString = "${amount.toInt().div(3)} tbsp"
        }
    }
    else if (unit == "tbsp"){

    }

    return convertedString
}