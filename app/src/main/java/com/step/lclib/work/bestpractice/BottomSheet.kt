package com.step.lclib.work.bestpractice

import android.os.Bundle
import android.util.TypedValue
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.step.lclib.R


//  https://www.jianshu.com/p/04711494868e

// https://blog.csdn.net/qq_39240270/article/details/103218778

// https://www.programmersought.com/article/61183606241/


class BottomSheet : BottomSheetDialogFragment() {


    lateinit var a: BottomSheetDialog;
    var mRecyBottom: RecyclerView? = null

    companion object {
        fun newInstance(): BottomSheet {
            val args = Bundle()

            val fragment = BottomSheet()
            fragment.arguments = args
            return fragment
        }
    }

//    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
//        return HeightBootSheetDialog(requireContext(), getTheme());
//    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bottom_sheet_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mRecyBottom = view.findViewById(R.id.recy_bottom_sheet)

        mRecyBottom?.layoutManager = LinearLayoutManager(context)
        mRecyBottom?.adapter = TextAdapter()

        getBehavior()?.isFitToContents = false
        getBehavior()?.expandedOffset =
            TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                100f, resources.displayMetrics
            ).toInt()


//        getBehavior()?.peekHeight = TypedValue.applyDimension(
//            TypedValue.COMPLEX_UNIT_DIP,
//            300f, resources.displayMetrics
//        ).toInt()
//
//        val maxHeight = TypedValue.applyDimension(
//            TypedValue.COMPLEX_UNIT_DIP,
//            500f, resources.displayMetrics
//        ).toInt();
//        getBehavior()?.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
//            override fun onStateChanged(bottomSheet: View, newState: Int) {
//                val layoutParams = bottomSheet.layoutParams
//                if (bottomSheet.height > maxHeight) {
//                    layoutParams.height = maxHeight
//                    bottomSheet.layoutParams = layoutParams
//                }
//
//            }
//
//            override fun onSlide(bottomSheet: View, slideOffset: Float) {
//            }
//
//        })

    }

    private fun getBehavior() = (dialog as? BottomSheetDialog)?.behavior


    inner class TextAdapter : RecyclerView.Adapter<TextHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextHolder {
            var textView = TextView(parent.context)

            with(textView) {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP,
                        70f, parent.resources.displayMetrics
                    ).toInt()
                )

                gravity = Gravity.CENTER
            }

            return TextHolder(textView)
        }

        override fun onBindViewHolder(holder: TextHolder, position: Int) {
            holder.bindData("$position")
        }

        override fun getItemCount() = 20

    }

    inner class TextHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindData(str: String) {
            (itemView as TextView).text = "position90 $str"
        }

    }
}