package com.testesantander.br.desafio_android_cledson_alves.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable


class PersonagemResult : Serializable {

//    "id":1009165,
//    "name":"Avengers",
//"description"

    @SerializedName("id")
    var id: Int = 0
    @SerializedName("name")
    var name: String = ""
    @SerializedName("description")
    var description: String = ""

    @SerializedName("modified")
    var modified: String = ""

    @SerializedName("thumbnail")
    lateinit var thunbnail: Thumbnail

    @SerializedName("urls")
    lateinit var urls: ArrayList<Url>

    @SerializedName("prices")
    lateinit var price: ArrayList<Prices>




}