package com.step.lclib.work.bestpractice.recyclerview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.step.lclib.R
import com.step.lclib.databinding.ActivityRecyclerViewBinding
import com.step.lclib.work.utils.GlideApp


//https://www.jianshu.com/p/b343fcff51b0
// https://www.youtube.com/watch?v=GvLgWjPigmQ
// https://guides.codepath.com/android/using-the-recyclerview#layouts

// 关于 recyclerview 预处理布局
// https://zhuanlan.zhihu.com/p/150105789
class StringAdapter(
    private val glide: RequestManager,
    private val dataList: List<String>,
    private val clickViewIds: List<Int>,
    val clickListener: (View, String) -> Unit
) :
    RecyclerView.Adapter<StringAdapter.StringViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StringViewHolder {
        val inflate = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recycler_string, parent, false)
        return StringViewHolder(inflate, glide, clickViewIds) { view, position ->
            clickListener(view, dataList[position])
        }
    }

    override fun onBindViewHolder(holder: StringViewHolder, position: Int) {
        holder.bindData(dataList[position])
    }

    override fun getItemCount() = dataList.size


    class StringViewHolder(
        itemView: View,
        val glide: RequestManager,
        clickViewIds: List<Int>,
        clickAtPosition: (View, Int) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {

        private val mTvString: TextView
        private val mIvIcon: ImageView

        init {
            clickViewIds.forEach { clickViewId ->
                itemView.findViewById<View>(clickViewId).setOnClickListener {
                    if (adapterPosition != RecyclerView.NO_POSITION) { // Check if an item was deleted, but the user clicked it before the UI removed it
                        clickAtPosition(it, adapterPosition)
                    }
                }
            }

            mTvString = itemView.findViewById(R.id.tv_recycler_string)

            mIvIcon = itemView.findViewById(R.id.iv_recycler_string)
        }


        fun bindData(item: String) {

            mTvString.context;

            mTvString.text = item
//            glide.load(R.drawable.lbxx).into(mIvIcon)
        }
    }
}


class RecyclerViewActivity : AppCompatActivity() {

    lateinit var video: VideoView

    lateinit var fm: FragmentManager;

    lateinit var binding: ActivityRecyclerViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityRecyclerViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dataList = mutableListOf<String>()

        for (i in 1..25) {
            dataList.add("$i")
        }

        val clickViewIds = listOf(
            R.id.iv_recycler_string
        )
        val glide = GlideApp.with(this)

        binding.recyclerViewString.apply {
            layoutManager = LinearLayoutManager(this@RecyclerViewActivity)
            adapter = StringAdapter(glide, dataList, clickViewIds) { view, item ->
                when (view.id) {
                    R.id.iv_recycler_string -> {
                        Toast.makeText(this@RecyclerViewActivity, "$item", Toast.LENGTH_SHORT)
                            .show()
                    }
                }

            }
        }


        val smoothScroller: LinearSmoothScroller = object : LinearSmoothScroller(this) {
            override fun getVerticalSnapPreference(): Int {
                return SNAP_TO_START
            }
        }

        binding.btnTestScrollPosition.setOnClickListener {

            smoothScroller.targetPosition = 18
            binding.recyclerViewString.layoutManager?.startSmoothScroll(smoothScroller)

//            binding.recyclerViewString.smoothScrollToPosition(postion)

        }

    }
}