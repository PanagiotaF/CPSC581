package com.example.cpsc581sensor

import android.animation.ObjectAnimator
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.view.KeyEvent
import android.view.View
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.ImageView
import android.widget.VideoView
import kotlinx.android.synthetic.main.activity_main.cocacola
import kotlinx.android.synthetic.main.mentos.*

class MainActivity : AppCompatActivity(), SensorEventListener {
    private lateinit var sensorManager: SensorManager
    private var mAccelCurrent // current acceleration including gravity
            = 0f
    // just for initialization purposes
    private var lastUpdate = 200000000000000000
    private var passwordFlag = true
    private var acc = false
    private var sp = false
    private var dr = false
    private var mentosdone = false
    // coke can after shaking
    private var opened = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager

        lastUpdate = System.currentTimeMillis()
        setContentView(R.layout.activity_main)
        sprite.visibility = View.INVISIBLE
        cocacola.visibility = View.INVISIBLE
        drpepper.visibility = View.VISIBLE
        dr = true
        sp = false
        checkPassword()
    }
    fun checkPassword(){
        if (passwordFlag == true) {
            drpepper.setOnClickListener(){
                sprite.visibility = View.INVISIBLE
                sprite_tab.visibility = View.INVISIBLE
                cocacola.visibility = View.VISIBLE
                drpepper.visibility = View.INVISIBLE
                drpepper_tab.visibility = View.INVISIBLE
                dr = false
                sp = false
                acc = true
            }
            cocacola.setOnClickListener(){
                sprite.visibility = View.VISIBLE
                sprite_tab.visibility = View.VISIBLE
                cocacola.visibility = View.INVISIBLE
                drpepper.visibility = View.INVISIBLE
                drpepper_tab.visibility = View.INVISIBLE
                dr = false
                sp = true
                acc = false
            }
            sprite.setOnClickListener(){
                sprite.visibility = View.INVISIBLE
                sprite_tab.visibility = View.INVISIBLE
                cocacola.visibility = View.INVISIBLE
                drpepper.visibility = View.VISIBLE
                drpepper_tab.visibility = View.VISIBLE
                acc = false
                dr = true
                sp = false
            }
        }
    }    // click the volume down key
    override fun onKeyDown(keycode: Int, event: KeyEvent): Boolean {
        return when (keycode){
            KeyEvent.KEYCODE_VOLUME_DOWN ->{
                //startMentos()
                setContentView(R.layout.mentos)
                val animation = AnimationUtils.loadAnimation(this,R.anim.slide_down)
                mentoscandy.startAnimation(animation)
                Handler().postDelayed({
                    mentosdone = true
                    startVideo()
                }, 4000)
                true
            }
            else -> super.onKeyUp(keycode, event)
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
            if(dr == true){
                /////// reset button stuff
                drpepperFail()
            }else if(sp == true){
                spriteFail()
            } else{
                passwordFlag = false
                lastUpdate=actualTime
                afterShake()
            }
        }
    }
    // error handling for sprite
    fun spriteFail(){
        setContentView(R.layout.spritefail)
    }
    // error handling for dr.pepper
    fun drpepperFail(){
        setContentView(R.layout.drpepperfail)
    }
    // tab opened
    fun opening(){
        setContentView(R.layout.cokeopened)
        val drink = findViewById(R.id.tab) as ImageView
        ObjectAnimator.ofFloat(drink, "translationY", 0f).apply {
            duration = 2000
            start()
            opened = true
        }
    }
    private fun afterShake(){
        setContentView(R.layout.cokenotopened)
        val cokebutton = findViewById(R.id.coke_button) as ImageView
        cokebutton.setOnClickListener() {
            setContentView(R.layout.cokeopened)
            opening()
            if (opened == true) {
                startPop()
            }
        }
    }
    // drink moving down
    fun startPop(){
        setContentView(R.layout.pop1)
        val drink = findViewById(R.id.cokedrink) as ImageView
        ObjectAnimator.ofFloat(drink, "translationY", 4000f).apply {
            duration = 4000
            start()
        }
    }
    // video of explosion
    fun startVideo(){
        if(mentosdone == true){
            setContentView(R.layout.video)
            val vv = findViewById(R.id.videoView) as VideoView
            val path = Uri.parse("android.resource://" + packageName +"/" +R.raw.video)
            vv.setVideoURI(path)
            vv.setOnPreparedListener{
                vv.start()
            }
            vv.setOnCompletionListener{
                setContentView(R.layout.camera)
            }
        }
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


