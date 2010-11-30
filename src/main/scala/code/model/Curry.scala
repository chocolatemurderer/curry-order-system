package code.model

import _root_.net.liftweb.mapper._
import _root_.net.liftweb.util._
import _root_.net.liftweb.common._

class Curry extends LongKeyedMapper[Curry] with IdPK{
  def getSingleton=Curry 
  object name extends MappedString(this, 64)
  object description extends MappedText(this)
  object picUrl extends MappedText(this)

}  

object Curry extends Curry with LongKeyedMetaMapper[Curry] {
}


// vim: set ts=4 sw=4 et:
