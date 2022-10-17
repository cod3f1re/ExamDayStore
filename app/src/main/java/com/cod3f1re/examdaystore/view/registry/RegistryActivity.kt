package com.cod3f1re.examdaystore.view.registry

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.cod3f1re.examdaystore.databinding.ActivityRegistryBinding
import com.cod3f1re.examdaystore.model.LocationAdapter
import com.cod3f1re.examdaystore.model.entities.AppDatabase
import com.cod3f1re.examdaystore.model.entities.Locations
import com.cod3f1re.examdaystore.model.entities.LocationsItem
import com.cod3f1re.examdaystore.view.location.LocationActivity

/**
 * @author Abraham Rivera Rojas
 * @since 15/10/2022
 */
class RegistryActivity : AppCompatActivity() {

    //Contiene la vista inflada para acceder a los id
    private lateinit var binding: ActivityRegistryBinding
    //Contiene la lista de ubicaciones anteriormente enviadas y guardadas de la bd
    var locationList: MutableList<Locations>? = null
    //Contiene la lista de ubicaciones anteriormente enviadas y guardadas
    private var locationAdapterList= ArrayList<LocationsItem>()
    //Adaptador del recyclerview
    private lateinit var locationAdapter: LocationAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistryBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        getLocationsFromRoom()
    }


    private fun getLocationsFromRoom(){
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "locations"
        ).allowMainThreadQueries().build()
        val locationDAO = db.locationsDao()
        locationList = locationDAO.getLocations()

        var id=0
        for (location in locationList!!) {
            id++
            locationAdapterList.add(LocationsItem(id,location.latitude.toString(),location.longitude.toString()))
        }
        // Se establece el adaptador en vertical
        binding.rvLocations.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL ,false)

        //Se agregan las listas de datos
        locationAdapter = LocationAdapter(locationAdapterList)
        locationAdapter.submitList(locationAdapterList)
        binding.rvLocations.adapter = locationAdapter

        //Le damos la funcion de tap a las ubicaciones
        locationAdapter.onItemClick = { locations ->
            Log.d("TAGGGGG", locations.id.toString())
            val intent = Intent(this,LocationActivity::class.java)
            intent.putExtra("latitude",locations.latitude.toDouble())
            intent.putExtra("longitude",locations.longitude.toDouble())
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
    }

}