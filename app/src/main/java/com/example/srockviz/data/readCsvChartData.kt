package com.example.srockviz.data

import android.content.Context
import com.example.srockviz.R // Make sure this import matches your package name
import com.github.mikephil.charting.data.Entry
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.InputStreamReader

suspend fun readCsvAndCreateChartData(context: Context, company: String): List<Entry> {
    return withContext(Dispatchers.IO) {
        val entries = mutableListOf<Entry>()

        val inputStream = context.resources.openRawResource(R.raw.stocks) // Use raw resource
        val reader = BufferedReader(InputStreamReader(inputStream))
        var index = 0f

        reader.readLine() // Skip header
        reader.forEachLine { line ->
            val parts = line.split(",")
            if (parts.size == 3 && parts[0].trim() == company.trim()) {
                val price = parts[2].toFloatOrNull()
                if (price != null) {
                    entries.add(Entry(index++, price))
                }
            }
        }

        entries
    }
}
