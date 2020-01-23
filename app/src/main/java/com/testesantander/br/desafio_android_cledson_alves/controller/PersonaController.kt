package com.testesantander.br.desafio_android_cledson_alves.controller

import android.util.Log
import androidx.annotation.NonNull
import com.testesantander.br.desafio_android_cledson_alves.BuildConfig.*
import com.testesantander.br.desafio_android_cledson_alves.model.Personagem
import com.testesantander.br.desafio_android_cledson_alves.model.PersonagemResult
import com.testesantander.br.desafio_android_cledson_alves.network.RetrofitInstance
import com.testesantander.br.desafio_android_cledson_alves.service.PersonaServices
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class PersonaController {

    fun getPersonagens(): ArrayList<PersonagemResult>? {
        val personaServices = RetrofitInstance.retrofitInstance?.create(PersonaServices::class.java)
        val call = personaServices?.getAllPersonagens(TS, PUBLIC_KEY, MD5)
        var personas = Personagem()

        call?.enqueue(object : Callback<Personagem> {
            override fun onResponse(@NonNull call: Call<Personagem>, @NonNull response: Response<Personagem>) {
                if (response.isSuccessful) {
                    personas = response.body()!!
                    Log.i("#PersonaController", "Response : $response.body()")
                } else {
                    Log.e("#PersonaController", "Response : $response.errorBody()")
                }
            }

            override fun onFailure(@NonNull call: Call<Personagem>, @NonNull t: Throwable) {
                Log.e("#PersonaController", "OnFailure :$t.message")
            }
        })

        return personas.data.personagemResult
    }
}