package toledo.luis.peliculas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class LoginActivity : AppCompatActivity() {


    private lateinit var auth: FirebaseAuth
    lateinit var btn_ingresar: Button
    lateinit var btn_registro: Button
    lateinit var tv_recuperacion: TextView
    lateinit var et_contra: TextView
    lateinit var et_correo: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        btn_registro = findViewById(R.id.btn_register)
        btn_ingresar = findViewById(R.id.btn_login)
        tv_recuperacion = findViewById(R.id.tv_recuperacion)
        et_contra = findViewById(R.id.password_login)
        et_correo = findViewById(R.id.correo_login)

        auth = Firebase.auth

        btn_registro.setOnClickListener {
            var intent = Intent(this, RegistroActivity::class.java)
            startActivity(intent)
        }
        btn_ingresar.setOnClickListener {

            var correo = et_correo.text.toString()
            var password = et_contra.text.toString()

            if (correo.isNullOrEmpty() || password.isNullOrEmpty()) {
                Toast.makeText(this, "Ingresar datos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            auth.signInWithEmailAndPassword(correo, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("exito", "signInWithEmail:success")
                        val user = auth.currentUser
                        var intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("error", "signInWithEmail:failure", task.exception)
                        Toast.makeText(
                            baseContext,
                            "Datos incorrectos",
                            Toast.LENGTH_SHORT,
                        ).show()

                    }
                }


        }


        tv_recuperacion.setOnClickListener {
            var intent = Intent(this, RecuepracionActivity::class.java)
            startActivity(intent)
        }

    }

}