package com.whatsupchat

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class AuthActivity : AppCompatActivity() {
    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        val userIn = findViewById<EditText>(R.id.etUsername)
        val passIn = findViewById<EditText>(R.id.etPassword)

        findViewById<Button>(R.id.btnRegister).setOnClickListener {
            val user = userIn.text.toString()
            val pass = passIn.text.toString()
            // Pakai virtual email biar login pake username doang
            auth.createUserWithEmailAndPassword("$user@whatsup.com", pass).addOnSuccessListener {
                val uid = auth.currentUser?.uid
                db.collection("users").document(uid!!).set(mapOf("username" to user, "uid" to uid))
                startActivity(Intent(this, MainActivity::class.java))
            }
        }
    }
}

