package code.snippet

import _root_.net.liftweb.util._
import _root_.net.liftweb.common._
import Helpers._
import net.liftweb.http.js.{JsCmd, JsCmds}
import xml.NodeSeq
import net.liftweb.http.js.JsCmds.Replace
import net.liftweb.http.{S, RequestVar, TemplateFinder, SHtml}
import code.model.{User, Order, Curry, Heat}
import net.liftweb.mapper.{By, By_>, Descending}

class CurrentOrders {
  val ordersCurrent = Order.findCurrent
  val ordersTakeaways = Order.findTakeaways

  def listCurr = ".orderCurr" #> rowsCurr & ClearClearable
  def listTake = ".orderTake" #> rowsTake & ClearClearable

  def rowsCurr: (NodeSeq => NodeSeq) = (ns: NodeSeq) => ordersCurrent.flatMap(row(_).apply(ns))
  def rowsTake: (NodeSeq => NodeSeq) = (ns: NodeSeq) => ordersTakeaways.flatMap(row(_).apply(ns))

  def row(o: Order) = ".name *" #> (if(o.orderFor.toString.isEmpty){User.find(By(User.id, o.user.is)).map(_.realName) openOr "?"} else {(User.find(By(User.id, o.user.is)).map(_.realName) openOr "?") + " (" + o.orderFor.toString +")"}) &
          ".curry *" #> (o.curry.obj.map(_.name.is) openOr "?") &
          ".heat *" #> o.heat &
          ".delete *" #> deleteButton (o) &
          ".order [id]" #> ("order" + o.id)

  def email = {
    ClearClearable &
      ".shop" #> Props.get("order.shop").openOr("") &
      ".numberSeating" #> ordersCurrent.size &
      listCurr &
      listTake
  }

  def deleteButton (o: Order):NodeSeq = {
      val currentUser = User.currentUser openOr null
      o.user.obj.filter(_ == currentUser) match {
        case Full(_) => SHtml.ajaxButton("Delete", () => {
          Order.delete_!(o)
          code.comet.OrderServer ! "deleted"
          S.notice("Order Deleted")
          JsCmds.Replace("order" + o.id, NodeSeq.Empty)
        })
        case _ => NodeSeq.Empty
      }


  }
}
