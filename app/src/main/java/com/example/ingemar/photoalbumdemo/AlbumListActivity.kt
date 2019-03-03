package com.example.ingemar.photoalbumdemo

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_album_list.*
import android.util.Log
import android.widget.*
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Response
import android.widget.ArrayAdapter




class AlbumListActivity : AppCompatActivity() {

    //json call for albums
    //convert json to objects
    //add list of albums with thumbnails

    lateinit var photos: List<Photo>

    //load json photo objects, draw layout when finished
    private fun loadData(){
        val tag = "Data"
        NetworkService.instance()
                .jsonInterface
                .getPhotos()
                .enqueue(object : retrofit2.Callback<List<Photo>> {
                    override fun onResponse(call: Call<List<Photo>>, response: Response<List<Photo>>) {
                        photos = response.body()!!
                        init()
                    }

                    override fun onFailure(call: Call<List<Photo>>, t: Throwable) {
                        Log.e(tag, "Error occurred while getting request!")
                        t.printStackTrace()
                    }
                })
    }
    private fun init(){
        val tag = "Init"

        NetworkService.instance().jsonInterface.getAlbums()
                .enqueue(object : retrofit2.Callback<List<Album>> {
                    override fun onResponse(call: Call<List<Album>>, response: Response<List<Album>>) {
                        val albums = response.body()!!
                        val list = findViewById<ListView>(R.id.albums_list)
                        var listItems = ArrayList<String>()

                        for (a in albums){
                            listItems.add(a.id.toString() + ": " + a.title + " (user " + a.userId + ")")
                        }

                        list.adapter = ArrayAdapter<String>(applicationContext,
                                android.R.layout.simple_list_item_1,
                                listItems)
                    }

                    override fun onFailure(call: Call<List<Album>>, t: Throwable) {
                        Log.e(tag,"Error occurred while getting request!")
                        t.printStackTrace()
                    }
                })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album_list)
        setSupportActionBar(toolbar)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
        loadData()

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_album_list, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
