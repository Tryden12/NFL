package com.tryden.simplenfl.domain.newmapper

/**
 * This is a generic mapper class utilized to map DTO model(s) to UI model(s).
 */
interface Mapper<T : Any, Model: Any> {

//    fun toModel(value: T): Model
//    fun fromModel(value: Model): T
    fun buildFrom(value: Model): T
}