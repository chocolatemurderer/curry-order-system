package code.model

import _root_.net.liftweb.mapper._
import _root_.net.liftweb.util._
import _root_.net.liftweb.common._
import java.math.MathContext

class Curry extends LongKeyedMapper[Curry] with IdPK{
  def getSingleton=Curry 
  object name extends MappedString(this, 64)
  object description extends MappedText(this)
  object picUrl extends MappedText(this)
  object price extends MappedDecimal(this, MathContext.DECIMAL64, 2)
  object deprecated extends MappedBoolean(this)
  def info = name.is + " ($" + price.is.toString + ")"
}  

object Curry extends Curry with LongKeyedMetaMapper[Curry] {
}


// vim: set ts=4 sw=4 et:
