package code 
package model 

import _root_.net.liftweb.http._
import _root_.net.liftweb.mapper._
import _root_.net.liftweb.util._
import _root_.net.liftweb.common._

class User extends LongKeyedMapper[User] with IdPK{
  def getSingleton=User
  object realName extends MappedString(this, 64)
  object email extends MappedEmail(this, 64) {
    override def apply(s: String) = super.apply(s.toLowerCase)
    override def apply(b: Box[String]) = super.apply(b map { _.toLowerCase })
    override def validations = valUnique(S.??("unique.email.address")) _ :: super.validations // Doesn't seem to work.
  }

}  

object User extends User with LongKeyedMetaMapper[User] {
}
