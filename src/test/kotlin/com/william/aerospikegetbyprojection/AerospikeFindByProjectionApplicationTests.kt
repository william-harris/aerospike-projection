package com.william.aerospikegetbyprojection

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.testcontainers.containers.GenericContainer
import org.testcontainers.containers.wait.strategy.Wait
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import org.testcontainers.utility.DockerImageName
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource


@SpringBootTest
@Testcontainers
class AerospikeFindByProjectionApplicationTests {

    @Autowired
    lateinit var personRepository: PersonRepository

    @BeforeEach
    fun purge() {
        personRepository.deleteAll()
    }

    @Test
    fun findByNameDynamicProjection() {
        val person = Person(1, "John",  40)
        personRepository.save(person)

        val firstName = personRepository.findByName("John", PersonName::class.java)
        assertEquals(firstName.first(), PersonName("John"))
    }

    @Test
    fun findByIdDynamicProjection() {
        val person = Person(1, "John",  40)
        personRepository.save(person)

        val firstName = personRepository.findById(1, PersonName::class.java)
        assertEquals(firstName.first(), PersonName("John"))
    }

    @Test
    fun findByNameDTOProjection() {
        val person = Person(1, "John",  40)
        personRepository.save(person)

        val firstName = personRepository.findPersonNameByName("John")
        assertEquals(firstName.first(), PersonName("John"))
    }

    @Test
    fun findByIdDTOProjection() {
        val person = Person(1, "John",  40)
        personRepository.save(person)

        val firstName = personRepository.findPersonNameById(1)
        assertEquals(firstName.first(), PersonName("John"))
    }

    companion object {
        @Container
        private val aerospike = GenericContainer(DockerImageName.parse("aerospike/aerospike-server:6.4.0.0"))
                .withExposedPorts(3000, 3001, 3002)
                .waitingFor(Wait.forLogMessage(".*migrations: complete.*", 2))

        @JvmStatic
        @DynamicPropertySource
        fun registerAerospikeProperties(registry: DynamicPropertyRegistry) {
            registry.add("aerospike.port") { aerospike.getMappedPort(3000) }
        }
    }
}
