package uz.triples.qulaymarket.app

import android.app.Application
import android.util.Log
import com.google.android.gms.common.GooglePlayServicesNotAvailableException
import com.google.android.gms.common.GooglePlayServicesRepairableException
import com.google.android.gms.common.GooglePlayServicesUtil
import com.google.android.gms.security.ProviderInstaller
import uz.triples.qulaymarket.SplashActivity
import uz.triples.qulaymarket.database.Cache

class Application : Application() {
    override fun onCreate() {
        super.onCreate()
        Cache.initialize(applicationContext)

        fff()
    }

    private fun fff() {
        try {
            ProviderInstaller.installIfNeeded(this)
        } catch (e: GooglePlayServicesRepairableException) {
            // Thrown when Google Play Services is not installed, up-to-date, or enabled
            // Show dialog to allow users to install, update, or otherwise enable Google Play services.
            GooglePlayServicesUtil.getErrorDialog(
                e.connectionStatusCode,
                SplashActivity(),
                0
            )
        } catch (e: GooglePlayServicesNotAvailableException) {
            Log.e("SecurityException", "Google Play Services not available.")
        }
    }
}