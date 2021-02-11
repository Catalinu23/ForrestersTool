package com.example.forresterstool

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.text.Html
import android.view.*
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

const val CYLINDER = false
const val CONE_TRUNK = true
const val MIL = 1000000

var totalVolume = 0.toFloat()

fun getString(x: Float): String {

    var xstring = (x/MIL).toString().take(8)
    return "%.6f".format(x/ MIL)
    //return xstring
}

private fun showDialog(context: Context) {
    val dialogView = LayoutInflater.from(context).inflate(R.layout.fragment_custom_dialog, null)
    val view = View.inflate(context, R.layout.content_list, null)
    val radiusNumberInput = dialogView.findViewById<EditText>(R.id.radiusNumberInput)
    val radiusNumberInput2 = dialogView.findViewById<EditText>(R.id.radiusNumberInput2)
    val lengthNumberInput = dialogView.findViewById<EditText>(R.id.lengthNumberInput)
    val okButton = dialogView.findViewById<Button>(R.id.okButton)
    val spinner = dialogView.findViewById<Spinner>(R.id.spinner)
    val sumTextView = view.findViewById<TextView>(R.id.sumTextView)

    val builder = AlertDialog.Builder(context).setView(dialogView).show()

    val array = arrayOf("CYLINDER", "CONE TRUNK")
    spinner.adapter = ArrayAdapter(context, R.layout.support_simple_spinner_dropdown_item, array)

    okButton.setOnClickListener {
        var type = if (spinner.selectedItem.toString() == "CYLINDER") CYLINDER else CONE_TRUNK
        var radius1 = radiusNumberInput.text.toString().toFloat()
        var radius2: Float
        var length = lengthNumberInput.text.toString().toFloat()
        if (type == CONE_TRUNK)
            radius2 = radiusNumberInput2.text.toString().toFloat()
        else
            radius2 = radius1
        val branch = Branch(type, radius1, radius2, length)
        branches.add(branch)
        totalVolume += branch.getVolume()
        builder.dismiss()
    }

}

class ListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        setSupportActionBar(findViewById(R.id.toolbar))

        val branchesRecyclerView = findViewById<RecyclerView>(R.id.branchesRecyclerView)
        val addButton = findViewById<FloatingActionButton>(R.id.addButton)
        val sumTextView = findViewById<TextView>(R.id.sumTextView)

        //val three = R.string.test_string
        //sumTextView.text = getString(totalVolume) + " m3"

        addButton.setOnClickListener {
            showDialog(this)
        }

        branchesRecyclerView.layoutManager = LinearLayoutManager(this)
        branchesRecyclerView.adapter = RecyclerAdapter(this, branches)

        sumTextView.text = "0 m3"
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        getMenuInflater().inflate(R.menu.menu_list, menu);
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //val view = LayoutInflater.from(this).inflate(R.layout.content_list, null)
        val sumTextView = findViewById<TextView>(R.id.sumTextView)
        return when (item.itemId) {
            R.id.actionUpdate -> {
                sumTextView.text = getString(totalVolume) + " m3"
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}