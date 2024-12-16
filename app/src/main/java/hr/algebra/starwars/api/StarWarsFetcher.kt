package hr.algebra.starwars.api

import android.content.Context
import android.content.Intent
import hr.algebra.starwars.StarWarsReceiver
import hr.algebra.starwars.framework.sendBroadcast

class StarWarsFetcher (private val context : Context){
    fun fetchItems(count : Int){
        //fake work like real Croat!
        //background thread
        Thread.sleep(6000)
        //javi glavnoj dretvi da je gotovo
        context.sendBroadcast<StarWarsReceiver>()

    }

}