package br.com.gilbarco.clientes.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.gilbarco.clientes.presenter.CountryPresenter

class CountryViewModelFactory(private val context: Context): ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CountryViewModel::class.java)) {
            return CountryViewModel(
                CountryPresenter(context)
            ) as T
        }
        throw IllegalArgumentException("Classe ViewModel desconhecida")
    }
}
