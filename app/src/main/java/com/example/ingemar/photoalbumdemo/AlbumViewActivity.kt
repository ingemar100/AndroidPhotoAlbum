package com.example.ingemar.photoalbumdemo

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.CoordinatorLayout
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.*
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_album_view.*


class AlbumViewActivity : AppCompatActivity() {

    lateinit var album : Album
    lateinit var photos : List<Photo>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album_view)
        setSupportActionBar(album_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        album = Loader.albums.find{ it.id == intent.getIntExtra("id", 0)}!!
        photos = Loader.photos!!.filter { it.albumId == album.id }
        createView()
    }
//
//    override fun onResume(){
//        super.onResume()
//        createView()
//    }

    private fun createView(){
        title = album.title

        var layout = findViewById<GridLayout>(R.id.photo_grid)

        val displayMetrics = applicationContext.getResources().getDisplayMetrics()
        val screenWidthDp = displayMetrics.widthPixels / displayMetrics.density
        val columnWidthDp = 150 / displayMetrics.density
        layout.columnCount = (screenWidthDp / columnWidthDp).toInt()

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
