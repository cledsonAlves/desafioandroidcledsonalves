package com.testesantander.br.desafio_android_cledson_alves.util


import com.testesantander.br.desafio_android_cledson_alves.model.Prices
import com.testesantander.br.desafio_android_cledson_alves.ui.utils.Util
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.runners.MockitoJUnitRunner
import java.text.DecimalFormat

@RunWith(MockitoJUnitRunner::class)
class UtilTest {

    @InjectMocks
    internal var util: Util? = null


    @Test
    fun getMagazinePriceTest() {
        var lista = ArrayList<Prices>()

        val p1 = Prices()
        p1.price = 0.7

        val p2 = Prices()
        p2.price = 0.0
        lista.add(p1)
        lista.add(p2)
        Assert.assertEquals(DecimalFormat.getCurrencyInstance().format(p1.price), util!!.getMagazinePrice(lista))
    }

    @Test
    fun getMagazinePriceElseTest(){
       Assert.assertEquals("R$ 0,00",util!!.getMagazinePrice(ArrayList()))
    }



}
