package com.example.ingemar.photoalbumdemo

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.ListView
import kotlinx.android.synthetic.main.activity_album_list.*


class AlbumListActivity : AppCompatActivity() {

    private fun init(){
        val list = findViewById<ListView>(R.id.albums_list)

        list.adapter = ArrayAdapter<Album>(applicationContext,
                android.R.layout.simple_list_item_1,
                Loader.albums)

        list.onItemClickListener = object : OnItemClickListener {
            override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                val a: Album = list.getItemAtPosition(position) as Album

                val n = Intent(applicationContext, AlbumViewActivity::class.java)
                n.putExtra("id", a.id)
                startActivity(n)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album_list)
        setSupportActionBar(toolbar)
        title = "Photo albums"

        Thread{
            //Loading dialog
            Loader.load()
            runOnUiThread{
                init()
            }
        }.start()
    }
}
