package com.testesantander.br.desafio_android_cledson_alves.controller

import android.os.Build
import android.util.Log
import androidx.annotation.NonNull
import androidx.annotation.RequiresApi
import com.testesantander.br.desafio_android_cledson_alves.BuildConfig
import com.testesantander.br.desafio_android_cledson_alves.model.HQ
import com.testesantander.br.desafio_android_cledson_alves.model.Prices
import com.testesantander.br.desafio_android_cledson_alves.network.RetrofitInstance
import com.testesantander.br.desafio_android_cledson_alves.service.PersonaServices
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetalhePersonaController {

     fun getHQ(id: Int):ArrayList<Prices> {
        val personaServices = RetrofitInstance.retrofitInstance?.create(PersonaServices::class.java)
        val call = personaServices?.getAllHQ(id, BuildConfig.TS, BuildConfig.PUBLIC_KEY, BuildConfig.MD5)
        var listPrices =  ArrayList<Prices>()
        call?.enqueue(object : Callback<HQ> {
            @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
            override fun onResponse(@NonNull call: Call<HQ>, @NonNull response: Response<HQ>) {
                if (response.isSuccessful) {
                    val hq = response.body()
                    val results = hq?.data?.hqResult

                    for (it in 0 until results?.size!!){
                        for (x in 0 until results[it].price.size){
                            listPrices.add(results[it].price[x])
                        }
                    }
                    Log.i("#DetalheController", "Response : $response.body()")

                } else {
                    Log.e("#DetalheController", "Response : $response.errorBody()")
                }
            }
            override fun onFailure(@NonNull call: Call<HQ>, @NonNull t: Throwable) {
                Log.e("#DetalheController", "OnFailure : $t.message")
            }
        })
      return listPrices
    }

}