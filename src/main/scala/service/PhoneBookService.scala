package service

case class Contact(
                    id: Int,
                    firstName: String,
                    lastName: String,
                    phoneNumber: String,
                    email: String,
                    address: String
                  )


class PhoneBookService {

  def getAllContacts(): Seq[Contact] = {
    ???
  }

  def deleteContactsByIds(idsToDelete: Set[Int]) = {
    ???
  }

  def addContact(contact: Contact) = {
    ???
  }
}
