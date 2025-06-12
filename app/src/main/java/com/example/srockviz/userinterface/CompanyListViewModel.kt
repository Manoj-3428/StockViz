package com.example.srockviz.userinterface

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.srockviz.data.model.Company
import com.example.stockviz.data.repository.CompanyRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CompanyListViewModel(application: Application) : AndroidViewModel(application) {
    private val repo = CompanyRepository(application)
    private val _companies = MutableStateFlow<List<Company>>(emptyList())
    val companies: StateFlow<List<Company>> = _companies

    init {
        _companies.value = repo.getCompanies()
    }
}
