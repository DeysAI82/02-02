package ci.nsu.moble.main.ui.main

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.graphics.Color
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.EditText
import android.widget.LinearLayout

import ci.nsu.moble.main.R

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        val editText = view.findViewById<EditText>(R.id.editTextText)
        val linearLayout = view.findViewById<LinearLayout>(R.id.linearLayout)


        fun getColorByName(name: String): Int {
            return when (name.trim().lowercase()) {
                "yellow" -> Color.YELLOW
                "red" -> Color.RED
                "green" -> Color.GREEN
                "blue" -> Color.BLUE
                "black" -> Color.BLACK
                else -> Color.GRAY
            }
        }

        button.setOnClickListener {
            val colorText = editText.text.toString()
            val color = getColorByName(colorText)
            button.setBackgroundColor(color)
            Log.d("MainFragment", "Цвет кнопки изменён на: $colorText")
        }


        for (i in 0 until linearLayout.childCount) {
            val child = linearLayout.getChildAt(i)
            if (child is TextView) {
                val text = child.text.toString()
                val color = getColorByName(text)
                child.setBackgroundColor(color)
            }
        }
    }
}