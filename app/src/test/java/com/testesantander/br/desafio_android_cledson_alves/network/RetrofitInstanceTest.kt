package com.testesantander.br.desafio_android_cledson_alves.network

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.runners.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class RetrofitInstanceTest {

    @InjectMocks
    lateinit var service: RetrofitInstance


    @Test
    fun getURLBaseTest(){
        Assert.assertEquals( "http://gateway.marvel.com/",service.retrofitInstance?.baseUrl().toString())
    }


}