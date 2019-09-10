package ca.judacribz.week7day2_mvvm.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import ca.judacribz.week7day2_mvvm.R
import ca.judacribz.week7day2_mvvm.model.urbandictionary.Definition
import ca.judacribz.week7day2_mvvm.view.adapters.WordAdapter.WordHolder
import kotlinx.android.synthetic.main.definition.view.*

class WordAdapter(private val definitions: List<Definition>?) : Adapter<WordHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordHolder {
        return WordHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.definition,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: WordHolder, position: Int) {
        holder.bindViews(definitions?.get(position), position)
    }

    override fun getItemCount(): Int {
        return definitions?.size!!
    }

    inner class WordHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindViews(definition: Definition?, position: Int) {

            if (definition != null) {
                itemView.tvRank.text = when (definition.rank) {
                    1 -> itemView.context.getString(R.string.top_definition)
                    else -> (definition.rank).toString()
                }
                itemView.tvWord.text = definition.word
                itemView.tvDefinition.text = definition.definition
                itemView.btnThumbsUp.text = definition.thumbsUp.toString()
                itemView.btnThumbsDown.text = definition.thumbsDown.toString()
            }
        }
    }
}

