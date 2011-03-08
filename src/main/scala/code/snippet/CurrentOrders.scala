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
  val orders = Order.findCurrent
  
  def list = ".order" #> rows & ClearClearable
//  def order: (NodeSeq => NodeSeq) = ".orders" #>  & ClearClearable

  def rows: (NodeSeq => NodeSeq) = (ns: NodeSeq) => orders.flatMap(row(_).apply(ns))

  def row(o: Order) = ".name *" #> (User.find(By(User.id, o.user.is)).map(_.realName) openOr "?") &
          ".curry *" #> (o.curry.obj.map(_.name.is) openOr "?") &
          ".heat *" #> o.heat &
          ".takeAway *" #> {if(o.takeAway){"Take Away"} else {""}} &
          ".delete *" #> deleteButton (o) &
          ".order [id]" #> ("order" + o.id)

  def email = ".numberSeating" #> orders.size & ClearClearable & list

  def deleteButton (o: Order):NodeSeq = {
      val currentUser = User.currentUser openOr null
      o.user.obj.filter(_ == currentUser) match {
        case Full(_) => SHtml.ajaxButton("Delete", () =>{Order.delete_!(o)
          code.comet.OrderServer ! "deleted"
          S.notice("Order Deleted")
          JsCmds.Replace("order" + o.id, NodeSeq.Empty)

        })
        case _ => NodeSeq.Empty
      }


  }
}
