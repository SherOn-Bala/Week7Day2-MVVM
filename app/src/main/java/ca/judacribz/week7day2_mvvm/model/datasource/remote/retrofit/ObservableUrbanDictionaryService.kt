package ca.judacribz.week7day2_mvvm.model.datasource.remote.retrofit

import ca.judacribz.week7day2_mvvm.model.urbandictionary.Word
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ObservableUrbanDictionaryService {

    // https://mashape-community-urban-dictionary.p.rapidapi.com/define?term=<word>
    @Headers(
        "x-rapidapi-host: mashape-community-urban-dictionary.p.rapidapi.com",
        "x-rapidapi-key: d12762b14amshdc075ebdfc01572p17cf04jsn6860472af633"
    )
    @GET("define")
    fun getDefinitions(@Query("term") word: String): Observable<Word>
}
