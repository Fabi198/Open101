package com.example.open101.superhero


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.open101.R
import com.example.open101.databinding.ItemSuperheroBinding
import com.example.open101.superhero.entities.SuperheroDto
import com.squareup.picasso.Picasso

class SuperheroAdapter(private var listSuperhero: List<SuperheroDto>) : RecyclerView.Adapter<SuperheroAdapter.SuperheroViewHolder>() {


    class SuperheroViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemSuperheroBinding.bind(itemView)

        fun bind(item: SuperheroDto) {
            var i = 0
            binding.DtoID.text = item.id
            binding.DtoName.text = item.name
            binding.DtoIntelligence.text = item.powerstats.intelligence
            binding.DtoStrenght.text = item.powerstats.strength
            binding.DtoSpeed.text = item.powerstats.speed
            binding.DtoDurability.text = item.powerstats.durability
            binding.DtoPower.text = item.powerstats.power
            binding.DtoCombat.text = item.powerstats.combat
            binding.DtoFullname.text = item.biography.fullname
            binding.DtoAlteregos.text = item.biography.alteregos
            binding.DtoAliases.text = item.biography.aliases.toString()
            binding.DtoPlaceofbirth.text = item.biography.placeofbirth
            binding.DtoFirstappearance.text = item.biography.firstappearance
            binding.DtoPublisher.text = item.biography.publisher
            binding.DtoAlignment.text = item.biography.alignment
            binding.DtoGender.text = item.appearance.gender
            binding.DtoRace.text = item.appearance.race
            binding.DtoHeight.text = item.appearance.height.toString()
            binding.DtoWeight.text = item.appearance.weight.toString()
            binding.DtoEyecolor.text = item.appearance.eyecolor
            binding.DtoHaircolor.text = item.appearance.haircolor
            binding.DtoOccupation.text = item.work.occupation
            binding.DtoBase.text = item.work.base
            binding.DtoGroupAffiliation.text = item.connections.groupaffiliation
            binding.DtoRelatives.text = item.connections.relatives
            Picasso.get().load(item.image.url).into(binding.imageView)
            Picasso.get().load(item.image.url).into(binding.imageViewBig)
            binding.imageView.setOnClickListener {
                i++
                if (i%2 == 0) {
                    binding.imageViewBig.visibility = View.GONE
                } else {
                    binding.imageViewBig.visibility = View.VISIBLE
                }
            }
            binding.clSuperhero.setOnClickListener {
                if (binding.imageViewBig.visibility == View.VISIBLE) {
                    i++
                    binding.imageViewBig.visibility = View.GONE
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperheroViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return SuperheroViewHolder(layoutInflater.inflate(R.layout.item_superhero, parent, false))
    }

    override fun getItemCount(): Int {
        return listSuperhero.size
    }

    override fun onBindViewHolder(holder: SuperheroViewHolder, position: Int) {
        val item = listSuperhero[position]
        holder.bind(item)
    }
}