package com.example.ab.transformers.screens.main

import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.ab.transformers.R
import com.example.ab.transformers.base.BaseViewModelActivity
import com.example.ab.transformers.screens.create.CreateFragment
import com.example.ab.transformers.screens.fight.FightFragment
import com.example.ab.transformers.screens.team.TeamFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_navigation.view.*
import kotlinx.android.synthetic.main.main_navigation.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseViewModelActivity() {

    override val viewModel: MainViewModel by viewModel()

    override fun getActivityLayout(): Int = R.layout.activity_main

    override fun initViews() {
        main_navigation.create_btn.title_tv.text = getString(R.string.create)
        main_navigation.fight_btn.title_tv.text = getString(R.string.fight)
        main_navigation.team_btn.title_tv.text = getString(R.string.team)

        main_navigation.create_btn.icon_iv.setBackgroundResource(R.drawable.ic_gear)
        main_navigation.fight_btn.icon_iv.setBackgroundResource(R.drawable.ic_autobot)
        main_navigation.team_btn.icon_iv.setBackgroundResource(R.drawable.ic_team)

        selectFightBtn()
        setNewFragment(FightFragment())

        setupListeners()
    }

    private fun setupListeners() {
        main_navigation.create_btn.setOnClickListener {
            selectCreateBtn()
            setNewFragment(CreateFragment())
        }

        main_navigation.fight_btn.setOnClickListener {
            selectFightBtn()
            setNewFragment(FightFragment())
        }

        main_navigation.team_btn.setOnClickListener {
            selectTeamBtn()
            setNewFragment(TeamFragment())
        }
    }

    override fun initViewModelObservers() {

    }

    private fun setNewFragment(fragment: Fragment, bundle: Bundle? = null) {
        bundle?.let { fragment.arguments = it }
        supportFragmentManager.beginTransaction()
            .replace(mainFragment.id, fragment).addToBackStack(fragment.tag).commit()
    }

    private fun selectCreateBtn() {
        main_navigation.create_btn.setBackgroundColor(ContextCompat.getColor(this, R.color.color_primary))
        main_navigation.create_btn.title_tv.setTextColor(ContextCompat.getColor(this, R.color.color_white))

        deselectFightBtn()
        deselectTeamBtn()
    }

    private fun deselectCreateBtn() {
        main_navigation.create_btn.setBackgroundColor(ContextCompat.getColor(this, R.color.color_white))
        main_navigation.create_btn.title_tv.setTextColor(ContextCompat.getColor(this, R.color.color_black))
    }

    private fun selectFightBtn() {
        main_navigation.fight_btn.setBackgroundColor(ContextCompat.getColor(this, R.color.color_primary))
        main_navigation.fight_btn.title_tv.setTextColor(ContextCompat.getColor(this, R.color.color_white))

        deselectCreateBtn()
        deselectTeamBtn()
    }

    private fun deselectFightBtn() {
        main_navigation.fight_btn.setBackgroundColor(ContextCompat.getColor(this, R.color.color_white))
        main_navigation.fight_btn.title_tv.setTextColor(ContextCompat.getColor(this, R.color.color_black))
    }

    private fun selectTeamBtn() {
        main_navigation.team_btn.setBackgroundColor(ContextCompat.getColor(this, R.color.color_primary))
        main_navigation.team_btn.title_tv.setTextColor(ContextCompat.getColor(this, R.color.color_white))

        deselectCreateBtn()
        deselectFightBtn()
    }

    private fun deselectTeamBtn() {
        main_navigation.team_btn.setBackgroundColor(ContextCompat.getColor(this, R.color.color_white))
        main_navigation.team_btn.title_tv.setTextColor(ContextCompat.getColor(this, R.color.color_black))
    }
}