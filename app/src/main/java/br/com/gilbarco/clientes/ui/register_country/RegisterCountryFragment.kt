package br.com.gilbarco.clientes.ui.register_country

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import br.com.gilbarco.clientes.R
import br.com.gilbarco.clientes.model.model.Country
import br.com.gilbarco.clientes.ui.RegisterCountryViewModelFactory
import br.com.gilbarco.clientes.ui.RegisterCountryViewModel
import br.com.gilbarco.clientes.ui.afterTextChanged
import br.com.gilbarco.clientes.ui.alert
import br.com.gilbarco.clientes.ui.validator.ValidatesDefault
import br.com.gilbarco.clientes.ui.validator.Validator
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.fragment_register_country.*
import kotlinx.android.synthetic.main.fragment_register_country.view.*

class RegisterCountryFragment : Fragment() {
    private val validators = ArrayList<Validator>()
    private lateinit var model: RegisterCountryViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_register_country, container, false)

        setViewModel()
        setFields(root)
        setBtRegister(root)
        return root
    }

    override fun onResume() {
        super.onResume()
        fillForm()
    }

    private fun setViewModel() {
        activity?.let {activity ->
            context?.let{
                model = ViewModelProvider(activity,
                    RegisterCountryViewModelFactory(it)
                ).get(RegisterCountryViewModel::class.java)
                model.init()
            }
        }
        model.saveResult.observe(viewLifecycleOwner, Observer {
            val resource = it ?: return@Observer

            if (resource.error == null) {
                fillForm()
                resource.data?.let{txt ->
                    alert("Sucesso", txt)
                }
            } else {
                alert("Falha", resource.error)
            }
        })
    }

    private fun fillForm() {
        model.getCountry().observe(viewLifecycleOwner, Observer { userFound ->
            if (userFound != null) {
                (activity_form_register_country_code as TextInputLayout).editText?.setText(userFound.code.toString())
                (activity_form_register_country_name as TextInputLayout).editText?.setText(userFound.name)
                (activity_form_register_country_description as TextInputLayout).editText?.setText(userFound.description)
            } else {
                (activity_form_register_country_code as TextInputLayout).editText?.setText("")
                (activity_form_register_country_name as TextInputLayout).editText?.setText("")
                (activity_form_register_country_description as TextInputLayout).editText?.setText("")
            }
        })
    }

    private fun setFields(root: View) {
        val code = root.activity_form_register_country_code as TextInputLayout
        val name = root.activity_form_register_country_name as TextInputLayout
        val description= root.activity_form_register_country_description as TextInputLayout
        addValidatorDefault(code)
        addValidatorDefault(name)
        addValidatorDefault(description)
        code.editText?.afterTextChanged {
            model.setCode(it)
        }
        name.editText?.afterTextChanged {
            model.setName(it)
        }
        description.editText?.afterTextChanged {
            model.setDescription(it)
        }
    }

    private fun setBtRegister(root: View) = root.activity_form_bt_register.setOnClickListener {
        val country = preparesToSave()
        model.country.postValue(country)
        if (isFieldsValid()) {
            save(country)
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

    private fun preparesToSave(): Country {
        val name = (activity_form_register_country_name as TextInputLayout).editText?.text.toString()
        val description = (activity_form_register_country_description as TextInputLayout).editText?.text.toString()
        val code = ((activity_form_register_country_code as TextInputLayout).editText?.text.toString()).toLong()
        return Country(code= code, name=name, description =description)
    }

    private fun isFieldsValid(): Boolean {
        var formStatusValid = true
        for (validador in validators) {
            if (!validador.statusValid()) {
                formStatusValid = false
            }
        }
        return formStatusValid
    }

    private fun save(country: Country) {
        model.save(country)
    }

}