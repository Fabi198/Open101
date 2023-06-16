package com.example.open101.mallweb.fragmentsDrawerMenu

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.open101.R
import com.example.open101.databinding.FragmentShoppingCartStep2Binding
import com.example.open101.mallweb.db.DbMallweb
import com.example.open101.mallweb.entities.dbEntities.Client
import java.util.*
import kotlin.collections.ArrayList


@Suppress("DEPRECATION")
class ShoppingCartFragmentStep2 : Fragment(R.layout.fragment_shopping_cart_step2) {

    private lateinit var binding: FragmentShoppingCartStep2Binding
    private var flagSpinnerIVA = false
    private lateinit var dbMallweb: DbMallweb
    private var postalCodeFromBundle = 0
    private var withShipping = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentShoppingCartStep2Binding.bind(view)

        setAddressFromPostalCode()
        setShippingVisibility()
        setClientData()
        setBillCheckers()
        setIVASpinner()
    }

    private fun notFoundBillLocality() {
        if (binding.cbLocalityBillNotFound.isChecked) {
            setSpinnerAllProvinces(binding.spinnerProvinceBillAddress)
            binding.spinnerLocalityBillAddress.visibility = View.GONE
            binding.localityBillAddress.setTextColor(resources.getColor(R.color.white))
            binding.localityBillAddress.visibility = View.VISIBLE
        } else {
            binding.spinnerLocalityBillAddress.visibility = View.VISIBLE
            binding.localityBillAddress.visibility = View.GONE
        }
    }

    private fun notFoundShippingLocality() {
        if (binding.cbLocalityShippingNotFound.isChecked) {
            setSpinnerAllProvinces(binding.spinnerProvinceShippingAddress)
            binding.spinnerLocalityShippingAddress.visibility = View.GONE
            binding.localityShippingAddress.setTextColor(resources.getColor(R.color.white))
            binding.localityShippingAddress.visibility = View.VISIBLE
        } else {
            binding.spinnerLocalityShippingAddress.visibility = View.VISIBLE
            binding.localityShippingAddress.visibility = View.GONE
        }
    }

    private fun setAddressFromPostalCode() {
        binding.postalCodeBillAddress.setOnKeyListener { _, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                hideKeyboard()
                setSpinnerProvince(
                    binding.postalCodeBillAddress.text.toString(),
                    binding.spinnerProvinceBillAddress
                )
                return@setOnKeyListener true
            }
            false
        }
        binding.postalCodeShippingAddress.setOnKeyListener { _, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                hideKeyboard()
                setSpinnerProvince(
                    binding.postalCodeShippingAddress.text.toString(),
                    binding.spinnerProvinceShippingAddress
                )
                return@setOnKeyListener true
            }
            false
        }
    }

    private fun setShippingVisibility() {
        if (postalCodeFromBundle > 0 && withShipping) {
            binding.cvShippingAddress.visibility = View.VISIBLE
        } else {
            binding.cvShippingAddress.visibility = View.GONE
        }
    }

    private fun setIVASpinner() {
        val adapterIVA = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, setIVAConditions())
        adapterIVA.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerIVACondition.adapter = adapterIVA
        binding.spinnerIVACondition.setSelection(0)
        binding.spinnerIVACondition.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) { Toast.makeText(requireContext(), "NothingSelected", Toast.LENGTH_SHORT).show() }

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    if (!flagSpinnerIVA) {
                        flagSpinnerIVA = true
                    } else {
                        binding.spinnerIVACondition.selectedItem.toString()
                    }
                }
            }
    }

    private fun getArrayForAdapter(postalCode: String): ArrayList<String> {
        dbMallweb = DbMallweb(requireContext())
        val list: ArrayList<String> = dbMallweb.getProvinceForSpinner(postalCode)
        if (list.size == 0) { Toast.makeText(requireContext(), "No existe el codigo postal", Toast.LENGTH_SHORT).show() }
        return list
    }

    private fun getAdapterForSpinner(postalCode: String): ArrayAdapter<String> {
        Log.i("postalCodeGetAdapterForSpinner", postalCode)
        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            getArrayForAdapter(postalCode)
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        return adapter
    }

    private fun setKnownProvinceSpinner(province_name: String) {
        val list = arrayOf(province_name)
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, list)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerProvinceBillAddress.adapter = adapter
        binding.spinnerProvinceBillAddress.setSelection(0)
    }

    private fun setKnownLocalitySpinner(city_name: String) {
        val list = arrayOf(city_name)
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, list)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerLocalityBillAddress.adapter = adapter
        binding.spinnerLocalityBillAddress.setSelection(0)
    }

    private fun setSpinnerAllProvinces(spinner: Spinner) {
        dbMallweb = DbMallweb(requireContext())
        val list = ArrayList<String>()
        dbMallweb.getProvinces().forEach { list.add(it.name) }
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, list)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
        spinner.setSelection(0)
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                Toast.makeText(requireContext(), "NothingSelected", Toast.LENGTH_SHORT).show()
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                spinner.selectedItem.toString()
            }
        }
    }

    private fun setSpinnerProvince(postalCode: String, spinner: Spinner) {
        spinner.adapter = getAdapterForSpinner(postalCode)
        spinner.setSelection(0)
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                Toast.makeText(requireContext(), "NothingSelected", Toast.LENGTH_SHORT).show()
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (spinner == binding.spinnerProvinceBillAddress) {setLocalitySpinner(binding.spinnerLocalityBillAddress, postalCode, spinner.selectedItem.toString())} else if (spinner == binding.spinnerProvinceShippingAddress) {setLocalitySpinner(binding.spinnerLocalityShippingAddress, postalCode, spinner.selectedItem.toString())}
            }
        }
    }

    private fun getArrayForAdapterLocality(
        postalCode: String,
        province_name: String
    ): ArrayList<String> {
        dbMallweb = DbMallweb(requireContext())
        return dbMallweb.getCitysForSpinner(postalCode, province_name)
    }

    private fun setAdapterForLocalitySpinner(
        postalCode: String,
        province_name: String
    ): ArrayAdapter<String> {
        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            getArrayForAdapterLocality(postalCode, province_name)
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        return adapter
    }

    private fun setLocalitySpinner(spinner: Spinner, postalCode: String, province_name: String) {
        spinner.adapter = setAdapterForLocalitySpinner(postalCode, province_name)
        spinner.setSelection(0)
        spinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) { Toast.makeText(requireContext(), "NothingSelected", Toast.LENGTH_SHORT).show() }
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) { spinner.selectedItem.toString() }
            }
    }

    private fun setClientData() {
        dbMallweb = DbMallweb(requireContext())
        val prefs: SharedPreferences =
            requireActivity().getSharedPreferences("MY PREF", AppCompatActivity.MODE_PRIVATE)
        val email = prefs.getString("email", null)
        if (email != null) {
            val client = dbMallweb.queryForClient(email)
            fillClientData(client)
            if (dbMallweb.queryForShippingAddress(client.id).idClient > 0) {
                fillShippingAddress(client.id)
            } else {
                binding.postalCodeShippingAddress.setText(postalCodeFromBundle.toString()); binding.cbLocalityShippingNotFound.setOnClickListener { notFoundShippingLocality() }
            }
            if (dbMallweb.queryForBillAddress(client.id).idClient > 0) {
                fillBillAddress(client.id)
            } else {
                binding.cbLocalityBillNotFound.setOnClickListener { notFoundBillLocality() }
            }

            binding.btnActProfile.setOnClickListener {
                if (binding.cbWantABillYES.isChecked) {
                    dbMallweb.editClient(
                        client.id,
                        binding.nameClient.text.toString(),
                        binding.lastnameClient.text.toString(),
                        binding.dateBirthClient.text.toString(),
                        binding.codAreaNumber.text.toString(),
                        binding.numberCellphoneMallwebClient.text.toString(),
                        binding.dniClientMallweb.text.toString(),
                        binding.cuitClientMallweb.text.toString(),
                        "si",
                        binding.spinnerIVACondition.selectedItem.toString()
                    )
                }
                if (binding.cbWantABillNO.isChecked) {
                    dbMallweb.editClient(
                        client.id,
                        binding.nameClient.text.toString(),
                        binding.lastnameClient.text.toString(),
                        binding.dateBirthClient.text.toString(),
                        binding.codAreaNumber.text.toString(),
                        binding.numberCellphoneMallwebClient.text.toString(),
                        binding.dniClientMallweb.text.toString(),
                        binding.cuitClientMallweb.text.toString(),
                        "no",
                    )
                }
                if ((binding.cvShippingAddress.visibility == View.VISIBLE && areShippingAddressFieldEmpty()) || (binding.cvBillAddress.visibility == View.VISIBLE && areBillAddressFieldEmpty())) {
                    if (dbMallweb.queryForShippingAddress(client.id).idClient > 0) {
                        dbMallweb.editShippingAddress(
                            client.id,
                            binding.streetShippingAddress.text.toString(),
                            binding.heightShippingAddress.text.toString(),
                            binding.floorShippingAddress.text.toString(),
                            binding.doorShippingAddress.text.toString(),
                            binding.postalCodeShippingAddress.text.toString(),
                            binding.spinnerProvinceShippingAddress.selectedItem.toString(),
                            binding.localityShippingAddress.text.toString()
                        )
                    } else {
                        dbMallweb.createShippingAddress(
                            client.id,
                            binding.streetShippingAddress.text.toString(),
                            binding.heightShippingAddress.text.toString(),
                            binding.floorShippingAddress.text.toString(),
                            binding.doorShippingAddress.text.toString(),
                            binding.postalCodeShippingAddress.text.toString(),
                            binding.spinnerProvinceShippingAddress.selectedItem.toString(),
                            binding.localityShippingAddress.text.toString()
                        )
                    }
                    if (dbMallweb.queryForBillAddress(client.id).idClient > 0) {
                        dbMallweb.editBillAddress(
                            client.id,
                            binding.streetBillAddress.text.toString(),
                            binding.heightBillAddress.text.toString(),
                            binding.floorBillAddress.text.toString(),
                            binding.doorBillAddress.text.toString(),
                            binding.postalCodeBillAddress.text.toString(),
                            binding.spinnerProvinceBillAddress.selectedItem.toString(),
                            binding.localityBillAddress.text.toString()
                        )
                    } else {
                        dbMallweb.createBillAddress(
                            client.id,
                            binding.streetBillAddress.text.toString(),
                            binding.heightBillAddress.text.toString(),
                            binding.floorBillAddress.text.toString(),
                            binding.doorBillAddress.text.toString(),
                            binding.postalCodeBillAddress.text.toString(),
                            binding.spinnerProvinceBillAddress.selectedItem.toString(),
                            binding.localityBillAddress.text.toString()
                        )
                    }
                    showAlertSuccess()
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Debe completar todos los campos",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }


    }

    private fun fillBillAddress(idClient: Int) {
        val billAddress = dbMallweb.queryForBillAddress(idClient)
        binding.streetBillAddress.setText(billAddress.street)
        binding.heightBillAddress.setText(billAddress.number)
        binding.floorBillAddress.setText(billAddress.floor)
        binding.doorBillAddress.setText(billAddress.door)
        binding.postalCodeBillAddress.setText(billAddress.postalCode)
        setKnownProvinceSpinner(billAddress.province)
        setKnownLocalitySpinner(billAddress.locality)
        binding.cbLocalityBillNotFound.setOnClickListener { notFoundBillLocality() }
    }

    private fun fillClientData(client: Client) {
        binding.idclient.text = client.id.toString()
        binding.emailClient.text = client.email
        binding.nameClient.setText(client.name)
        binding.lastnameClient.setText(client.lastName)
        binding.dateBirthClient.setText(client.birthday)
        binding.codAreaNumber.setText(client.codArea)
        binding.numberCellphoneMallwebClient.setText(client.numCelular)
        binding.dniClientMallweb.setText(client.dni)
        binding.cuitClientMallweb.setText(client.cuit)
        if (client.wantABill.lowercase() == "si" && client.ivaCondition != "") {
            setBillBoolean(client.wantABill, client.ivaCondition)
        } else if (client.wantABill.lowercase() == "no" || client.wantABill.lowercase() == "si") {
            setBillBoolean(client.wantABill)
        }
        binding.dateBirthClient.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                openCalendar(); binding.dateBirthClient.setOnClickListener { openCalendar() }
            }
        }
    }

    private fun fillShippingAddress(idClient: Int) {
        val shippingAddress = dbMallweb.queryForShippingAddress(idClient)
        binding.streetShippingAddress.setText(shippingAddress.street)
        binding.heightShippingAddress.setText(shippingAddress.number)
        binding.floorShippingAddress.setText(shippingAddress.floor)
        binding.doorShippingAddress.setText(shippingAddress.door)
        binding.postalCodeShippingAddress.setText(shippingAddress.postalCode)
        dbMallweb.getProvinces().forEachIndexed { index, s ->
            if (shippingAddress.province == s.name) {
                binding.spinnerProvinceShippingAddress.setSelection(index)
            }
        }
        binding.localityShippingAddress.setText(shippingAddress.locality)
    }

    private fun setBillCheckers() {
        binding.cbWantABillNO.setOnClickListener {
            if (binding.cbWantABillNO.isChecked) {
                binding.cbWantABillYES.isChecked = false
                if (binding.llwantBillA.visibility == View.VISIBLE) {
                    binding.llwantBillA.visibility = View.GONE
                }
            }
        }
        binding.cbWantABillYES.setOnClickListener {
            if (binding.cbWantABillYES.isChecked) {
                binding.cbWantABillNO.isChecked = false
                binding.llwantBillA.visibility = View.VISIBLE
                if (binding.cuitClientMallweb.text.toString().isNotEmpty()) {
                    binding.cuit2ClientMallweb.text = binding.cuitClientMallweb.text
                }
            }
        }
    }

    private fun areBillAddressFieldEmpty(): Boolean {
        return (binding.spinnerProvinceBillAddress.adapter != null && binding.streetBillAddress.text.toString()
            .isNotEmpty() && binding.heightBillAddress.text.toString()
            .isNotEmpty() && binding.postalCodeBillAddress.text.toString()
            .isNotEmpty() && binding.localityBillAddress.text.toString().isNotEmpty())
    }

    private fun areShippingAddressFieldEmpty(): Boolean {
        return (binding.spinnerProvinceBillAddress.adapter != null && binding.streetShippingAddress.text.toString()
            .isNotEmpty() && binding.heightShippingAddress.text.toString()
            .isNotEmpty() && binding.postalCodeShippingAddress.text.toString()
            .isNotEmpty() && binding.localityShippingAddress.text.toString().isNotEmpty())
    }

    private fun setBillBoolean(s: String, iClient: String? = null) {
        if (s.lowercase() == "si") {
            binding.cbWantABillYES.isChecked = true
            binding.llwantBillA.visibility = View.VISIBLE
            if (binding.cuitClientMallweb.text.toString().isNotEmpty()) {
                binding.cuit2ClientMallweb.text = binding.cuitClientMallweb.text
            }
            if (iClient != null) {
                setIVAConditions().forEachIndexed { index, i ->
                    if (iClient == i) {
                        binding.spinnerIVACondition.setSelection(index)
                    }
                }
            }
        } else if (s.lowercase() == "no") {
            binding.cbWantABillNO.isChecked = true
            binding.llwantBillA.visibility = View.GONE
        }

    }

    private fun openCalendar() {
        val cal: Calendar = Calendar.getInstance()
        val yearGetter = cal.get(Calendar.YEAR)
        val monthGetter = cal.get(Calendar.MONTH)
        val dayGetter = cal.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(requireContext(), 0,
            { _, year, month, dayOfMonth ->
                lateinit var fecha: String
                if ((month + 1) in 0..9 && dayOfMonth in 10..31) {
                    fecha = "$year-0${month + 1}-$dayOfMonth"
                } else if ((month + 1) in 0..9 && dayOfMonth in 0..9) {
                    fecha = "$year-0${month + 1}-0$dayOfMonth"
                } else if ((month + 1) in 10..12 && dayOfMonth in 0..9) {
                    fecha = "$year-${month + 1}-0$dayOfMonth"
                } else if ((month + 1) in 10..12 && dayOfMonth in 10..31) {
                    fecha = "$year-${month + 1}-$dayOfMonth"
                }
                binding.dateBirthClient.setText(fecha)
            }, yearGetter, monthGetter, dayGetter
        )
        dpd.show()
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

    private fun setIVAConditions(): Array<String> {
        return arrayOf(
            "Seleccione",
            "Inscripto",
            "Excento",
            "Consumidor finál",
            "Monotributo",
            "No categorizado"
        )
    }

    private fun hideKeyboard() {
        val imm =
            requireActivity().getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.svSCFStep2.windowToken, 0)
    }

}