package com.testesantander.br.desafio_android_cledson_alves.ui.activity



import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.testesantander.br.desafio_android_cledson_alves.BuildConfig
import com.testesantander.br.desafio_android_cledson_alves.R
import com.testesantander.br.desafio_android_cledson_alves.model.Personagem
import com.testesantander.br.desafio_android_cledson_alves.ui.utils.PersonagemClickListener
import com.testesantander.br.desafio_android_cledson_alves.model.PersonagemResult
import com.testesantander.br.desafio_android_cledson_alves.network.RetrofitInstance
import com.testesantander.br.desafio_android_cledson_alves.service.PersonaServices
import com.testesantander.br.desafio_android_cledson_alves.ui.adapter.PersonaAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.persona_item_card_view.*
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
     //   progress =  indeterminateProgressDialog("Carregando aguarde ... ")
        getPersonas()
    }

    private fun getPersonas() {

//
//      /**   TODO MOCK PARA TESTES */
//        var person =  PersonaAdapter(Util().getPersonagensMock(),
//            object : PersonagemClickListener {
//                override fun onClick(personagem: PersonagemResult) {
//                    startActivity<DetalhePersonaActivity>()
//
//                }
//            })
//
//
//        recicler.adapter = person
//        /** ttt  remover ^ */



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


                    recyclerView.adapter = personaResult?.let {
                        PersonaAdapter(it, object :
                            PersonagemClickListener {
                            override fun onClick(personagem: PersonagemResult) {
//
                                val bundle = Bundle()
                                bundle.putSerializable("personagem", personagem)

                                val intent = Intent(this@MainActivity, DetalhePersonaActivity::class.java)
                                intent.putExtras(bundle)
                                startActivityForResult(intent, 1)

                            }
                        })
                    }

                } else {
                    Log.e("#NotSucces", "Response : " + response.message())
                }
            }

            override fun onFailure(@NonNull call: Call<Personagem>, @NonNull t: Throwable) {
                Log.e("#Error", "OnFailure :" + t.message)
            }
        })

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // check if the request code is same as what is passed  here it is 2
        if (requestCode == 2) {
            val message = data!!.getStringExtra("MESSAGE")
            toast(""+ message)
        }
    }

    fun loadImage(view: View){
        Picasso.with(applicationContext).load("http://goo.gl/gEgYUd").into(iv_persona)
    }
}


private operator fun <VH : RecyclerView.ViewHolder?> RecyclerView.Adapter<VH>?.invoke() {}

//
//import android.content.Intent
//import android.os.Build
//import android.os.Bundle
//import androidx.annotation.RequiresApi
//import androidx.appcompat.app.AppCompatActivity
//import androidx.recyclerview.widget.LinearLayoutManager
//import com.testesantander.br.desafio_android_cledson_alves.R
//import com.testesantander.br.desafio_android_cledson_alves.controller.PersonaController
//import com.testesantander.br.desafio_android_cledson_alves.model.PersonagemResult
//import com.testesantander.br.desafio_android_cledson_alves.ui.adapter.PersonaAdapter
//import com.testesantander.br.desafio_android_cledson_alves.ui.utils.PersonagemClickListener
//import kotlinx.android.synthetic.main.activity_main.*




//class MainActivity : AppCompatActivity() {
//
//    private lateinit var linearLayoutManager: LinearLayoutManager
//
//    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//        initComponents()
//    }
//
//    private fun initComponents() {
//        linearLayoutManager = LinearLayoutManager(this)
//        recyclerView.layoutManager = linearLayoutManager
//
//        var lista = PersonaController().getPersonagens()
//        recyclerView.adapter = lista?.let {
//            PersonaAdapter(it,
//                object : PersonagemClickListener {
//                    override fun onClick(personagemResult: PersonagemResult) {
//                        val bundle = Bundle()
//                        bundle.putSerializable("personagem", personagemResult)
//                        val intent = Intent(this@MainActivity, DetalhePersonaActivity::class.java)
//                                intent.putExtras(bundle)
//                                startActivityForResult(intent, 1)
//                    }
//                })
//        }
//    }
//}

