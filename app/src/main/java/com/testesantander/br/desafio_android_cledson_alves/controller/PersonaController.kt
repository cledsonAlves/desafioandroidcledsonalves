package com.testesantander.br.desafio_android_cledson_alves.controller

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.annotation.NonNull
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.recyclerview.widget.RecyclerView
import com.testesantander.br.desafio_android_cledson_alves.BuildConfig.*
import com.testesantander.br.desafio_android_cledson_alves.model.Personagem
import com.testesantander.br.desafio_android_cledson_alves.model.PersonagemResult
import com.testesantander.br.desafio_android_cledson_alves.network.RetrofitInstance
import com.testesantander.br.desafio_android_cledson_alves.service.PersonaServices
import com.testesantander.br.desafio_android_cledson_alves.ui.activity.DetalhePersonaActivity
import com.testesantander.br.desafio_android_cledson_alves.ui.adapter.PersonaAdapter
import com.testesantander.br.desafio_android_cledson_alves.ui.utils.PersonagemClickListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PersonaController  {

    private fun getPersonas() {

        val personaServices = RetrofitInstance.retrofitInstance?.create(PersonaServices::class.java)
        val call = personaServices?.getAllPersonagens(TS, PUBLIC_KEY, MD5)


        call?.enqueue(object : Callback<Personagem> {
            override fun onResponse(@NonNull call: Call<Personagem>, @NonNull response: Response<Personagem>) {
                if (response.isSuccessful) {
                    val personas = response.body()
                    val personaResult = personas?.data?.personagemResult

                } else {
                    Log.e("#NotSucces", "Response : " + response.message())
                }
            }

            override fun onFailure(@NonNull call: Call<Personagem>, @NonNull t: Throwable) {
                Log.e("#Error", "OnFailure :" + t.message)
            }
        })

    }
}