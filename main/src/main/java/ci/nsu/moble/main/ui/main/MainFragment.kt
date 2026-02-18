package ci.nsu.moble.main.ui.main

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.graphics.Color
import android.widget.Button
import android.widget.TextView
import android.widget.EditText

import ci.nsu.moble.main.R

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val button = view.findViewById<Button>(R.id.button)
        val textView = view.findViewById<TextView>(R.id.textView)
        val editText = view.findViewById<EditText>(R.id.editTextText)

        button.setOnClickListener {

            val colorText = editText.text.toString().lowercase()

            when (colorText) {
                "yellow" -> button.setBackgroundColor(Color.YELLOW)
                "red" -> button.setBackgroundColor(Color.RED)
                "green" -> button.setBackgroundColor(Color.GREEN)
                "blue" -> button.setBackgroundColor(Color.BLUE)
                "black" -> button.setBackgroundColor(Color.BLACK)
                else -> button.setBackgroundColor(Color.GRAY)
            }
        }
    }
}