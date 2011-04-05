package code.comet

import net.liftweb._
import http._
import actor._
import util._
import Helpers._
import code.model.Order

object OrderServer extends LiftActor with ListenerManager {
  def createUpdate = "update" 
  override def lowPriority = {
    case _ => 
      updateListeners()
  }
}

class OrderCount extends CometActor with CometListener {
  def registerWith = OrderServer
  override def lowPriority = {
    case _ => 
      reRender()
  }
  def render = ".count *" #> (count + " orders") & ClearClearable
  private def count = Order.findAllOrders.size
}
