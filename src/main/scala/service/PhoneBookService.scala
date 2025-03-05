package service

import dao.PhoneBookDAO
import org.json4s.{DefaultFormats, Formats}

case class Contact(id: Int, firstName: String, lastName: String, phoneNumber: String, email: Option[String], address: Option[String])

object Contact {
  implicit val formats: Formats = DefaultFormats
}


class PhoneBookService {

  def getAllContacts: Seq[Contact] = {
    val contacts = PhoneBookDAO.fetchAllContacts()
    println(s"Fetched ${contacts.size} contacts")
    contacts
  }

  def deleteContactsByIds(idsToDelete: Set[Int]): Unit = {
    val deletedContactCount = PhoneBookDAO.deleteByIds(idsToDelete)
    println(s"Deleted ${deletedContactCount} contacts from database")
  }

  def addContact(contact: Contact) = {
    ???
  }
}
