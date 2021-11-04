package wms.`fun`.molto.data

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import wms.`fun`.molto.utils.BASE_URL
import java.util.concurrent.TimeUnit


interface ApiClient{
    open fun buildPostApiService(): ApiPostInterface
    open fun buildGetApiService(): ApiGetInterface
}

class ApiClientInterface: ApiClient{

    companion object{
        var retrofit: Retrofit?=null
        private fun buildClient() : OkHttpClient {
            return OkHttpClient.Builder().addInterceptor(
                HttpLoggingInterceptor().setLevel(
                    HttpLoggingInterceptor.Level.BODY)).connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS).build()
        }

        fun getClient(): Retrofit{
            if (retrofit ==null){
                retrofit = Retrofit.Builder()
                    .client(buildClient())
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BASE_URL)
                    .build()
            }
            return retrofit!!
        }
    }

    override fun buildPostApiService(): ApiPostInterface {
        return getClient().create(ApiPostInterface::class.java)
    }

    override fun buildGetApiService(): ApiGetInterface {
        return getClient().create(ApiGetInterface::class.java)
    }
}