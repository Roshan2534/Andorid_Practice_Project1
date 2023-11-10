package com.example.androidassessment1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.MenuItem
import android.view.WindowInsetsAnimationController
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.appbar.MaterialToolbar
import java.util.regex.Pattern

private const val TAG = "SignUp"
class SignUp : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val emailEditText: EditText = findViewById(R.id.emailEditText)
        val validationMessage: TextView = findViewById(R.id.validationMessageTextView)

        val firstpass:EditText = findViewById(R.id.passwordEditText)
        val passValidMessage: TextView = findViewById(R.id.validationPasswordTextView)

        val rpass: EditText = findViewById(R.id.rpasswordEditText)
        val rpassmessage: TextView = findViewById(R.id.rvalidationPasswordTextView)

        val submitButton: Button = findViewById(R.id.nextbutton)

        val toolbar: MaterialToolbar = findViewById(R.id.toolbar)
        toolbar.title = "Create an account"
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)



        toolbar.setNavigationOnClickListener{
            onBackPressed()
        }

        submitButton.isEnabled = false
        submitButton.alpha = 0.5f

        validationMessage.visibility = TextView.GONE
        passValidMessage.visibility = TextView.GONE
        rpassmessage.visibility = TextView.GONE

        var emailvalid = false
        var passvalid = false
        var rpassvalid = false

        fun updateButtonState() {
            if (emailvalid && passvalid && rpassvalid) {
                submitButton.isEnabled = true
                submitButton.alpha = 1.0f

            } else {
                submitButton.isEnabled = false
                submitButton.alpha = 0.5f
            }
        }

        submitButton.setOnClickListener {
            val toast = Toast.makeText(applicationContext, "Thank you for Sign up!", Toast.LENGTH_SHORT)
            toast.show()
        }



        emailEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                emailvalid = validateEmail(s.toString())
                updateButtonState()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        firstpass.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                passvalid = validatePass(s.toString())
                updateButtonState()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        })

        rpass.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                rpassvalid = rvalidatePass(s.toString())

                val firstpassword: EditText = findViewById(R.id.passwordEditText)


                if (rpassvalid && (firstpassword.text.toString() == rpass.text.toString())){
                    rpass.setBackgroundResource(R.drawable.edit_text_background)

                    val validDrawable = resources.getDrawable(R.drawable.tick)
                    rpass.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, validDrawable, null)

                    rpassmessage.visibility = TextView.GONE
                }
                else {
                    rpass.setBackgroundResource(R.drawable.edit_text_background_invalid)
                    rpass.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, null, null) // Set drawable to null
                    rpassmessage.visibility = TextView.VISIBLE
                    rpassmessage.text = "Passwords don't match"
                }

                rpass.setTag(R.id.rpasswordEditText, rpassvalid)
                updateButtonState()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        })


    }

    private fun validateEmail(email: String): Boolean{
        val emailPattern = Pattern.compile("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")

        val isValid = emailPattern.matcher(email).matches()

        val emailEditText: EditText = findViewById(R.id.emailEditText)
        val validationMessage: TextView = findViewById(R.id.validationMessageTextView)
        

        if (isValid){
            emailEditText.setBackgroundResource(R.drawable.edit_text_background)

            val validDrawable = resources.getDrawable(R.drawable.tick)
            emailEditText.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, validDrawable, null)

            validationMessage.visibility = TextView.GONE
        } else {
            emailEditText.setBackgroundResource(R.drawable.edit_text_background_invalid)
            emailEditText.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, null, null)
            validationMessage.visibility = TextView.VISIBLE
            validationMessage.text = "Invalid Email entered"
        }

        emailEditText.setTag(R.id.emailEditText, isValid)
        return isValid

    }


    private fun validatePass(password: String) : Boolean{
        val emailPattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@\$!%*?&])[A-Za-z\\d@\$!%*?&]{8,}\$")

        val isValid = emailPattern.matcher(password).matches()

        val passwordEditText: EditText = findViewById(R.id.passwordEditText)
        val passwordvalidationMessage: TextView = findViewById(R.id.validationPasswordTextView)


        if (isValid){
            passwordEditText.setBackgroundResource(R.drawable.edit_text_background)

            val validDrawable = resources.getDrawable(R.drawable.tick)
            passwordEditText.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, validDrawable, null)

            passwordvalidationMessage.visibility = TextView.GONE
        } else {
            passwordEditText.setBackgroundResource(R.drawable.edit_text_background_invalid)
            passwordEditText.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, null, null)
            passwordvalidationMessage.visibility = TextView.VISIBLE
            passwordvalidationMessage.text = "Invalid Password, see the criteria below"
        }

        passwordEditText.setTag(R.id.passwordEditText, isValid)
        return isValid

    }

    private fun rvalidatePass(password: String): Boolean{
        val rpasspattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@\$!%*?&])[A-Za-z\\d@\$!%*?&]{8,}\$")

        val isValid = rpasspattern.matcher(password).matches()

        return isValid

    }


    }


