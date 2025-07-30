package soft.divan.financemanager.feature.security.security_impl.domain.usecase.impl

import soft.divan.financemanager.feature.security.security_impl.domain.repository.SecurityRepository
import soft.divan.financemanager.feature.security.security_impl.domain.usecase.DeletePinUseCase
import javax.inject.Inject

class DeletePinUseCaseImpl @Inject constructor(
    val repository: SecurityRepository
) : DeletePinUseCase {
    override fun invoke() {
        repository.deletePin()
    }
}