package com.testesantander.br.desafio_android_cledson_alves.ui.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Build
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.annotation.IdRes
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import com.testesantander.br.desafio_android_cledson_alves.model.PersonagemResult
import com.testesantander.br.desafio_android_cledson_alves.model.Prices
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation
import java.text.DecimalFormat
import java.util.function.ToDoubleFunction


object Util {

    // valida conexão internet
    @RequiresApi(api = Build.VERSION_CODES.N)
    fun isNetwork(context: Context): Boolean{
        val connection = context.getSystemService(Context.CONNECTIVITY_SERVICE)
        as ConnectivityManager
        val networks = connection.allNetworks
        return networks.map { connection.getNetworkInfo(it) }
            .any{it.state == NetworkInfo.State.CONNECTED}
    }

    // atualiza  uma imageview
    @RequiresApi(Build.VERSION_CODES.N)
    fun getPicture(context:Context, path:String, imView:ImageView, progressBar:ProgressBar){
        // Picasso.with(itemView.context).load(path).placeholder(R.drawable.placeholder).fit().centerCrop().into(iv_persona)
            Picasso.with(context)
                .load(path).fit()
                .centerCrop()
                .transform(RoundedCornersTransformation(10, 10))
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

    // retorna o maior valor de uma lista
    @RequiresApi(api = Build.VERSION_CODES.N)
    fun getMagazinePrice(lista: java.util.ArrayList<Prices>): String? {
       var price =  lista.stream()
            .mapToDouble{ it.price }
            .max()
            .orElse(0.0)
        return  DecimalFormat.getCurrencyInstance().format(price)

    }

    //Adiciona o fragment no layout
    fun AppCompatActivity.addFragments(@IdRes layoutId: Int, fragment: Fragment){
        fragment.arguments = intent.extras
        val ft = supportFragmentManager.beginTransaction()
        ft.add(layoutId,fragment)
        ft.commit()
    }


}