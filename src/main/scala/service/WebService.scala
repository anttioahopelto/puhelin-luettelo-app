package service

import org.json4s.jackson.Serialization
import org.scalatra._

class WebService extends ScalatraServlet {
  // Enable CORS headers for all responses
  before() {
    response.setHeader("Access-Control-Allow-Origin", "*")
    response.setHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, OPTIONS")
    response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization")
  }

  val phoneBookService = new PhoneBookService

  get("/contacts") {
    val contacts =  phoneBookService.getAllContacts
    val contactsJson = Serialization.write(contacts)(Contact.formats)
    println(contactsJson)
    contactsJson
  }
}
