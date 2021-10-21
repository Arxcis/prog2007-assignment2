package no.ntnu.prog2007.assignment2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class GroceryListAdapter(private val groceryList: ArrayList<GroceryListItem>) :
    RecyclerView.Adapter<GroceryListAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val text: TextView =  view.findViewById(R.id.GroceryListItemText)

    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.grocery_list_item, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.text.text = groceryList.elementAt(position).description
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = groceryList.size

    fun add(item: GroceryListItem) {
        groceryList.add(item); notifyDataSetChanged()
    }

    fun popFirst() {
        when (groceryList.size) {
            in 1..Int.MAX_VALUE -> { groceryList.removeAt(1); notifyDataSetChanged()}
        }
    }

    fun clear() {
        groceryList.clear()
    }
}
