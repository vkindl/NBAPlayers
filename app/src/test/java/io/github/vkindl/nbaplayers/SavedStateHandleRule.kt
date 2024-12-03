package io.github.vkindl.nbaplayers

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.internalToRoute
import io.github.vkindl.nbaplayers.core.navigation.Destination
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.unmockkStatic
import org.junit.rules.TestWatcher
import org.junit.runner.Description

class SavedStateHandleRule(
    private val destination: Destination,
) : TestWatcher() {
    val savedStateHandle: SavedStateHandle = mockk()
    override fun starting(description: Description?) {
        mockkStatic(SAVED_STATE_HANDLE_CLASS)
        every { savedStateHandle.internalToRoute<Any>(any(), any()) } returns destination
        super.starting(description)
    }

    override fun finished(description: Description?) {
        unmockkStatic(SAVED_STATE_HANDLE_CLASS)
        super.finished(description)
    }

    private companion object {
        const val SAVED_STATE_HANDLE_CLASS = "androidx.navigation.SavedStateHandleKt"
    }
}
