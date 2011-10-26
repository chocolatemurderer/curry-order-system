package code
package model

import net.liftweb.mapper._
import net.liftweb.util._
import net.liftweb.common._

class User extends MegaProtoUser[User] {
  def getSingleton = User
  def realName = shortName
  override lazy val email = new MyEmail(this, 50)
  def isSuper = {
    (email == "***REMOVED***" || email == "***REMOVED***" || email == "***REMOVED***")
  }
}

object User extends User with MetaMegaProtoUser[User] {
  override def dbTableName = "users"
  override def screenWrap = Full(<lift:surround with="default" at="content">
                                <lift:bind /></lift:surround>)
   override def fieldOrder = List(id, firstName, lastName, email, locale, timezone, password)
   override def skipEmailValidation = true

  override val basePath: List[String] = "user" :: Nil
}
