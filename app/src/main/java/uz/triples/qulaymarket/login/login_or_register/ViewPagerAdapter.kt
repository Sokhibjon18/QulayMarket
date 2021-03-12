package uz.triples.qulaymarket.login.login_or_register

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import uz.triples.qulaymarket.login.login_or_register.login_page.LoginPage
import uz.triples.qulaymarket.login.login_or_register.register_page.RegisterPage

class ViewPagerAdapter(fm: FragmentManager): FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getCount() = 2

    override fun getItem(position: Int): Fragment {
        return when(position){
            0-> LoginPage.getInstance()
            else -> RegisterPage()
        }
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when(position){
            0 -> "Kirish"
            else -> "Ro'yhatdan o'tish"
        }
    }
}