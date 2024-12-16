package hr.algebra.starwars

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import hr.algebra.starwars.databinding.ActivitySplashScreenBinding
import hr.algebra.starwars.framework.applyAnimation
import hr.algebra.starwars.framework.startActivity
import hr.algebra.starwars.HostActivity
import hr.algebra.starwars.R
import hr.algebra.starwars.api.StarWarsWorker
import hr.algebra.starwars.framework.applyAnimation
import hr.algebra.starwars.framework.callDelayed
import hr.algebra.starwars.framework.getBooleanPreference
import hr.algebra.starwars.framework.isOnline
import hr.algebra.starwars.framework.startActivity

private const val DELAY = 3000L
const val DATA_IMPORTED = "hr.algebra.starwars.data_imported"
class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        startAnimations()
        redirect()
    }

    private fun startAnimations() {
        binding.tvSplash.applyAnimation(R.anim.blink)
        binding.ivSplash.applyAnimation(R.anim.rotate)
    }

    private fun redirect() {

        if(getBooleanPreference(DATA_IMPORTED)){
            callDelayed(DELAY) { startActivity<HostActivity>() }


        }else{
            if(isOnline()){

                WorkManager.getInstance(this).apply{
                    enqueueUniqueWork(
                        DATA_IMPORTED,
                        ExistingWorkPolicy.KEEP,
                        OneTimeWorkRequest.Companion.from(StarWarsWorker::class.java)
                    )
                }

            }else{
                binding.tvSplash.text = getString(R.string.no_internet)
                callDelayed(DELAY) { finish() }
            }

        }



    }

}