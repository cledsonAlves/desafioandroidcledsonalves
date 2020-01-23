package com.testesantander.br.desafio_android_cledson_alves.ui.activity



import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import com.testesantander.br.desafio_android_cledson_alves.BuildConfig.*
import com.testesantander.br.desafio_android_cledson_alves.R
import com.testesantander.br.desafio_android_cledson_alves.model.HQ
import com.testesantander.br.desafio_android_cledson_alves.model.PersonagemResult
import com.testesantander.br.desafio_android_cledson_alves.model.Prices
import com.testesantander.br.desafio_android_cledson_alves.network.RetrofitInstance
import com.testesantander.br.desafio_android_cledson_alves.service.PersonaServices
import com.testesantander.br.desafio_android_cledson_alves.ui.utils.Util
import kotlinx.android.synthetic.main.activity_detalhe_persona.*
import kotlinx.android.synthetic.main.activity_detalhe_persona.txt_nome
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DetalhePersonaActivity : AppCompatActivity() {
    private lateinit var personagemHQ: PersonagemResult

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhe_persona)
        initComponents()
    }

   private fun initComponents() {
        Thread{
            runOnUiThread{
                val path = "${personagemHQ.thunbnail.patch}.${personagemHQ.thunbnail.extension}"
                Util.getPicture(applicationContext,path,iv_persona_detalhe,progressBar_detalhe)
                getPersonasService(personagemHQ.id)
            }
        }.start()

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

    private fun getPersonasService(id: Int) {
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

                        val intent = Intent(this@DetalhePersonaActivity, DetalheHQActivity::class.java)
                        intent.putExtras(bundle)
                        startActivityForResult(intent, 1)
                        finish()
                    }
                } else {
                    Log.e("#DetalhePersActivity", "Response : " + response.message())
                }
            }
            override fun onFailure(@NonNull call: Call<HQ>, @NonNull t: Throwable) {
                Log.e("#DetalhePersActivity", "OnFailure :" + t.message)
            }
        })
    }
}
