package toledo.luis.peliculas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class RegistroActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var et_correo: EditText
    private lateinit var et_password: EditText
    private lateinit var et_confirm_password: EditText
    private lateinit var btn_register: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        et_correo = findViewById(R.id.et_correo)
        et_password = findViewById(R.id.et_password)
        et_confirm_password = findViewById(R.id.et_confirm_password)
        btn_register = findViewById(R.id.btn_register)


        auth = Firebase.auth

//        auth.


        btn_register.setOnClickListener {
            var email: String = et_correo.text.toString()
            var password: String = et_password.text.toString()
            var confirmPassowrd: String = et_confirm_password.text.toString()

            if (email.isNullOrEmpty() || password.isNullOrEmpty() || confirmPassowrd.isNullOrEmpty()) {
                Toast.makeText(this, "Rellena los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!password.equals(confirmPassowrd)) {
                Toast.makeText(this, "Contraseñas no coinciden", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("exito", "createUserWithEmail:success")
                        val user = auth.currentUser
                        Toast.makeText(
                            baseContext,
                            "Usuario ${user?.email} registrado",
                            Toast.LENGTH_SHORT
                        ).show()
//                        updateUI(user)
                        finish()
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("error", "createUserWithEmail:failure", task.exception)

                        Toast.makeText(
                            baseContext,
                            "Error al registrarse",
                            Toast.LENGTH_SHORT
                        ).show()
//                        updateUI(null)
                    }
                }

        }
    }
}