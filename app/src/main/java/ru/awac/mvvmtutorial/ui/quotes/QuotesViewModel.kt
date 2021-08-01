package ru.awac.mvvmtutorial.ui.quotes

import androidx.lifecycle.ViewModel
import ru.awac.mvvmtutorial.data.Quote
import ru.awac.mvvmtutorial.data.QuoteRepository

// QuoteRepository dependency will again be passed in the
// constructor using dependency injection
class QuotesViewModel(private val quoteRepository: QuoteRepository) : ViewModel() {

    fun getQuotes() = quoteRepository.getQuotes()

    fun addQuotes(quote: Quote) = quoteRepository.addQuotes(quote)
}