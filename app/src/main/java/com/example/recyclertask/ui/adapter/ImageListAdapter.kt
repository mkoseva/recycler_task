package com.example.recyclertask.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import com.example.recyclertask.AppExecutors
import com.example.recyclertask.vo.ImageInfo
import  com.example.recyclertask.R
import com.example.recyclertask.databinding.ImageItemBinding

/**
 * A RecyclerView adapter for [ImageInfo] class.
 */
class ImageListAdapter(private val dataBindingComponent: DataBindingComponent,
                       appExecutors: AppExecutors,
                       private val callback: ((ImageInfo, ImageView) -> Unit)?
) : DataBoundListAdapter<ImageInfo, ImageItemBinding>(
    appExecutors = appExecutors,
    diffCallback = object : DiffUtil.ItemCallback<ImageInfo>() {
    override fun areItemsTheSame(oldItem: ImageInfo, newItem: ImageInfo): Boolean {
        return oldItem.author == newItem.author
                && oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: ImageInfo, newItem: ImageInfo): Boolean {
        return oldItem.name == newItem.name
                && oldItem.post_url == newItem.post_url
    }
}
) {
    override fun submitList(categoryName: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun createBinding(parent: ViewGroup): ImageItemBinding {
        val binding = DataBindingUtil.inflate<ImageItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.image_item,
            parent,
            false,
            dataBindingComponent
        )

        binding.root.setOnClickListener {
            binding.imageInfo?.let {
                callback?.invoke(it, binding.imageView)
            }
        }
        return binding
    }

    override fun bind(binding: ImageItemBinding, item: ImageInfo) {
        binding.imageInfo = item
    }
}
