package soft.divan.financemanager.presenter.ui.model

import soft.divan.financemanager.category.presenter.model.UiCategory
import java.math.BigDecimal
import java.time.LocalDateTime

data class UiTransaction(
    val id: Int,
    val accountId: Int,
    val category: UiCategory,
    val amount: BigDecimal,
    val amountFormatted: String,
    val transactionDate: LocalDateTime,
    val comment: String?,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)