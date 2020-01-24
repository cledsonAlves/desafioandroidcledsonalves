package com.testesantander.br.desafio_android_cledson_alves.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.testesantander.br.desafio_android_cledson_alves.R

class MainFragment : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detalhe_fragment)
      if (savedInstanceState == null){


          val ft = supportFragmentManager.beginTransaction()
          val frag1 = DetalheFragment()
          ft.add(R.id.layoutFrag,frag1,"HeLLO")
          ft.commit()
      }

    }
}