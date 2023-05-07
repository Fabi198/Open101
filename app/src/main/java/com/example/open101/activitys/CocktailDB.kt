package com.example.open101.activitys

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.open101.cocktailDB.RetrofitCocktail
import com.example.open101.cocktailDB.adapters.PopularCocktailsAdapter
import com.example.open101.cocktailDB.entities.DrinkDTO
import com.example.open101.cocktailDB.fragments.CocktailFullView
import com.example.open101.databinding.ActivityCocktailDbBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CocktailDB : AppCompatActivity() {

    private lateinit var binding: ActivityCocktailDbBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCocktailDbBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setPopularDrinks()
        searchByLetterClicked()




    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 1) {
            supportFragmentManager.popBackStack()
        } else if (supportFragmentManager.backStackEntryCount == 1) {
            supportFragmentManager.popBackStack()
            allVisible()
        } else {
            finish()
        }
    }

    private fun allGone() {
        binding.ABCBrowserView.visibility = View.GONE
        binding.rvPopularDrinks.visibility = View.GONE
        binding.tvPopularDrinks.visibility = View.GONE
        binding.tvBrowseByLetter.visibility = View.GONE
    }

    private fun allVisible() {
        binding.ABCBrowserView.visibility = View.VISIBLE
        binding.rvPopularDrinks.visibility = View.VISIBLE
        binding.tvPopularDrinks.visibility = View.VISIBLE
        binding.tvBrowseByLetter.visibility = View.VISIBLE
    }

    private fun setPopularDrinks() {
        binding.rvPopularDrinks.layoutManager = GridLayoutManager(this, 4)
        lifecycleScope.launch {
            val list = ArrayList<DrinkDTO>()
            for (i in 0..7) {
                val responseService = RetrofitCocktail.APICOCKTAILS.getPopularDrinks("lookup.php?i=1100$i")
                list.add(responseService.body()!!.Drinks[0])
            }
            withContext(Dispatchers.Main) {
                val adapter = PopularCocktailsAdapter(list) {
                    val bundle = Bundle()
                    bundle.putString("id", it)
                    val popuFrag = CocktailFullView()
                    popuFrag.arguments = bundle
                    supportFragmentManager.beginTransaction().add(
                        binding.cocktailsContainer.id,
                        popuFrag,
                        "PopularDrinksOnItemClickedFragment"
                    ).addToBackStack(null).commit()
                    binding.cocktailsContainer.visibility = View.VISIBLE
                    allGone()
                }
                binding.rvPopularDrinks.adapter = adapter
                binding.rvPopularDrinks.visibility = View.VISIBLE
            }
        }
    }


    private fun searchByLetterClicked() {
        binding.ABCBrowserView.a.setOnClickListener {
            supportFragmentManager.beginTransaction().add(binding.cocktailsContainer.id,
                binding.ABCBrowserView.throwFragment(binding.ABCBrowserView.a.text.toString().lowercase()),
                "CocktailListedResults").addToBackStack(null).commit()
            binding.cocktailsContainer.visibility = View.VISIBLE
            allGone()}
        binding.ABCBrowserView.b.setOnClickListener {
            supportFragmentManager.beginTransaction().add(binding.cocktailsContainer.id,
                binding.ABCBrowserView.throwFragment(binding.ABCBrowserView.b.text.toString().lowercase()),
                "CocktailListedResults").addToBackStack(null).commit()
            binding.cocktailsContainer.visibility = View.VISIBLE
            allGone()}
        binding.ABCBrowserView.c.setOnClickListener {
            supportFragmentManager.beginTransaction().add(binding.cocktailsContainer.id,
                binding.ABCBrowserView.throwFragment(binding.ABCBrowserView.c.text.toString().lowercase()),
                "CocktailListedResults").addToBackStack(null).commit()
            binding.cocktailsContainer.visibility = View.VISIBLE
            allGone()}
        binding.ABCBrowserView.d.setOnClickListener {
            supportFragmentManager.beginTransaction().add(binding.cocktailsContainer.id,
                binding.ABCBrowserView.throwFragment(binding.ABCBrowserView.d.text.toString().lowercase()),
                "CocktailListedResults").addToBackStack(null).commit()
            binding.cocktailsContainer.visibility = View.VISIBLE
            allGone()}
        binding.ABCBrowserView.e.setOnClickListener {
            supportFragmentManager.beginTransaction().add(binding.cocktailsContainer.id,
                binding.ABCBrowserView.throwFragment(binding.ABCBrowserView.e.text.toString().lowercase()),
                "CocktailListedResults").addToBackStack(null).commit()
            binding.cocktailsContainer.visibility = View.VISIBLE
            allGone()}
        binding.ABCBrowserView.f.setOnClickListener {
            supportFragmentManager.beginTransaction().add(binding.cocktailsContainer.id,
                binding.ABCBrowserView.throwFragment(binding.ABCBrowserView.f.text.toString().lowercase()),
                "CocktailListedResults").addToBackStack(null).commit()
            binding.cocktailsContainer.visibility = View.VISIBLE
            allGone()}
        binding.ABCBrowserView.g.setOnClickListener {
            supportFragmentManager.beginTransaction().add(binding.cocktailsContainer.id,
                binding.ABCBrowserView.throwFragment(binding.ABCBrowserView.g.text.toString().lowercase()),
                "CocktailListedResults").addToBackStack(null).commit()
            binding.cocktailsContainer.visibility = View.VISIBLE
            allGone()}
        binding.ABCBrowserView.h.setOnClickListener {
            supportFragmentManager.beginTransaction().add(binding.cocktailsContainer.id,
                binding.ABCBrowserView.throwFragment(binding.ABCBrowserView.h.text.toString().lowercase()),
                "CocktailListedResults").addToBackStack(null).commit()
            binding.cocktailsContainer.visibility = View.VISIBLE
            allGone()}
        binding.ABCBrowserView.i.setOnClickListener {
            supportFragmentManager.beginTransaction().add(binding.cocktailsContainer.id,
                binding.ABCBrowserView.throwFragment(binding.ABCBrowserView.i.text.toString().lowercase()),
                "CocktailListedResults").addToBackStack(null).commit()
            binding.cocktailsContainer.visibility = View.VISIBLE
            allGone()}
        binding.ABCBrowserView.j.setOnClickListener {
            supportFragmentManager.beginTransaction().add(binding.cocktailsContainer.id,
                binding.ABCBrowserView.throwFragment(binding.ABCBrowserView.j.text.toString().lowercase()),
                "CocktailListedResults").addToBackStack(null).commit()
            binding.cocktailsContainer.visibility = View.VISIBLE
            allGone()}
        binding.ABCBrowserView.k.setOnClickListener {
            supportFragmentManager.beginTransaction().add(binding.cocktailsContainer.id,
                binding.ABCBrowserView.throwFragment(binding.ABCBrowserView.k.text.toString().lowercase()),
                "CocktailListedResults").addToBackStack(null).commit()
            binding.cocktailsContainer.visibility = View.VISIBLE
            allGone()}
        binding.ABCBrowserView.l.setOnClickListener {
            supportFragmentManager.beginTransaction().add(binding.cocktailsContainer.id,
                binding.ABCBrowserView.throwFragment(binding.ABCBrowserView.l.text.toString().lowercase()),
                "CocktailListedResults").addToBackStack(null).commit()
            binding.cocktailsContainer.visibility = View.VISIBLE
            allGone()}
        binding.ABCBrowserView.m.setOnClickListener {
            supportFragmentManager.beginTransaction().add(binding.cocktailsContainer.id,
                binding.ABCBrowserView.throwFragment(binding.ABCBrowserView.m.text.toString().lowercase()),
                "CocktailListedResults").addToBackStack(null).commit()
            binding.cocktailsContainer.visibility = View.VISIBLE
            allGone()}
        binding.ABCBrowserView.n.setOnClickListener {
            supportFragmentManager.beginTransaction().add(binding.cocktailsContainer.id,
                binding.ABCBrowserView.throwFragment(binding.ABCBrowserView.n.text.toString().lowercase()),
                "CocktailListedResults").addToBackStack(null).commit()
            binding.cocktailsContainer.visibility = View.VISIBLE
            allGone()}
        binding.ABCBrowserView.o.setOnClickListener {
            supportFragmentManager.beginTransaction().add(binding.cocktailsContainer.id,
                binding.ABCBrowserView.throwFragment(binding.ABCBrowserView.o.text.toString().lowercase()),
                "CocktailListedResults").addToBackStack(null).commit()
            binding.cocktailsContainer.visibility = View.VISIBLE
            allGone()}
        binding.ABCBrowserView.p.setOnClickListener {
            supportFragmentManager.beginTransaction().add(binding.cocktailsContainer.id,
                binding.ABCBrowserView.throwFragment(binding.ABCBrowserView.p.text.toString().lowercase()),
                "CocktailListedResults").addToBackStack(null).commit()
            binding.cocktailsContainer.visibility = View.VISIBLE
            allGone()}
        binding.ABCBrowserView.q.setOnClickListener {
            supportFragmentManager.beginTransaction().add(binding.cocktailsContainer.id,
                binding.ABCBrowserView.throwFragment(binding.ABCBrowserView.q.text.toString().lowercase()),
                "CocktailListedResults").addToBackStack(null).commit()
            binding.cocktailsContainer.visibility = View.VISIBLE
            allGone()}
        binding.ABCBrowserView.r.setOnClickListener {
            supportFragmentManager.beginTransaction().add(binding.cocktailsContainer.id,
                binding.ABCBrowserView.throwFragment(binding.ABCBrowserView.r.text.toString().lowercase()),
                "CocktailListedResults").addToBackStack(null).commit()
            binding.cocktailsContainer.visibility = View.VISIBLE
            allGone()}
        binding.ABCBrowserView.s.setOnClickListener {
            supportFragmentManager.beginTransaction().add(binding.cocktailsContainer.id,
                binding.ABCBrowserView.throwFragment(binding.ABCBrowserView.s.text.toString().lowercase()),
                "CocktailListedResults").addToBackStack(null).commit()
            binding.cocktailsContainer.visibility = View.VISIBLE
            allGone()}
        binding.ABCBrowserView.t.setOnClickListener {
            supportFragmentManager.beginTransaction().add(binding.cocktailsContainer.id,
                binding.ABCBrowserView.throwFragment(binding.ABCBrowserView.t.text.toString().lowercase()),
                "CocktailListedResults").addToBackStack(null).commit()
            binding.cocktailsContainer.visibility = View.VISIBLE
            allGone()}
        binding.ABCBrowserView.u.setOnClickListener {
            supportFragmentManager.beginTransaction().add(binding.cocktailsContainer.id,
                binding.ABCBrowserView.throwFragment(binding.ABCBrowserView.u.text.toString().lowercase()),
                "CocktailListedResults").addToBackStack(null).commit()
            binding.cocktailsContainer.visibility = View.VISIBLE
            allGone()}
        binding.ABCBrowserView.v.setOnClickListener {
            supportFragmentManager.beginTransaction().add(binding.cocktailsContainer.id,
                binding.ABCBrowserView.throwFragment(binding.ABCBrowserView.v.text.toString().lowercase()),
                "CocktailListedResults").addToBackStack(null).commit()
            binding.cocktailsContainer.visibility = View.VISIBLE
            allGone()}
        binding.ABCBrowserView.w.setOnClickListener {
            supportFragmentManager.beginTransaction().add(binding.cocktailsContainer.id,
                binding.ABCBrowserView.throwFragment(binding.ABCBrowserView.w.text.toString().lowercase()),
                "CocktailListedResults").addToBackStack(null).commit()
            binding.cocktailsContainer.visibility = View.VISIBLE
            allGone()}
        binding.ABCBrowserView.x.setOnClickListener {
            supportFragmentManager.beginTransaction().add(binding.cocktailsContainer.id,
                binding.ABCBrowserView.throwFragment(binding.ABCBrowserView.x.text.toString().lowercase()),
                "CocktailListedResults").addToBackStack(null).commit()
            binding.cocktailsContainer.visibility = View.VISIBLE
            allGone()}
        binding.ABCBrowserView.y.setOnClickListener {
            supportFragmentManager.beginTransaction().add(binding.cocktailsContainer.id,
                binding.ABCBrowserView.throwFragment(binding.ABCBrowserView.y.text.toString().lowercase()),
                "CocktailListedResults").addToBackStack(null).commit()
            binding.cocktailsContainer.visibility = View.VISIBLE
            allGone()}
        binding.ABCBrowserView.z.setOnClickListener {
            supportFragmentManager.beginTransaction().add(binding.cocktailsContainer.id,
                binding.ABCBrowserView.throwFragment(binding.ABCBrowserView.z.text.toString().lowercase()),
                "CocktailListedResults").addToBackStack(null).commit()
            binding.cocktailsContainer.visibility = View.VISIBLE
            allGone()}

    }


}