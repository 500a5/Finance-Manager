package soft.divan.financemanager.presenter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dagger.hilt.android.AndroidEntryPoint
import soft.divan.financemanager.feature.account.account_impl.AccountFeatureApi
import soft.divan.financemanager.feature.category.category_api.CategoryFeatureApi
import soft.divan.financemanager.feature.expenses.expenses_api.ExpensesFeatureApi
import soft.divan.financemanager.feature.income.income_api.IncomeFeatureApi
import soft.divan.financemanager.feature.settings.settings_api.SettingsFeatureApi
import soft.divan.financemanager.feature.transaction.transaction_api.TransactionFeatureApi
import soft.divan.financemanager.presenter.screens.MainScreen
import soft.divan.financemanager.uikit.theme.FinanceManagerTheme
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var settingsFeatureApi: SettingsFeatureApi

    @Inject
    lateinit var incomeFeatureApi: IncomeFeatureApi

    @Inject
    lateinit var expensesFeatureApi: ExpensesFeatureApi

    @Inject
    lateinit var categoryFeatureApi: CategoryFeatureApi

    @Inject
    lateinit var accountFeatureApi: AccountFeatureApi

    @Inject
    lateinit var transactionFeatureApi: TransactionFeatureApi


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FinanceManagerTheme {
                MainScreen(
                    incomeFeatureApi = incomeFeatureApi,
                    expenseFeatureApi = expensesFeatureApi,
                    categoryFeatureApi = categoryFeatureApi,
                    accountFeatureApi = accountFeatureApi,
                    settingsFeatureApi = settingsFeatureApi,
                    transactionFeatureApi = transactionFeatureApi
                )
            }
        }
    }

}
