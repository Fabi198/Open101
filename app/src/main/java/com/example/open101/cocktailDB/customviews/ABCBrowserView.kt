package com.example.open101.cocktailDB.customviews

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.example.open101.R
import com.example.open101.cocktailDB.fragments.CocktailsLetterListedResults

class ABCBrowserView @JvmOverloads constructor(context: Context, private val attrs: AttributeSet?= null, private var defStyleAttr: Int = 0): LinearLayout(context, attrs, defStyleAttr) {

    lateinit var a: TextView
    lateinit var b: TextView
    lateinit var c: TextView
    lateinit var d: TextView
    lateinit var e: TextView
    lateinit var f: TextView
    lateinit var g: TextView
    lateinit var h: TextView
    lateinit var i: TextView
    lateinit var j: TextView
    lateinit var k: TextView
    lateinit var l: TextView
    lateinit var m: TextView
    lateinit var n: TextView
    lateinit var o: TextView
    lateinit var p: TextView
    lateinit var q: TextView
    lateinit var r: TextView
    lateinit var s: TextView
    lateinit var t: TextView
    lateinit var u: TextView
    lateinit var v: TextView
    lateinit var w: TextView
    lateinit var x: TextView
    lateinit var y: TextView
    lateinit var z: TextView






    init {
        View.inflate(context, R.layout.customview_abcbrowser, this)
        initializeAttrs()
        setupUI()
        orientation = HORIZONTAL
    }

    private fun setupUI() {
        a = findViewById(R.id.btnA)
        b = findViewById(R.id.btnB)
        c = findViewById(R.id.btnC)
        d = findViewById(R.id.btnD)
        e = findViewById(R.id.btnE)
        f = findViewById(R.id.btnF)
        g = findViewById(R.id.btnG)
        h = findViewById(R.id.btnH)
        i = findViewById(R.id.btnI)
        j = findViewById(R.id.btnJ)
        k = findViewById(R.id.btnK)
        l = findViewById(R.id.btnL)
        m = findViewById(R.id.btnM)
        n = findViewById(R.id.btnN)
        o = findViewById(R.id.btnO)
        p = findViewById(R.id.btnP)
        q = findViewById(R.id.btnQ)
        r = findViewById(R.id.btnR)
        s = findViewById(R.id.btnS)
        t = findViewById(R.id.btnT)
        u = findViewById(R.id.btnU)
        v = findViewById(R.id.btnV)
        w = findViewById(R.id.btnW)
        x = findViewById(R.id.btnX)
        y = findViewById(R.id.btnY)
        z = findViewById(R.id.btnZ)




    }

    private fun initializeAttrs() {
        attrs?.let {
            val typedArray =
                context.theme.obtainStyledAttributes(
                    it,
                    R.styleable.ABCBrowserView,
                    defStyleAttr,
                    0
                )
        }
    }

    fun throwFragment(letter: String): CocktailsLetterListedResults {
        val clr = CocktailsLetterListedResults()
        val bundle = Bundle()
        bundle.putString("letter", letter)
        clr.arguments = bundle
        return clr
    }



}