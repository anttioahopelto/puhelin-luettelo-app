import service.{PhoneBookWebService, WebServiceBuilder}

object Main extends App {
  val server = WebServiceBuilder.buildWebService(8080, classOf[PhoneBookWebService])
  server.start()
}
