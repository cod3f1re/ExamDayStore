package com.cod3f1re.examdaystore.view.location

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.room.Room
import com.cod3f1re.examdaystore.R
import com.cod3f1re.examdaystore.databinding.ActivityLocationBinding
import com.cod3f1re.examdaystore.model.entities.AppDatabase
import com.cod3f1re.examdaystore.model.entities.Locations
import com.cod3f1re.examdaystore.model.repository.location.LocationRepository
import com.cod3f1re.examdaystore.utils.APIModule
import com.cod3f1re.examdaystore.view.MainActivity
import com.cod3f1re.examdaystore.viewmodel.location.LocationViewModel
import com.cod3f1re.examdaystore.viewmodel.location.LocationViewModelFactory
import com.google.android.gms.location.FusedLocationProviderClient
import com.cod3f1re.examdaystore.model.apientities.location.LocationRequest
import com.cod3f1re.examdaystore.view.registry.RegistryActivity
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


/**
 * @author Abraham Rivera Rojas
 * @since 15/10/2022
 */
class LocationActivity : AppCompatActivity(), OnMapReadyCallback,GoogleMap.OnMyLocationButtonClickListener{
    private var location: Locations?=null
    private lateinit var map: GoogleMap
    private lateinit var binding: ActivityLocationBinding
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private var latitudeSelected: Double =-1.0
    private var longitudeSelected: Double =-1.0

    //Inicializar el ViewModel
    private val viewModel: LocationViewModel by viewModels {
        LocationViewModelFactory(LocationRepository(APIModule.getAPIService()))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLocationBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //Leemos posibles valores enviados desde otra activity
        latitudeSelected=intent.getDoubleExtra("latitude",-1.0)
        longitudeSelected=intent.getDoubleExtra("longitude",-1.0)

        //Se crea el fragmento del mapa
        createMapFragment()

        //Se establecen los eventos de la app
        setEvents()
    }

    private fun setEvents(){
        //Damos la funcion de guardar en room la ubicacion actual y guardarlo en el endpoint al btn de enviar
        binding.btnSend.setOnClickListener {
            if(latitudeSelected!=-1.0 && longitudeSelected!=-1.0){
                Toast.makeText(this, "Esta ubicacion ya fue guardada anteriormente.", Toast.LENGTH_SHORT).show()
            }else{
                if(location!=null){
                    setLocationsFromRoom(location!!)
                }else{
                    Toast.makeText(this, "No es posible enviar tu ubicacion si rechazas los permisos.", Toast.LENGTH_SHORT).show()
                }
            }
        }
        viewModel.locationLiveData.observe(this){ code->
            Toast.makeText(this, "Se ha enviado la ubicacion, con codigo: ${code.code}", Toast.LENGTH_SHORT).show()
        }
        binding.btnRegistry.setOnClickListener {
            val intent = Intent(this, RegistryActivity::class.java)
            startActivity(intent)
        }
    }
    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        finish()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun createMapFragment() {
        (supportFragmentManager
            .findFragmentById(R.id.fragmentMap) as SupportMapFragment?)?.getMapAsync(this)
    }

    @SuppressLint("MissingPermission")
    override fun onMapReady(p0: GoogleMap) {
        map = p0
        map.setOnMyLocationButtonClickListener(this)
        enableMyLocation()
        //Se establece el listener para crear el marcador hasta que el mapa esta listo
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        fusedLocationClient.lastLocation.addOnSuccessListener { location : Location? ->
            if (location != null) {
                //Se verifica si obtuvo datos desde otra activity
                if(latitudeSelected!=-1.0 && longitudeSelected!=-1.0){
                    createMarker(latitudeSelected,longitudeSelected)
                }else{
                    createMarker(location.latitude,location.longitude)
                }
            }
        }
    }

    /**
     * Se crea el marcador en el mapa con un zoom y tiempo de aumento predeterminado
     */
    private fun createMarker(lat:Double, long:Double) {
        val favoritePlace = LatLng(lat,long)
        map.addMarker(MarkerOptions().position(favoritePlace).title("Tu ubicación actual!"))
        map.animateCamera(
            CameraUpdateFactory.newLatLngZoom(favoritePlace, 17f),
            4000,
            null
        )
        location=Locations(lat,long)
    }

    /**
     * Se verifica si los permisos son aceptados
     */
    private fun isPermissionsGranted() = ContextCompat.checkSelfPermission(
        this,
        Manifest.permission.ACCESS_FINE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED

    @SuppressLint("MissingPermission")
    private fun enableMyLocation() {
        if (!::map.isInitialized) return
        if (isPermissionsGranted()) {
            map.isMyLocationEnabled = true
        } else {
            requestLocationPermission()
        }
    }

    /**
     * Se verifican los permisos o se muestra un mensaje de error
     */
    private fun requestLocationPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.ACCESS_FINE_LOCATION)) {
            Toast.makeText(this, "Ve a ajustes y acepta los permisos", Toast.LENGTH_SHORT).show()
        } else {
            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_CODE_LOCATION)
        }
    }
    @SuppressLint("MissingPermission")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            REQUEST_CODE_LOCATION -> if(grantResults.isNotEmpty() && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                map.isMyLocationEnabled = true
                map.setOnMyLocationButtonClickListener(this)
            }else{
                Toast.makeText(this, "Para activar la localización ve a ajustes y acepta los permisos", Toast.LENGTH_SHORT).show()
            }
            else -> {}
        }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        if (!::map.isInitialized) return
    }
    //Si da click al elemento para encontrar su ubicacion, se le indica al usuario
    override fun onMyLocationButtonClick(): Boolean {
        Toast.makeText(this, "Navegando hacia su ubicación...", Toast.LENGTH_SHORT).show()
        return false
    }

    companion object {
        const val REQUEST_CODE_LOCATION = 0
    }

    //Se establece una nueva ubicacion en la bd de Room
    private fun setLocationsFromRoom(location:Locations){
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "locations"
        ).allowMainThreadQueries().build()
        db.locationsDao().insert(location)
        viewModel.getLocationCode(LocationRequest(location.latitude.toString(),location.longitude.toString(),"Abraham"))
    }
}