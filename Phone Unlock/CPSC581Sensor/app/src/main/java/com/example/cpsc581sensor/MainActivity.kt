package com.example.cpsc581sensor

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.view.Gravity
import android.view.MotionEvent
import android.view.VelocityTracker
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.cokenotopened.*
import android.view.animation.AnimationUtils
import android.view.animation.TranslateAnimation
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.cocacola
import kotlinx.android.synthetic.main.mentos.*

class MainActivity : AppCompatActivity(), SensorEventListener {
    private lateinit var sensorManager: SensorManager
    private var flags = false
    private var flagd = false
    private var mAccel // acceleration apart from gravity
            = 0f
    private var mAccelCurrent // current acceleration including gravity
            = 0f
    // just for initialization purposes
    private var lastUpdate = 200000000000000000
    private var passwordFlag = true
    private var cokeflag = false
    private var spriteflag = false
    private var pepperflag = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        lastUpdate = System.currentTimeMillis()
        // set up window and start
        setContentView(R.layout.activity_main)
        sprite.visibility = View.INVISIBLE
        cocacola.visibility = View.INVISIBLE
        drpepper.visibility = View.VISIBLE
        checkPassword()
    }
    fun checkPassword(){
        if (passwordFlag == true) {
            drpepper.setOnClickListener(){
                sprite.visibility = View.INVISIBLE
                cocacola.visibility = View.VISIBLE
                drpepper.visibility = View.INVISIBLE
                cokeflag = true
                spriteflag = false
                pepperflag = false
            }
            cocacola.setOnClickListener(){
                sprite.visibility = View.VISIBLE
                cocacola.visibility = View.INVISIBLE
                drpepper.visibility = View.INVISIBLE
                cokeflag = false
                spriteflag = true
                pepperflag = false
            }
            sprite.setOnClickListener(){
                sprite.visibility = View.INVISIBLE
                cocacola.visibility = View.INVISIBLE
                drpepper.visibility = View.VISIBLE
                cokeflag = false
                spriteflag = false
                pepperflag = true
            }
        }
    }
    override fun onSensorChanged(event: SensorEvent) {
        if(event.sensor.type == Sensor.TYPE_ACCELEROMETER){
            getAccelerometer(event)
        }
    }
    private fun getAccelerometer(event: SensorEvent){
        val x = event.values[0]
        val y = event.values[1]
        val z = event.values[2]

        mAccelCurrent = ((x * x + y * y + z * z)/SensorManager.GRAVITY_EARTH * SensorManager.GRAVITY_EARTH)
        val actualTime = event.timestamp

        if (mAccelCurrent >=1000){
            if(actualTime-lastUpdate <200){
                return
            }
            if(cokeflag == true) {
                passwordFlag = false
                lastUpdate=actualTime
                afterShake()
            }else if(spriteflag == true){
                //passwordFlag = false
                lastUpdate=actualTime
                setContentView(R.layout.spritefail)
            }else if(pepperflag == true){
                //passwordFlag = false
                lastUpdate=actualTime
                setContentView(R.layout.drpepperfail)
            }
        }
    }
    private fun afterShake(){
        setContentView(R.layout.cokenotopened)
        val cokebutton = findViewById<Button>(R.id.button)
        cokebutton.setOnClickListener(){
            setContentView(R.layout.cokeopened)
            //startPop()
        }
    }
    fun startPop(){
        setContentView(R.layout.mentos)
        val animation = AnimationUtils.loadAnimation(this, R.anim.slide_down)
        mentoscandy.startAnimation(animation)
    }
    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }
    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }
    override fun onResume() {
        super.onResume()
        sensorManager.registerListener(
            this,
            sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
            SensorManager.SENSOR_DELAY_NORMAL
        )
    }
}

