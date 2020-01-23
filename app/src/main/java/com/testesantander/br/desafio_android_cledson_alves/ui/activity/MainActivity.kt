package com.testesantander.br.desafio_android_cledson_alves.ui.activity



import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.testesantander.br.desafio_android_cledson_alves.BuildConfig
import com.testesantander.br.desafio_android_cledson_alves.BuildConfig.*
import com.testesantander.br.desafio_android_cledson_alves.R
import com.testesantander.br.desafio_android_cledson_alves.controller.PersonaController
import com.testesantander.br.desafio_android_cledson_alves.model.Personagem
import com.testesantander.br.desafio_android_cledson_alves.model.PersonagemResult
import com.testesantander.br.desafio_android_cledson_alves.network.RetrofitInstance
import com.testesantander.br.desafio_android_cledson_alves.service.PersonaServices
import com.testesantander.br.desafio_android_cledson_alves.ui.adapter.PersonaAdapter
import com.testesantander.br.desafio_android_cledson_alves.ui.utils.PersonagemClickListener
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.indeterminateProgressDialog
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity(){

    private lateinit var linearLayoutManager: LinearLayoutManager
    lateinit var progress : Dialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager
        initComponents()
    }

    private fun initComponents() {
        progress =  indeterminateProgressDialog("Carregando aguarde ... ")
        val personaServices = RetrofitInstance.retrofitInstance?.create(PersonaServices::class.java)
        val call = personaServices?.getAllPersonagens(TS, PUBLIC_KEY, MD5)

        call?.enqueue(object : Callback<Personagem> {
            override fun onResponse(@NonNull call: Call<Personagem>, @NonNull response: Response<Personagem>) {
                progress.dismiss()
                if (response.isSuccessful) {
                    val personaResult = response.body()?.data?.personagemResult
                    recyclerView.adapter = personaResult?.let {
                        PersonaAdapter(it, object :
                            PersonagemClickListener {
                            override fun onClick(personagem: PersonagemResult) {
                               val bundle = Bundle()
                               bundle.putSerializable("personagem", personagem)
                               val intent = Intent(this@MainActivity, DetalhePersonaActivity::class.java)
                               intent.putExtras(bundle)
                               startActivityForResult(intent, 1)

                            }
                        })
                    }
                } else {
                    Log.e("#MainActivity", "Response : " + response.message())
                }
            }
            override fun onFailure(@NonNull call: Call<Personagem>, @NonNull t: Throwable) {
                Log.e("#MainActivity", "OnFailure :" + t.message)

            }
        })

    }
}