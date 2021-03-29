package com.example.ab.transformers.screens.create

import android.app.AlertDialog
import androidx.core.view.isVisible
import com.example.ab.transformers.R
import com.example.ab.transformers.base.BaseViewModelFragment
import com.example.ab.transformers.data.models.Transformer
import com.example.ab.transformers.screens.main.MainViewModel
import kotlinx.android.synthetic.main.fragment_create.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*


class CreateFragment : BaseViewModelFragment() {

    override val viewModel: MainViewModel by viewModel()

    override fun getActivityLayout(): Int = R.layout.fragment_create

    override fun initViews() {
        save_btn.setOnClickListener {
            saveTransformerIfValid()
        }
    }

    private fun saveTransformerIfValid() {
        val name = name_et.text.toString()
        val team = team_spinner.selectedItem
        val strength = strength_spinner.selectedItem
        val intelligence = intelligence_spinner.selectedItem
        val speed = speed_spinner.selectedItem
        val endurance = endurance_spinner.selectedItem
        val rank = rank_spinner.selectedItem
        val courage = courage_spinner.selectedItem
        val firepower = firepower_spinner.selectedItem
        val skill = skill_spinner.selectedItem

        when {
            name.isBlank() ||
                    team == null || team.toString().isEmpty() ||
                    strength == null || strength.toString().isEmpty() ||
                    intelligence == null || intelligence.toString().isEmpty() ||
                    speed == null || speed.toString().isEmpty() ||
                    endurance == null || endurance.toString().isEmpty() ||
                    rank == null || rank.toString().isEmpty() ||
                    courage == null || courage.toString().isEmpty() ||
                    firepower == null || firepower.toString().isEmpty() ||
                    skill == null || skill.toString().isEmpty() -> showDialog(
                "Information Missing",
                "Please add all the info."
            )
            else -> {
                val random = Random()
                val teamType = if (team.equals("Autobot")) {
                    "A"
                } else {
                    "D"
                }

                viewModel.saveNewTransformer(
                    Transformer(
                        random.nextInt(),
                        name,
                        teamType,
                        strength.toString().toInt(),
                        intelligence.toString().toInt(),
                        speed.toString().toInt(),
                        endurance.toString().toInt(),
                        rank.toString().toInt(),
                        courage.toString().toInt(),
                        firepower.toString().toInt(),
                        skill.toString().toInt(),
                        ""
                    )
                )
            }
        }
    }

    override fun initViewModelObservers() {
        viewModel.transformerSavedLiveData.observe(this, {
            progress_layout.isVisible = false

            showDialog(
                getString(R.string.transformer_saved),
                getString(R.string.transformer_saved_desc)
            )
        })

        viewModel.isLoadingLiveDataLiveData.observe(this, {
            progress_layout.isVisible = true
        })
    }

    private fun showDialog(title: String, message: String) {
        AlertDialog.Builder(context)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(R.string.ok) { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
}