package no.ntnu.prog2007.assignment2.task2_gordonramsey

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

import java.net.URL

import android.os.Handler
import android.os.Looper
import android.widget.ImageView

import java.lang.Exception
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        // https://www.geeksforgeeks.org/how-to-load-any-image-from-url-without-using-any-dependency-in-android/
        val executor = Executors.newSingleThreadExecutor()
        val handler = Handler(Looper.getMainLooper())

        loadImage(executor, handler, ProfilePicture, "https://www.gordonramsay.com/assets/Uploads/_resampled/CroppedFocusedImage158088946-29-GTRF-media-image-141.JPG")
    }
}

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