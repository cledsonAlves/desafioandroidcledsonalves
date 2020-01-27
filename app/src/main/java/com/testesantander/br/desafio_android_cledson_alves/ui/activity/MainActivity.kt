package com.testesantander.br.desafio_android_cledson_alves.ui.activity



import android.app.Dialog
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.testesantander.br.desafio_android_cledson_alves.BuildConfig.*
import com.testesantander.br.desafio_android_cledson_alves.R
import com.testesantander.br.desafio_android_cledson_alves.model.Personagem
import com.testesantander.br.desafio_android_cledson_alves.model.PersonagemResult
import com.testesantander.br.desafio_android_cledson_alves.network.RetrofitInstance
import com.testesantander.br.desafio_android_cledson_alves.service.PersonaServices
import com.testesantander.br.desafio_android_cledson_alves.ui.adapter.PersonaAdapter
import com.testesantander.br.desafio_android_cledson_alves.ui.utils.EndlessRecyclerViewScrollListener
import com.testesantander.br.desafio_android_cledson_alves.ui.utils.PersonagemClickListener
import com.testesantander.br.desafio_android_cledson_alves.ui.utils.Util
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.indeterminateProgressDialog
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import androidx.recyclerview.widget.RecyclerView
import org.jetbrains.anko.toast


class MainActivity : AppCompatActivity(){

    private lateinit var linearLayoutManager: LinearLayoutManager
    lateinit var progress : Dialog
    private var scrollListener: EndlessRecyclerViewScrollListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager
        initComponents()
    }

    private fun initComponents() {
        scrollListener = object : EndlessRecyclerViewScrollListener(linearLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                loadNextDataFromApi(page)
            }
        }
        recyclerView.addOnScrollListener(scrollListener as EndlessRecyclerViewScrollListener)

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
                    Util.getErroHtmlApi(response.code())
                    errorApi( response.message(),Util.getErroHtmlApi(response.code()))
                    Log.e("#MainActivity", "Response : $response.message()")
                    Log.e("#MainActivity", "Response code : $response.code()")
                }
            }
            @RequiresApi(Build.VERSION_CODES.N)
            override fun onFailure(@NonNull call: Call<Personagem>, @NonNull t: Throwable) {
                Log.e("#MainActivity", "OnFailure :" + t.message)
                progress.dismiss()
                if (!Util.isNetwork(applicationContext)) {
                    errorApi(getString(R.string.msg_error_conection), getString(R.string.msg_error_network))
                }

            }
        })

    }


    fun loadNextDataFromApi(offset: Int) {
        Log.i("#MainActivity", "$offset")
    }

    fun errorApi(title:String, msg:String){
        val builder: AlertDialog.Builder? = this@MainActivity?.let {
            AlertDialog.Builder(it)
        }

        builder?.setMessage(msg)
            ?.setTitle(title)?.setPositiveButton(R.string.msg_sim
            ) { dialog, _ ->
                initComponents()
                dialog.dismiss()
                progress.show()
            }
            ?.setNegativeButton(R.string.msg_nao
            ) { _, _ ->
                finish()
            }
        val dialog: AlertDialog? = builder?.create()
        dialog?.show()

    }
}