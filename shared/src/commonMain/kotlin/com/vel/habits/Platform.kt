package com.vel.habits

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform