package ci.nsu.mobile.main.ui.main

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlin.coroutines.Continuation

data class CounterUiState(
    val count: Int = 0,
    val history: List<String> = emptyList()
)
object Constans {
    const val MAX_S = 4
}

class MainViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(CounterUiState())
    val uiState: StateFlow<CounterUiState> = _uiState.asStateFlow()

    fun increment() {
        _uiState.update { state ->
            val newCount = state.count + 1
            val newHistory = listOf("+1 (итого: $newCount)") + state.history.take(Constans.MAX_S)

            state.copy(count = newCount, history = newHistory)
        }
    }

    fun decrement() {
        _uiState.update { state ->
            if (state .count == 0) return@update state
            val newCount = state.count - 1
            val newHistory = listOf("-1 (итого: $newCount)") + state.history.take(Constans.MAX_S)

            state.copy(count = newCount, history = newHistory)
        }
    }

    fun reset() {
        _uiState.update { state ->
            val newHistory = listOf("Сброс (итого: 0)") + state.history.take(Constans.MAX_S)

            state.copy(count = 0, history = newHistory)
        }
    }
}