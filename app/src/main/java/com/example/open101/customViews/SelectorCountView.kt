package com.example.open101.customViews

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import com.example.open101.R

class SelectorCountView @JvmOverloads constructor(context: Context, private val attrs: AttributeSet?= null, private var defStyleAttr: Int = 0): LinearLayout(context, attrs, defStyleAttr){

    private lateinit var imgAgregar: ImageButton
    private lateinit var imgRemover: ImageButton
    private lateinit var txtNumber: TextView
    var test = ""
    private var cantMax = CantTops.CANTIDAD_MAXIMA
    private var cantMin = CantTops.CANTIDAD_MINIMA


    init {
        View.inflate(context, R.layout.customview_selectorcount, this)
        initializeAttrs()
        setupUI()
        setupClickListeners()
        orientation = HORIZONTAL
    }

    private fun initializeAttrs() {
        attrs?.let {
            val typedArray =
                context.theme.obtainStyledAttributes(
                    it,
                    R.styleable.SelectorCountView,
                    defStyleAttr,
                    0
                )
            cantMax = typedArray.getInt(R.styleable.SelectorCountView_cantidadMaxima,
                CantTops.CANTIDAD_MAXIMA
            )
            cantMin = typedArray.getInt(R.styleable.SelectorCountView_cantidadMinima,
                CantTops.CANTIDAD_MINIMA
            )
        }
    }

    private fun setupClickListeners() {
        imgAgregar.setOnClickListener{plus()}
        imgRemover.setOnClickListener {minus()}
    }

    @SuppressLint("SetTextI18n")
    private fun plus() {
        txtNumber.text = (txtNumber.text.toString().toInt()+1).toString()
        test = (txtNumber.text.toString().toInt()).toString()
        checkQuantity()
    }

    @SuppressLint("SetTextI18n")
    private fun minus() {
        txtNumber.text = (txtNumber.text.toString().toInt()-1).toString()
        test = (txtNumber.text.toString().toInt()).toString()
        checkQuantity()
    }

    private fun checkQuantity(){
        when(txtNumber.text.toString().toInt()) {
            cantMin -> imgRemover.isEnabled = false
            cantMax -> imgAgregar.isEnabled = false
            else -> {
                imgRemover.isEnabled = true
                imgAgregar.isEnabled = true
            }
        }
    }

    private fun setupUI() {
        imgAgregar = findViewById(R.id.imgAgregar)
        imgRemover = findViewById(R.id.imgRemover)
        imgRemover.isEnabled = false
        txtNumber = findViewById(R.id.txtNumber)
    }

    fun clearText() {
        txtNumber.text = "0"
    }


}