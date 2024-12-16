package hr.algebra.starwars

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import hr.algebra.starwars.framework.setBooleanPreference
import hr.algebra.starwars.framework.startActivity

class StarWarsReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        context.setBooleanPreference(DATA_IMPORTED)
        context.startActivity<HostActivity>()
    }
}