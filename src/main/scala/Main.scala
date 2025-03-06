import service.{WebService, WebServiceBuilder}
import utils.DBSetup

object Main extends App {
  val server = WebServiceBuilder.buildWebService(8080, classOf[WebService])
  server.start()

  val jdbcUrl = sys.env.getOrElse("DB_URL", "")
  val pgUsername = sys.env.getOrElse("DB_USERNAME", "")
  val pgPassword = sys.env.getOrElse("DB_PASSWORD", "")

  DBSetup.setup(jdbcUrl, pgUsername, pgPassword)
  server.join()
}
