package soft.divan.financemanager.presenter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import soft.divan.financemanager.core.network.util.NetworkMonitor
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject constructor(
    networkMonitor: NetworkMonitor
) : ViewModel() {


    val isOffline = networkMonitor.isOnline
        .map(Boolean::not)
        .flowOn(Dispatchers.IO)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = false,
        )
}