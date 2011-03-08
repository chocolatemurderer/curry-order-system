package code 
package model 

import _root_.net.liftweb.http._
import _root_.net.liftweb.mapper._
import _root_.net.liftweb.util._
import _root_.net.liftweb.common._
import xml.NodeSeq

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
