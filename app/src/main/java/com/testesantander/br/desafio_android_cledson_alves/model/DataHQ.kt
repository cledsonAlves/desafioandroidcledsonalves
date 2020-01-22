package com.testesantander.br.desafio_android_cledson_alves.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.ArrayList

class DataHQ : Serializable{

    @SerializedName("ofsset")
    var offset: Int = 0
    @SerializedName("limit")
    var limit: Int = 0
    @SerializedName("total")
    var total: Int = 0
    @SerializedName("count")
    var count: Int = 0
    @SerializedName("results")
    var hqResult: ArrayList<HQResult>? = null



}