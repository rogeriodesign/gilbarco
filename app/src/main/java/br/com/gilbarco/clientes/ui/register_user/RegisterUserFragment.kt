package br.com.gilbarco.clientes.ui.register_user

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import br.com.gilbarco.clientes.R
import br.com.gilbarco.clientes.model.model.User
import br.com.gilbarco.clientes.presenter.UserPresenter
import br.com.gilbarco.clientes.ui.alert
import br.com.gilbarco.clientes.ui.validator.CNPJormatter
import br.com.gilbarco.clientes.ui.validator.ValidatesCnpj
import br.com.gilbarco.clientes.ui.validator.ValidatesDefault
import br.com.gilbarco.clientes.ui.validator.Validator
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.fragment_register_user.*
import kotlinx.android.synthetic.main.fragment_register_user.view.*
import br.com.gilbarco.clientes.ui.afterTextChanged


class RegisterUserFragment : Fragment(), RegisterUserContract.ViewImpl {
    private val validators = ArrayList<Validator>()
    private lateinit var model: RegisterUserViewModel
    private lateinit var presenter: UserPresenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_register_user, container, false)
        context?.let {
            presenter = UserPresenter(it)
        }

        setViewModel()
        setFields(root)
        setBtCountry(root)
        setBtRegister(root)
        return root
    }

    override fun onResume() {
        super.onResume()
        fillForm()
    }

    private fun setViewModel() {
        activity?.let {
            model = ViewModelProvider(it, RegisterUserViewModelFactory()).get(RegisterUserViewModel::class.java)
        }
    }

    private fun fillForm() {
        Log.i("view user", "preenchendo o form")
        model.getUser().observe(viewLifecycleOwner, Observer { userFound ->
            if (userFound != null) {
                (activity_form_register_user_code as TextInputLayout).editText?.setText(userFound.code.toString())
                (activity_form_register_user_name as TextInputLayout).editText?.setText(userFound.name)
                (activity_form_register_user_cnpj as TextInputLayout).editText?.setText(userFound.cnpj)
            } else {
                (activity_form_register_user_code as TextInputLayout).editText?.setText("")
                (activity_form_register_user_name as TextInputLayout).editText?.setText("")
                (activity_form_register_user_cnpj as TextInputLayout).editText?.setText("")
            }
        })
        model.getCountrySelected().observe(viewLifecycleOwner, Observer { countryFound ->
            Log.i("view user", "pegou país")
            if (countryFound != null) {
                (activity_form_register_user_country as TextView).text = countryFound.name
            } else {
                (activity_form_register_user_country as TextView).text = ""
            }
        })
    }

    private fun setFields(root: View) {
        val code = root.activity_form_register_user_code as TextInputLayout
        val name = root.activity_form_register_user_name as TextInputLayout
        val cnpj = root.activity_form_register_user_cnpj as TextInputLayout
        addValidatorDefault(code)
        addValidatorDefault(name)
        addValidatorCnpj(cnpj)

        code.editText?.afterTextChanged {
            model.setCode(it)
            Log.i("registro usuario", model.getUser().toString())
            Log.i("registro usuario", model.getUser().value.toString())
        }
        name.editText?.afterTextChanged {
            model.setName(it)
            Log.i("registro usuario", model.getUser().value.toString())
        }
        cnpj.editText?.afterTextChanged {
            model.setCnpj(it)
            Log.i("registro usuario", model.getUser().value.toString())
        }
    }

    private fun setBtCountry(root: View) = root.fragment_form_bt_country.setOnClickListener {
        findNavController().navigate(R.id.action_nav_add_user_to_nav_list_countries)
    }

    private fun setBtRegister(root: View) = root.fragment_form_bt_register.setOnClickListener {
        val user = preparesToSave()
        model.user.postValue(user)
        if (isFieldsValid()) {
            save(user)
        }
    }

    private fun addValidatorDefault(textInputField: TextInputLayout) {
        val field = textInputField.editText
        val validator = ValidatesDefault(textInputField)
        validators.add(validator)
        field?.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                validator.statusValid()
            }
        }
    }

    private fun addValidatorCnpj(textInputField: TextInputLayout) {
        val field = textInputField.editText
        val formatter = CNPJormatter()
        val validator = ValidatesCnpj(textInputField)
        validators.add(validator)
        field?.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                removeFormat(formatter, field!!)
            } else {
                validator.statusValid()
            }
        }
    }

    private fun removeFormat(formatador: CNPJormatter, campoCnpj: EditText) {
        val cnpj = campoCnpj.text.toString()
        try {
            val cnpjNoFormat = formatador.unformat(cnpj)
            campoCnpj.setText(cnpjNoFormat)
        } catch (e: IllegalArgumentException) {
        }
    }

    private fun preparesToSave(): User {
        val name = (activity_form_register_user_name as TextInputLayout).editText?.text.toString()
        val cnpj = (activity_form_register_user_cnpj as TextInputLayout).editText?.text.toString()
        val code = ((activity_form_register_user_code as TextInputLayout).editText?.text.toString()).toLong()
        return User(model.getUser().value?.id, code, name, cnpj, model.getCountrySelected().value?.id)
    }

    private fun isFieldsValid(): Boolean {        
        var formStatusValid = true
        for (validador in validators) {
            if (!validador.statusValid()) {
                formStatusValid = false
            }
        }
        Log.i("view user", "retorno do validador " + formStatusValid)
        return formStatusValid
    }

    private fun save(user: User) {
        presenter.save(user).observe(viewLifecycleOwner, Observer {
            if (it.error == null) {
                Log.i("registro pais", "gravou com sucesso")
                model.user.postValue(null)
                fillForm()
                alert("Sucesso", "Cliente ${user.name} gravado com sucesso")
            } else {
                Log.i("registro pais", "gravou com erro "+ it.error)
                alert("Falha", "Não foi possivél gravar o cliente ${user.name}.\n ${it.error}")
            }
        })
    }
}