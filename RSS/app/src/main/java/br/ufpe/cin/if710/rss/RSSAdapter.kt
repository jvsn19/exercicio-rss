package br.ufpe.cin.if710.rss

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class RSSAdapter(var rssList: ArrayList<ItemRSS>):
        RecyclerView.Adapter<RSSAdapter.RSSHolder>() {

    var items = rssList

    // Creating the Holder for my RSS Item
    class RSSHolder(view: View) :
            RecyclerView.ViewHolder(view) {
        var titleView: TextView = view.findViewById(R.id.item_titulo)
        var dateView: TextView = view.findViewById(R.id.item_data)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RSSHolder {
        val inflatedView = parent.inflate(R.layout.itemlista, false)
        return RSSHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: RSSHolder, position: Int) {
        val rssItem = this.items[position]
        holder.titleView.text = rssItem.title
        holder.dateView.text = rssItem.pubDate
    }

    override fun getItemCount() = this.items.size
}