import service.{WebService, WebServiceBuilder}

object Main extends App {
  val server = WebServiceBuilder.buildWebService(8080, classOf[WebService])
  server.start()
}
