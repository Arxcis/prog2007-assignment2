package no.ntnu.prog2007.assignment2

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class GroceryListAdapter(private val groceryList: ArrayList<GroceryListItem>) :
    RecyclerView.Adapter<GroceryListAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val row: LinearLayout = view.findViewById(R.id.GroceryRow)
        val text: TextView = view.findViewById(R.id.GroceryRowText)
        val radio: RadioButton = view.findViewById(R.id.GroceryRowRadio)

    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.grocery_list_item, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        val item = groceryList.elementAt(position)
        holder.text.text = item.description
        holder.radio.isSelected = item.selected
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = groceryList.size

    // NotifyChanged functions
   private fun toggle(selected: Boolean, position: Int) {
        Log.d("toggle", "selected $selected position $position")
       groceryList[position].selected = !selected
       notifyItemChanged(position)
   }

    fun add(item: GroceryListItem) {
        groceryList.add(item); notifyDataSetChanged()
    }

    fun popFirst() {
        when (groceryList.size) {
            in 1..Int.MAX_VALUE -> {
                groceryList.removeAt(0); notifyDataSetChanged()
            }
        }
    }

    fun clear() {
        groceryList.clear(); notifyDataSetChanged()
    }
}
