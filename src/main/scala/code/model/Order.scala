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
  def findCurrent = findAll(By_>(Order.timeStamp, new java.util.Date(new java.util.Date().getTime - (3*24*60*60*1000))))
}

object Heat extends Enumeration{
  type Heat=Value
  val veryMild = Value("Very Mild")
  val mild = Value("Mild")
  val mildMedium = Value("Mild-Medium")
  val medium = Value("Medium")
  val mediumHot = Value("Medium-Hot")
  val hot = Value("Hot")
  val veryHot = Value("Very-Hot")
  val extremelyHot = Value("Extremely-Hot")
  val challengeHot = Value("Challenge-Hot")
}
