package com.example.ingemar.photoalbumdemo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import com.squareup.picasso.Picasso

import kotlinx.android.synthetic.main.activity_photo_view_activity.*

class PhotoViewActivity : AppCompatActivity() {

    lateinit var photo : Photo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_view_activity)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        photo = Loader.photos.find{ it.id == intent.getIntExtra("id", 0)}!!
        displayImage()

        //swipe left/right listener for next photo
        findViewById<ImageView>(R.id.photo_view).setOnTouchListener(object : OnSwipeTouchListener(applicationContext){
            override fun onSwipeRight(){
                //previous
                val n = photo.album.photos.find{it.id == photo.id - 1}
                if (n != null){
                    photo = n
                    displayImage()
                }
            }
            override fun onSwipeLeft(){
                //next
                val n = photo.album.photos.find{it.id == photo.id + 1}
                if (n != null){
                    photo = n
                    displayImage()
                }
            }
        })
    }

    private fun displayImage(){
        var pic: ImageView = findViewById<ImageView>(R.id.photo_view)
        Picasso.get().load(photo.url).into(pic)

        title = photo.title
    }
}
