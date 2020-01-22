package com.testesantander.br.desafio_android_cledson_alves.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable


class HQ : Serializable {



//    "code": 200,
//    "status": "Ok",
//    "etag": "e52e95f71334d797c3c2d0cf144bcc3e611718db",
//    "data": {
//        "offset": 0,
//        "limit": 20,
//        "total": 1,
//        "count": 1,

    @SerializedName("code")
    var code: Int = 0
    @SerializedName("status")
    var status: String = ""
    @SerializedName("etag")
    var etag: String = ""
    @SerializedName("data")
    lateinit var data: DataHQ
}