package code.snippet

import net.liftweb.util._
import net.liftweb.util.Helpers._

class Home {
  def render = {
    ".shop" #> Props.get("order.shop").openOr("Shop")
  }
}
