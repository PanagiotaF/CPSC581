package com.example.cpsc581gesture

import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main. activity_main.*
import android.widget.Toast
import android.view.Gravity
import android.view.View
import kotlinx.android.synthetic.main.success_screen.*

class MainActivity : AppCompatActivity() {
    private var clickCount = 0;
    private var tapCount = 0;
    private var tapCount2 =0;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //tap.visibility = View.INVISIBLE
        //tap2.visibility = View.INVISIBLE
        setContentView(R.layout.activity_main)
        checkPassword()
    }

    fun checkPassword(){
        tap.visibility = View.INVISIBLE
        tap2.visibility = View.INVISIBLE
        watercan.setOnClickListener(){
            tapCount++
            checktap()
            clickCount++;
            checkCount()
            plant1.setOnClickListener(){
                clickCount++;
                checkCount()
                plant5.setOnClickListener(){
                    clickCount++;
                    checkCount()
                    plant3.setOnClickListener(){
                        clickCount++;
                        checkCount()
                        shovel.setOnClickListener(){
                            tapCount2++
                            checktap2()
                            clickCount++;
                            checkCount()
                            plant2.setOnClickListener(){
                                clickCount++;
                                checkCount()
                                plant5.setOnClickListener(){
                                    clickCount=0
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

        // wrong password
        plant1.setOnClickListener(){
            clickCount++; checkCount()}
        plant2.setOnClickListener(){
            clickCount++; checkCount()}
        plant3.setOnClickListener(){
            clickCount++; checkCount()}
        plant4.setOnClickListener(){
            clickCount++; checkCount()}
        plant5.setOnClickListener(){
            clickCount++; checkCount()}
        plant6.setOnClickListener(){
            clickCount++; checkCount()}
        shovel.setOnClickListener(){
            checktap2()
            clickCount++; checkCount()}

    }
    fun checkCount (){
        if (clickCount>6){
            rainfall.visibility= View.VISIBLE
            cuteSun.setOnClickListener(){
                clickCount=0
                rainfall.visibility= View.INVISIBLE
                checkPassword()
            }
        }
    }
    fun checktap (){
        if (tapCount == 1){
            tap.visibility = View.VISIBLE
        } else if (tapCount ==2){
            tap.visibility = View.INVISIBLE
            tapCount=0;
        }
    }
    fun checktap2() {
        if (tapCount2 == 1){
            tap2.visibility = View.VISIBLE
        } else if (tapCount2 ==2){
            tap2.visibility = View.INVISIBLE
            tapCount2=0;
        }
    }
}
