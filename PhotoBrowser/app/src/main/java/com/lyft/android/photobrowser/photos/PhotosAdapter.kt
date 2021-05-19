package com.lyft.android.photobrowser.photos

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lyft.android.photobrowser.R
import com.lyft.android.photobrowser.views.ImageLoader

class PhotosAdapter internal constructor(
    private val imageLoader: ImageLoader,
    context: Context,
    private val data: List<Photo>
) : RecyclerView.Adapter<PhotosAdapter.ViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.photo_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val photo = data[position]
        imageLoader.load(photo.largeImageURL, holder.imageView)
        holder.usernameTextView.text = photo.user
        holder.likesTextView.text = photo.likes.toString()
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class ViewHolder internal constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        var imageView: ImageView = itemView.findViewById(R.id.image_view)
        var usernameTextView: TextView = itemView.findViewById(R.id.username_text_view)
        var likesTextView: TextView = itemView.findViewById(R.id.likes_text_view)
    }
}
