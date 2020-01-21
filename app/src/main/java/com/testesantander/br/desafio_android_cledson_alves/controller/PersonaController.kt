package com.testesantander.br.desafio_android_cledson_alves.controller

import android.util.Log
import androidx.annotation.NonNull
import com.testesantander.br.desafio_android_cledson_alves.BuildConfig.*
import com.testesantander.br.desafio_android_cledson_alves.model.Personagem
import com.testesantander.br.desafio_android_cledson_alves.network.RetrofitInstance
import com.testesantander.br.desafio_android_cledson_alves.service.PersonaServices
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PersonaController {


    fun getPersonas() {
        val personaServices = RetrofitInstance.retrofitInstance?.create(PersonaServices::class.java)
        val call = personaServices?.getAllPersonagens(TS, PUBLIC_KEY, MD5)

        call?.enqueue(object : Callback<Personagem> {
            override fun onResponse(@NonNull call: Call<Personagem>, @NonNull response: Response<Personagem>) {
                if (response.isSuccessful) {
                    Log.e("#SUCESS", "Response : " + response.body()!!.data.personagemResult?.size)
                }else {
                    Log.e("#NotSucces", "Response : " + response.message())
                }

            }

            override fun onFailure(@NonNull call: Call<Personagem>, @NonNull t: Throwable) {
                Log.e("#Error", "OnFailure :" + t.message)
            }
        })

    }
}