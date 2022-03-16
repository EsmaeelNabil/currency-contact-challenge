package com.esmaeel.challenge.utils

import com.esmaeel.challenge.di.ContextProvider
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlin.coroutines.CoroutineContext

class TestContextProvider : ContextProvider() {
    @OptIn(ExperimentalCoroutinesApi::class)
    val testDispatcher = TestCoroutineDispatcher()
    override val Main: CoroutineContext
        get() = testDispatcher
    override val IO: CoroutineContext
        get() = testDispatcher
}


