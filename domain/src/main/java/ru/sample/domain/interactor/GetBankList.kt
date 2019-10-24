package ru.sample.domain.interactor

import io.reactivex.Observable
import ru.sample.domain.entity.ShortBankEntity
import ru.sample.domain.executor.PostExecutionThread
import ru.sample.domain.executor.ThreadExecutor
import ru.sample.domain.repository.IBanksRepository
import javax.inject.Inject

class GetBankList @Inject constructor(val bankRepository: IBanksRepository,
                  threadExecutor: ThreadExecutor,
                  postExecutionThread: PostExecutionThread
                  ) : UseCase<List<ShortBankEntity>, GetBankList.Params?>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseObservable(params: Params?): Observable<List<ShortBankEntity>> {
        return bankRepository.listBanks()
    }

    class Params
}