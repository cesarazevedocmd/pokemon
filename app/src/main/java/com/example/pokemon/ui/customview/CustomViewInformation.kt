package com.example.pokemon.ui.customview

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.pokemon.R

const val zero = 0
const val empty = ""

class CustomViewInformation @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = zero
) : ConstraintLayout(context, attrs, defStyle) {

    private lateinit var title: String
    private lateinit var description: String

    private lateinit var titleView: AppCompatTextView
    private lateinit var descriptionView: AppCompatTextView

    init {
        inflate(context, R.layout.custom_view_information, this)
        val typeArray = context.theme.obtainStyledAttributes(attrs, R.styleable.CustomViewInformation, zero, zero)
        loadAttrs(typeArray)
        initViews()
        loadViews()
    }

    private fun loadViews() {
        setTitle(title)
        setDescription(description)
    }

    fun setDescription(value: String) {
        description = value
        descriptionView.text = description
    }

    fun setTitle(value: String) {
        title = value
        titleView.text = title
    }

    private fun loadAttrs(typeArray: TypedArray) {
        title = typeArray.getString(R.styleable.CustomViewInformation_cvi_title) ?: empty
        description = typeArray.getString(R.styleable.CustomViewInformation_cvi_description) ?: empty
    }

    private fun initViews() {
        titleView = findViewById(R.id.custom_view_information_title)
        descriptionView = findViewById(R.id.custom_view_information_description)
    }
}