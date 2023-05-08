package com.example.open101.activitys

//noinspection SuspiciousImport
import android.R
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.open101.cocktailDB.RetrofitCocktail
import com.example.open101.cocktailDB.adapters.PopularCocktailsAdapter
import com.example.open101.cocktailDB.entities.DrinkDTO
import com.example.open101.cocktailDB.fragments.*
import com.example.open101.databinding.ActivityCocktailDbBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class CocktailDB : AppCompatActivity() {

    private lateinit var binding: ActivityCocktailDbBinding
    private var flagSpinnerGlass = false
    private var flagSpinnerCategory = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCocktailDbBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setPopularDrinks()
        setupAllCocktailsArray()
        setupAllIngredientsArray()
        setupSpinnerCategory()
        setupSpinnerGlass()
        setupFiltersAlcoholic()
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

    private fun setupFiltersAlcoholic() {
        binding.cvAlcoholicYes.setOnClickListener {
            val bundle = Bundle()
            val alcoholic = "Alcoholic"
            bundle.putString("Alcoholic", alcoholic)
            val listedAlcoholic = CocktailsAlcoholicListedResults()
            listedAlcoholic.arguments = bundle
            supportFragmentManager.beginTransaction().add(binding.cocktailsContainer.id, listedAlcoholic, "ListedAlcoholic").addToBackStack(null).commit()
            allGone()
            binding.cocktailsContainer.visibility = View.VISIBLE
        }
        binding.cvAlcoholicOp.setOnClickListener {
            val bundle = Bundle()
            val alcoholic = "Optional_Alcohol"
            bundle.putString("Alcoholic", alcoholic)
            val listedAlcoholic = CocktailsAlcoholicListedResults()
            listedAlcoholic.arguments = bundle
            supportFragmentManager.beginTransaction().add(binding.cocktailsContainer.id, listedAlcoholic, "ListedAlcoholic").addToBackStack(null).commit()
            allGone()
            binding.cocktailsContainer.visibility = View.VISIBLE
        }
        binding.cvAlcoholicNo.setOnClickListener {
            val bundle = Bundle()
            val alcoholic = "Non_Alcoholic"
            bundle.putString("Alcoholic", alcoholic)
            val listedAlcoholic = CocktailsAlcoholicListedResults()
            listedAlcoholic.arguments = bundle
            supportFragmentManager.beginTransaction().add(binding.cocktailsContainer.id, listedAlcoholic, "ListedAlcoholic").addToBackStack(null).commit()
            allGone()
            binding.cocktailsContainer.visibility = View.VISIBLE
        }
    }

    private fun setupSpinnerGlass() {
        lifecycleScope.launch(Dispatchers.IO) {
            val responseService = RetrofitCocktail.APICOCKTAILS.getGlassesList("list.php?g=list")
            val categories = responseService.body()
            withContext(Dispatchers.Main) {
                val l = mutableListOf<String>()
                val listCategories = categories?.drinksDTO
                listCategories?.forEach {
                    l.add(it.glassDTO)
                }
                val adapter = ArrayAdapter(applicationContext,
                    com.example.open101.R.layout.spinner_text,
                    l)
                adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
                binding.spinnerGlass.adapter = adapter
                binding.spinnerGlass.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onNothingSelected(parent: AdapterView<*>?) {
                        Toast.makeText(applicationContext, "NothingSelected", Toast.LENGTH_SHORT).show()
                    }

                    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                        if (!flagSpinnerGlass) {
                           flagSpinnerGlass = true
                        } else {
                            val bundle = Bundle()
                            val glass = binding.spinnerGlass.selectedItem.toString()
                            bundle.putString("Glass", glass)
                            val listedGlasses = CocktailsGlassListedResults()
                            listedGlasses.arguments = bundle
                            supportFragmentManager.beginTransaction().add(binding.cocktailsContainer.id, listedGlasses, "ListedGlasses").addToBackStack(null).commit()
                            allGone()
                            binding.cocktailsContainer.visibility = View.VISIBLE
                        }


                    }
                }
            }
        }
    }

    private fun setupSpinnerCategory() {
        lifecycleScope.launch(Dispatchers.IO) {
            val responseService = RetrofitCocktail.APICOCKTAILS.getCategoriesList("list.php?c=list")
            val categories = responseService.body()
            withContext(Dispatchers.Main) {
                val l = mutableListOf<String>()
                val listCategories = categories?.drinks
                listCategories?.forEach {
                    l.add(it.strCategory)
                }
                val adapter = ArrayAdapter(applicationContext,
                com.example.open101.R.layout.spinner_text,
                l)
                adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
                binding.spinnerCategory.adapter = adapter
                binding.spinnerCategory.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onNothingSelected(parent: AdapterView<*>?) {
                        Toast.makeText(applicationContext, "NothingSelected", Toast.LENGTH_SHORT).show()
                    }

                    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                        if (!flagSpinnerCategory) {
                            flagSpinnerCategory = true
                        } else {
                            val bundle = Bundle()
                            val category = binding.spinnerCategory.selectedItem.toString()
                            bundle.putString("Category", category)
                            val listedCategories = CocktailsCategoryListedResults()
                            listedCategories.arguments = bundle
                            supportFragmentManager.beginTransaction().add(binding.cocktailsContainer.id, listedCategories, "ListedCategories").addToBackStack(null).commit()
                            allGone()
                            binding.cocktailsContainer.visibility = View.VISIBLE
                        }


                    }
                }
            }
        }
    }

    private fun allGone() {
        binding.ABCBrowserView.visibility = View.GONE
        binding.rvPopularDrinks.visibility = View.GONE
        binding.tvPopularDrinks.visibility = View.GONE
        binding.tvBrowseByLetter.visibility = View.GONE
        binding.llnavigationSpinners.visibility = View.GONE
        binding.tvBrowseByFilter.visibility = View.GONE
        binding.llnavigationfilters.visibility = View.GONE
    }

    private fun allVisible() {
        binding.ABCBrowserView.visibility = View.VISIBLE
        binding.rvPopularDrinks.visibility = View.VISIBLE
        binding.tvPopularDrinks.visibility = View.VISIBLE
        binding.tvBrowseByLetter.visibility = View.VISIBLE
        binding.llnavigationSpinners.visibility = View.VISIBLE
        binding.tvBrowseByFilter.visibility = View.VISIBLE
        binding.llnavigationfilters.visibility = View.VISIBLE
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

    private fun setupAllCocktailsArray() {
        val allCocktails = ArrayList<String>()
        lifecycleScope.launch(Dispatchers.IO) {
            for (i in 97..122) {
                val responseService = RetrofitCocktail.APICOCKTAILS.getPopularDrinks("search.php?f=${i.toChar()}")
                val cocktails = responseService.body()
                cocktails?.Drinks?.forEach {
                    allCocktails.add("${it.idDrink} - ${it.strDrink}")
                }
            }
            withContext(Dispatchers.Main) {
                val adapter = ArrayAdapter(applicationContext, com.example.open101.R.layout.spinner_text, allCocktails)
                binding.actvCocktails.threshold = 0
                binding.actvCocktails.setAdapter(adapter)
                binding.btnACTVCocktails.setOnClickListener {
                    hideKeyboard()
                    val bundle = Bundle()
                    val takeid: List<String> = binding.actvCocktails.text.toString().split(" - ")
                    val id = takeid[0]
                    bundle.putString("id", id)
                    val popuFrag = CocktailFullView()
                    popuFrag.arguments = bundle
                    supportFragmentManager.beginTransaction().add(
                        binding.cocktailsContainer.id,
                        popuFrag,
                        "PopularDrinksOnItemClickedFragment"
                    ).addToBackStack(null).commit()
                    binding.cocktailsContainer.visibility = View.VISIBLE
                    binding.actvIngredients.setText("")
                    allGone()
                }
            }
        }
    }

    private fun setupAllIngredientsArray() {
        val allIngredients = ArrayList<String>()
        lifecycleScope.launch(Dispatchers.IO) {
            for (i in 97..122) {
                val responseService = RetrofitCocktail.APICOCKTAILS.getIngredient("search.php?i=${i.toChar()}")
                val ingredients = responseService.body()
                ingredients?.ingredient?.forEach {
                    allIngredients.add("${it.idIngredient} - ${it.strIngredient}")
                }
            }
            withContext(Dispatchers.Main) {
                val adapter = ArrayAdapter(applicationContext, com.example.open101.R.layout.spinner_text, allIngredients)
                binding.actvIngredients.threshold = 0
                binding.actvIngredients.setAdapter(adapter)
                binding.btnACTVIngredients.setOnClickListener {
                    hideKeyboard()
                    val bundle = Bundle()
                    val takeid: List<String> = binding.actvIngredients.text.toString().split(" - ")
                    val id = takeid[0]
                    bundle.putInt("id", Integer.parseInt(id))
                    val popuFrag = IngredientFullView()
                    popuFrag.arguments = bundle
                    supportFragmentManager.beginTransaction().add(
                        binding.cocktailsContainer.id,
                        popuFrag,
                        "PopularDrinksOnItemClickedFragment"
                    ).addToBackStack(null).commit()
                    binding.cocktailsContainer.visibility = View.VISIBLE
                    binding.actvIngredients.setText("")
                    allGone()
                }
            }
        }
    }

    private fun hideKeyboard() {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.viewRoot.windowToken, 0)
    }
}