package soft.divan.financemanager.data.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import soft.divan.financemanager.data.network.util.ConnectivityManagerNetworkMonitor
import soft.divan.financemanager.data.network.util.NetworkMonitor
import soft.divan.financemanager.data.repository.AccountRepositoryImpl
import soft.divan.financemanager.data.repository.CategoryRepositoryImpl
import soft.divan.financemanager.data.repository.CurrencyRepositoryImpl
import soft.divan.financemanager.data.repository.TransactionRepositoryImp
import soft.divan.financemanager.data.source.AccountRemoteDataSource
import soft.divan.financemanager.data.source.AccountRemoteDataSourceImpl
import soft.divan.financemanager.data.source.CategoryRemoteDataSource
import soft.divan.financemanager.data.source.CategoryRemoteDataSourceImpl
import soft.divan.financemanager.data.source.CurrencyLocalDataSource
import soft.divan.financemanager.data.source.CurrencyLocalDataSourceImpl
import soft.divan.financemanager.data.source.TransactionRemoteDataSource
import soft.divan.financemanager.data.source.TransactionRemoteDataSourceImpl
import soft.divan.financemanager.domain.repository.AccountRepository
import soft.divan.financemanager.domain.repository.CategoryRepository
import soft.divan.financemanager.domain.repository.CurrencyRepository
import soft.divan.financemanager.domain.repository.TransactionRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    abstract fun bindAccountRemoteDataSource(
        impl: AccountRemoteDataSourceImpl
    ): AccountRemoteDataSource

    @Binds
    abstract fun bindAccountRepository(
        impl: AccountRepositoryImpl
    ): AccountRepository


    @Binds
    abstract fun bindTransactionRemoteDataSource(
        impl: TransactionRemoteDataSourceImpl
    ): TransactionRemoteDataSource

    @Binds
    abstract fun bindTransactionRepository(
        impl: TransactionRepositoryImp
    ): TransactionRepository

    @Binds
    abstract fun bindNetworkMonitor(
        networkMonitor: ConnectivityManagerNetworkMonitor,
    ): NetworkMonitor


    @Binds
    abstract fun bindCategoryRemoteDataSource(
        impl: CategoryRemoteDataSourceImpl
    ): CategoryRemoteDataSource

    @Binds
    abstract fun bindCategoryRepository(
        impl: CategoryRepositoryImpl
    ): CategoryRepository

    @Binds
    abstract fun bindCurrencyRepository(
        impl: CurrencyRepositoryImpl
    ): CurrencyRepository


}

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    private val Context.dataStore by preferencesDataStore("currency_dataStore")

    @Provides
    fun provideDataStore(@ApplicationContext context: Context): DataStore<Preferences> {
        return context.dataStore
    }

    @Provides
    fun provideCurrencyLocalDataSource(
        dataStore: DataStore<Preferences>
    ): CurrencyLocalDataSource {
        return CurrencyLocalDataSourceImpl(dataStore)
    }
}