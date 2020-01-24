package com.testesantander.br.desafio_android_cledson_alves.ui.activity

import android.content.Context
import android.net.ConnectivityManager
import android.widget.ImageView
import android.widget.ProgressBar
import com.testesantander.br.desafio_android_cledson_alves.R
import com.testesantander.br.desafio_android_cledson_alves.ui.utils.Util
import kotlinx.android.synthetic.main.persona_item_card_view.*
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.runners.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class MainActivityTest {

    @InjectMocks
    lateinit var activity: MainActivity

    @Mock
    lateinit var context: Context

    @Mock
    lateinit var progression:ProgressBar

    @Mock
    lateinit var imageView:ImageView

    @Mock
    lateinit var conection : ConnectivityManager


    @Before
    fun setup(){
        activity::class.java
    }

    @Test
    fun initComponentTest(){
        Assert.assertNotNull(progression)
    }


    @Test
    fun getLabelTest(){
        Mockito.`when`(context.getString(R.string.msg_error_conection)).thenReturn("Error")
        Assert.assertEquals("Error",context.getString(R.string.msg_error_conection ))
    }
}