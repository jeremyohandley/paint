package com.ohandley.jeremy.paintapplication


import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProviders


class MainActivity : AppCompatActivity() {

    private var viewModel: MainViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        val clearButton = toolbar.findViewById<ImageButton>(R.id.clear_button)
        val colorPickButton = toolbar.findViewById<ImageButton>(R.id.color_pick_button)

        clearButton.setOnClickListener {
            clearCanvas()
        }
        colorPickButton.setOnClickListener {
            showBottomSheet()
        }

        initColors()

        val canvas = findViewById<DrawingCanvasView>(R.id.canvas)
        val newPaint = Paint()
        viewModel!!.color.observe(this, androidx.lifecycle.Observer {
            newPaint.color = it
            canvas.setPaintColor(newPaint.color)
        })

    }

    private fun clearCanvas() {
        val canvas = findViewById<DrawingCanvasView>(R.id.canvas)
        canvas.clearCanvas()
    }

    private fun showBottomSheet() {
        val bottomSheetFragment = BottomSheetFragment()
        bottomSheetFragment.viewModel = viewModel!!
        bottomSheetFragment.show(supportFragmentManager, bottomSheetFragment.tag)
    }

    private fun initColors(){
        val red = ContextCompat.getColor(this, R.color.red)
        val darkRed = ContextCompat.getColor(this, R.color.darkRed)
        val orange = ContextCompat.getColor(this, R.color.orange)
        val darkOrange = ContextCompat.getColor(this, R.color.darkOrange)
        val green = ContextCompat.getColor(this, R.color.green)
        val darkGreen = ContextCompat.getColor(this, R.color.darkGreen)
        val blue = ContextCompat.getColor(this, R.color.blue)
        val darkBlue = ContextCompat.getColor(this, R.color.darkBlue)
        val gold = ContextCompat.getColor(this, R.color.gold)
        val yellow = ContextCompat.getColor(this, R.color.yellow)
        val purple = ContextCompat.getColor(this, R.color.purple)
        val pink = ContextCompat.getColor(this, R.color.pink)
        val gray = ContextCompat.getColor(this, R.color.gray)
        val silver = ContextCompat.getColor(this, R.color.silver)
        val black = ContextCompat.getColor(this, R.color.black)
        val colors = arrayListOf(
            red,
            darkRed,
            orange,
            darkOrange,
            green,
            darkGreen,
            blue,
            darkBlue,
            gold,
            yellow,
            purple,
            pink,
            gray,
            silver,
            black
        )
        viewModel!!.colors = colors
    }
}
