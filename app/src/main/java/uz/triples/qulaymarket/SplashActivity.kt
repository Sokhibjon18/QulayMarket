package uz.triples.qulaymarket

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import uz.triples.qulaymarket.database.Cache

class SplashActivity : AppCompatActivity() {

    private val mainContainer: NavController by lazy {
        val controller = supportFragmentManager.findFragmentById(R.id.fragmentContainer)!!
        controller.findNavController() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        if (Cache.loggedIn()) {
            val intent = Intent(this@SplashActivity, HomeActivity::class.java )

            startActivity(intent)
        }
    }

    override fun onBackPressed() {
        val current = mainContainer.currentDestination!!.id
        if(current == R.id.settingPasswordFragment){
            mainContainer.navigate(R.id.action_settingPasswordFragment_to_loginFragment)
        } else{
            super.onBackPressed()
        }

    }
}