package ca.judacribz.week7day2_mvvm.model.datasource.remote.retrofit

import ca.judacribz.week7day2_mvvm.model.Constants.URL_URBAN_DICTIONARY
import ca.judacribz.week7day2_mvvm.model.urbandictionary.Word
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class UrbanDictionaryHelper {


    private val retrofitInstance: Retrofit
        get() =
            Retrofit.Builder()
                .baseUrl(URL_URBAN_DICTIONARY)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

    val obsService: ObservableUrbanDictionaryService
        get() =
            retrofitInstance.create(ObservableUrbanDictionaryService::class.java)
}
