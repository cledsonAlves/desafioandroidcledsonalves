package com.testesantander.br.desafio_android_cledson_alves.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable


class HQResult : Serializable {

    @SerializedName("id")
    var id: Int = 0

    @SerializedName("urls")
    lateinit var urls: ArrayList<Url>

    @SerializedName("prices")
    lateinit var price: ArrayList<Prices>

}