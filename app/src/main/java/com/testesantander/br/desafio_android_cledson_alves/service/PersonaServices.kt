package com.testesantander.br.desafio_android_cledson_alves.service

import com.testesantander.br.desafio_android_cledson_alves.BuildConfig.PUBLIC_KEY
import com.testesantander.br.desafio_android_cledson_alves.model.PersonagemResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PersonaServices {
    @GET("/v1/public/characters")
    fun getAllPersonagens(@Query(PUBLIC_KEY) chave: String): Call<PersonagemResult>

    @GET("/v1/public/characters/{characterId}/comics")
    fun getAllHQ(@Query("characterId") id: Int,@Query(PUBLIC_KEY) chave: String)

}