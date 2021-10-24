package no.ntnu.prog2007.assignment2.task2_gordonramsey

import android.graphics.BitmapFactory
import android.os.Handler
import android.widget.ImageView
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