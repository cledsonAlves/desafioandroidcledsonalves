package com.testesantander.br.desafio_android_cledson_alves.controller

import com.testesantander.br.desafio_android_cledson_alves.controller.PersonaController
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.runners.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class PersonaControllerTest {

    @InjectMocks
    lateinit var controller:PersonaController


    @Test
    fun getPersonasTest (){
       // println(controller.getPersonagens())
    }
}