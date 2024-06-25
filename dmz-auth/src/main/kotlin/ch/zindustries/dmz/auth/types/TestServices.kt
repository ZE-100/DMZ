package ch.zindustries.dmz.auth.types

enum class TestServices(
    private val serviceName: String,
    private val serviceId: Long,
) : ServiceType {
    TEST_SERVICE("testService", 0L);

    override fun serviceName() = this.serviceName

    override fun serviceId() = this.serviceId
}
