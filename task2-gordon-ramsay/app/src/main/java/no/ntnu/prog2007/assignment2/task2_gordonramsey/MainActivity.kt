package no.ntnu.prog2007.assignment2.task2_gordonramsey

import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

import java.net.URL

import android.os.Handler
import android.os.Looper
import android.widget.ImageView

import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


class MainActivity : AppCompatActivity() {
    private val executor = Executors.newSingleThreadExecutor()
    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // https://www.geeksforgeeks.org/how-to-load-any-image-from-url-without-using-any-dependency-in-android/
        loadImage(executor, handler, ProfilePicture, "https://www.gordonramsay.com/assets/Uploads/_resampled/CroppedFocusedImage158088946-29-GTRF-media-image-141.JPG")

        AllRecipes.setOnClickListener {
            val intent = Intent(this, RecipesActivity::class.java)
            startActivity(intent)
        }
    }
}

