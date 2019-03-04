package com.example.ingemar.photoalbumdemo

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.AdapterView
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_album_list.*
import kotlinx.android.synthetic.main.activity_album_view.*

class AlbumViewActivity : AppCompatActivity() {

    lateinit var album : Album
    lateinit var photos : List<Photo>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album_view)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        album = Loader.albums.find{ it.id == intent.getIntExtra("id", 0)}!!
        photos = Loader.photos!!.filter { it.albumId == album.id }
        createView()
    }

    private fun createView(){
        //fix titlebar with album name, add user id
        album_toolbar.title = album.title

        var layout = findViewById<GridLayout>(R.id.photo_grid)
        layout.columnCount = 7
        for (photo in photos){
            var thumb = ImageView(applicationContext)
            Picasso.get().load(photo.thumbnailUrl).into(thumb)

            thumb.setOnClickListener{
                val n = Intent(applicationContext, PhotoViewActivity::class.java)
                n.putExtra("id", photo.id)
                startActivity(n)
            }
            layout.addView(thumb)
        }
    }
}
