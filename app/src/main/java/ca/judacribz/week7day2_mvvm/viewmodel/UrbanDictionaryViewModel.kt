package ca.judacribz.week7day2_mvvm.viewmodel

import android.text.Editable
import android.view.View
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.databinding.PropertyChangeRegistry
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ca.judacribz.week7day2_mvvm.R
import ca.judacribz.week7day2_mvvm.model.datasource.remote.retrofit.UrbanDictionaryHelper
import ca.judacribz.week7day2_mvvm.model.datasource.remote.retrofit.UrbanDictionaryObserver
import ca.judacribz.week7day2_mvvm.model.urbandictionary.Definition
import ca.judacribz.week7day2_mvvm.model.urbandictionary.Word
import ca.judacribz.week7day2_mvvm.view.adapters.WordAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*

class UrbanDictionaryViewModel : ViewModel(), Observable,
    UrbanDictionaryObserver.DefinitionsListener {

    private val propertyChangeRegistry: PropertyChangeRegistry = PropertyChangeRegistry()
    private val word: MutableLiveData<String> = MutableLiveData()
    private val definitionsLiveData: MutableLiveData<List<Definition>> = MutableLiveData()
    private val urbanDictionaryHelper: UrbanDictionaryHelper = UrbanDictionaryHelper()

    @Bindable
    var wordAdapter: WordAdapter? = null

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback) {
        propertyChangeRegistry.add(callback)
    }

    fun getDefinitionsLiveData() : MutableLiveData<List<Definition>> = definitionsLiveData

    fun afterWordChanged(editable: Editable) {
        word.postValue(editable.toString())
    }

    fun onGetResults(view: View) {
        val wordStr = word.value

        if (wordStr != null) {
            urbanDictionaryHelper.obsService.getDefinitions(wordStr)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(UrbanDictionaryObserver(this))
        }
    }

    override fun onDefinitionsReceived(word: Word?) {
        if (word != null) {
            val definitions = word.definitions
            if (definitions != null) {
                for (i in definitions.indices) {
                    definitions[i].rank = i + 1
                }
                definitionsLiveData.value = definitions
            }

        }
    }

    fun onSort(view: View) {
        val definitions = definitionsLiveData.value

        if (definitions != null) {
            var definitionThumbsComparator: DefinitionThumbsComparator? = null
            when (view.id) {
                R.id.ibtnThumbsUp -> definitionThumbsComparator = DefinitionThumbsComparator(true)
                R.id.ibtnThumbsDown -> definitionThumbsComparator =
                    DefinitionThumbsComparator(false)
            }

            if (definitionThumbsComparator != null) {
                Collections.sort(definitions, definitionThumbsComparator)
                definitionsLiveData.value = definitions
            }
        }
    }

    fun setAdapter(definitions: List<Definition>?) {
        wordAdapter = WordAdapter(definitions)
        notifyAllPropertiesChanged()
    }


    private fun notifyAllPropertiesChanged() {
        propertyChangeRegistry.notifyChange(this, 0)
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback) {
        propertyChangeRegistry.remove(callback)
    }


    internal inner class DefinitionThumbsComparator(private val sortByThumbsUp: Boolean) :
        Comparator<Definition> {

        override fun compare(d1: Definition, d2: Definition): Int {
            return Integer.compare(getThumbs(d2), getThumbs(d1))
        }

        private fun getThumbs(def: Definition): Int {
            return if (sortByThumbsUp) def.thumbsUp else def.thumbsDown
        }
    }
}
