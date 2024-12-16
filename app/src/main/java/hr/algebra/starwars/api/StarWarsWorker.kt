package hr.algebra.starwars.api

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class StarWarsWorker(private val context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {
    override fun doWork(): Result {
        //background thread
        StarWarsFetcher(context).fetchItems(10)
        return Result.success()
    }
}