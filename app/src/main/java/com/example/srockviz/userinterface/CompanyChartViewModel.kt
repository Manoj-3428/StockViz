import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.stockviz.data.model.StockEntry
import com.example.stockviz.data.repository.CompanyRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CompanyChartViewModel(application: Application, company: String) : AndroidViewModel(application) {
    private val repo = CompanyRepository(application)
    private val _entries = MutableStateFlow<List<StockEntry>>(emptyList())
    val entries: StateFlow<List<StockEntry>> = _entries

    init {
        _entries.value = repo.getStockEntries(company)
    }
}
