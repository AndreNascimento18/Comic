package com.example.comics.ui.comicList.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.comics.R
import com.example.comics.databinding.ItemListBinding
import com.example.domain.dto.ComicsDataDTO

class ListComicAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val items = mutableListOf<ComicsDataDTO.Result>()

    fun updateItems(items: List<ComicsDataDTO.Result>) {
        this.items.apply {
            clear()
            addAll(items)
        }
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ItemViewHolder).bind(result = items[position])
    }

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        getItemHolder(parent = parent)

    private fun getItemHolder(parent: ViewGroup) = ItemViewHolder(
        itemBinding = ItemListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    private class ItemViewHolder(val itemBinding: ItemListBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(result: ComicsDataDTO.Result) = with(itemBinding) {
            val image = "${result.thumbnail.path}.${result.thumbnail.extension}"
            val title = result.title
            val subtitle = result.description.ifEmpty {
                itemBinding.root.context.getString(R.string.image_not_description)
            }

            Glide.with(itemBinding.root)
                .load(image)
                .centerCrop()
                .into(itemBinding.actionImage)

            actionTitle.text = title
            actionSubTitle.text = subtitle
        }
    }

}
