package service

import org.json4s.{DefaultFormats, Formats}
import org.json4s.jackson.Serialization
import org.scalatra._
import org.scalatra.json.JacksonJsonSupport

case class DeleteRequest(ids: Set[Int])
object DeleteRequest {
  implicit val formats: Formats = DefaultFormats
}

class WebService extends ScalatraServlet with JacksonJsonSupport {
  // Enable CORS headers for all responses
  before() {
    response.setHeader("Access-Control-Allow-Origin", "*")
    response.setHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, OPTIONS")
    response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization")
  }

  options("/*") {
    response.setHeader("Access-Control-Allow-Origin", "*")
    response.setHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, OPTIONS")
    response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization")
  }

  // Enable JSON requests and responses
  protected implicit val jsonFormats: Formats = DefaultFormats

  val phoneBookService = new PhoneBookService

  get("/contacts") {
    println("Received request to /contacts")
    try {
      val contacts = phoneBookService.getAllContacts
      val contactsJson = Serialization.write(contacts)(Contact.formats)
      contactsJson
    } catch {
      case e: Exception =>
        println(e.getMessage)
        halt(500, "Internal Server Error")
    }
  }

  post("/deleteByIds") {
    println("Received request to /deleteByIds")
    try {
      val deleteRequest = parsedBody.extract[DeleteRequest]
      val deletedCount = phoneBookService.deleteContactsByIds(deleteRequest.ids)
      val responseJson = Serialization.write(Map("deletedCount" -> deletedCount))
      responseJson
    } catch {
      case e: Exception =>
        println(e.getMessage)
        halt(500, "Internal Server Error")
    }
  }

  post("/addContact") {
    println("Received request to /addContact")
    try {
      val contact = parsedBody.extract[IncomingContact]
      val newContactId = phoneBookService.addContact(contact)
      val responseJson = Serialization.write(Map("newContactId" -> newContactId))
      responseJson
    } catch {
      case e: Exception =>
        println(e.getMessage)
        halt(500, "Internal Server Error")
    }
  }
}
