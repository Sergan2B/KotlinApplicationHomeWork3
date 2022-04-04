package kg.geektech.kotlinapplicationhomework3.adapter

import android.annotation.SuppressLint
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kg.geektech.kotlinapplicationhomework3.R
import kg.geektech.kotlinapplicationhomework3.databinding.ItemSelectedGalleryBinding

class SelectedImageAdapter(private val listener: Listener) :
    RecyclerView.Adapter<SelectedImageAdapter.ImageHolder>() {

    private val imageList = arrayListOf<Uri>()

    class ImageHolder(item: View) : RecyclerView.ViewHolder(item) {
        private val binding = ItemSelectedGalleryBinding.bind(item)
        fun bind(mainImage: Uri, listener: Listener) = with(binding) {
            imageSelectedRecycler.setImageURI(mainImage)
            itemView.setOnClickListener {
                listener.deleteClick(mainImage)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_selected_gallery, parent, false)
        return ImageHolder(view)
    }

    override fun onBindViewHolder(holder: ImageHolder, position: Int) {
        holder.bind(imageList[position], listener)
    }

    override fun getItemCount() = imageList.size

    @SuppressLint("NotifyDataSetChanged")
    fun addImage(mainImage: ArrayList<Uri>) {
        this.imageList.addAll(mainImage)
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun deleteImage(mainImage: Uri) {
        this.imageList.remove(mainImage)
        notifyDataSetChanged()
    }

    interface Listener {
        fun onClick(mainImage: Uri)
        fun deleteClick(mainImage: Uri)
    }
}