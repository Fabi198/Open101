package com.example.open101.mallweb.fragments

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
import com.example.open101.databinding.FragmentMyPersonalDataBinding
import com.example.open101.mallweb.db.DbMallweb
import java.util.*


class MyPersonalData : Fragment(R.layout.fragment_my_personal_data) {

    private lateinit var binding: FragmentMyPersonalDataBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMyPersonalDataBinding.bind(view)


        setClientData()

    }

    private fun setClientData() {
        val dbMallweb = DbMallweb(requireContext())
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
            if (dbMallweb.queryForShippingAddress(client.id).idClient > 0) {
                with(dbMallweb.queryForShippingAddress(client.id)) {
                    binding.streetShippingClientMallweb.text = street
                    binding.heightShippingClientMallweb.text = number
                    binding.floorShippingClientMallweb.text = floor
                    binding.doorShippingClientMallweb.text = door
                    binding.postalCodeShippingClientMallweb.text = postalCode
                    binding.provinceShippingClientMallweb.text = province
                    binding.localityShippingClientMallweb.text = locality
                }
            }
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
        imm.hideSoftInputFromWindow(binding.svMyPersonalData.windowToken, 0)
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