package dao

import scalikejdbc.{DB, scalikejdbcSQLInterpolationImplicitDef}
import service.Contact

object PhoneBookDAO {

  def fetchAllContacts(): List[Contact] = {
    DB.readOnly { implicit session =>
      sql"SELECT * FROM contacts".map(rs => Contact(
        rs.int("id"),
        rs.string("first_name"),
        rs.string("last_name"),
        rs.string("phone_number"),
        rs.stringOpt("email"),
        rs.stringOpt("address")
      )).list.apply()
    }
  }

  def deleteByIds(ids: Set[Int]): Int = {
    val deletedRowCount = DB.localTx { implicit session =>
      sql"""
        DELETE FROM contacts
        WHERE id IN (${ids.toSeq})
      """.update.apply()
    }

    deletedRowCount
  }

  def insertContact(contact: Contact): Int = {
    DB.localTx { implicit session =>
      val id = sql"""
        INSERT INTO contacts (first_name, last_name, phone_number, email, address)
        VALUES (${contact.firstName}, ${contact.lastName}, ${contact.phoneNumber}, ${contact.email}, ${contact.address})
      """.updateAndReturnGeneratedKey.apply().toInt
      id
    }
  }
}
