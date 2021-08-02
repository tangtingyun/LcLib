package com.step.lclib.debug.preference.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.step.lclib.R
import com.step.lclib.debug.preference.model.PreferenceItem
import com.step.lclib.debug.preference.model.PreferenceType
import com.step.lclib.debug.preference.utils.DialogUtils
import com.step.lclib.debug.preference.utils.DebugPrefManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class PrefDetailsFragment : Fragment() {
    private var mPrefName: String? = null

    private val mDebugPrefManager: DebugPrefManager by lazy {
        DebugPrefManager.getInstance(requireContext())
    }


    private val mPrefDetailsAdapter = PrefDetailsAdapter { view, preferenceItem ->
        DialogUtils.showEditValueDialog(requireContext(), preferenceItem) { item, newValue ->
            try {
                val preference = item.sp
                when (item.type) {
                    PreferenceType.Integer -> {
                        mDebugPrefManager.putInt(preference, item.key, newValue.toInt())
                        item.value = newValue.toInt()
                    }
                    PreferenceType.Float -> {
                        mDebugPrefManager.putFloat(preference, item.key, newValue.toFloat())
                        item.value = newValue.toFloat()
                    }
                    PreferenceType.Long -> {
                        mDebugPrefManager.putLong(preference, item.key, newValue.toLong())
                        item.value = newValue.toLong()
                    }
                    PreferenceType.Boolean -> {
                        mDebugPrefManager.putBoolean(preference, item.key, newValue.toBoolean())
                        item.value = newValue.toBoolean()
                    }
                    PreferenceType.String -> {
                        mDebugPrefManager.putString(preference, item.key, newValue)
                        item.value = newValue
                    }
                }
                fetchData()
            } catch (ex: NumberFormatException) {
                Toast.makeText(requireContext(), "请输入正确的类型", Toast.LENGTH_SHORT)
                    .show()
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            mPrefName = it.getString(ARG_PARAM_PREF_NAME)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val recyclerView = RecyclerView(requireContext())

        with(recyclerView) {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.VERTICAL
                )
            )
            adapter = mPrefDetailsAdapter
        }
        return recyclerView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetchData()
    }

    private fun fetchData() {
        mPrefName?.let { prefName ->
            viewLifecycleOwner.lifecycleScope.launch {
                val preferenceFile = withContext(Dispatchers.IO) {
                    val filesName = DebugPrefManager.getInstance(requireContext()).getDataBySpName(
                        prefName
                    )
                    filesName
                }
                mPrefDetailsAdapter.setNewData(preferenceFile.items)
            }
        }

    }

    companion object {
        private const val ARG_PARAM_PREF_NAME = "arg_pref_name"

        @JvmStatic
        fun newInstance(prefName: String) =
            PrefDetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM_PREF_NAME, prefName)
                }
            }
    }
}


class PrefDetailsAdapter(
    val clickListener: (View, PreferenceItem) -> Unit
) : RecyclerView.Adapter<PrefDetailsAdapter.PrefDetailsViewHolder>() {

    private val mList = ArrayList<PreferenceItem>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PrefDetailsViewHolder {

        val detailsLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_pref_details, parent, false)

        return PrefDetailsViewHolder(detailsLayout) { view, position ->
            clickListener(view, mList[position])
        }
    }

    override fun onBindViewHolder(holder: PrefDetailsViewHolder, position: Int) {
        holder.bindData(mList[position])
    }

    fun setNewData(list: List<PreferenceItem>) {
        mList.clear()
        mList.addAll(list)
        notifyDataSetChanged()
    }

    override fun getItemCount() = mList.size

    inner class PrefDetailsViewHolder(
        itemView: View,
        clickAtPosition: (View, Int) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {

        private val mTvKey: TextView
        private val mTvValue: TextView
        private val mTvType: TextView

        init {
            itemView.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    clickAtPosition(it, adapterPosition)
                }
            }
            mTvKey = itemView.findViewById(R.id.tv_pref_name)
            mTvValue = itemView.findViewById(R.id.tv_pref_value)
            mTvType = itemView.findViewById(R.id.tv_pref_type)
        }

        fun bindData(prefName: PreferenceItem) {
            mTvKey.text = "${prefName.key}"
            mTvType.text = "${prefName.type}"
            mTvValue.text = "${prefName.value}"
        }
    }
}