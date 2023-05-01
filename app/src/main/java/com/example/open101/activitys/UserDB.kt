package com.example.open101.activitys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.open101.databinding.ActivityUserDbBinding
import com.example.open101.userDB.NombreDeUser
import com.example.open101.userDB.NombresRepository

class UserDB : AppCompatActivity() {

    private lateinit var binding: ActivityUserDbBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserDbBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonOk.setOnClickListener {
            val nameToDB = NombreDeUser()
            nameToDB.setName(binding.editText.text.toString())
            NombresRepository(this).addName(nameToDB)
            binding.editText.text.clear()
        }

        binding.buttonRefresh.setOnClickListener {
            val namesOnDB = NombresRepository(this).getNames()
            if (namesOnDB.isNotEmpty()) {
                val list1 = mutableListOf<String>()
                namesOnDB.forEach {
                    list1.add(it.getName().toString())
                }
                binding.textView.text = list1.toString()
            } else {
                binding.textView.text = ""
            }
            binding.editText.text.clear()
        }

        binding.buttonDelete.setOnClickListener {
            val namesOnDB = NombresRepository(this).getNames()
            if(namesOnDB.isNotEmpty()) {
                namesOnDB.forEach {
                    NombresRepository(this).deleteAll(it.getId()!!)
                }
            }
            binding.editText.text.clear()
        }

    }
}