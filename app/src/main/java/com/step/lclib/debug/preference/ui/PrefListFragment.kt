package com.step.lclib.debug.preference.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import com.step.lclib.debug.preference.utils.DebugPrefManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PrefListFragment : Fragment() {

    private val mPrefAdapter = PrefListAdapter { _, prefName ->
        if (activity is DebugPrefActivity) {
            (activity as DebugPrefActivity).showDetails(prefName)
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val recyclerView = RecyclerView(requireContext())
        with(recyclerView) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = mPrefAdapter
            setHasFixedSize(true)
            addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.VERTICAL
                )
            )
        }
        return recyclerView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launch {
            val filesName = withContext(Dispatchers.IO) {
                val filesName = DebugPrefManager.getInstance(requireContext()).getFilesName()
                filesName
            }
            mPrefAdapter.setNewData(filesName)
        }
    }

    companion object {

        @JvmStatic
        fun newInstance() = PrefListFragment()
    }
}


class PrefListAdapter(
    val clickListener: (View, String) -> Unit
) : RecyclerView.Adapter<PrefListAdapter.PrefNameViewHolder>() {

    private val mList = ArrayList<String>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PrefNameViewHolder {

        val simpleTextView = LayoutInflater.from(parent.context)
            .inflate(android.R.layout.simple_list_item_activated_1, parent, false)

        return PrefNameViewHolder(simpleTextView) { view, position ->
            clickListener(view, mList[position])
        }
    }

    override fun onBindViewHolder(holder: PrefNameViewHolder, position: Int) {
        holder.bindData(mList[position])
    }

    fun setNewData(list: List<String>) {
        mList.clear()
        mList.addAll(list)
        notifyDataSetChanged()
    }

    override fun getItemCount() = mList.size

    inner class PrefNameViewHolder(
        itemView: View,
        clickAtPosition: (View, Int) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    clickAtPosition(it, adapterPosition)
                }
            }
        }

        fun bindData(prefName: String) {
            (itemView as TextView).text = prefName
        }
    }
}