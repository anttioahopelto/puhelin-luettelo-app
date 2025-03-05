package utils

import scalikejdbc._

object DBSetup {
  def setup(jdbcurl: String, user: String, password: String): Unit = {
    // Set up the JDBC driver & connection pool
    Class.forName("org.postgresql.Driver")
    ConnectionPool.singleton(jdbcurl, user, password)

    // Set SQL logging
    GlobalSettings.loggingSQLAndTime = LoggingSQLAndTimeSettings(
      enabled = true,
      singleLineMode = true,
      logLevel = 'debug
    )
  }
}

