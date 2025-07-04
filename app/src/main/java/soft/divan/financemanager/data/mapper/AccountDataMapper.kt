package soft.divan.financemanager.data.mapper

import soft.divan.financemanager.data.entity.AccountEntity
import soft.divan.financemanager.data.entity.AccountStateEntity
import soft.divan.financemanager.data.network.dto.AccountDto
import soft.divan.financemanager.data.network.dto.AccountStateDto
import soft.divan.financemanager.data.network.dto.AccountWithStatsDto
import soft.divan.financemanager.domain.model.Account
import soft.divan.financemanager.domain.model.AccountBrief
import java.math.BigDecimal
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun AccountDto.toEntity(): AccountEntity = AccountEntity(
    id = this.id,
    userId = this.userId,
    name = this.name,
    balance = this.balance,
    currency = this.currency,
    createdAt = this.createdAt,
    updatedAt = this.updatedAt
)

private val formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME
fun AccountEntity.toDomain(): Account = Account(
    id = this.id,
    userId = this.userId,
    name = this.name,
    balance = this.balance.toBigDecimal(),
    currency = this.currency,
    createdAt = LocalDateTime.parse(this.createdAt, formatter),
    updatedAt = LocalDateTime.parse(this.updatedAt, formatter)
)

fun AccountWithStatsDto.toAccountBriefDomain(): AccountBrief = AccountBrief(
    id = this.id,
    name = this.name,
    balance = this.balance.toBigDecimalOrNull() ?: BigDecimal.ZERO,
    currency = this.currency
)

fun AccountStateDto.toEntity(): AccountStateEntity = AccountStateEntity(
    id = this.id,
    name = this.name,
    balance = this.balance,
    currency = this.currency
)