package uz.triples.qulaymarket

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.core.view.iterator
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.*

class HomeActivity : AppCompatActivity() {
    private val mainContainer: NavController by lazy {
        val controller = supportFragmentManager.findFragmentById(R.id.fragmentContainer)!!
        controller.findNavController() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loadLanguage()

        setContentView(R.layout.activity_home)

        nav_view.setupWithNavController(mainContainer)

        mainContainer.addOnDestinationChangedListener { controller, destination, arguments ->
            for(menuItem in nav_view.menu.iterator()){
                menuItem.isEnabled = true
            }

            val menu = nav_view.menu.findItem(destination.id)
            menu?.isEnabled = false
        }
    }

    private fun loadLanguage(){
        val languageToLoad = "uz"

        val locale = Locale(languageToLoad)
        Locale.setDefault(locale)
        val config = Configuration()
        config.setLocale(locale)
        baseContext.resources.updateConfiguration(
            config,
            baseContext.resources.displayMetrics
        )
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
        else if(currentFragment == R.id.profileFragment || currentFragment == R.id.addAnnouncementFragment
            || currentFragment == R.id.likedFragment || currentFragment == R.id.chatNotFoundFragment){
            nav_view.findViewById<View>(R.id.mainFragment).performClick()
        } else if(currentFragment == R.id.myProfileDetails){
            mainContainer.navigate(R.id.action_myProfileDetails_to_profileFragment)
        }else{
            super.onBackPressed()
        }
    }
}