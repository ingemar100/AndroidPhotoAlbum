package com.example.ingemar.photoalbumdemo

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_album_list.*
import kotlinx.android.synthetic.main.activity_album_view.*

class AlbumViewActivity : AppCompatActivity() {

    var albumId = 0
    lateinit var photos : List<Photo>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album_view)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        albumId = intent.getIntExtra("id", 0)
        photos = Loader.photos!!.filter { it.albumId == albumId }
        createView()
    }

    private fun createView(){
        //titlebar with album name

        var layout = findViewById<GridLayout>(R.id.photo_grid)
        layout.columnCount = 7
        for (photo in photos){
            //imageview
            val width = 150 // change to screen width / 4
            var thumb = ImageView(applicationContext)
//            thumb.maxWidth = width
//            thumb.maxHeight = width

            Picasso.get().load(photo.thumbnailUrl).into(thumb)

            layout.addView(thumb)
        }
    }
}
