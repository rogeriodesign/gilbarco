package br.com.gilbarco.clientes.ui.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import br.com.gilbarco.clientes.MainActivity
import br.com.gilbarco.clientes.R
import br.com.gilbarco.clientes.ui.afterTextChanged
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var model: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

        setViewModel()
        setFields()
    }

    private fun setFields() {
        field_username.afterTextChanged {
            model.loginDataChanged(
                field_username.text.toString(),
                field_password.text.toString()
            )
        }

        field_password.apply {
            afterTextChanged {
                model.loginDataChanged(
                    field_username.text.toString(),
                    field_password.text.toString()
                )
            }

            setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE ->
                        model.login(
                            field_username.text.toString(),
                            field_password.text.toString()
                        )
                }
                false
            }

            btn_login.setOnClickListener {
                login_loading.visibility = View.VISIBLE
                model.login(field_username.text.toString(), field_password.text.toString())
            }
        }
    }

    private fun setViewModel() {
        model = ViewModelProvider(this, LoginViewModelFactory()).get(LoginViewModel::class.java)
        model.loginFormState.observe(this@LoginActivity, Observer {
            val loginState = it ?: return@Observer

            // disable login button unless both username / password is valid
            btn_login.isEnabled = loginState.isDataValid

            if (loginState.usernameError != null) {
                field_username.error = getString(loginState.usernameError)
            }
            if (loginState.passwordError != null) {
                field_password.error = getString(loginState.passwordError)
            }
        })

        model.loginResult.observe(this@LoginActivity, Observer {
            val loginResult = it ?: return@Observer

            login_loading.visibility = View.GONE
            if (loginResult.error != null) {
                showLoginFailed(loginResult.error)
            }
            if (loginResult.success != null) {
                updateUiWithUser(loginResult.success)
            }
            setResult(Activity.RESULT_OK)

            startAndFinish(MainActivity.callingIntent(this))
        })
    }

    private fun updateUiWithUser(model: LoggedInUserView) {
        val welcome = getString(R.string.welcome)
        val displayName = model.displayName

        Toast.makeText(
            applicationContext,
            "$welcome $displayName",
            Toast.LENGTH_LONG
        ).show()
    }

    private fun showLoginFailed(@StringRes errorString: Int) {
        Toast.makeText(applicationContext, errorString, Toast.LENGTH_SHORT).show()
    }

    private fun startAndFinish(intent: Intent) {
        startActivity(intent)
        finish()
    }
}


