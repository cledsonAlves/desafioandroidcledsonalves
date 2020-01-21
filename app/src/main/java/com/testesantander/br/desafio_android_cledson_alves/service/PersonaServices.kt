package com.testesantander.br.desafio_android_cledson_alves.service

import com.testesantander.br.desafio_android_cledson_alves.BuildConfig.PUBLIC_KEY
import com.testesantander.br.desafio_android_cledson_alves.model.Personagem
import com.testesantander.br.desafio_android_cledson_alves.model.PersonagemResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PersonaServices {
    @GET("/v1/public/characters")
    fun getAllPersonagens(@Query("ts") ts: String, @Query("apikey")apkey:String, @Query("hash")hash:String): Call<Personagem>

    @GET("/v1/public/characters/{characterId}/comics")
    fun getAllHQ(@Query("characterId") id: Int,@Query(PUBLIC_KEY) chave: String)




}