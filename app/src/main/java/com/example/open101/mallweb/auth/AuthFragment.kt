package com.example.open101.mallweb.auth

import android.app.AlertDialog
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.open101.R
import com.example.open101.activitys.MallWeb
import com.example.open101.databinding.FragmentAuthBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

@Suppress("DEPRECATION")
class AuthFragment : Fragment(R.layout.fragment_auth) {

    private lateinit var binding: FragmentAuthBinding
    private val idGOOGLESIGNIN = 100
    private val provider = ProviderType()
    private lateinit var clientGoogle: GoogleSignInClient



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAuthBinding.bind(view)
        val id2 = arguments?.getInt("ContainerID")


        val analytics = FirebaseAnalytics.getInstance(requireContext())
        val bundle = Bundle()
        bundle.putString("message", "Integracion de firebase completa")
        analytics.logEvent("InitScreen", bundle)

        setup(id2)
    }

    private fun setup(id2: Int?) {
        binding.btnReg.setOnClickListener {
            if (id2 != null) {
                requireActivity()
                    .supportFragmentManager
                    .beginTransaction()
                    .setCustomAnimations(
                        R.anim.right_in,
                        R.anim.left_out,
                        R.anim.right_in,
                        R.anim.left_out)
                    .replace(id2, RegisterFragment(), "Register Fragment")
                    .apply { arguments?.putInt("ContainerID", id) }
                    .addToBackStack("Register Fragment")
                    .commit()
            }
        }

        binding.btnBasic.setOnClickListener {
            if (binding.etEmail.text.isNotEmpty() && binding.etPass.text.isNotEmpty()) {
                val providerName = provider.BASIC
                FirebaseAuth.getInstance().signInWithEmailAndPassword(binding.etEmail.text.toString(), binding.etPass.text.toString())
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            saveData(it.result?.user?.email ?: "", providerName)
                            startActivity(Intent(requireContext(), MallWeb::class.java).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                            requireActivity().overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
                        } else {
                            showAlert()
                        }
                    }

            }
        }

        binding.btnGoogle.setOnClickListener {
            val options = GoogleSignInOptions.Builder().requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()
            clientGoogle = GoogleSignIn.getClient(requireActivity(), options)
            val intent = clientGoogle.signInIntent
            startActivityForResult(intent, 10001)
            /*
            val googleConf = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()

            val googleClient = GoogleSignIn.getClient(requireActivity(), googleConf)
            googleClient.signOut()
            startActivityForResult(googleClient.signInIntent, idGOOGLESIGNIN)
            */
        }


        binding.btnLostPassword.setOnClickListener {
            if (id2 != null) {
                requireActivity()
                    .supportFragmentManager
                    .beginTransaction()
                    .setCustomAnimations(
                        R.anim.right_in,
                        R.anim.left_out,
                        R.anim.right_in,
                        R.anim.left_out)
                    .replace(id2, RecoverPassFragment(), "Recover Pass Fragment")
                    .apply { arguments?.putInt("ContainerID", id) }
                    .addToBackStack("Recover Pass Fragment")
                    .commit()
            }
        }

    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == idGOOGLESIGNIN) {
            val providerName = provider.GOOGLE
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            val account = task.getResult(ApiException::class.java)
            val credential = GoogleAuthProvider.getCredential(account.idToken, null)
            FirebaseAuth.getInstance().signInWithCredential(credential)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        saveData(account.email ?: "", providerName)
                    } else {
                        Toast.makeText(requireContext(), task.exception?.message, Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }

    /*
    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val providerName = provider.GOOGLE

        if (requestCode == idGOOGLESIGNIN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)

            try {
                val account = task.getResult(ApiException::class.java)

                if (account != null) {

                    val credential = GoogleAuthProvider.getCredential(account.idToken, null)

                    FirebaseAuth.getInstance().signInWithCredential(credential).addOnCompleteListener {
                        if (it.isSuccessful) {
                            saveData(account.email ?: "", providerName)
                        } else {
                            showAlert()
                        }
                    }

                }
            } catch (e: ApiException) {
                showAlert2()
            }


        }
    }

     */



    private fun showAlert() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error autenticando el usuario")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun showAlert2() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error autenticando el usuario, la cuenta es nula")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun saveData(email: String, providerName: String) {
        val prefs: SharedPreferences = requireActivity().getSharedPreferences("MY PREF", AppCompatActivity.MODE_PRIVATE)
        val prefsEd = prefs.edit()
        prefsEd.putString("email", email)
        prefsEd.putString("provider", providerName)
        prefsEd.apply()
    }

}