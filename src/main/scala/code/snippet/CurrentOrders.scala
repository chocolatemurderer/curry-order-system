package code.snippet

import _root_.net.liftweb.util._
import _root_.net.liftweb.common._
import Helpers._
import net.liftweb.http.js.{JsCmd, JsCmds}
import xml.NodeSeq
import net.liftweb.http.js.JsCmds.Replace
import code.model.{Order, Curry, Heat}
import net.liftweb.http.{S, RequestVar, TemplateFinder, SHtml}
import net.liftweb.mapper.Descending

class CurrentOrders {
  val orders = Order.findAll() 

  def list = ".order" #> rows & ClearClearable
//  def order: (NodeSeq => NodeSeq) = ".orders" #>  & ClearClearable

  def rows: (NodeSeq => NodeSeq) = (ns: NodeSeq) => orders.flatMap(row(_).apply(ns))

  def row(o: Order) = ".name *" #> o.user.is &
          ".curry *" #> (o.curry.obj.map(_.name.is) openOr "?") &
          ".heat *" #> o.heat
}
