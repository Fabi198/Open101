package com.example.open101.mallweb.fragmentsDrawerMenu

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.open101.R
import com.example.open101.databinding.FragmentPersonalDataBinding
import com.example.open101.mallweb.db.DbMallweb
import java.util.*

@Suppress("DEPRECATION")
class PersonalDataFragment : Fragment(R.layout.fragment_personal_data) {

    private lateinit var binding: FragmentPersonalDataBinding
    private var flagData = true
    private var flagOrders = true
    private var flagOrdersCancelled = true
    private lateinit var dbMallweb: DbMallweb

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPersonalDataBinding.bind(view)
        // val id = arguments?.getInt("ContainerID")



        binding.cvMyData.setOnClickListener {
            if (flagData) {
                if (!flagOrders) {setGoneMyOrders()}
                if (!flagOrdersCancelled) {setGoneMyCancelledOrders()}
                setVisibleMyData()
            } else {
                setGoneMyData()
            }
        }


        binding.cvMyOrders.setOnClickListener {
            if (flagOrders) {
                if (!flagData) {setGoneMyData()}
                if (!flagOrdersCancelled) {setGoneMyCancelledOrders()}
                setVisibleMyOrders()
            } else {
                setGoneMyOrders()
            }
        }


        binding.cvMyCancelledOrders.setOnClickListener {
            if (flagOrdersCancelled) {
                if (!flagData) {setGoneMyData()}
                if (!flagOrders) {setGoneMyOrders()}
                setVisibleMyCancelledOrders()
            } else {
                setGoneMyCancelledOrders()
            }
        }

    }

    private fun setVisibleMyData() {
        binding.cvMyData.setCardBackgroundColor(resources.getColor(R.color.red))
        binding.cvMyDataDisplayed.visibility = View.VISIBLE
        setClientData()
        flagData = false
    }

    private fun setGoneMyData() {
        binding.cvMyData.setCardBackgroundColor(resources.getColor(R.color.black))
        binding.cvMyDataDisplayed.visibility = View.GONE
        flagData = true
    }

    private fun setVisibleMyOrders() {
        binding.cvMyOrders.setCardBackgroundColor(resources.getColor(R.color.red))
        binding.tvOrders.visibility = View.VISIBLE
        flagOrders = false
    }

    private fun setGoneMyOrders() {
        binding.cvMyOrders.setCardBackgroundColor(resources.getColor(R.color.black))
        binding.tvOrders.visibility = View.GONE
        flagOrders = true
    }

    private fun setVisibleMyCancelledOrders() {
        binding.cvMyCancelledOrders.setCardBackgroundColor(resources.getColor(R.color.red))
        binding.tvOrdersAbbandoned.visibility = View.VISIBLE
        flagOrdersCancelled = false
    }

    private fun setGoneMyCancelledOrders() {
        binding.cvMyCancelledOrders.setCardBackgroundColor(resources.getColor(R.color.black))
        binding.tvOrdersAbbandoned.visibility = View.GONE
        flagOrdersCancelled = true
    }

    private fun setClientData() {
        dbMallweb = DbMallweb(requireContext())
        val prefs: SharedPreferences = requireActivity().getSharedPreferences("MY PREF", AppCompatActivity.MODE_PRIVATE)
        val email = prefs.getString("email", null)
        if (email != null) {
            val client = dbMallweb.queryForClient(email)
            binding.idclient.text = client.id.toString()
            binding.emailClient.text = client.email
            binding.nameClient.setText(client.name)
            binding.lastnameClient.setText(client.lastName)
            binding.dateBirthClient.setText(client.birthday)
            binding.codAreaNumber.setText(client.codArea)
            binding.numberCellphoneMallwebClient.setText(client.numCelular)
            binding.dniClientMallweb.setText(client.dni)
            binding.cuitClientMallweb.setText(client.cuit)
            binding.wantABillClientMallweb.setText(client.wantABill)
        }




        binding.dateBirthClient.setOnFocusChangeListener { _, hasFocus -> if (hasFocus) { hideKeyboard(); openCalendar(); binding.dateBirthClient.setOnClickListener { openCalendar() } } }
        binding.wantABillClientMallweb.setOnFocusChangeListener { _, hasFocus -> if (hasFocus) { hideKeyboard(); setBillBoolean(); binding.wantABillClientMallweb.setOnClickListener { setBillBoolean() } } }
        binding.btnActProfile.setOnClickListener {
            if (areEmpty()) {
                if (dbMallweb.editClient(
                        Integer.parseInt(binding.idclient.text.toString()),
                        binding.nameClient.text.toString(),
                        binding.lastnameClient.text.toString(),
                        binding.dateBirthClient.text.toString(),
                        binding.codAreaNumber.text.toString(),
                        binding.numberCellphoneMallwebClient.text.toString(),
                        binding.dniClientMallweb.text.toString(),
                        binding.cuitClientMallweb.text.toString(),
                        binding.wantABillClientMallweb.text.toString()
                    )) {
                    showAlertSuccess()
                    setGoneMyData()
                    setVisibleMyData()
                } else {
                    showAlertError()
                }
            } else {
                Toast.makeText(requireContext(), "Debe completar todos los campos", Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun areEmpty(): Boolean { return (binding.idclient.text.toString().isNotEmpty() && binding.nameClient.text.toString().isNotEmpty() && binding.lastnameClient.text.toString().isNotEmpty() && binding.dateBirthClient.text.toString().isNotEmpty() && binding.codAreaNumber.text.toString().isNotEmpty() && binding.numberCellphoneMallwebClient.text.toString().isNotEmpty() && binding.dniClientMallweb.text.toString().isNotEmpty() && binding.cuitClientMallweb.text.toString().isNotEmpty() && binding.wantABillClientMallweb.text.toString().isNotEmpty()) }

    private fun setBillBoolean() {
        val si = resources.getString(R.string.si)
        val no = resources.getString(R.string.no)
        val options = arrayOf(si, no)
        var defaultPosition = 0
        val builderSingle = AlertDialog.Builder(requireContext())
        builderSingle.setPositiveButton(getString(android.R.string.ok)) {dialog, _ -> dialog.dismiss()}
        builderSingle.setSingleChoiceItems(options, defaultPosition) {_, witch ->
            defaultPosition = witch
            binding.wantABillClientMallweb.setText(options[witch])
        }
        builderSingle.show()

    }

    private fun openCalendar() {
        val cal: Calendar = Calendar.getInstance()
        val yearGetter = cal.get(Calendar.YEAR)
        val monthGetter = cal.get(Calendar.MONTH)
        val dayGetter = cal.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(requireContext(), 0,
            { _, year, month, dayOfMonth ->
                lateinit var fecha: String
                if ((month+1) in 0..9 && dayOfMonth in 10..31) {
                    fecha = "$year-0${month+1}-$dayOfMonth"
                } else if ((month+1) in 0..9 && dayOfMonth in 0..9) {
                    fecha = "$year-0${month+1}-0$dayOfMonth"
                } else if ((month+1) in 10..12 && dayOfMonth in 0..9) {
                    fecha = "$year-${month+1}-0$dayOfMonth"
                } else if ((month+1) in 10..12 && dayOfMonth in 10..31) {
                    fecha = "$year-${month+1}-$dayOfMonth"
                }
                binding.dateBirthClient.setText(fecha)
            }, yearGetter, monthGetter, dayGetter)
        dpd.show()
    }

    private fun hideKeyboard() {
        val imm = requireActivity().getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.llPersonalDataMain.windowToken, 0)
    }

    private fun showAlertSuccess() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setMessage("Sus datos han sido actualizados")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun showAlertError() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Error")
        builder.setMessage("Sus datos no han sido actualizados")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

}