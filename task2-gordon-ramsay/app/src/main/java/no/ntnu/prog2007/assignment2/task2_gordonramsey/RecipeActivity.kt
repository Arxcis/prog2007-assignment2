package no.ntnu.prog2007.assignment2.task2_gordonramsey

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import kotlinx.android.synthetic.main.activity_recipe.*
import java.util.concurrent.Executors

class RecipeActivity : AppCompatActivity() {
    private val executor = Executors.newSingleThreadExecutor()
    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)
        setContentView(R.layout.activity_recipe)

        val recipe = intent.extras?.getSerializable("recipe") as Recipe?

        RecipeTitle.text = recipe?.title ?: "<title: undefined>"
        RecipeDescription.text = recipe?.description ?: "<description: undefined>"

        recipe?.imageURL?.run {
            loadImage(executor, handler, RecipeImage, url = recipe.imageURL)
        }

        Back.setOnClickListener { finish() }
    }
}