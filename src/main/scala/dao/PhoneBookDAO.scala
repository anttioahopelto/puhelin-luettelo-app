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
}
