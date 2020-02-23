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
    private var first = 0;
    private var second = 0;
    private var third = 0;
    private var fourth = 0;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // successful password input as follows
        watercan.setOnClickListener(){
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
            clickCount++; checkCount()}
    }
    fun checkCount (){
        if (clickCount>6){
            rainfall.visibility= View.VISIBLE
            //setContentView(R.layout.fail_layout)
            cuteSun.setOnClickListener(){
                clickCount=0
                rainfall.visibility= View.INVISIBLE
                setContentView(R.layout.activity_main)
            }
        }
    }

}
