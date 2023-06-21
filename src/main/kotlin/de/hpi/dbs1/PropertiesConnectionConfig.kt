package de.hpi.dbs1

import java.io.File
import java.util.Properties

class PropertiesConnectionConfig(file: File) : ConnectionConfig {
    val properties = Properties()

    init {
        file.inputStream().use { properties.load(it) }
    }

    override fun getHost(): String = properties.getProperty("host")
    override fun getPort(): Int = properties.getProperty("port").toInt()
    override fun getDatabase(): String = properties.getProperty("database")
    override fun getUsername(): String = properties.getProperty("username")
    override fun getPassword(): String = properties.getProperty("password")
}
