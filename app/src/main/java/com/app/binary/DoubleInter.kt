package com.app.binary

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.double_inter.*

class DoubleInter: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.double_inter)
        var bundle: Bundle? = intent.extras
        val title = bundle!!.getString("label")
        doubleinterlabel.text = title
        var state = false
        doubleinterswitch.setOnCheckedChangeListener { _, isChecked ->
            doubleinterswitch.text = if(isChecked) "Subtraction" else "Addition"
            state = if(isChecked) true else false
            doubleinterbtn.text = if(isChecked) "Subtract" else "Add"
        }

        doubleinterbtn.setOnClickListener {
            var binary1 = query1.text.toString()
            var binary2 = query2.text.toString()
            var result: String

            if(validbin(binary1) && validbin(binary2)) {
                Toast.makeText(this, "Successful", Toast.LENGTH_SHORT).show()
                if(state)
                    result = subtract(binary1, binary2)
                else
                    result = binadd(binary1, binary2)

                doubleinterans.text = result
            }
            else
                Toast.makeText(this, "Invalid Binary Code", Toast.LENGTH_LONG).show()
        }
    }

    fun binadd(binary1: String, binary2: String, op: Boolean = true): String {
        val bins = if(op) equaldigit(binary1, binary2, true) else equaldigit(binary1, binary2, false)
        var bin1 = bins[0]
        var bin2 = bins[1]

        var ans = ""
        var carry = 0

        for(i in 1..bin1.length) {
            if(bin1.get(bin1.length-i) == '.') {
                ans = "." + ans
                continue
            }
            var sum = carry + (bin1.get(bin1.length-i).toString()).toInt() + (bin2.get(bin2.length-i).toString()).toInt()
            when(sum) {
                0 -> {
                    ans = "0" + ans
                    carry = 0
                }
                1 -> {
                    ans = "1" + ans
                    carry = 0
                }
                2 -> {
                    ans = "0" + ans
                    carry = 1
                }
                else -> {
                    ans = "1" + ans
                    carry = 1
                }
            }
        }
        if(carry == 1 && op)
            ans = "1" + ans


        return ans
    }

    fun equaldigit(s1: String, s2: String, oz: Boolean): MutableList<String> {
        var num1 = s1
        var num2 = s2
        var dot1: Int = num1.indexOf(".")
        var dot2: Int = num2.indexOf(".")
        if(dot1 < 0)
            num1 += ".0"
        if(dot2 < 0)
            num2 += ".0"

        dot1 = num1.indexOf(".")
        dot2 = num2.indexOf(".")

        if(dot1>dot2) {
            val v = if(oz) "0" else "1"
            for(i in 1..dot1-dot2)
                num2 = v + num2
        }
        else if(dot1<dot2)
            for(i in 1..dot2-dot1)
                num1 = "0" + num1

        if(num1.length > num2.length)
            for(i in 1..num1.length-num2.length)
                num2 += "0"
        else if(num1.length < num2.length)
            for(i in 1..num2.length-num1.length)
                num1 += "0"


        val al = mutableListOf(num1, num2)
        return al
    }

    fun twoscomplement(binary: String): String {
        var check = false
        var binc: String = ""
        for(i in 1..binary.length) {
            if(binary.get(binary.length-i)=='.') {
                binc = "." + binc
                continue
            }
            if(binary.get(binary.length-i)=='1' && check == false) {
                binc = "1" + binc
                check = true
                continue
            }
            if(check == false){
                binc = binary.get(binary.length-i) + binc
                continue
            }
            if(binary.get(binary.length-i)=='0')
                binc = "1" + binc
            else
                binc = "0" + binc
        }

        return binc
    }

    fun subtract(binary1: String, binary2: String): String {
        if(binary1.toDouble() == 1.0 && binary2.toDouble() == 0.0)
            return "1.0"
        var bin1 = binary1
        var bin2 = twoscomplement(binary2)
        println(bin2)
        var ans = binadd(bin1, bin2, false)
        if(ans.get(0)=='1')
            ans = "-" + twoscomplement(ans)
        return ans
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
}