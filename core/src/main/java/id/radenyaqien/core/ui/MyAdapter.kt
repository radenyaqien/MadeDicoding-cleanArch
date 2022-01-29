package id.radenyaqien.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import id.radenyaqien.core.R
import id.radenyaqien.core.domain.PexelsImage

class MyAdapter : PagingDataAdapter<PexelsImage, MyAdapter.ListViewHolder>(DIFF_CALLBACK) {

    inner class ListViewHolder(binding: View) :
        RecyclerView.ViewHolder(binding) {

        private val textv: TextView = binding.findViewById(R.id.txt_test)
        private val imageView: ImageView = binding.findViewById(R.id.image)
        fun bind(film: PexelsImage) {
            textv.text = film.url
            imageView.load(film.src) {
                crossfade(true)
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<PexelsImage>() {
            override fun areItemsTheSame(oldItem: PexelsImage, newItem: PexelsImage): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: PexelsImage, newItem: PexelsImage): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return ListViewHolder(view)
    }
}