package com.example.comics.ui.views

import android.content.Context
import android.util.AttributeSet
import android.widget.Button
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import com.example.comics.R

class LoadingView : RelativeLayout {

    private val progressBar: ProgressBar by lazy {
        findViewById(R.id.progressBar)
    }
    private val title: TextView by lazy {
        findViewById(R.id.tvTitle)
    }
    private val description: TextView by lazy {
        findViewById(R.id.tvDescription)
    }
    private val button: Button by lazy {
        findViewById(R.id.button)
    }

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int): super(context, attrs, defStyleAttr) {
        inflate(context, R.layout.view_loading, this)
    }

    fun setButtonClickListener(listener: () -> Unit) {
        button.setOnClickListener {
            listener.invoke()
        }
    }

    fun showLoading() {
        progressBar.visibility = VISIBLE
        title.visibility = GONE
        description.visibility = GONE
        button.visibility = GONE
    }

    fun showError(title: String, description: String) {
        progressBar.visibility = GONE
        this.title.apply {
            visibility = VISIBLE
            text = title
        }
        this.description.apply {
            visibility = VISIBLE
            text = description
        }
        button.visibility = VISIBLE
    }
}