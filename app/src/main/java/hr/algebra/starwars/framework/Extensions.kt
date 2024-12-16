package hr.algebra.starwars.framework

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.AnimationUtils
import androidx.core.content.getSystemService
import androidx.preference.PreferenceManager
import hr.algebra.starwars.HostActivity
import hr.algebra.starwars.StarWarsReceiver

fun View.applyAnimation(id: Int) {
    startAnimation(AnimationUtils.loadAnimation(context, id))
}

inline fun <reified T : Activity> Context.startActivity() {
    startActivity(
        Intent(this, T::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
    )
}
inline fun <reified T : BroadcastReceiver> Context.sendBroadcast() {
    sendBroadcast(Intent(this, T::class.java))
}




fun Context.setBooleanPreference(key: String, value: Boolean = true){
    PreferenceManager.getDefaultSharedPreferences(this)
        .edit()
        .putBoolean(key,value)
        .apply()
}

fun Context.getBooleanPreference(key: String) =
    PreferenceManager.getDefaultSharedPreferences(this)
        .getBoolean(key, false)


fun Context.isOnline() : Boolean {
    val connectivityManager = getSystemService<ConnectivityManager>()
    connectivityManager?.activeNetwork?.let{ network ->
        connectivityManager.getNetworkCapabilities(network)?.let{ cap ->
            return cap.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
            cap.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
        }

    }


    return false
}

fun callDelayed(delay : Long, work : Runnable){
    Handler(Looper.getMainLooper()).postDelayed(
        work,
        delay
    )
}