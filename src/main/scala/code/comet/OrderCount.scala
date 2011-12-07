package code.comet

import net.liftweb._
import http._
import actor._
import util._
import Helpers._
import code.model.Order
import xml.{Text, NodeSeq}

object OrderServer extends LiftActor with ListenerManager {

  def createUpdate = "update"

  override def lowPriority = {
    case _ => {
      updateListeners()
    }
  }
}

class OrderCount extends CometActor with CometListener {

  def registerWith = OrderServer

  override def lowPriority = {
    case _ => {
      reRender()
    }
  }

  def render = {
    ".count *" #> orderInfo() &
      ClearClearable
  }

  private def orderInfo(): NodeSeq = {
    def countBlock(description: String)(count: Int): NodeSeq = {
      <span>{ count + " " + description }</span>
    }
    val dineIn = countBlock("dine-in") _
    val takeAway = countBlock("takeaway") _

    (Order.findCurrent, Order.findTakeaways) match {
      case (Nil, Nil) => {
        <span>Nothing</span>
      }
      case (in, Nil) => {
        dineIn(in.size)
      }
      case (Nil, out) => {
        takeAway(out.size)
      }
      case (in, out) => {
        dineIn(in.size) ++ <span>&nbsp;+&nbsp;</span> ++ takeAway(out.size)
      }
    }
  }
}
