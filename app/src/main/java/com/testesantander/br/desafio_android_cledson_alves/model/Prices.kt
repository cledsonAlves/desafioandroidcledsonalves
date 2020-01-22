package com.testesantander.br.desafio_android_cledson_alves.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Prices : Serializable{

    @SerializedName("type")
    var type: String = ""
    @SerializedName("price")
    var price: Double = 0.0



}
