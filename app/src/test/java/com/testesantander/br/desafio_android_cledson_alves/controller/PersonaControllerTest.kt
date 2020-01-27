package com.testesantander.br.desafio_android_cledson_alves.controller

import android.util.Log
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.runners.MockitoJUnitRunner
import org.mockito.Mockito.verify as verify1


@RunWith(MockitoJUnitRunner::class)
class PersonaControllerTest {

    @InjectMocks
    lateinit var controller:PersonaController

    @Mock
    lateinit var log:Log


    @Test
    fun getPersonasTest (){ println(controller.getPersonas()) }
}