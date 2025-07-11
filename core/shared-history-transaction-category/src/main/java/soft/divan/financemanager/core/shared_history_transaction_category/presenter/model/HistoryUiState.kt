package soft.divan.financemanager.feature.expenses_income_shared.presenter.model

import soft.divan.financemanager.core.domain.util.DateHelper

sealed interface HistoryUiState {
    data object Loading : HistoryUiState
    data class Success(
        val transactions: List<UiTransaction>,
        val sumTransaction: String,

        val startDate: String = DateHelper.getCurrentMonthStartDisplayFormat(),
        val endDate: String = DateHelper.getTodayDisplayFormat(),
    ) : HistoryUiState

    data class Error(val message: String) : HistoryUiState
}