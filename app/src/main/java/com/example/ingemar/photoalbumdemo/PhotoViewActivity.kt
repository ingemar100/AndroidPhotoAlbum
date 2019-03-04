package com.example.ingemar.photoalbumdemo

import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity.CENTER
import android.widget.GridLayout
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
    }

    private fun displayImage(){
        var pic: ImageView = findViewById<ImageView>(R.id.photo_view)
        Picasso.get().load(photo.url).into(pic)
    }
}
