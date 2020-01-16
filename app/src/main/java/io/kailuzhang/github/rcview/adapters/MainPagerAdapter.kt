package io.kailuzhang.github.rcview.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import io.kailuzhang.github.rcview.FavoriteFragment
import io.kailuzhang.github.rcview.ProductListFragment

const val FIRST_PAGE_INDEX = 0
const val SECOND_PAGE_INDEX = 1

class MainPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val tabFragmentsCreators: Map<Int, () -> Fragment> = mapOf(
        FIRST_PAGE_INDEX to { FavoriteFragment() },
        SECOND_PAGE_INDEX to { ProductListFragment() }
    )

    override fun getItemCount(): Int = tabFragmentsCreators.size

    override fun createFragment(position: Int): Fragment {
        return tabFragmentsCreators[position]?.invoke() ?: throw IndexOutOfBoundsException()
    }
}