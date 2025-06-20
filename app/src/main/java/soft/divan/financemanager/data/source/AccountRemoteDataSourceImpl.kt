package soft.divan.financemanager.data.source

import soft.divan.financemanager.data.network.api.AccountApiService
import soft.divan.financemanager.data.network.dto.AccountDto
import soft.divan.financemanager.data.network.dto.AccountWithStatsDto
import soft.divan.financemanager.data.network.dto.CreateAccountRequestDto
import soft.divan.financemanager.data.network.dto.UpdateAccountRequestDto
import soft.divan.financemanager.data.util.safeHttpResult
import soft.divan.financemanager.domain.model.AccountBrief
import soft.divan.financemanager.domain.utils.Rezult
import javax.inject.Inject

class AccountRemoteDataSourceImpl @Inject constructor(
    private val accountApiService: AccountApiService,
) : AccountRemoteDataSource {
    override suspend fun getAccounts(): Rezult<List<AccountDto>> {
        return safeHttpResult { accountApiService.getAccounts() }
    }

    override suspend fun createAccount(createAccountRequestDto: CreateAccountRequestDto): Rezult<AccountDto> {
        return safeHttpResult { accountApiService.createAccount(createAccountRequestDto) }
    }

    override suspend fun updateAccount(accountBrief: AccountBrief): Rezult<AccountWithStatsDto> {
        val requestDto = UpdateAccountRequestDto(
            name = accountBrief.name,
            balance = accountBrief.balance.toPlainString(),
            currency = accountBrief.currency
        )

        val accountId = accountBrief.id
        return safeHttpResult { accountApiService.updateAccount(accountId, requestDto) }
    }
}