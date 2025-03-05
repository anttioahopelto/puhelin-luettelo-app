import service.{WebService, WebServiceBuilder}
import utils.DBSetup

object Main extends App {
  val server = WebServiceBuilder.buildWebService(8080, classOf[WebService])
  server.start()

  val jdbcUrl = ""
  val pgUsername = ""
  val pgPassword = ""
  DBSetup.setup(jdbcUrl, pgUsername, pgPassword)
}
