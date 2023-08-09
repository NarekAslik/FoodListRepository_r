package com.example.foodlist

import android.content.res.Resources
import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.foodlist.databinding.DeleteDialogLayoutBinding
import com.example.foodlist.fragment.DELETE_ITEM_DATA

class Dialog : DialogFragment() {
    private lateinit var binding: DeleteDialogLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog?.window?.setBackgroundDrawableResource(R.drawable.round_corner)
        return inflater.inflate(R.layout.delete_dialog_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DeleteDialogLayoutBinding.bind(view)
        binding.apply {
            yesBtn.setOnClickListener {
                val bundle = Bundle()
                activity?.supportFragmentManager?.setFragmentResult(DELETE_ITEM_DATA, bundle)
                dismiss()
            }
            noBtn.setOnClickListener {
                dismiss()
            }
            setWithPercent(80)
        }
    }

    private fun setWithPercent(percentage: Int) {
        val percent = percentage.toFloat() / 100
        val dm = Resources.getSystem().displayMetrics
        val rect = dm.run { Rect(0, 0, widthPixels, heightPixels) }
        val percentWidth = rect.width() * percent
        dialog?.window?.setLayout(percentWidth.toInt(), ViewGroup.LayoutParams.WRAP_CONTENT)
    }

}