package com.idevelopstudio.whatson.login

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.idevelopstudio.whatson.R
import com.idevelopstudio.whatson.databinding.ActivityLoginBinding
import com.idevelopstudio.whatson.main.MainActivity
import com.idevelopstudio.whatson.onBoarding.OnBoardingActivity
import timber.log.Timber

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var callbackManager: CallbackManager

    private lateinit var binding : ActivityLoginBinding
    private lateinit var sharedPreferences: SharedPreferences

    private val viewModel: LoginViewModel by lazy {
        ViewModelProvider(this).get(LoginViewModel::class.java)
    }

    companion object{
        private const val RC_SIGN_IN = 9001
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar!!.hide()

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        auth = FirebaseAuth.getInstance()

        binding.googleLoginButton.setOnClickListener {
            signInGoogle()
        }
        binding.facebookLoginButton.setOnClickListener {
            signInWithFacebook()
        }

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        sharedPreferences = applicationContext.getSharedPreferences(getString(R.string.SHARED_PREF_KEY), Context.MODE_PRIVATE)

        googleSignInClient = GoogleSignIn.getClient(applicationContext, gso)
        callbackManager = CallbackManager.Factory.create()

        viewModel.response.observe(this, Observer {
            Timber.d(it.message)
            goToMainActivity()
            hideLoading()
        })
    }

    private fun signInWithFacebook(){
        showLoading()
        LoginManager.getInstance().logInWithReadPermissions(this, listOf("email", "public_profile"))
        LoginManager.getInstance().registerCallback(callbackManager, object :
            FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {
                Timber.d("facebook:onSuccess:$loginResult")
                handleFacebookAccessToken(loginResult.accessToken)
            }

            override fun onCancel() {
                Timber.d("facebook:onCancel")
                // ...
                hideLoading()
            }

            override fun onError(error: FacebookException) {
                Timber.d(error, "facebook:onError")
                // ...
                hideLoading()
            }
        })
    }
    private fun signInGoogle() {
        showLoading()
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser: FirebaseUser? = auth.currentUser
        currentUser?.let{
            Timber.d("Current User: ${it.email}")
            goToMainActivity()
        }
    }

    private fun goToMainActivity(){
        val isNewUser = sharedPreferences.getInt(getString(R.string.SHARED_PREF_NEW_USER), 1)
        if (isNewUser == 0 ){
            val intent = Intent(applicationContext, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }else{
            val intent = Intent(applicationContext, OnBoardingActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)
                firebaseAuthWithGoogle(account!!)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Timber.w("Google Sign in Failed ${e}")
                // ...
            }
        }else{
            callbackManager.onActivityResult(requestCode, resultCode, data)
        }
    }

    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {
        Timber.d("firebaseAuthWithGoogle:%s", acct.id!!)
        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener() { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Timber.d("signInWithCredential:success")
                    viewModel.createUser(auth.currentUser!!)
                } else {
                    // If sign in fails, display a message to the user.
                    hideLoading()
                    Timber.w(task.exception, "signInWithCredential:failure")
                    Snackbar.make(binding.mainLayout, "Authentication Failed.", Snackbar.LENGTH_SHORT)
                        .show()
                }
            }
    }

    private fun handleFacebookAccessToken(token: AccessToken) {
        Timber.d("handleFacebookAccessToken:$token")

        val credential = FacebookAuthProvider.getCredential(token.token)
        auth.signInWithCredential(credential)
            .addOnCompleteListener() { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Timber.d("signInWithCredential:success")
                    viewModel.createUser(auth.currentUser!!)
                } else {
                    // If sign in fails, display a message to the user.
                    Timber.w(task.exception, "signInWithCredential:failure")
                    Snackbar.make(binding.mainLayout, "Authentication Failed.", Snackbar.LENGTH_SHORT)
                        .show()
                    hideLoading()
                }
            }
    }

    private fun showLoading(){
        binding.progressCircular.visibility = View.VISIBLE
        binding.buttonLayout.visibility = View.INVISIBLE
    }


    private fun hideLoading(){
        binding.progressCircular.visibility = View.INVISIBLE
        binding.buttonLayout.visibility = View.VISIBLE
    }

}
