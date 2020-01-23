package com.testesantander.br.desafio_android_cledson_alves.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.testesantander.br.desafio_android_cledson_alves.R
import com.testesantander.br.desafio_android_cledson_alves.model.PersonagemResult
import kotlinx.android.synthetic.main.layout.*

class DetalheHQActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout)
        initComponents()

    }

    private fun initComponents() {
        val intent = intent
        var personagem = getIntent().getSerializableExtra("personagem") as PersonagemResult

        if (intent != null) {
            val params = intent!!.getExtras()
            if (params != null) {

                txt_price.text = personagem.price[0].price.toString()
                txt_nome_hq.text = personagem.name
                val path = "${personagem.thunbnail.patch}.${personagem.thunbnail.extension}"
                Log.e("#...View Holder", "PACTH : $path")

            }
        }
    }

}