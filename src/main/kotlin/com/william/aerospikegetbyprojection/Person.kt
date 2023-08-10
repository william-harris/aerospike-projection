package com.william.aerospikegetbyprojection

import com.aerospike.client.query.IndexType
import org.springframework.data.aerospike.annotation.Indexed
import org.springframework.data.aerospike.mapping.Document
import org.springframework.data.aerospike.mapping.Field
import org.springframework.data.annotation.Id


@Document
data class Person(
    @Id @Field @Indexed(type = IndexType.NUMERIC) var id: Long?,
    @Field @Indexed(type = IndexType.STRING) var name: String?,
    var age: Int?
) {

}