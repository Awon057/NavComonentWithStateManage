package wms.`fun`.molto.data

import com.google.gson.JsonObject
import io.reactivex.rxjava3.core.Single
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import wms.`fun`.molto.domain.entities.LoginResponse

interface ApiPostInterface {

    @Headers("Content-Type: application/json")
    @POST("/api/v1/employer/login")
    fun getLoginCall(@Body bean: JsonObject): Single<LoginResponse>

}