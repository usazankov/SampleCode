package ru.sample.data.repository.datasource.cache

import android.content.Context
import com.google.gson.reflect.TypeToken
import io.reactivex.Observable
import ru.sample.data.repository.datasource.serializer.Serializer
import ru.sample.domain.entity.FullBankEntity
import ru.sample.domain.entity.ShortBankEntity
import ru.sample.domain.executor.ThreadExecutor
import java.io.File
import javax.inject.Inject

class BanksCacheImpl @Inject constructor(context: Context, serializer: Serializer, fileManager: FileManager, threadExecutor: ThreadExecutor) :
    BaseCache(context, serializer, fileManager, threadExecutor, SETTINGS_KEY_LAST_CACHE_UPDATE), IBanksCache{

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

    override fun getBank(bankId: Int?): Observable<FullBankEntity> {
        return Observable.create {
            if(bankId == null) it.onError(RuntimeException("bank id is null"))
            val userEntityFile = buildFile(bankId)
            val fileContent = fileManager.readFileContent(userEntityFile)
            val bankEntity: FullBankEntity = serializer.deserialize(fileContent, FullBankEntity::class.java)
            if (bankEntity.id != null) {
                it.onNext(bankEntity)
                it.onComplete()
            } else {
                it.onError(RuntimeException("bank id not found"))
            }
        }
    }

    override fun getBankList(): Observable<List<ShortBankEntity>> =
        Observable.create {
            val userEntityFile = buildFileBankList()
            val fileContent = fileManager.readFileContent(userEntityFile)
            val listOfBankEntityType = object : TypeToken<List<ShortBankEntity>>() {}.type
            val bankListEntity: List<ShortBankEntity> =
                serializer.deserialize(fileContent, listOfBankEntityType)
            if (validateBankList(bankListEntity)) {
                it.onNext(bankListEntity)
                it.onComplete()
            } else {
                it.onError(RuntimeException("bank id not found"))
            }
        }

    private fun validateBankList(list: List<ShortBankEntity>): Boolean {
        for (item in list) {
            if (item.id == null) return false;
        }
        return true;
    }

    override fun putBankList(bankEntity: List<ShortBankEntity>) {
        val userEntityFile = this.buildFileBankList()
        if (!isCachedBankList()) {
            val listOfBankEntityType = object : TypeToken<List<ShortBankEntity>>() {}.type
            val jsonString = this.serializer.serialize(bankEntity, listOfBankEntityType)
            this.executeAsynchronously(CacheWriter(this.fileManager, userEntityFile, jsonString))
            setLastCacheUpdateTimeMillis()
        }
    }

    override fun putBank(bankEntity: FullBankEntity) {
        if(bankEntity.id != null){
            val bankEntityFile = this.buildFile(bankEntity.id)
            if (!isCached(bankEntity.id)) {
                val jsonString = this.serializer.serialize(bankEntity, FullBankEntity::class.java)
                this.executeAsynchronously(
                    CacheWriter(
                        this.fileManager,
                        bankEntityFile,
                        jsonString
                    )
                )
                setLastCacheUpdateTimeMillis()
            }
        }
    }

    override fun isCached(bankId: Int?): Boolean {
        if(bankId == null) return false
        val bankEntityFile = this.buildFile(bankId)
        return this.fileManager.exists(bankEntityFile)
    }

    override fun isCachedBankList(): Boolean {
        val bankEntityFile = this.buildFileBankList()
        return this.fileManager.exists(bankEntityFile)
    }

    /**
     * Build a file, used to be inserted in the disk cache.
     *
     * @return A valid file.
     */
    private fun buildFileBankList(): File {
        val fileNameBuilder = StringBuilder()
        fileNameBuilder.append(this.cacheDir.path)
        fileNameBuilder.append(File.separator)
        fileNameBuilder.append(BANKS_LIST_FILE_NAME)
        return File(fileNameBuilder.toString())
    }

    /**
     * Build a file, used to be inserted in the disk cache.
     *
     * @param bankId The id user to build the file.
     * @return A valid file.
     */
    private fun buildFile(bankId: Int?): File {
        val fileNameBuilder = StringBuilder()
        fileNameBuilder.append(this.cacheDir.path)
        fileNameBuilder.append(File.separator)
        fileNameBuilder.append(DEFAULT_FILE_NAME)
        if(bankId != null) fileNameBuilder.append(bankId)

        return File(fileNameBuilder.toString())
    }

}