package com.testesantander.br.desafio_android_cledson_alves.util


import com.testesantander.br.desafio_android_cledson_alves.commonConstants.CommonConstants
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

    @Test
    fun getErroHtmlApiTest(){
        Assert.assertEquals(Util.getErroHtmlApi(404), CommonConstants.NOT_FOUND)
        Assert.assertEquals(Util.getErroHtmlApi(401),CommonConstants.NOT_AUTHORIZATED)
        Assert.assertEquals(Util.getErroHtmlApi(403),CommonConstants.ERROR_ACCESS)
        Assert.assertEquals(Util.getErroHtmlApi(409), CommonConstants.ERROR_TS)
        Assert.assertEquals(Util.getErroHtmlApi(400),CommonConstants.ERROR_REQUEST)
        Assert.assertEquals(Util.getErroHtmlApi(502),CommonConstants.INTERNAL_ERROR)
        Assert.assertEquals(Util.getErroHtmlApi(504),CommonConstants.TIME_OUT)
        Assert.assertEquals(Util.getErroHtmlApi(0),CommonConstants.ERROR_NOT_FOUND)
    }



}
