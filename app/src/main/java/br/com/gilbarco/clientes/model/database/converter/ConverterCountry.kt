package br.com.gilbarco.clientes.model.database.converter

import androidx.room.TypeConverter
import br.com.gilbarco.clientes.model.model.Country

class ConverterCountry {
    @TypeConverter
    fun toLong(country: Country): Long? {
        return country.id
    }

    @TypeConverter
    fun toCountry(countryId: Long?): Country {
        return Country(countryId, 0, "", "")
    }
}
