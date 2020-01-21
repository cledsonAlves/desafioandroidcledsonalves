package com.testesantander.br.desafio_android_cledson_alves.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.ArrayList

class Data : Serializable {

    //    "data": {
//        "offset": 0,
//        "limit": 20,
//        "total": 1,
//        "count": 1,

    @SerializedName("ofsset")
    var offset: Int = 0
    @SerializedName("limit")
    var limit: Int = 0
    @SerializedName("total")
    var total: Int = 0
    @SerializedName("count")
    var count: Int = 0
    @SerializedName("results")
    var personagemResult: ArrayList<PersonagemResult>? = null


}