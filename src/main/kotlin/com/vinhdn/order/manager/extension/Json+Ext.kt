package com.vinhdn.order.manager.extension

import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider
import org.springframework.http.converter.json.MappingJacksonValue

fun Any.filter(id: String, vararg propertyFilter: String): MappingJacksonValue {
    val simpleBeanPropertyFilter = SimpleBeanPropertyFilter.serializeAllExcept(*propertyFilter)
    val filterProvider =  SimpleFilterProvider().addFilter(id, simpleBeanPropertyFilter)
    val mappingJacksonValue = MappingJacksonValue(this)
    mappingJacksonValue.filters = filterProvider
    return mappingJacksonValue
}