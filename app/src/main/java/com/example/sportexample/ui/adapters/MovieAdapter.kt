package com.example.sportexample.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sportexample.data.model.Team
import com.example.sportexample.databinding.MovieItemBinding
import com.example.sportexample.ui.ext.load
import java.util.*


class MovieAdapter(private val movieList: List<Team>, private val listener: (Team) -> Unit) :
    RecyclerView.Adapter<MovieAdapter.MovieHolder>() {

    private var items: MutableList<Team> =ArrayList()

    init{
        items= ArrayList()
        items.addAll(movieList)
}
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val binding =
            MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        holder.render(items[position], listener)
    }

    override fun getItemCount(): Int = items.size


//    fun filter(strSearch: String) {
//        Log.d("tag",movieList.size.toString())
//        if (strSearch.isEmpty()) {
//            items.clear()
//            items.addAll(movieList)
//        } else {
//            items.clear()
//            for (item in movieList) {
//                    if (item.title.lowercase(Locale.getDefault()).contains(strSearch)) {
//                        items.add(item)
//                    }
//                }
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//                items.clear()
//                val collect: List<ItemList> = originalItems.stream()
//                    .filter { i -> i.getTitulo().toLowerCase().contains(strSearch) }
//                    .collect(Collectors.toList())
//                items.addAll(collect)
//            } else {
//                items.clear()
//                for (i in originalItems) {
//                    if (i.getTitulo().toLowerCase().contains(strSearch)) {
//                        items.add(i)
//                    }
//                }
//            }
//        }
//        notifyDataSetChanged()
//    }


    class MovieHolder(private val binding: MovieItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun render(movie: Team, listener: (Team) -> Unit) {
            binding.textView.text = movie.strTeam
            binding.textView2.text = movie.strStadium
            binding.imageView.load(movie.strTeamBadge!!)
            binding.root.setOnClickListener { listener(movie) }

        }
    }
}