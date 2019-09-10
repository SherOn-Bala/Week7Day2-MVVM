package ca.judacribz.week7day2_mvvm.view.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import ca.judacribz.week7day2_mvvm.R
import ca.judacribz.week7day2_mvvm.databinding.ActivityMainBinding
import ca.judacribz.week7day2_mvvm.model.urbandictionary.Definition
import ca.judacribz.week7day2_mvvm.viewmodel.UrbanDictionaryViewModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityMainBinding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        val urbanDictionaryViewModel = UrbanDictionaryViewModel()
        activityMainBinding.viewmodel = urbanDictionaryViewModel

        urbanDictionaryViewModel.getDefinitionsLiveData().observe(
            this,
            Observer<List<Definition>>(urbanDictionaryViewModel::setAdapter)
        )
    }

//    companion object {
//        @BindingAdapter("bind:adapter")
//        fun initRecyclerView(view: RecyclerView, adapter: WordAdapter) {
//            view.layoutManager = LinearLayoutManager(view.context)
//            view.adapter = adapter
//        }
//    }
}
