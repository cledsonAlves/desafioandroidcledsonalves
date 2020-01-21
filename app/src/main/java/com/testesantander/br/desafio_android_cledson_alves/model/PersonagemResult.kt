package com.testesantander.br.desafio_android_cledson_alves.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.ArrayList



class PersonagemResult(
@field:SerializedName("id")
var page: Int, @field:SerializedName("name")
var totalResults: Int, @field:SerializedName("result")
var personagemResult: ArrayList<Personagem>?
) : Serializable