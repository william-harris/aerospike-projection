package com.william.aerospikegetbyprojection

import com.aerospike.client.Host
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.data.aerospike.config.AbstractAerospikeDataConfiguration
import org.springframework.data.aerospike.repository.config.EnableAerospikeRepositories
import java.util.*

@Configuration
@EnableAerospikeRepositories(basePackages = ["com.william.aerospikegetbyprojection"])
class AerospikeConfig(
    @Value("\${aerospike.port}") private val port: Int
) : AbstractAerospikeDataConfiguration()
{
    override fun getHosts(): Collection<Host?>? {
        return Collections.singleton(Host("localhost", port))
    }

    override fun nameSpace(): String? {
        return "test"
    }
}