package com.testesantander.br.desafio_android_cledson_alves.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.ArrayList



class PersonagemResult{

//    "id":1009165,
//    "name":"Avengers",
//"description"

    @SerializedName("id")
    var id: Int = 0
    @SerializedName("name")
    var name: String = ""
    @SerializedName("description")
    var description: String = ""

}