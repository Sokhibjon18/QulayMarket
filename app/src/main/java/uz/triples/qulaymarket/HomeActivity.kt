package uz.triples.qulaymarket

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.fragment_home.*

class HomeActivity : AppCompatActivity() {
    private val mainContainer: NavController by lazy { findNavController(R.id.fragmentContainer) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        initializeTabOnClick()
    }

    private fun initializeTabOnClick() {
        tabLayout.addOnTabSelectedListener(object :
            TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab!!.position) {
                    0 -> mainContainerNavigator(R.id.mainFragment)
                    1 -> mainContainerNavigator(R.id.likedFragment)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })
    }

    private fun mainContainerNavigator(id: Int) {
        mainContainer.navigate(id)
    }

    override fun onBackPressed() {
        val container = findNavController(R.id.fragmentContainer)
        val currentFragment = container.currentDestination!!.id
        if (currentFragment == R.id.mainFragment) {
            val containerMini =
                findNavController(R.id.fragmentContainerOfHomeFragment)
            val id = containerMini.currentDestination?.id
            if (id == R.id.recentlySearchedFragment || id == R.id.notFoundFragment) {
                containerMini.navigate(R.id.allGoodsFragment)
                searchEditText.setText("")
            } else if (id == R.id.allGoodsFragment) {
                finish()
            } else {
                super.onBackPressed()
            }
        }
        super.onBackPressed()
    }
}