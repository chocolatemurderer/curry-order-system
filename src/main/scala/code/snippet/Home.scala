/*
 * Copyright (c) 2013 Curry Order System authors.
 * This file is part of Curry Order System. Please refer to the NOTICE.txt file for license details.
 */

package code.snippet

import net.liftweb.util._
import net.liftweb.util.Helpers._

class Home {
  def render = {
    ".shop" #> Props.get("order.shop").openOr("Shop")
  }
}
