package com.example.ingemar.photoalbumdemo

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_album_list.*
import android.util.Log
import android.widget.TextView
import retrofit2.Call
import retrofit2.Response


class AlbumListActivity : AppCompatActivity() {

    //json call for albums
    //convert json to objects
    //add list of albums with thumbnails
    private fun load(){
        val tag = "Network:"
        NetworkService.getInstance()
                .jsonInterface
                .getAlbums()
                .enqueue(object : retrofit2.Callback<List<Album>> {
                    override fun onResponse(call: Call<List<Album>>, response: Response<List<Album>>) {
                        val albums = response.body()!!
                        val view = findViewById<TextView>(R.id.albums_textview)
                        view.setText("")
                        for (a in albums){
                            view.append(a.id.toString() + ": " + a.title + " (user: " + a.userId + "\n")
                        }
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

        load()
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
