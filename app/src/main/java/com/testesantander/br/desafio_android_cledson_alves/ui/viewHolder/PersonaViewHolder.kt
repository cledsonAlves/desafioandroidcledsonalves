package com.testesantander.br.desafio_android_cledson_alves.ui.viewHolder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.testesantander.br.desafio_android_cledson_alves.model.PersonagemClickListener
import com.testesantander.br.desafio_android_cledson_alves.model.PersonagemResult
import kotlinx.android.synthetic.main.layout.view.*

class PersonaViewHolder(view: View) : RecyclerView.ViewHolder(view) {


    var persona_name = view.txt_nome




    fun bind(personas: PersonagemResult) {
        persona_name.text = personas.name
    }
}