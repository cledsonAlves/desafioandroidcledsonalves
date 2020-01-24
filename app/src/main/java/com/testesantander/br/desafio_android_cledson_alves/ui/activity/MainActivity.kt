package com.testesantander.br.desafio_android_cledson_alves.ui.activity



import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.NonNull
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.fragment.app.DialogFragment
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
import com.testesantander.br.desafio_android_cledson_alves.ui.utils.Util
import kotlinx.android.synthetic.main.activity_detalhe_persona.*
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
            @RequiresApi(Build.VERSION_CODES.N)
            override fun onFailure(@NonNull call: Call<Personagem>, @NonNull t: Throwable) {
                Log.e("#MainActivity", "OnFailure :" + t.message)
                progress.dismiss()
                if (!Util.isNetwork(applicationContext)) {
                    errorApi(getString(R.string.msg_error_conection),getString(R.string.msg_error_network))
                }

            }
        })

    }
    fun errorApi(title:String,error:String){
        val builder: AlertDialog.Builder? = this@MainActivity?.let {
            AlertDialog.Builder(it)
        }

        builder?.setMessage(getString(R.string.msg_error_network))
            ?.setTitle(title)?.setPositiveButton(R.string.msg_sim,
                DialogInterface.OnClickListener { dialog, id ->
                    initComponents()
                    dialog.dismiss()
                    progress.show()
                })
            ?.setNegativeButton(R.string.msg_nao,
                DialogInterface.OnClickListener { dialog, id ->
                    finish()
                })
        val dialog: AlertDialog? = builder?.create()
        dialog?.show()

    }
}