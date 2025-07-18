package soft.divan.financemanager.feature.account.account_impl.domain.usecase.impl

import soft.divan.financemanager.core.domain.model.CurrencyCode
import soft.divan.financemanager.core.domain.repository.CurrencyRepository
import soft.divan.financemanager.feature.account.account_impl.domain.usecase.UpdateCurrencyUseCase
import javax.inject.Inject

class UpdateCurrencyUseCaseIml @Inject constructor(
    private val currencyRepository: CurrencyRepository
) : UpdateCurrencyUseCase {

    override suspend fun invoke(currency: CurrencyCode) {
        currencyRepository.updateCurrency(currency)
    }
}