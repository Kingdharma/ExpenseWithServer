package org.example.expensewithserver

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform