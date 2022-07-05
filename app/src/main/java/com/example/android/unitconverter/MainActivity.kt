package com.example.android.unitconverter

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.example.android.unitconverter.databinding.ActivityMainBinding
import java.text.DecimalFormat
import android.app.AlertDialog.Builder
import android.content.DialogInterface
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var selectedUnitLayout: LinearLayout
    private lateinit var selectedUnitText: TextView
    private lateinit var editInput: EditText
    private lateinit var textResult: TextView
    private lateinit var resultTypeText: TextView
    private lateinit var selectedUnit: String

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        selectedUnitText = findViewById(R.id.textType)
        editInput = findViewById(R.id.input)
        textResult = findViewById(R.id.textResult)
        resultTypeText = findViewById(R.id.textResultType)
        selectedUnitLayout = findViewById(R.id.selectType)
        selectedUnit = "Fahrenheit"
        selectedUnitLayout.setOnClickListener(){
            showAlertDialog()
        }
        editInput.addTextChangedListener {
            var resultText: String = ""
            var inputVal = editInput.text.toString()
            val df = DecimalFormat("#.##")
            if(inputVal.isNotEmpty()){
                val doubleInput = inputVal.toDouble()
                if(selectedUnit == "Fahrenheit"){
                    resultText = df.format((doubleInput *9/5)+32)
                    selectedUnitText.text = "Celsius"
                }else{
                    resultText = df.format((doubleInput *9/5)+32)
                    selectedUnitText.text = "Fahrenheit"
                }
                textResult.text = resultText
            }
        }
    }

    private fun showAlertDialog() {
        val alertDialog: AlertDialog.Builder = AlertDialog.Builder(this@MainActivity)
        alertDialog.setTitle("Select Unit")
        val items = arrayOf("Fahrenheit", "Celsius")
        val checkedItem = -1
        alertDialog.setSingleChoiceItems(items, checkedItem,
            DialogInterface.OnClickListener { dialog, which ->
                selectedUnit = items[which]
                selectedUnitText.setText(selectedUnit)
            })
        alertDialog.setPositiveButton(
            android.R.string.ok,
            DialogInterface.OnClickListener { dialog, which ->
                dialog.dismiss()
            })
        val alert: AlertDialog = alertDialog.create()
        alertDialog.show()
    }
}