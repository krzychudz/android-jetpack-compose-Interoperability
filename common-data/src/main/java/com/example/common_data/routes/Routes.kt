package com.example.common_data.routes

import android.util.Log

object Routes {
    const val Settings = "/settings"
    const val About = "/settings/about"
    const val AboutCounter = "/settings/about/counter/{counter_value}"
    const val TermsAndConditions = "/settings/terms"
    const val GoBack = "back"
}

fun <T> String.withArguments(argsMap: Map<String, T>): String {
    var finalRoute = this
    argsMap.keys.forEach { pathArgName ->
        finalRoute = finalRoute.replace("{$pathArgName}", argsMap[pathArgName].toString())
    }
    return finalRoute
}