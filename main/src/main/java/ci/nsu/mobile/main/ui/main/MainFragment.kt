package ci.nsu.mobile.main.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.Lifecycle
import ci.nsu.mobile.main.R
import kotlinx.coroutines.launch

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val textCount = view.findViewById<TextView>(R.id.textCount)
        val textHistory = view.findViewById<TextView>(R.id.textHistory)

        val btnPlus = view.findViewById<Button>(R.id.buttonPlus)
        val btnMinus = view.findViewById<Button>(R.id.buttonMinus)
        val btnReset = view.findViewById<Button>(R.id.buttonReset)

        // кнопки
        btnPlus.setOnClickListener { viewModel.increment() }
        btnMinus.setOnClickListener { viewModel.decrement() }
        btnReset.setOnClickListener { viewModel.reset() }

        // наблюдение за состоянием
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    textCount.text = state.count.toString()

                    textHistory.text = if (state.history.isEmpty()) {
                        "Пока пусто"
                    } else {
                        state.history.reversed().joinToString("\n")
                    }
                }
            }
        }
    }
}