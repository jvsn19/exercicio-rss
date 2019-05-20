package br.ufpe.cin.if710.rss

import android.app.Activity
import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.ViewManager
import android.widget.Adapter
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.*
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL
import java.nio.charset.Charset

class MainActivity : Activity() {
    val feedList :ArrayList<ItemRSS> = ArrayList(0)

    private val RSS_FEED: String = "http://leopoldomt.com/if1001/g1brasil.xml"
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var recyclerView: RecyclerView
    private lateinit var rssAdapter: RSSAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.conteudoRSS)
        linearLayoutManager = LinearLayoutManager(this)
        conteudoRSS.layoutManager = linearLayoutManager
        rssAdapter = RSSAdapter(feedList)
        recyclerView.adapter = rssAdapter
    }

    override fun onStart() {
        super.onStart()
        doAsync {
            val xmlString = getRssFeed(RSS_FEED)
            val feedList = ParserRSS.parse(xmlString) as ArrayList<ItemRSS>
            rssAdapter.rssArrayList = feedList
            uiThread {
                rssAdapter.notifyDataSetChanged()
            }
        }
    }

    @Throws(IOException::class)
    private fun getRssFeed(feed: String): String {
        var inputStream: InputStream? = null
        var rssFeed = ""

        try {
            val url = URL(feed)
            val conn = url.openConnection()
            inputStream = conn.inputStream
            val out = ByteArrayOutputStream()
            val buffer = ByteArray(1024)

            var count = inputStream.read(buffer)
            while (count != -1) {
                out.write(buffer, 0, count)
                count = inputStream.read(buffer)
            }
            var response = out.toByteArray()
            rssFeed = String(response, Charsets.UTF_8)

        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            inputStream?.close()
        }

        return rssFeed
    }
}
