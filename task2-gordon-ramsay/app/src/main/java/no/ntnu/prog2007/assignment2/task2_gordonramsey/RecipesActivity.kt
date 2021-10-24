package no.ntnu.prog2007.assignment2.task2_gordonramsey

import android.content.Intent

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
    private val executor = Executors.newSingleThreadExecutor()
    private val handler = Handler(Looper.getMainLooper())

    private var recipes: ArrayList<Recipe> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipes)

        // Init adapter with click-listener
        val adapter = RecipeListAdapter(recipes) { recipe ->
            val intent = Intent(this, RecipeActivity::class.java).apply {
                putExtra("recipe", recipe)
            }
            startActivity(intent)
        }

        RecipeList.adapter = adapter
        RecipeList.layoutManager = LinearLayoutManager(this)

        loadRecipes(executor, handler, recipes, adapter, url = "https://www.gordonramsay.com/gr/recipes/")

        RecipesBack.setOnClickListener { finish() }
    }
}



class RecipeListAdapter(
    private val recipeList: ArrayList<Recipe>,
    private val onClick: (recipe: Recipe) -> Unit
) : RecyclerView.Adapter<RecipeListAdapter.ViewHolder>() {

    private val executor = Executors.newSingleThreadExecutor()
    private val handler = Handler(Looper.getMainLooper())

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val row: View? = view.findViewById(R.id.RecipeRow)
        val title: TextView? = view.findViewById(R.id.RecipeRowTitle)
        val description: TextView? = view.findViewById(R.id.RecipeRowDescription)
        val image: ImageView = view.findViewById(R.id.RecipeRowImage)
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

        holder.row?.setOnClickListener { onClick(recipe) }
        loadImage(executor, handler, holder.image, recipe.imageURL)
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = recipeList.size
}

