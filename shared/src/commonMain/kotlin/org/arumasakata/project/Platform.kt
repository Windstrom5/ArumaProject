package org.arumasakata.project

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform