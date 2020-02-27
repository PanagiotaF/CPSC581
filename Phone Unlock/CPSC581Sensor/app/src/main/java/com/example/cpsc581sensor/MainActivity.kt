package com.example.cpsc581sensor

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.cokenotopened.*
import android.view.animation.AnimationUtils
import android.view.animation.TranslateAnimation
import android.widget.Button
import android.widget.ImageView
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
    private var acc = false

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
                acc = true
            }
            cocacola.setOnClickListener(){
                sprite.visibility = View.VISIBLE
                cocacola.visibility = View.INVISIBLE
                drpepper.visibility = View.INVISIBLE
                acc = false
            }
            sprite.setOnClickListener(){
                sprite.visibility = View.INVISIBLE
                cocacola.visibility = View.INVISIBLE
                drpepper.visibility = View.VISIBLE
                acc = false
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
            // success
            /*Toast.makeText(applicationContext,"this is toast message",Toast.LENGTH_SHORT).show()
            val toast = Toast.makeText(applicationContext, "Hello Javatpoint", Toast.LENGTH_LONG)
            toast.show()
            val myToast = Toast.makeText(applicationContext,"toast message with gravity",Toast.LENGTH_SHORT)
            myToast.setGravity(Gravity.LEFT,200,200)
            myToast.show()*/
            passwordFlag = false
            lastUpdate=actualTime
            afterShake()
        }
    }

    private fun afterShake(){
        setContentView(R.layout.cokenotopened)
        val cokebutton = findViewById(R.id.coke_button) as ImageView
        cokebutton.setOnClickListener(){
            Toast.makeText(applicationContext,"this is toast message",Toast.LENGTH_SHORT).show()
            val toast = Toast.makeText(applicationContext, "Hello Javatpoint", Toast.LENGTH_LONG)
            toast.show()
            setContentView(R.layout.cokeopened)
            startPop()
        }



    }
    fun startPop(){
        setContentView(R.layout.pop1)
        //val drink = findViewById(R.id.cokedrink) as ImageView

       // val animation = AnimationUtils.loadAnimation(this, R.anim.animation_list)
       // val translateAnimation = TranslateAnimation( 0.0f, 0.0f,0.0f, 735.0f)
        //translateAnimation.duration = 2000
        //translateAnimation.fillAfter = true

       // mentoscandy.startAnimation(animation)
        /*private void startPop(){
            translateAnimation = new TranslateAnimation(Animation.ABSOLUTE,0.0f,Animation.ABSOLUTE, 0.0f,
            Animation.ABSOLUTE, 0.0f,
            Animation.ABSOLUTE, 735.0f );
            translateAnimation.setDuration(2000);
            translateAnimation.setFillAfter(true);
            imageView.startAnimation(translateAnimation);
        }*/

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

    /*override fun onTouchEvent(event: MotionEvent?): Boolean {
        if(cocacola.visibility == View.VISIBLE){
            if(event!!.action == MotionEvent.ACTION_MOVE){

            }
        }else if(sprite.visibility == View.VISIBLE){
            if(event!!.action == MotionEvent.ACTION_MOVE){

            }
        }else if(drpepper.visibility == View.VISIBLE){
            if(event!!.action == MotionEvent.ACTION_MOVE){

            }
        }
        return super.onTouchEvent(event)
    }*/
}

