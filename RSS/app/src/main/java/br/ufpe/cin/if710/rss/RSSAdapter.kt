package br.ufpe.cin.if710.rss

import android.content.Intent
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class RSSAdapter(var rssArrayList: ArrayList<ItemRSS>):
        RecyclerView.Adapter<RSSAdapter.RSSHolder>() {

    // Creating the Holder for my RSS Item
    class RSSHolder(view: View) :
            RecyclerView.ViewHolder(view),
            View.OnClickListener {

        var titleView: TextView = view.findViewById(R.id.item_titulo)
        var dateView: TextView = view.findViewById(R.id.item_data)
        var link: String? = null

        init {
            view.setOnClickListener(this)
        }

        override fun onClick(view: View?) {
            val url = Uri.parse(this.link)
            val intent = Intent(Intent.ACTION_VIEW, url)
            view?.context?.startActivity(intent)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RSSHolder {
        val inflatedView = parent.inflate(R.layout.itemlista, false)
        return RSSHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: RSSHolder, position: Int) {
        val rssItem = this.rssArrayList[position]
        holder.titleView.text = rssItem.title
        holder.dateView.text = rssItem.pubDate
        holder.link = rssItem.link
    }

    override fun getItemCount() = this.rssArrayList.size
}