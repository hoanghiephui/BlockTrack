package com.blockchain.blocktrack

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform