package soft.divan.financemanager.feature.income.income_impl.presenter.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import soft.divan.financemanager.core.domain.usecase.GetSumTransactionsUseCase
import soft.divan.financemanager.feature.expenses_income_shared.presenter.mapper.formatWith
import soft.divan.financemanager.feature.expenses_income_shared.presenter.mapper.toUi
import soft.divan.financemanager.feature.income.income_impl.domain.usecase.GetTodayIncomeUseCase
import soft.divan.financemanager.feature.income.income_impl.presenter.model.IncomeUiState
import javax.inject.Inject

@HiltViewModel
class IncomeViewModel @Inject constructor(
    private val getTodayIncomeUseCase: GetTodayIncomeUseCase,
    private val getSumTransactionsUseCase: GetSumTransactionsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<IncomeUiState>(IncomeUiState.Loading)
    val uiState: StateFlow<IncomeUiState> = _uiState
        .onStart { loadTodayIncome() }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            IncomeUiState.Loading
        )


    private fun loadTodayIncome() {
        getTodayIncomeUseCase()
            .onStart {
                _uiState.update { IncomeUiState.Loading }
            }
            .onEach { transactions ->
                val sumTransactions = getSumTransactionsUseCase(transactions.first)
                _uiState.update {
                    IncomeUiState.Success(
                        transactions = transactions.first.map { it.toUi(transactions.second) },
                        sumTransaction = sumTransactions.formatWith(transactions.second)
                    )
                }
            }
            .catch { exception ->
                _uiState.update { IncomeUiState.Error(exception.message ?: "Unknown error") }
            }
            .flowOn(Dispatchers.IO)
            .launchIn(viewModelScope)
    }
}