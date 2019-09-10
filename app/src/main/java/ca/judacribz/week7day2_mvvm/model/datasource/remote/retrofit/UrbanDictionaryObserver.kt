package ca.judacribz.week7day2_mvvm.model.datasource.remote.retrofit

import android.util.Log
import ca.judacribz.week7day2_mvvm.model.urbandictionary.Word
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

class UrbanDictionaryObserver(private val definitionsListener: DefinitionsListener) :
    Observer<Word> {
    private var word: Word? = null

    interface DefinitionsListener {
        fun onDefinitionsReceived(word: Word?)
    }

    override fun onSubscribe(d: Disposable) {
        Log.d(TAG, "onNext: ")
    }

    override fun onNext(word: Word) {
        Log.d(TAG, "onNext: ")
        this.word = word
    }

    override fun onError(e: Throwable) {
        Log.e(TAG, "onError: ", e)
    }

    override fun onComplete() {
        Log.d(TAG, "onComplete: ")
        definitionsListener.onDefinitionsReceived(word)
    }

    companion object {
        val TAG = UrbanDictionaryHelper::class.java.simpleName
    }
}