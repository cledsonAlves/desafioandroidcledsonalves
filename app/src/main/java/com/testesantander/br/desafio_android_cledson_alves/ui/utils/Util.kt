package com.testesantander.br.desafio_android_cledson_alves.ui.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Build
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.annotation.RequiresApi
import com.squareup.picasso.Picasso
import com.testesantander.br.desafio_android_cledson_alves.model.PersonagemResult
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation


object Util {

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun isNetwork(context: Context): Boolean{
        val connection = context.getSystemService(Context.CONNECTIVITY_SERVICE)
        as ConnectivityManager
        val networks = connection.allNetworks
        return networks.map { connection.getNetworkInfo(it) }
            .any{it.state == NetworkInfo.State.CONNECTED}
    }

    fun getPersonagensMock() : ArrayList<PersonagemResult>{
        var list = ArrayList<PersonagemResult>()
        for (it in 1..500){
            var personagem = PersonagemResult()
            personagem.name = "PERSONAGEM $it"
            personagem.id = it
            personagem.description = "TDESCRIPTION"
            list.add(personagem)
        }
       return  list
    }

    fun getPicture(context:Context, path:String, imView:ImageView,progressBar:ProgressBar){
        // Picasso.with(itemView.context).load(path).placeholder(R.drawable.placeholder).fit().centerCrop().into(iv_persona)
        Picasso.with(context)
            .load(path).fit()
            .centerCrop()
            .transform( RoundedCornersTransformation(10, 10))
            .into(imView, object : com.squareup.picasso.Callback {
                override fun onSuccess() {
                    if (progressBar != null) {
                        progressBar.visibility = View.GONE
                    }
                }
                override fun onError() {

                }
            })

    }


}