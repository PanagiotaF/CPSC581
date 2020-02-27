package com.example.cpsc581sensor

import android.animation.ObjectAnimator
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.net.Uri
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.ImageView
import android.widget.VideoView
import androidx.core.animation.addListener
import kotlinx.android.synthetic.main.activity_main.cocacola

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
    private var acc = false
    private var sp = false
    private var dr = false

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
    }

    // click the volume down key
    override fun onKeyDown(keycode: Int, event: KeyEvent): Boolean {
        return when (keycode){
            KeyEvent.KEYCODE_VOLUME_DOWN ->{
                startMentos()
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
            passwordFlag = false
            lastUpdate=actualTime
            afterShake()
        }
    }

    // NOT WORKING
    // to go back to the selection after fails
    fun first(){
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        lastUpdate = System.currentTimeMillis()
        setContentView(R.layout.activity_main)
        sprite.visibility = View.INVISIBLE
        cocacola.visibility = View.INVISIBLE
        drpepper.visibility = View.VISIBLE
        dr = true
        sp = false
        acc = false
        passwordFlag = true
        checkPassword()
    }


    // error handling for sprite
    fun spriteFail(){
        setContentView(R.layout.spritefail)
        val fail = findViewById(R.id.fail) as ImageView
        fail.setOnClickListener() {
            first()
        }
    }


    // error handling for dr.pepper
    fun drpepperFail(){
        setContentView(R.layout.drpepperfail)
        val faildrpepper = findViewById(R.id.faildrpepper) as ImageView
        faildrpepper.setOnClickListener() {
           first()
        }
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


    // coke can after shaking
    private var opened = false
    private fun afterShake(){
        if (dr == true){
            drpepperFail()
        }
        else if (sp == true){
            spriteFail()
        }
        else {
            setContentView(R.layout.cokenotopened)
            val cokebutton = findViewById(R.id.coke_button) as ImageView
            cokebutton.setOnClickListener() {
                val opening = setContentView(R.layout.cokeopened)
                opening()
                if (opened == true) {
                    startPop()
                }
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


    // dropping mentos
    fun startMentos(){
        setContentView(R.layout.mentos)
        val drink = findViewById(R.id.mentoscandy) as ImageView
        val men = ObjectAnimator.ofFloat(drink, "translationY", 300f)
        men.apply {
            duration = 2000
            men.addListener(onStart = {
                drink.visibility = View.VISIBLE
            })
            AccelerateDecelerateInterpolator()
            start()
        }
        startVideo()
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


