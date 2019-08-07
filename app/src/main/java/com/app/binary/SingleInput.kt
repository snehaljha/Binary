package com.app.binary

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.single_input.*

class SingleInput : AppCompatActivity() {


    fun eliminatespaces (s: String): String {
        var str = s
        while(str.indexOf(' ')>=0) {
            str = str.replace(" ", "")
        }
        return str
    }


    fun todecimal(bin: String): Double {
        var binary = bin
        var ans: Double = 0.0
        var dot = binary.indexOf('.')
        var bef = 0
        var aft = 0
        var s: String
        if(dot == 0) {
            binary = "0" + binary
            dot++
        }
        else if(dot == binary.length-1) {
            dot = -1
            binary = binary.substring(0, binary.length-1)
        }
        if(dot < 0){
            bef = binary.toInt()
        }
        else if(dot > 0) {
            s = binary.substring(0, dot)
            bef = s.toInt()
            s = binary.substring(dot+1)
            s = s.reversed()
            aft = s.toInt()
        }
        var i: Double = 0.0

        while(bef%10!=0 || bef/10!=0) {
            var digit = bef%10
            bef /= 10
            ans += digit*Math.pow(2.0, i)
            i += 1.0
        }

        i = -1.0

        while(aft%10!=0 || aft/10!=0) {
            var digit = aft%10
            aft /= 10
            ans += digit*Math.pow(2.0, i)
            i -= 1.0
        }

        return ans

    }

    fun change(gpno: Int, binary: String): String {
        var bin = binary
        var dot = bin.indexOf('.')
        var bef: Int
        var aft: String
        if(dot == 0) {
            bin = "0" + bin
            dot++
        }
        else if(dot == bin.length-1) {
            dot = -1
            bin = bin.substring(0, bin.length-1)
        }

        if (dot > 0) {
            var bef1 = bin.substring(0, dot)
            bef = bef1.toInt()
            aft = bin.substring(dot + 1)
        } else {
            bef = bin.toInt()
            aft = "-1"
        }
        var ans = ""
        var ans1 = ""
        var check = true
        if (bef == 0) {
            ans = "0"
            check = false
        }
        var fgpno = gpno.toDouble()
        while (bef % ((Math.pow(10.0, fgpno)).toInt()) != 0 || bef / ((Math.pow(10.0, fgpno)).toInt()) != 0) {
            var gp: Int = bef % ((Math.pow(10.0, fgpno)).toInt())
            var part_ans: Int = 0
            for (i in 0..gpno - 1) {
                var digit: Int = gp % 10
                gp = gp / 10
                part_ans += (digit * Math.pow(2.0, i.toDouble())).toInt()
            }
            bef = bef / ((Math.pow(10.0, fgpno)).toInt())
            ans1 += hexalpha(part_ans)
        }

        if (check) {
            ans += ans1.reversed()
        }

        if (aft == "-1")
            return ans
        ans += "."

        /*while(aft != "") {
            var gp = aft.substring(0, gpno)
            var part_ans: Int = 0
            var j = fgpno-1
            for(i in 0..gpno-1) {
                var digit = gp.get(i).toInt()
                part_ans += (digit*Math.pow(2.0,j)).toInt()
                j --
            }
            ans += hexalpha(part_ans)
            aft = aft.substring(gpno)
        }*/

        var ztba: Int = aft.length % gpno
        if (ztba != 0) {
            ztba = gpno - ztba
            for (i in 1..ztba) {
                aft += "0"
            }
        }

        var after: Int = aft.toInt()
        ans1 = ""

        while (after % ((Math.pow(10.0, fgpno)).toInt()) != 0 || after / ((Math.pow(10.0, fgpno)).toInt()) != 0) {
            var gp: Int = after % ((Math.pow(10.0, fgpno)).toInt())
            var part_ans: Int = 0
            for (i in 0..gpno - 1) {
                var digit: Int = gp % 10
                gp = gp / 10
                part_ans += (digit * Math.pow(2.0, i.toDouble())).toInt()
            }
            after = after / ((Math.pow(10.0, fgpno)).toInt())
            ans1 += hexalpha(part_ans)
        }

        ans += ans1.reversed()

        return ans

    }

    fun hexalpha(n: Int): String {
        when (n) {
            10 -> return "A"
            11 -> return "B"
            12 -> return "C"
            13 -> return "D"
            14 -> return "E"
            15 -> return "F"
            else -> {
                return n.toString()
            }
        }
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
        setContentView(R.layout.single_input)
        var bundle: Bundle? = intent.extras
        var title = bundle!!.getString("label")
        singlelabel.text = title
        singlebtn.setOnClickListener {
            var q = singletf.text.toString()
            q = eliminatespaces(q)
            if (title == "Binary to Octal") {
                if (validbin(q)) {
                    singleans.text = change(3, q)
                    Toast.makeText(this, "Successful", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Invalid Binary Code", Toast.LENGTH_LONG).show()
                }
            }
            else if(title == "Binary to Decimal") {
                if(validbin(q)) {
                    singleans.text = todecimal(q).toString()
                    Toast.makeText(this, "Successful", Toast.LENGTH_SHORT).show()
                }
                else {
                    Toast.makeText(this, "Invalid Binary Code", Toast.LENGTH_LONG).show()
                }
            }
            else if(title == "Binary to Hexadecimal") {
                if(validbin(q)) {
                    singleans.text = change(4, q)
                    Toast.makeText(this, "Successful", Toast.LENGTH_SHORT).show()
                }
                else {
                    Toast.makeText(this, "Invalid Binary Code", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}