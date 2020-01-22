package com.testesantander.br.desafio_android_cledson_alves.ui.utils

import com.testesantander.br.desafio_android_cledson_alves.model.PersonagemResult

class Util {



    fun getPersonagensMock() : ArrayList<PersonagemResult>{
        var list = ArrayList<PersonagemResult>()
        for (it in 1..500){
            var personagem = PersonagemResult()
            personagem.name = "PERSONAGEM $it"
            personagem.id = it
            personagem.description = "TATATATATATATATATATATATA"
            list.add(personagem)
        }
       return  list
    }
}