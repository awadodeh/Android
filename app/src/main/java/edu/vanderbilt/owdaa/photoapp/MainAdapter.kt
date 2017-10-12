package edu.vanderbilt.owdaa.photoapp

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import edu.vanderbilt.owdaa.photoapp.model.Photo

/**
 * Created by owdaa on 10/12/17.
 */
class MainAdapter (var photos: List<Photo>,
                   var clickListener:View.OnClickListener) :
        RecyclerView.Adapter<MainAdapter.PhotoHolder>() {

    fun getPhoto(adapterPosition: Int) : Photo{
        return photos[adapterPosition]
    }


    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): PhotoHolder {
        return PhotoHolder(LayoutInflater.from(parent?.context)
                .inflate(R.layout.photo_item, parent, false))
    }

    override fun onBindViewHolder(holder: PhotoHolder?, position: Int) {
        val photo = photos[position]
        holder?.tags?.text = photo.tags
        holder?.likes?.text = photo.likes.toString()
        holder?.favorites?.text = photo.favorites.toString()

        if(photo.previewURL.isNotEmpty()){
            //glide to load the image
            Glide.with(holder?.tags?.context)
                    .load(photo.previewURL)
                    .into(holder?.photo_item)
        }
    }

    override fun getItemCount(): Int {
       return photos.size
    }

    inner class PhotoHolder(itemView : View) : RecyclerView.ViewHolder
    (itemView){
        var tags : TextView
        var likes : TextView
        var favorites : TextView
        var photo_item : ImageView
        init {
            if ( clickListener !=null){
                itemView.setOnClickListener { clickListener }
            }
            itemView.tag = this

            tags = itemView.findViewById<TextView>(R.id.tags)
            likes = itemView.findViewById<TextView>(R.id.likes)
            favorites = itemView.findViewById<TextView>(R.id.favorites)
            photo_item = itemView.findViewById<ImageView>(R.id.photo_item)
        }
    }


}