package no.ntnu.prog2007.assignment2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var groceryList = ArrayList<String>();

        // 1. Setup adapter
        val groceryListView = findViewById<RecyclerView>(R.id.GroceryList)
        groceryListView.adapter = GroceryListAdapter(groceryList);
        groceryListView.layoutManager = LinearLayoutManager(this)

        // 2. Setup 3 buttons onclick-listeners
    }
}