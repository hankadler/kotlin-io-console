/*
 * Copyright (C) 2020 Hank Adler
 */

package com.hankadler.io

import java.io.IOException
import java.security.InvalidParameterException
import kotlin.Exception

/**
 * Functions for command-line I/O.
 *
 * @author   Hank Adler
 * @version  0.2.0
 * @license  MIT
 */
object Console {
    const val STRING_TYPE = 0
    const val INT_TYPE = 1
    const val DOUBLE_TYPE = 2
    const val BOOLEAN_TYPE = 3

    private val inputTypes = listOf("String", "Int", "Double", "Boolean")

    var clearScreen = false
    var keepAsking = true
    var loopSilently = false

    /**
     * Asks user for input.
     *
     * @param prompt       Message to display during prompt.
     * @param valueType    Type restriction on input.
     * @param valueDefault Value to return if nothing is entered.
     *
     * @return Value entered or `valueDefault`.
     */
    fun ask(prompt: String, valueType: Int = 0, valueDefault: Any? = null): Any? {
        // Validates <valueType>.
        if (valueType !in 0..3) {
            throw InvalidParameterException("ERROR: <valueType> not in 0..3 range!")
        }

        // Ensures <valueDefault> type conforms to <valueType> restriction.
        if (valueDefault != null) {
            val valueDefaultType = when (valueDefault::class) {
                String::class -> 0
                Int::class -> 1
                Double::class -> 2
                Boolean::class -> 3
                else -> throw InvalidParameterException("ERROR: Invalid type for valueDefault parameter!")
            }
            if (valueDefaultType != valueType) {
                throw InvalidParameterException("ERROR: Invalid type for valueDefault parameter!")
            }
        }

        // Asks for value.
        var value: Any? = valueDefault
        do {
            if (clearScreen) {
                clear()
            }
            print(prompt)
            val inputString = readLine()

            if (inputString.isNullOrBlank()) {
                return value
            }

            try {
                value = when (valueType) {
                    INT_TYPE -> inputString.toInt()
                    DOUBLE_TYPE -> inputString.toDouble()
                    BOOLEAN_TYPE -> {
                        if (inputString.toLowerCase() in listOf("1", "true".toLowerCase())) {
                            true
                        } else if (inputString.toLowerCase() in listOf("0", "false")) {
                            false
                        } else {
                            throw IllegalArgumentException()
                        }
                    }
                    else -> inputString
                }
                break
            } catch (e: IllegalArgumentException) {
                if (!loopSilently) {
                    println("ERROR: Input must be of <${inputTypes[valueType]}> type!")
                    Thread.sleep(1000)
                    continue
                }
            }
        } while (keepAsking)

        return value
    }

    /**
     * Issues operating system dependent command to clear the console.
     */
    fun clear() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor()
            } else {
                Runtime.getRuntime().exec("clear")
            }
        } catch (e: IOException) {
            println("ERROR: " + e.message)
        }
    }

    /**
     * Selects an item from a collection.
     *
     * @param prompt       Message to display during prompt.
     * @param valueDefault Value to return if nothing is entered.
     *
     * @return Selected item.
     */
    fun select(prompt: String, collection: List<Any>, valueDefault: Any? = null): Any {
        var index = collection.indexOf(valueDefault)

        if (valueDefault != null && index == -1) {
            throw Exception("ERROR: $valueDefault not in $collection!")
        }

        do {
            if (clearScreen) {
                clear()
            }
            println(prompt)
            for (i in collection.indices) {
                println("    ${i + 1}. ${collection[i]}")
            }
            print("Choice: ")
            val choice = readLine()

            if (choice.isNullOrBlank()) return if (valueDefault == null) continue else valueDefault!!

            try {
                index = choice.toInt() - 1
            } catch (e: NumberFormatException) {
                if (!loopSilently) {
                    println("ERROR: Index must be a number!")
                    Thread.sleep(1000)
                }
                continue
            }

            if (index !in collection.indices) {
                if (!loopSilently) {
                    println("ERROR: Index out of range!")
                    Thread.sleep(1000)
                }
                continue
            }

            break
        } while (keepAsking)

        return collection[index]
    }
}
