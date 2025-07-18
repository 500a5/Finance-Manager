package soft.divan.financemanager.core.domain.usecase

import kotlinx.coroutines.flow.Flow
import soft.divan.financemanager.core.domain.model.Account

interface GetAccountsUseCase {
    operator fun invoke(): Flow<List<Account>>
}