package code
package model

import net.liftweb.mapper._
import net.liftweb.util._
import net.liftweb.common._
import net.liftweb.http._
import scala.xml._

class User extends MegaProtoUser[User] {
  def getSingleton = User
  def realName = shortName
  override lazy val email = new MyEmail(this, 50)
  def isSuper: Boolean = {
    // Previously this was hardcoded by email. Now db lookup.
    superUser.is
  }
}

object User extends User with MetaMegaProtoUser[User] {
  override def dbTableName = "users"
  override def screenWrap = Full(<lift:surround with="default" at="content">
                                <lift:bind /></lift:surround>)
   override def fieldOrder = List(id, firstName, lastName, email, locale, timezone, password)
   override def skipEmailValidation = true

  override val basePath: List[String] = "user" :: Nil

  override def emailFrom = Props.get("server.from").openOr("root@localhost")
}
