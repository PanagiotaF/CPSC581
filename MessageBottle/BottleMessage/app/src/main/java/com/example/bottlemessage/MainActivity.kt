package com.example.bottlemessage

import android.graphics.drawable.AnimationDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.ImageView
import android.widget.VideoView
import kotlinx.android.synthetic.main.activity_main.*

private lateinit var no_bottle_animation : AnimationDrawable
private lateinit var bottle_still_animation : AnimationDrawable
private lateinit var bottle_swim_animation : AnimationDrawable

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottle_png.visibility= View.INVISIBLE
        name_sender.visibility = View.INVISIBLE

        val beachImage = findViewById<ImageView>(R.id.beach_img) .apply {
            setBackgroundResource(R.drawable.no_bottle_anim)
            no_bottle_animation = background as AnimationDrawable
        }

        val bottleSwim = findViewById<ImageView>(R.id.bottle_swim_img) .apply {
            setBackgroundResource(R.drawable.bottle_swim_anim)
            bottle_swim_animation = background as AnimationDrawable
        }
        bottleSwim.setOnClickListener(){
            bottle_swim_animation.start()
            Handler().postDelayed({
                bottleSwim.visibility=View.INVISIBLE
                bottle_png.visibility=View.VISIBLE
                no_bottle_animation.start()
            }, 3800)
         }

        bottle_png.setOnClickListener(){
            no_bottle_animation.stop()
            beachImage.visibility = View.INVISIBLE
            videoplay()
        };
    }

    private fun videoplay(){
        val videoView = findViewById<VideoView>(R.id.message_video)
        // relative path
        val path  = "android.resource://" + packageName + "/" + R.raw.drawvideo
        videoView.setVideoURI(Uri.parse(path))

        bottle_png.visibility = View.INVISIBLE
        name_sender.visibility = View.VISIBLE
        videoView.start()

        videoView.setOnClickListener(){
            videoView.visibility = View.INVISIBLE
            beach_img.visibility = View.VISIBLE
            name_sender.visibility = View.INVISIBLE
            no_bottle_animation.start()
        }
    }

}
