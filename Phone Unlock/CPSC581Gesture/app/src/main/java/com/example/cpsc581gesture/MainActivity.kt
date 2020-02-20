package com.example.cpsc581gesture

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main. activity_main.*
import android.widget.Toast
import android.view.Gravity
import kotlinx.android.synthetic.main.success_screen.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        watercan.setOnClickListener(){
            plant1.setOnClickListener(){
                plant5.setOnClickListener(){
                    plant3.setOnClickListener(){
                        shovel.setOnClickListener(){
                            plant2.setOnClickListener(){
                                plant5.setOnClickListener(){
                                    Toast.makeText(applicationContext,"this is toast message",Toast.LENGTH_SHORT).show()

                                    val toast = Toast.makeText(applicationContext, "Hello Javatpoint", Toast.LENGTH_SHORT)
                                    toast.show()

                                    val myToast = Toast.makeText(applicationContext,"toast message with gravity",Toast.LENGTH_SHORT)
                                    myToast.setGravity(Gravity.LEFT,200,200)
                                    myToast.show()

                                    setContentView(R.layout.success_screen)
                                    watercup.setOnClickListener(){
                                        setContentView(R.layout.homescreen)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }


}
