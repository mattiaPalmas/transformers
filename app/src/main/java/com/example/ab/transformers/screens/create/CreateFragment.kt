package com.example.ab.transformers.screens.create

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import com.example.ab.transformers.R
import com.example.ab.transformers.base.BaseViewModelFragment
import com.example.ab.transformers.data.models.Transformer
import com.example.ab.transformers.screens.main.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class CreateFragment : BaseViewModelFragment() {

    override val viewModel: MainViewModel by viewModel()

    override fun getActivityLayout(): Int = R.layout.fragment_create

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.saveNewTransformer(
            Transformer(
                1,
                "Ironman",
                "A",
                8,
                8,
                8,
                8,
                8,
                8,
                8,
                8,
                ""
            )
        )
    }

    override fun initViews() {

    }

    override fun initViewModelObservers() {
        viewModel.transformerSavedLiveData.observe(this, { showSavedDialog() })
    }

    private fun showSavedDialog() {
        AlertDialog.Builder(context)
            .setTitle(getString(R.string.transformer_saved))
            .setMessage(getString(R.string.transformer_saved_desc))
            .setPositiveButton(R.string.ok) { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
}