package com.example.doskamura.adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.doskamura.R
import com.example.doskamura.databinding.PostItemBinding
import com.example.doskamura.dataclasses.Post
import com.squareup.picasso.Picasso



class PostAdapter : ListAdapter<Post, PostAdapter.Holder>(PostComparator()){

    interface OnClickListener {
        fun onClick(position: Int, post : Post)
    }
    private var onClickListener: OnClickListener? = null

    class Holder(view : View) : RecyclerView.ViewHolder(view) {
        private val binding = PostItemBinding.bind(view)

        fun bind(post : Post)=with(binding){
            postName.text = post.title
            postPrice.text= post.price
            postTime.text = post.publicDate
            if (post.picsUrl.isNotEmpty()) { Picasso.get().load(post.picsUrl[0].url).into(postImage) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.post_item, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener {
            if (onClickListener != null) {
                onClickListener!!.onClick(position, getItem(position))
            }
        }
    }
    fun setOnClickListener(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener
    }

    class PostComparator : DiffUtil.ItemCallback<Post>(){
        override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem.id==newItem.id
        }

        override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem == newItem
        }
    }
}