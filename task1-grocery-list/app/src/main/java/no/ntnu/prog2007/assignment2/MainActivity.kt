package no.ntnu.prog2007.assignment2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var groceryList = arrayListOf(
        GroceryListItem("Orange 🍊"),
        GroceryListItem("Milk 🥛"),
        GroceryListItem("Bread 🍞"))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1. Setup adapter
        val adapter =  GroceryListAdapter(groceryList)
        GroceryList.adapter = adapter
        GroceryList.layoutManager = LinearLayoutManager(this)


        // 2. Add click listener
        Add.setOnClickListener {
            when {
                AddAnItem.text.isEmpty() -> {println("Error adding item to list: Text is empty")}
                else -> {
                    adapter.add(GroceryListItem(AddAnItem.text.toString(), false))
                    AddAnItem.text.clear()
                }
            }
        }

        // 3. Delete click listener
        Delete.setOnClickListener {
            adapter.deleteSelected()
        }

        // 4. Clear click listener
        Clear.setOnClickListener {
            adapter.clear()
        }
    }

}