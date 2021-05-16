package com.step.lclib.work

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer

class BizDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        activity?.let {
            AlertDialog.Builder(requireContext())
                .setMessage("${javaClass.simpleName}")
                .setPositiveButton("sure") { _, _ -> }
                .create()
        } ?: throw IllegalStateException("Activity cannot be null")


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    fun test() {

        lifecycle.addObserver(object : LifecycleObserver {

        })

        viewLifecycleOwner.lifecycle.addObserver(object : LifecycleObserver {

        })
        viewLifecycleOwnerLiveData.observe(viewLifecycleOwner,
            Observer<LifecycleOwner> {

            }
        )

    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    companion object {
        const val TAG = "BizDialogFragment"
    }


}