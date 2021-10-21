package no.ntnu.prog2007.assignment2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextUtils.isEmpty
import android.util.Log
import android.util.Log.INFO
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var groceryList = arrayListOf("hei", "på", "deg")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1. Setup adapter
        val adapter =  GroceryListAdapter(groceryList)
        GroceryList.adapter = adapter
        GroceryList.layoutManager = LinearLayoutManager(this)


        // 2. Add listener
        Add.setOnClickListener {
            when {
                AddAnItem.text.isEmpty() -> {println("Error adding item to list: Text is empty")};// @TODO You have to type something in this textbox first
                else -> {
                    Log.println(Log.INFO, "MainActivity", "Adding item to list: ${AddAnItem.text}")
                    groceryList.add(AddAnItem.text.toString())
                    adapter.notifyDataSetChanged()
                    AddAnItem.text.clear()
                }
            }
        }

        // 3. Delete listener
        Delete.setOnClickListener {
            when (groceryList.size) {
                0 -> { } // @TODO There are no items left in your grocery list
                else -> {
                    Log.println(Log.INFO, "MainActivity", "Removing the last item in the list")
                    groceryList.removeAt(groceryList.size-1)
                    adapter.notifyDataSetChanged();
                }
            }
        }
    }

}