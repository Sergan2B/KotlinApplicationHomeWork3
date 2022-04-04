package kg.geektech.kotlinapplicationhomework3.adapter

import android.annotation.SuppressLint
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import kg.geektech.kotlinapplicationhomework3.R
import kg.geektech.kotlinapplicationhomework3.databinding.ItemGalleryBinding

class ImageAdapter(private val listener: Listener) :
    RecyclerView.Adapter<ImageAdapter.ImageHolder>() {

    private val imageList = arrayListOf<Uri>()

    class ImageHolder(item: View) : RecyclerView.ViewHolder(item) {
        private val binding = ItemGalleryBinding.bind(item)

        fun bind(mainImage: Uri, listener: Listener) = with(binding) {
            imageRecycler.setImageURI(mainImage)
            imageRecyclerBorder.visibility = INVISIBLE
            imageRecyclerBorder2.visibility = INVISIBLE
            imageRecyclerBorder3.visibility = INVISIBLE
            itemView.setOnClickListener {
                if (!imageRecyclerBorder3.isVisible) {
                    listener.onClick(mainImage)
                    imageRecyclerBorder.visibility = VISIBLE
                    imageRecyclerBorder2.visibility = VISIBLE
                    imageRecyclerBorder3.visibility = VISIBLE
                    imageRecyclerBorder.alpha - 0.5f
                    imageRecyclerBorder.animate().alpha(0.5f).duration = 200
                } else {
                    listener.deleteClick(mainImage)
                    imageRecyclerBorder.visibility = INVISIBLE
                    imageRecyclerBorder2.visibility = INVISIBLE
                    imageRecyclerBorder3.visibility = INVISIBLE
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_gallery, parent, false)
        return ImageHolder(view)
    }

    override fun onBindViewHolder(holder: ImageHolder, position: Int) {
        holder.bind(imageList[position], listener)
    }

    override fun getItemCount() = imageList.size

    @SuppressLint("NotifyDataSetChanged")
    fun addImage(mainImage: Uri) {
        this.imageList.addAll(listOf(mainImage))
        notifyDataSetChanged()
    }

    interface Listener {
        fun onClick(mainImage: Uri)
        fun deleteClick(mainImage: Uri)
    }
}