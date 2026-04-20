package com.whatsupchat

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {
    private val db = FirebaseFirestore.getInstance()
    private val myUid = FirebaseAuth.getInstance().currentUser?.uid

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Fitur Add Friend Pake UID 32 Digit
        findViewById<Button>(R.id.btnAddFriend).setOnClickListener {
            val targetUid = findViewById<EditText>(R.id.etTargetUid).text.toString()
            if (targetUid.isNotEmpty()) {
                db.collection("users").document(myUid!!).collection("friends").document(targetUid).set(mapOf("status" to "friend"))
                Toast.makeText(this, "Friend Added!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

