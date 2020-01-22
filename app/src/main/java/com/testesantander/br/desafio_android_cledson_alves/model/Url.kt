package com.testesantander.br.desafio_android_cledson_alves.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Url : Serializable{

    @SerializedName("type")
    var type: String = ""
    @SerializedName("url")
    var url: String = ""



}
