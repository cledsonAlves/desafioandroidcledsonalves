package com.testesantander.br.desafio_android_cledson_alves

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.testesantander.br.desafio_android_cledson_alves.model.Personagem
import com.testesantander.br.desafio_android_cledson_alves.model.PersonagemClickListener
import com.testesantander.br.desafio_android_cledson_alves.model.PersonagemResult
import com.testesantander.br.desafio_android_cledson_alves.network.RetrofitInstance
import com.testesantander.br.desafio_android_cledson_alves.service.PersonaServices
import com.testesantander.br.desafio_android_cledson_alves.ui.adapter.PersonaAdapter
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), PersonagemClickListener {
    override fun onClick(personagemResult: PersonagemResult) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        linearLayoutManager = LinearLayoutManager(this)
        recicler.layoutManager = linearLayoutManager

        Thread(Runnable {
            getPersonas()
        }).start()


    }

    private fun getPersonas() {
        val personaServices = RetrofitInstance.retrofitInstance?.create(PersonaServices::class.java)
        val call = personaServices?.getAllPersonagens(
            BuildConfig.TS,
            BuildConfig.PUBLIC_KEY,
            BuildConfig.MD5


        )

        call?.enqueue(object : Callback<Personagem> {
            override fun onResponse(@NonNull call: Call<Personagem>, @NonNull response: Response<Personagem>) {
                if (response.isSuccessful) {
                    val personas = response.body()
                    val personaResult = personas?.data?.personagemResult

                    recicler.adapter = personaResult?.let {
                        PersonaAdapter(it, object : PersonagemClickListener {
                            override fun onClick(per: PersonagemResult) {
                                Log.e("#SUCESS", "Response : " + per.name)
                               toast(""+ per.name)
                            }
                        })
                    }
                    Log.e("#SUCESS", "Response : " + personaResult?.size)
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

private operator fun <VH : RecyclerView.ViewHolder?> RecyclerView.Adapter<VH>?.invoke(personaResult: ArrayList<PersonagemResult>?) {

}
