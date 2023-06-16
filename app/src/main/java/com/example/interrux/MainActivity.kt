package com.example.interrux


import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.interrux.`interface`.ApiInterface
import com.example.interrux.model.AuthTestingModel
import com.example.interrux.model.LoginReq
import com.example.interrux.model.LoginResponse
import com.example.interrux.model.RefreshRequest
import okhttp3.OkHttpClient
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity(), ErrorInterceptor.ErrorHandler {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val refreshAuthToken : (String?) -> String = {
            refreshToken ->
            val retrofitBuilder = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.escuelajs.co/api/v1/")
                .build()
            val apiInterface = retrofitBuilder.create(ApiInterface::class.java)
            val refreshRequest = refreshToken?.let { RefreshRequest(it) }
            val response = refreshRequest?.let {
                apiInterface.refreshToken(it).execute().let {
                    it
                }
            }
            response?.body()?.access_token ?: ""

        }
        val authInterceptor = AuthInterceptor(applicationContext, refreshAuthToken)
        login(authInterceptor)
    }


    private fun okhttpClient(authInterceptor: AuthInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .addInterceptor(ErrorInterceptor(this))
            .addInterceptor(LoggingInterceptor())
            .build()
    }

    private fun login(authInterceptor: AuthInterceptor) {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.escuelajs.co/api/v1/")
            .client(okhttpClient(authInterceptor))
            .build()
        val apiInterface = retrofitBuilder.create(ApiInterface::class.java)
        val userCredentials = LoginReq(
            "john@mail.com","changeme"
        )

        apiInterface.login(userCredentials).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                Log.d("MainActivity", "onLoginResponse: $response")
                if(response.body()!=null){
                    Log.d("Token",response.body()!!.access_token)
                    authInterceptor.saveTokens(response.body()!!.access_token, response.body()!!.refresh_token)
                    getProfile(authInterceptor)
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.d("MainActivity", "onLoginFailure--: " + t.message)
            }

        })
    }
    private fun getProfile(authInterceptor: AuthInterceptor) {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.escuelajs.co/api/v1/")
            .client(okhttpClient(authInterceptor))
            .build()
        val apiInterface = retrofitBuilder.create(ApiInterface::class.java)
        val response = apiInterface.getProfile()

        response.enqueue(object : Callback<AuthTestingModel> {


            override fun onFailure(call: Call<AuthTestingModel>, t: Throwable) {
                Log.d("MainActivity", "onFailure2: " + t.message)
            }

            override fun onResponse(
                call: Call<AuthTestingModel>,
                response: Response<AuthTestingModel>
            ) {
                Log.d("MainActivity", "onResponse2: " + response.body())
            }
        })

    }

    override fun onError(errorCode: Int, errorMessage: String) {
        Log.d("MainActivity", "onError: $errorMessage  You can handle the error like this")
    }


}

