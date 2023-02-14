package com.pinguapps.learntocook.data

fun getDiets(): HashMap<String, Boolean> {

    return hashMapOf(
        "vegan" to false,
        "vegetarian" to false,
        "peanut free" to false,
        "soy free" to false,
        "gluten free" to false,
        "dairy free" to false,
    )
}