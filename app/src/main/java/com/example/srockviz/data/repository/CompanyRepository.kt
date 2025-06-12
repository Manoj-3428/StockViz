package com.example.stockviz.data.repository

import android.content.Context
import com.example.srockviz.data.model.Company
import com.example.srockviz.util.CsvParser
import com.example.stockviz.data.model.StockEntry

class CompanyRepository(private val context: Context) {
    fun getCompanies(): List<Company> = CsvParser.parseCompanies(context)
    fun getStockEntries(company: String): List<StockEntry> = CsvParser.loadStockEntries(context, company)
}
