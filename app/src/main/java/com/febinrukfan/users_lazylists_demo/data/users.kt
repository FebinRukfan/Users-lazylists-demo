package com.febinrukfan.users_lazylists_demo.data

import androidx.compose.ui.text.capitalize
import kotlin.random.Random

data class users(
    val userId : Long,
    val username : String,
    val fullName : String,
    val email : String)

val emailDomains = listOf("@gmail.com", "@yahoo.com", "@hotmail.com", "@outlook.com")

val generator = generateSequence {
    users(
        //userId must be long and of 8 digit,
        userId = Random.nextLong(100_000_00L, 999_999_99L),
        //username must be alphanumeric and 6 characters,
        username = generateUsernames(6),
        //fullName must be alphabetical and up to 20 characters
        fullName = generateFullNames(20),
        //email should be any valid email address.
        email = "${generateUsernames(6)}${emailDomains.random()}"
    )
}

fun generateUsernames(limit: Int): String {
    val nameLength = Random.nextInt(4, limit + 1)
    val nameBuilder = StringBuilder(nameLength)
    repeat(nameLength) {
        val letter = ('a'..'z').filter { it.isLetter() }.random()
        nameBuilder.append(letter)
    }
    return nameBuilder.toString()
}

fun generateFullNames(limit: Int): String {
    val usernameBuilder = StringBuilder(limit)
    repeat(limit) {
        val char = when (Random.nextInt(3)) {
            0 -> ('a'..'z')
            else -> ('A'..'Z')
        }.filter { it.isLetterOrDigit() }.random()
        usernameBuilder.append(char)
    }
    return usernameBuilder.toString()
}


val user = generator.take(100).toList().sortedBy { it.fullName }