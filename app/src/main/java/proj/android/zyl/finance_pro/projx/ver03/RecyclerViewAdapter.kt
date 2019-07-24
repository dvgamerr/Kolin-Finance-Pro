package proj.android.zyl.finance_pro.projx.ver03

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import proj.android.zyl.finance_pro.R
import proj.android.zyl.finance_pro.projx.ver03.RecyclerViewAdapter.ViewHolder


class RecyclerViewAdapter(private val Dataset: Array<String>) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    val itemCount: Int
        @Override
        get() = Dataset.size

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var mTextView: TextView

        init {
            mTextView = v.findViewById(R.id.recyclerTextView)
        }

    }

    @Override
    fun onCreateViewHolder(parent: ViewGroup,
                           viewType: Int): RecyclerViewAdapter.ViewHolder {

        val v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.navigation_recycler_textview, parent, false)

        return ViewHolder(v)

    }

    @Override
    fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.mTextView.setText(Dataset[position])

    }
}