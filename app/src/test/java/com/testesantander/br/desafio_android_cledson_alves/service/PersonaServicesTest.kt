package com.testesantander.br.desafio_android_cledson_alves.service


import com.testesantander.br.desafio_android_cledson_alves.BuildConfig.*
import com.testesantander.br.desafio_android_cledson_alves.network.RetrofitInstance
import org.junit.Assert
import org.junit.Before
import org.junit.ComparisonFailure
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.runners.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PersonaServicesTest {

    private val urlChater = "http://gateway.marvel.com/v1/public/characters?ts=1579608910&apikey=e86e63a175b986079bc9dba2b15a6f30&hash=e6ee9220d5f822cb6def5965df83bc81"
    private val urlComics = "http://gateway.marvel.com/v1/public/characters/10077/comics?ts=1579608910&apikey=e86e63a175b986079bc9dba2b15a6f30&hash=e6ee9220d5f822cb6def5965df83bc81"
    private lateinit var  personaServices: PersonaServices


    @Before
    fun setup() {
         personaServices = RetrofitInstance.retrofitInstance?.create(PersonaServices::class.java)!!
    }

    @Test
    fun validaUrlRequestTest() {
        var call = personaServices?.getAllPersonagens(TS, PUBLIC_KEY, MD5)
        Assert.assertEquals(urlChater, call?.request()?.url().toString())
    }
    @Test
    fun validaUrlRequestHQTest() {
        val personaServices = RetrofitInstance.retrofitInstance?.create(PersonaServices::class.java)
        var call = personaServices?.getAllHQ(10077, TS, PUBLIC_KEY, MD5)
        Assert.assertEquals(urlComics, call?.request()?.url().toString())
    }

    @Test (expected = ComparisonFailure::class)
    fun validaUrlRequestErrorHQTest() {
        val personaServices = RetrofitInstance.retrofitInstance?.create(PersonaServices::class.java)
        var call = personaServices?.getAllHQ(1, TS, PUBLIC_KEY, MD5)
        Assert.assertEquals(urlComics, call?.request()?.url().toString())
    }
}