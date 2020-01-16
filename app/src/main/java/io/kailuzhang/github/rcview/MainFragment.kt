package io.kailuzhang.github.rcview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayoutMediator
import io.kailuzhang.github.rcview.adapters.FIRST_PAGE_INDEX
import io.kailuzhang.github.rcview.adapters.MainPagerAdapter
import io.kailuzhang.github.rcview.adapters.SECOND_PAGE_INDEX
import io.kailuzhang.github.rcview.databinding.MainFragmentBinding
import io.kailuzhang.github.rcview.viewmodels.MainViewModel

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var binding: MainFragmentBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(inflater, container, false)
        val tabLayout = binding.tabs
        val viewPager = binding.viewPager

        viewPager.adapter = MainPagerAdapter(this)

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = getTabTitle(position)
        }.attach()

        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider.NewInstanceFactory().create(MainViewModel::class.java)
    }

    private fun getTabTitle(position: Int): String? {
        return when (position) {
            FIRST_PAGE_INDEX -> getString(R.string.my_favorite_title)
            SECOND_PAGE_INDEX -> getString(R.string.product_list_title)
            else -> null
        }
    }
}
