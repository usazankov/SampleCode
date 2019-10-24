package ru.sample.data.repository.datasource.cache

import android.content.Context
import io.reactivex.Observable
import ru.sample.data.repository.datasource.serializer.Serializer
import ru.sample.domain.entity.FullBankEntity
import ru.sample.domain.entity.ShortBankEntity
import ru.sample.domain.executor.ThreadExecutor
import java.io.File
import javax.inject.Inject

class BanksCacheImpl @Inject constructor(context: Context, serializer: Serializer, fileManager: FileManager, threadExecutor: ThreadExecutor) :
    BaseCache(context, serializer, fileManager, threadExecutor, SETTINGS_KEY_LAST_CACHE_UPDATE), IBanksCache{

    override fun getBank(bankId: Int): Observable<FullBankEntity> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getBankList(): Observable<List<ShortBankEntity>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun putBankList(bankEntity: List<ShortBankEntity>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun putBank(bankEntity: FullBankEntity) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isCached(bankId: Int): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isCachedBankList(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    init {
        val rootDir = context.cacheDir
        val path = File(rootDir, "banks/")
        if (!path.exists()) {
            path.mkdir()
        }
        cacheDir = path
    }

    companion object{
        val SETTINGS_KEY_LAST_CACHE_UPDATE = "bank_cache_update";
        val DEFAULT_FILE_NAME = "bank_";
        val BANKS_LIST_FILE_NAME = "banks_list";
    }
}