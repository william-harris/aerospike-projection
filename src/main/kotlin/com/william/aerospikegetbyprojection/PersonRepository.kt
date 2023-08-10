package com.william.aerospikegetbyprojection


import org.springframework.data.aerospike.repository.AerospikeRepository

interface PersonRepository : AerospikeRepository<Person, Long> {
    fun findPersonNameById(id: Long): List<PersonName>
    fun findPersonNameByName(name: String): List<PersonName>
    fun <T> findByName(name: String, type: Class<T>): List<T>
    fun <T> findById(name: Long, type: Class<T>): List<T>
}


