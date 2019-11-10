package ru.sample.domain.entity

import com.google.gson.annotations.SerializedName
import ru.sample.data.entity.enums.ETypeInputField

data class ServiceDescriptionEntity(
    val name: String = "",
    val description: String = "",
    val logoUrl: String = "",
    val coverUrl: String = "",
    @SerializedName("technicalSupportPhone") val phone: String = ""
)

data class ShortBankEntity(
    val id: Int,
    val fullName: String = "",
    val shortName: String = "",
    val coverUrl: String = "",
    val color: String = "",
    val smallIconUrl: String = ""
)

data class FullBankEntity(
    val id: Int,
    val fullName: String = "",
    val shortName: String = "",
    val coverUrl: String = "",
    val color: String = "",
    val smallIconUrl: String = "",
    val tariffs: MutableList<ItemTariff> = mutableListOf(),
    val urlFullTariffs: String = "",
    val textFullTariffs: String = "",
    val offer: String = ""
)

data class ItemTariff(
    val name: String = "",
    val value: String = ""
){
    //helper
    var isUrl: Boolean = false
    var url: String = ""
    var textUrl: String = ""
}

data class BankManifestEntity(
    @SerializedName("bankId") val id: Int? = null,
    @SerializedName("personalDataConf") val personalDataConfEntity: PersonalDataConfEntity = PersonalDataConfEntity(),
    @SerializedName("documentDataConf") val documentsDataConfEntity: DocumentsDataConfEntity = DocumentsDataConfEntity()
)

data class PersonalDataConfEntity(
    val inputFields: List<InputFieldEntity> = ArrayList(0)
)

data class InputFieldEntity(
    val id: Int,
    val typeInputField: ETypeInputField? = null,
    val name: String = "",
    val description: String = "",
    val maxLen: Int? = null,
    val minLen: Int? = null,
    val regExp: String = "",
    val isMandatoryField: Boolean = false,
    val errorMsg: String = ""
)

data class DocumentsDataConfEntity(
    @SerializedName("documentItems") val documentItemEntity: List<DocumentItemEntity> = ArrayList(0)
)

data class DocumentItemEntity(
    val id: Int,
    @SerializedName("short_name") val shortName: String = "",
    @SerializedName("full_name") val fullName: String = "",
    val maxCountFiles: Int? = null,
    val coverUrl: String = ""
){
    val countDocs: Int = 0
}
