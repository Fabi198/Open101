package com.example.open101.simpsons


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.open101.R
import com.example.open101.databinding.ItemSimpsonEpisodesBinding
import com.example.open101.translator.TranslateService
import com.example.open101.simpsons.entities.EpisodesDTO
import com.squareup.picasso.Picasso

class SimpsonsAdapter(private val listEpisodes: List<EpisodesDTO>): RecyclerView.Adapter<SimpsonsAdapter.SimpsonsViewHolder>() {

    class SimpsonsViewHolder(v: View): RecyclerView.ViewHolder(v) {
        private val binding = ItemSimpsonEpisodesBinding.bind(v)
        private val translator = TranslateService


        fun bind(item: EpisodesDTO) {
            Picasso.get().load(item.thumbnailUrl).into(binding.thumbnailRs)
            binding.seasonRs.text = item.season.toString()
            binding.episodeRs.text = item.episode.toString()
            binding.ratingRs.text = item.rating.toString()
            binding.airDateRs.text = item.airDate
            binding.nameRs.text = item.name
            translator.englishSpanishTranslator.translate(item.description).addOnSuccessListener {
                binding.descRs.text = it
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpsonsViewHolder {
        return SimpsonsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_simpson_episodes, parent, false))
    }

    override fun getItemCount(): Int {
        return listEpisodes.size
    }

    override fun onBindViewHolder(holder: SimpsonsViewHolder, position: Int) {
        val item = listEpisodes[position]
        holder.bind(item)
    }

}