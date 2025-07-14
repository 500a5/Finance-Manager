package soft.divan.financemanager.feature.account.account_impl.presenter.model

sealed interface AccountUiState {
    data object Loading : AccountUiState
    data class Success(val account: AccountUiModel) : AccountUiState
    data class Error(val message: String) : AccountUiState
}