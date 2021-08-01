package ru.awac.mvvmtutorial.ui.quotes

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import ru.awac.mvvmtutorial.R
import ru.awac.mvvmtutorial.data.Quote
import ru.awac.mvvmtutorial.utilities.InjectorUtils

class QuotesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quotes)
        initializeUi()


    }

    private fun initializeUi() {
        val textViewQuotes: TextView = findViewById(R.id.textView_quotes)
        val buttonAddQuote: Button = findViewById(R.id.button_add_quote)
        val editTextQuote: EditText = findViewById(R.id.editText_quote)
        val editTextAuthor: EditText = findViewById(R.id.editText_author)
        // Get the QuotesViewModelFactory with all of it's dependencies constructed
        val factory = InjectorUtils.provideQuotesViewModelFactory()
        // Use ViewModelProviders class to create / get already created QuotesViewModel
        // for this view (activity)
        val viewModel = ViewModelProviders.of(this, factory).get(QuotesViewModel::class.java)

        // Observing LiveData from the QuotesViewModel which in turn observes
        // LiveData from the repository, which observes LiveData from the DAO
        viewModel.getQuotes().observe(this, { quotes ->
            val stringBuilder = StringBuilder()
            quotes.forEach { quote ->
                stringBuilder.append("$quote\n\n")
            }
            textViewQuotes.text = stringBuilder.toString()
        })

        // When button is clicked, instantiate a Quote and add it to DB through the ViewModel
        buttonAddQuote.setOnClickListener {
            val quote = Quote(editTextQuote.text.toString(), editTextAuthor.text.toString())
            viewModel.addQuotes(quote)
            editTextQuote.setText("")
            editTextAuthor.setText("")
        }
    }
}