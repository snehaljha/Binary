package com.app.binary

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        octal.setOnClickListener {
            var intent = Intent(this, SingleInput::class.java)
            intent.putExtra("label", "Binary to Octal")
            startActivity(intent)
        }

        decimal.setOnClickListener {
            var intent = Intent(this, SingleInput::class.java)
            intent.putExtra("label", "Binary to Decimal")
            startActivity(intent)
        }

        hexadecimal.setOnClickListener {
            var intent = Intent(this, SingleInput::class.java)
            intent.putExtra("label", "Binary to Hexadecimal")
            startActivity(intent)
        }

        graybin.setOnClickListener {
            var intent = Intent(this, SingleInter::class.java)
            intent.putExtra("label", "Binary-Gray")
            startActivity(intent)
        }

        btnaddsub.setOnClickListener {
            var intent = Intent(this, DoubleInter::class.java)
            intent.putExtra("label", "Addition/Subtraction")
            startActivity(intent)
        }
    }
}
