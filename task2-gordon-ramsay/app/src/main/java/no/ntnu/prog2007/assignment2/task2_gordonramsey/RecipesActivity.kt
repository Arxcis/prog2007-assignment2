package no.ntnu.prog2007.assignment2.task2_gordonramsey

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_recipes.*
import java.util.concurrent.Executors

class RecipesActivity : AppCompatActivity() {
    private var recipes = arrayListOf<Recipe>(Recipe(
        title = "CARAMELIZED ONION EVERYTHING DIP",
        description = "Combining everyone’s favorite condiments into one seriously savory umami-bomb takes minimal effort, but yields maximum reward. This creamy, crunchy, tangy dip will have your guests begging for it’s return at the next tailgate or picnic ",
        imageURL = "https://www.gordonramsay.com/assets/Uploads/_resampled/CroppedFocusedImage192072050-50-EverythingDip.png"))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipes)

        val adapter =  RecipeListAdapter(recipes)
        RecipeList.adapter = adapter
        RecipeList.layoutManager = LinearLayoutManager(this)
    }
}


class RecipeListAdapter(private val recipeList: ArrayList<Recipe>) :
    RecyclerView.Adapter<RecipeListAdapter.ViewHolder>() {

    private val executor = Executors.newSingleThreadExecutor()
    private val handler = Handler(Looper.getMainLooper())

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView? = view.findViewById(R.id.RecipeTitle)
        val description: TextView? = view.findViewById(R.id.RecipeDescription)
        val image: ImageView = view.findViewById(R.id.RecipeImage)
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.recipe_list_item, viewGroup, false)


        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val recipe = recipeList[position]
        holder.title?.text = recipe.title
        holder.description?.text = recipe.description

        loadImage(executor, handler, holder.image, recipe.imageURL)
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = recipeList.size
}

