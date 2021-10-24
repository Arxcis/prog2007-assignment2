package no.ntnu.prog2007.assignment2.task2_gordonramsey

import android.graphics.BitmapFactory
import android.os.Handler
import android.widget.ImageView
import org.jsoup.Jsoup
import java.net.URL
import java.util.concurrent.ExecutorService

// https://www.geeksforgeeks.org/how-to-load-any-image-from-url-without-using-any-dependency-in-android/
fun loadImage(executor: ExecutorService, handler: Handler, imageView: ImageView, url: String) {
    executor.execute {
        val url =
            URL(url);

        val stream = url.openStream()
        val image = BitmapFactory.decodeStream(stream)

        handler.post {
            imageView.setImageBitmap(image)
        }
    }
}

// https://thetechstack.net/using-jsoup-with-kotlin-to-scrape-wiki-pages/
fun loadRecipes(executor: ExecutorService, handler: Handler, recipes: ArrayList<Recipe>, adapter: RecipeListAdapter, url: String) {
    executor.execute {
        val doc = Jsoup.connect(url).get()

       val newRecipes = doc.select(".recipe").map { recipe ->
           val title = recipe.select(".summary h2").firstOrNull()?.text() ?: "<undefined title>"
           val description = recipe.select(".summary p").firstOrNull()?.text() ?: "<undefined description>"
           val imageURL = recipe.select(".image")
                .firstOrNull()
               ?.attr("style")
               ?.replace("background-image:url(", "https://www.gordonramsay.com/")
               ?.replace(");", "") ?: "https://www.gordonramsay.com/assets/Uploads/_resampled/CroppedFocusedImage192072050-50-EverythingDip.png"

            val categories = ArrayList<String>(recipe.select(".categories a").map { a ->
                a.text()
            })

           Recipe(
               title = title,
               description = description,
               imageURL = imageURL,
               categories = categories)
       }

        handler.post {
            recipes.addAll(newRecipes)
            adapter.notifyDataSetChanged()
        }
    }
}