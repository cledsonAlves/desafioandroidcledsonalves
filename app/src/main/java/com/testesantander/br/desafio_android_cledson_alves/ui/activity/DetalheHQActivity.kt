package com.testesantander.br.desafio_android_cledson_alves.ui.activity

import android.annotation.TargetApi
import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.testesantander.br.desafio_android_cledson_alves.R
import com.testesantander.br.desafio_android_cledson_alves.model.PersonagemResult
import com.testesantander.br.desafio_android_cledson_alves.ui.utils.Util
import kotlinx.android.synthetic.main.activity_detalhe_persona.*
import kotlinx.android.synthetic.main.layout.*
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.os.Build
import android.transition.Slide
import android.transition.Transition
import android.transition.TransitionInflater
import android.view.Window
import androidx.annotation.RequiresApi


class DetalheHQActivity : AppCompatActivity() {
    @TargetApi(Build.VERSION_CODES.N)
    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout)
        initComponents()

    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun initComponents() {
        val intent = intent
        var personagem = getIntent().getSerializableExtra("personagem") as PersonagemResult

        if (intent != null) {
            val params = intent!!.getExtras()
            if (params != null) {

                val price = Util.getMagazinePrice(personagem.price)

                txt_price.text = "R$ ${price.toString()}"
                txt_nome_hq.text = personagem.name


                val path = "${personagem.thunbnail.patch}.${personagem.thunbnail.extension}"
                Thread{
                    runOnUiThread{
                        Util.getPicture(applicationContext,path,iv_persona_hq,progressBar_hq)
                    }
                }.start()



            }
        }
    }

}