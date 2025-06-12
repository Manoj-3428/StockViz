package com.example.srockviz.util

import android.content.Context
import com.example.srockviz.R
import com.example.srockviz.data.model.Company
import com.example.stockviz.data.model.StockEntry

object CsvParser {

    fun parseCompanies(context: Context): List<Company> {
        context.resources.openRawResource(R.raw.stocks).bufferedReader().useLines { lines ->
            return lines.drop(1)
                .mapNotNull { line ->
                    val parts = line.split(",")
                    if (parts.size >= 2) {
                        val name = parts[0].trim()
                        val sector = parts[1].trim()
                        Company(name = name, sector = sector)
                    } else null
                }
                .distinctBy { it.name } // Avoid duplicate companies
                .toList()
        }
    }

    fun loadStockEntries(context: Context, company: String): List<StockEntry> {
        context.resources.openRawResource(R.raw.stocks).bufferedReader().useLines { lines ->
            return lines.drop(1)
                .map { it.split(",") }
                .filter { it[0].trim() == company }
                .mapIndexedNotNull { index, parts ->
                    try {
                        val price = parts[2].toFloat()
                        StockEntry(x = index.toFloat(), y = price)
                    } catch (e: Exception) {
                        null // Skip invalid rows
                    }
                }
                .toList()
        }
    }
}
