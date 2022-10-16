package com.cod3f1re.examdaystore.view.location

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.room.Room
import com.cod3f1re.examdaystore.R
import com.cod3f1re.examdaystore.databinding.ActivityLocationBinding
import com.cod3f1re.examdaystore.model.entities.AppDatabase
import com.cod3f1re.examdaystore.model.entities.Locations
import com.cod3f1re.examdaystore.view.MainActivity
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class LocationActivity : AppCompatActivity(), OnMapReadyCallback,GoogleMap.OnMyLocationButtonClickListener{
    private var location: Locations?=null
    private lateinit var map: GoogleMap
    private lateinit var binding: ActivityLocationBinding
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLocationBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        createMapFragment()
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        fusedLocationClient.lastLocation.addOnSuccessListener { location : Location? ->
            if (location != null) {
                createMarker(location.latitude,location.longitude)
            }
        }
        binding.btnSend.setOnClickListener {
            if(location!=null){
                setLocationsFromRoom(location!!)
            }else{
                Toast.makeText(this, "No es posible enviar tu ubicacion si rechazas los permisos", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onBackPressed() {
        finish()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun createMapFragment() {
        (supportFragmentManager
            .findFragmentById(R.id.fragmentMap) as SupportMapFragment?)?.getMapAsync(this)
    }

    override fun onMapReady(p0: GoogleMap) {
        map = p0
        map.setOnMyLocationButtonClickListener(this)
        enableMyLocation()
    }

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

    override fun onMyLocationButtonClick(): Boolean {
        Toast.makeText(this, "Navegando hacia su ubicación...", Toast.LENGTH_SHORT).show()
        return false
    }

    companion object {
        const val REQUEST_CODE_LOCATION = 0
    }

    private fun setLocationsFromRoom(location:Locations){
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "locations"
        ).build()
        db.locationsDao().insert(location)
    }
}