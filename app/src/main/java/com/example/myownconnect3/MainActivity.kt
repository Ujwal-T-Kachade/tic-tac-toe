package com.example.myownconnect3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import kotlinx.android.synthetic.main.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    var activeplayer = 0        // 0 :yellow    1 : red
    var state = arrayListOf<Int>(2, 2, 2, 2, 2, 2, 2, 2, 2)     //let 2 represents empty
    val winningPositions = arrayOf(
        intArrayOf(0, 1, 2),
        intArrayOf(3, 4, 5),
        intArrayOf(6, 7, 8),
        intArrayOf(0, 3, 6),
        intArrayOf(1, 4, 7),
        intArrayOf(2, 5, 8),
        intArrayOf(0, 4, 8),
        intArrayOf(2, 4, 6)
    )
    var gameActive = true;
    var count =0
    fun dropdown(view: View) {
        count++

        val counter = view as ImageView
        val tapedcounter = view.tag.toString().toInt()

        if (state[tapedcounter] == 2 && gameActive) {


            counter.translationY = -1500f
            counter.animate().translationYBy(1500f)

            state[tapedcounter] = activeplayer


            if (activeplayer == 0) {
                counter.setImageResource(R.drawable.yellow)
                activeplayer = 1

            } else {
                counter.setImageResource(R.drawable.red)
                activeplayer = 0

            }


            // changing the state

            for (a in winningPositions) {
                if (state[a[0]] == state[a[1]] && state[a[1]] == state[a[2]] && state[a[1]] != 2) {
                    // someone won
                    gameActive = false
                    if(activeplayer == 0){
                        result.text = "CONGRATS RED WON"
                    }else{
                        result.text = "CONGRATS YELLOW WON"
                    }

                    playbutton.visibility =  View.VISIBLE
                    break
                }

            }
            if(count == 9){

                gameActive = false
                result.text = "match draw"
                playbutton.visibility =  View.VISIBLE

            }

        }
    }



    fun playagain(view: View) {
        result.text = ""

        playbutton.visibility = View.INVISIBLE

        for (i in 0 until gridlayout.childCount) {
            val counter = gridlayout.getChildAt(i) as ImageView
            counter.setImageDrawable(null)
        }
        state = arrayListOf<Int>(2, 2, 2, 2, 2, 2, 2, 2, 2)
        gameActive = true
        activeplayer = 0
        count = 0
    }
}