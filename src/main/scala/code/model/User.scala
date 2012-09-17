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

  override def emailFrom = "***REMOVED***"

  /*
  override def signupXhtml(user: TheUserType) = {
    (<form class="form-vertical" method="post" action={S.uri}><legend>{ S.??("sign.up") }</legend>{localForm(user, false, signupFields)}<div class="control-group"><div class="controls"><user:submit/></div></div></form>)
  }

  override protected def localForm(user: TheUserType, ignorePassword: Boolean, fields: List[FieldPointerType]): NodeSeq = {
    for {
      pointer <- fields
      field <- computeFieldFromPointer(user, pointer).toList
      if field.show_? && (!ignorePassword || !pointer.isPasswordField_?)
      form <- field.toForm.toList
    } yield <div class="control-group"><label class="control-label">{field.displayName}</label><div class="controls">{form}</div></div>

  }
  */

}
