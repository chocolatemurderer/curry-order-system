package code.model

import _root_.net.liftweb.mapper._
import _root_.net.liftweb.util._
import _root_.net.liftweb.common._

class Order extends LongKeyedMapper[Order] with IdPK{
def getSingleton=Order 
  object timeStamp extends MappedDateTime(this)
  object user extends LongMappedMapper(this, User) 
  object curry extends LongMappedMapper(this, Curry) 
  object heat extends MappedEnum(this, Heat)
}  

object Order extends Order with LongKeyedMetaMapper[Order] {
}

object Heat extends Enumeration{
  type Heat=Value
  val vMild = Value("Very Mild")
  val mildMedium = Value("Mild-Medium")
}
