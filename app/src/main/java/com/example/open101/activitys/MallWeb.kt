package com.example.open101.activitys


import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.Animation.AnimationListener
import android.view.animation.AnimationUtils
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.open101.R
import com.example.open101.databinding.ActivityMallWebBinding
import com.example.open101.mallweb.adapters.NewBannersAdapter
import com.example.open101.mallweb.adapters.RoundBottomsAdapter
import com.example.open101.mallweb.auth.AuthFragment
import com.example.open101.mallweb.fragmentsDrawerMenu.*
import com.example.open101.mallweb.fragmentsSubAllCategories.*
import com.google.firebase.auth.FirebaseAuth
import com.squareup.picasso.Picasso

@Suppress("DEPRECATION")
class MallWeb : AppCompatActivity() {

    private lateinit var binding: ActivityMallWebBinding
    private lateinit var animFadeOut: Animation
    private lateinit var animFadeIn: Animation
    private lateinit var animFrag: Animation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMallWebBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setupDrawerNavigationBar()
        setupRoundBottoms()
        setupBannersRV()
        setShoppingCartButtonToolbar()
        setMyAccountButtonToolbar()
    }

    private fun session(): Boolean {
        val prefs: SharedPreferences = getSharedPreferences("MY PREF", MODE_PRIVATE)
        val email = prefs.getString("email", null)
        val provider = prefs.getString("provider", null)
        return email != null && provider != null
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 1) {
            supportFragmentManager.popBackStack()
        } else if (supportFragmentManager.backStackEntryCount == 1) {
            val animLeftOut: Animation = AnimationUtils.loadAnimation(this, R.anim.left_out)
            binding.mallwebHomeContainer.startAnimation(animLeftOut)
            animLeftOut.setAnimationListener(object: AnimationListener{
                override fun onAnimationStart(animation: Animation?) {}
                override fun onAnimationEnd(animation: Animation?) { supportFragmentManager.popBackStack() }
                override fun onAnimationRepeat(animation: Animation?) {}
            })
            allVisible()
        } else {
            finish()
        }
    }

    private fun allVisible() {
        animFadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        binding.tvBottomToolbar.visibility = View.VISIBLE
        binding.tvBottomToolbar.startAnimation(animFadeIn)
        binding.lltvBottomToolbar.visibility = View.VISIBLE
        binding.lltvBottomToolbar.startAnimation(animFadeIn)
        binding.rvRoundBottoms.visibility = View.VISIBLE
        binding.rvRoundBottoms.startAnimation(animFadeIn)
        binding.rvBanners.visibility = View.VISIBLE
        binding.rvBanners.startAnimation(animFadeIn)
    }

    private fun allGone() {
        animFadeOut = AnimationUtils.loadAnimation(this, R.anim.fade_out)
        binding.tvBottomToolbar.visibility = View.GONE
        binding.tvBottomToolbar.startAnimation(animFadeOut)
        binding.lltvBottomToolbar.visibility = View.GONE
        binding.lltvBottomToolbar.startAnimation(animFadeOut)
        binding.rvRoundBottoms.visibility = View.GONE
        binding.rvRoundBottoms.startAnimation(animFadeOut)
        binding.rvBanners.visibility = View.GONE
        binding.rvBanners.startAnimation(animFadeOut)
    }

    private fun setupDrawerNavigationBar() {
        if (session()) {
            binding.nvLateralMallweb.menu.findItem(R.id.item_close_mallweb_session).isVisible = true
            binding.btnMyAccount.visibility = View.VISIBLE
        }
        animFrag = AnimationUtils.loadAnimation(this, R.anim.left_in)
        setSupportActionBar(binding.toolbarMallwebHome)
        supportActionBar?.title = null
        Picasso.get().load(R.drawable.mallweb_logo).centerCrop().resize(600, 200).into(binding.ivToolbarMallwebHome)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val drawerToggle = ActionBarDrawerToggle(
            this,
            binding.dlMallweb,
            binding.toolbarMallwebHome,
            R.string.abrir,
            R.string.cerrar
        )
        binding.dlMallweb.addDrawerListener(drawerToggle)
        drawerToggle.syncState()
        drawerToggle.drawerArrowDrawable.color = resources.getColor(R.color.red)
        binding.nvLateralMallweb.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.item_home -> {
                    refresh()
                }
                R.id.item_categories -> {
                    showFragment(AllCategoriesFragment())
                }
                R.id.item_shop_container -> {
                    if (session()) {
                        showFragment(ShoppingCartFragment())
                    } else {
                        showFragment(AuthFragment())
                    }
                }
                R.id.item_your_orders -> {
                    if (session()) {
                        showFragment(PersonalDataFragment())
                    } else {
                        showFragment(AuthFragment())
                    }
                }
                R.id.item_your_directions -> {
                    if (session()) {
                        showFragment(PersonalDataFragment())
                    } else {
                        showFragment(AuthFragment())
                    }
                }
                R.id.item_personal_data -> {
                    if (session()) {
                        showFragment(PersonalDataFragment())
                    } else {
                        showFragment(AuthFragment())
                    }
                }
                R.id.item_who_are_we -> {
                    showFragment(WhoAreWeFragment())
                }
                R.id.item_faq -> {
                    showFragment(FAQFragment())
                }
                R.id.item_deliver_method -> {
                    showFragment(DeliverMethodsFragment())
                }
                R.id.item_payment_method -> {
                    showFragment(BankDataFragment())
                }
                R.id.item_contact -> {
                    showFragment(ContactUsFragment())
                }
                R.id.item_close_mallweb_session -> {
                    signOut()
                }
            }
            binding.dlMallweb.closeDrawers()
            true
        }
    }

    private fun refresh() {
        startActivity(Intent(this, MallWeb::class.java).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
    }

    private fun signOut() {
        FirebaseAuth.getInstance().signOut()
        val prefs: SharedPreferences = getSharedPreferences("MY PREF", MODE_PRIVATE)
        val prefsEd = prefs.edit()
        prefsEd.clear()
        prefsEd.apply()
        refresh()
    }

    private fun setupRoundBottoms() {
        binding.rvRoundBottoms.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        binding.rvRoundBottoms.adapter = RoundBottomsAdapter {
            when(it) {
                "Computación" -> {showFragment(NotebooksFragment())}
                "Comp. PC" -> {showFragment(ComponentsFragment())}
                "Storage" -> {showFragment(StorageMenu())}
                "Periféricos" -> {showFragment(PeripheralsMenu())}
                "Conectividad" -> {showFragment(ConnectivityFragment())}
                "Impresión" -> {showFragment(PrintFragment())}
                "Audio y Video" -> {showFragment(AudioAndVideoFragment())}
                "Zona Gamer" -> {showFragment(GamerZoneFragment())}
            }
        }
    }

    private fun setupBannersRV() {
        binding.rvBanners.layoutManager = LinearLayoutManager(this)
        binding.rvBanners.adapter = NewBannersAdapter {
            when(it) {
                "Mi Cuenta" -> {showFragment(PersonalDataFragment())}
                "Marcas Destacadas" -> {showFragment(FeaturedBrands())}
                "Comunidad" -> {showFragment(ContactUsFragment())}
                "Zona Gamer" -> {showFragment(GamerZoneFragment())}
                "Promociones" -> {showFragment(PromosFragment())}
                "Corsair" -> {showFragment(FeaturedBrands())}
                "Logitech" -> {showFragment(FeaturedBrands())}
                "Seagate" -> {showFragment(FeaturedBrands())}
                "Computación" -> {showFragment(NotebooksFragment())}
                "Comp. PC" -> {showFragment(ComponentsFragment())}
                "Almacenamiento" -> {showFragment(StorageMenu())}
                "Periféricos" -> {showFragment(PeripheralsMenu())}
                "Conectividad" -> {showFragment(ConnectivityFragment())}
                "Impresión" -> {showFragment(PrintFragment())}
                "Audio y Video" -> {showFragment(AudioAndVideoFragment())}
            }
        }
    }

    private fun setShoppingCartButtonToolbar() {
        animFrag = AnimationUtils.loadAnimation(this, R.anim.left_in)
        binding.btnShoppingCartToolbar.setOnClickListener {
            if (session()) {
                showFragment(ShoppingCartFragment())
            } else {
                showFragment(AuthFragment())
            }
        }
    }

    private fun setMyAccountButtonToolbar() {
        animFrag = AnimationUtils.loadAnimation(this, R.anim.left_in)
        binding.btnMyAccount.setOnClickListener {
            showFragment(PersonalDataFragment())
        }
    }

    private fun showFragment(fragment: Fragment) {
        val bundle = Bundle()
        bundle.putInt("ContainerID", binding.mallwebHomeContainer.id)
        fragment.arguments = bundle
        supportFragmentManager.beginTransaction()
            .replace(binding.mallwebHomeContainer.id, fragment, fragment.tag)
            .addToBackStack(fragment.tag)
            .commit()
        allGone()
        binding.mallwebHomeContainer.visibility = View.VISIBLE
        binding.mallwebHomeContainer.startAnimation(animFrag)
    }
}