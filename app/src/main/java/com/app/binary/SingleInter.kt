package com.app.binary

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Switch
import android.widget.Toast
import kotlinx.android.synthetic.main.single_inter.*

class SingleInter: AppCompatActivity() {

    fun togray(binary: String): String {
        var bin: String = binary
        if(binary.indexOf('.') == binary.length-1)
            bin = binary.substring(0, binary.length-1)
        if(binary.indexOf('.')==0)
            bin = "0"
        else if(binary.indexOf('.')>=0){
            bin = binary.substring(0, binary.indexOf('.'))
        }
        var gray: String = bin.get(0).toString()
        for(i in 1..bin.length-1) {
            if(bin.get(i)==bin.get(i-1))
                gray += "0"
            else
                gray += "1"
        }
        return gray
    }

    fun tobinary(binary: String): String {
        var gray: String = binary
        if(binary.indexOf('.') == binary.length-1)
            gray = binary.substring(0, binary.length-1)
        if(binary.indexOf('.')==0)
            gray = "0"
        else if(binary.indexOf('.')>=0){
            gray = binary.substring(0, binary.indexOf('.'))
        }
        var bin: String = gray.get(0).toString()
        for(i in 1..gray.length-1) {
            if(gray.get(i)==bin.get(i-1))
                bin += "0"
            else
                bin += "1"
        }
        return bin
    }

    fun validbin(binary: String): Boolean {
        if(binary == "")
            return false
        var check: Boolean = true
        var dots = 0
        for (i in 0..binary.length - 1) {
            if (binary.get(i) == '.') {
                dots++
                if (dots > 1) {
                    check = false
                    break
                }
                continue
            }
            if (binary.get(i) != '0' && binary.get(i) != '1') {
                check = false
                break
            }
        }
        return check
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.single_inter)
        var bundle: Bundle? = intent.extras
        var title = bundle!!.getString("label")
        singleinterlabel.text = title
        var state = 0
        //var sw = findViewById<Switch>(R.id.singleinterswitch)
        singleinterswitch.setOnCheckedChangeListener { _, isChecked ->
            var ctext = if(isChecked) "Gray to Binary" else "Binary to Gray"
            singleinterswitch.text = ctext
            if(isChecked) {
                state = 1
                query.hint = "Enter Gray Code"
            }
            else {
                state = 0
                query.hint = "Enter Binary Code"
            }
        }

        singleinterbtn.setOnClickListener {
            var q = query.text.toString()
            if(state == 0 && validbin(q)) {
                Toast.makeText(this, "Successful", Toast.LENGTH_SHORT).show()
                singleinterans.text = togray(q)
            }
            else if(state == 1 && validbin(q)) {
                Toast.makeText(this, "Successful", Toast.LENGTH_SHORT).show()
                singleinterans.text = tobinary(q)
            }
            else {
                Toast.makeText(this, "Invalid Code", Toast.LENGTH_LONG).show()
            }
        }
    }
}