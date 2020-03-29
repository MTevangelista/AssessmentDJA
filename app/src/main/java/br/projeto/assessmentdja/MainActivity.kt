package br.projeto.assessmentdja

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    var mCallbackManager = CallbackManager.Factory.create()
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setListeners()
        mAuth = FirebaseAuth.getInstance()

        login_button.setReadPermissions("email", "public_profile")
        login_button.registerCallback(mCallbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {
                handleFacebookAccessToken(loginResult.accessToken)
//                startActivity(Intent(baseContext, PrincipalActivity::class.java))
            }

            override fun onCancel() {}

            override fun onError(error: FacebookException) {}
        })
    }

    override fun onClick(view: View) {
        var btnId = view.id

        when (btnId){
            R.id.button_login -> startActivity(Intent(baseContext, LoginActivity::class.java))
            R.id.button_cadastro -> startActivity(Intent(baseContext, CadastroActivity::class.java))
        }
    }

    private fun setListeners(){
        button_cadastro.setOnClickListener(this)
        button_login.setOnClickListener(this)
    }

    private fun handleFacebookAccessToken(token: AccessToken) {
        val credential = FacebookAuthProvider.getCredential(token.token)

        mAuth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = mAuth.currentUser
                    startActivity(Intent(baseContext, PrincipalActivity::class.java))
                } else {
                    showToast("Autenticação falhou")
                }
            }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Pass the activity result back to the Facebook SDK
        mCallbackManager.onActivityResult(requestCode, resultCode, data)
    }

    private fun showToast(string: String){
        Toast.makeText(baseContext, string, Toast.LENGTH_LONG).show()
    }
}
