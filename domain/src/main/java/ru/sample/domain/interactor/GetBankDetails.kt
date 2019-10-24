package ru.sample.domain.interactor

import io.reactivex.Observable
import ru.sample.domain.entity.FullBankEntity
import ru.sample.domain.executor.PostExecutionThread
import ru.sample.domain.executor.ThreadExecutor
import ru.sample.domain.repository.IBanksRepository
import javax.inject.Inject

class GetBankDetails @Inject constructor(
    private val banksRepository: IBanksRepository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : UseCase<FullBankEntity, GetBankDetails.Params>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseObservable(params: Params): Observable<FullBankEntity> {
        return banksRepository.bankDescription(params.bankId)
    }

    class Params private constructor(val bankId: Int) {
        companion object {
            fun forBankId(bankId: Int): Params {
                return Params(bankId)
            }
        }
    }
}