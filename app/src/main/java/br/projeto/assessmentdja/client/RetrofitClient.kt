package br.projeto.assessmentdja.client

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    companion object {
        private var instance : Retrofit? = null
        fun getInstance() : Retrofit {
            if (instance == null){
                instance = Retrofit.Builder()
                    .baseUrl("http://infnet.educacao.ws/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return instance!!
        }
    }
}