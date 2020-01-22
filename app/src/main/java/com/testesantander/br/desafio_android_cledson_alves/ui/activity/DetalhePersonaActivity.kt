package com.testesantander.br.desafio_android_cledson_alves.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import com.testesantander.br.desafio_android_cledson_alves.BuildConfig.*
import com.testesantander.br.desafio_android_cledson_alves.R
import com.testesantander.br.desafio_android_cledson_alves.model.HQ
import com.testesantander.br.desafio_android_cledson_alves.model.Personagem
import com.testesantander.br.desafio_android_cledson_alves.model.PersonagemResult
import com.testesantander.br.desafio_android_cledson_alves.model.Prices
import com.testesantander.br.desafio_android_cledson_alves.network.RetrofitInstance
import com.testesantander.br.desafio_android_cledson_alves.service.PersonaServices
import kotlinx.android.synthetic.main.activity_detalhe_persona.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.max


class DetalhePersonaActivity : AppCompatActivity() {
    private lateinit var personagemHQ: PersonagemResult
    lateinit var listPrices:Prices

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhe_persona)
        initComponts()
        getPersonas(personagemHQ.id)

    }

    fun initComponts() {
        val intent = getIntent()
        personagemHQ = getIntent().getSerializableExtra("personagem") as PersonagemResult

        if (intent != null) {
            val params = intent!!.getExtras()
            if (params != null) {
                txt_nome.text = personagemHQ.name
                txt_descricao.text = personagemHQ.description

            }
        }
    }

    private fun getPersonas(id: Int) {
        val personaServices = RetrofitInstance.retrofitInstance?.create(PersonaServices::class.java)
        val call = personaServices?.getAllHQ(id, TS, PUBLIC_KEY, MD5)



        call?.enqueue(object : Callback<HQ> {
            override fun onResponse(@NonNull call: Call<HQ>, @NonNull response: Response<HQ>) {
                if (response.isSuccessful) {
                    val hq = response.body()
                    val results = hq?.data?.hqResult

                    var listPrices = ArrayList<Prices>()

                    for (it in 0 until results?.size!!){
                        for (x in 0 until results[it].price.size){
                            listPrices.add(results[it].price[x])
                        }

                    }

                    personagemHQ.price = listPrices
                    

                        btn_visualizar.setOnClickListener {
                        val bundle = Bundle()
                        bundle.putSerializable("personagem", personagemHQ)

                        val intent =
                            Intent(this@DetalhePersonaActivity, DetalheHQActivity::class.java)
                        intent.putExtras(bundle)
                        startActivityForResult(intent, 1)
                        finish()
                    }
                } else {
                    Log.e("#NotSucces", "Response : " + response.message())
                }
            }

            override fun onFailure(@NonNull call: Call<HQ>, @NonNull t: Throwable) {
                Log.e("#Error", "OnFailure :" + t.message)
            }
        })

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 2) {
        }
    }
}