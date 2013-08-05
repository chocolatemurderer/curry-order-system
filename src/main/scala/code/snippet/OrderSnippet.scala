/*
 * Copyright (c) 2013 Curry Order System authors.
 * This file is part of Curry Order System. Please refer to the NOTICE.txt file for license details.
 */

package code.snippet

import _root_.net.liftweb.util._
import _root_.net.liftweb.common._
import Helpers._
import net.liftweb.http.js.{JsCmd, JsCmds}
import xml.NodeSeq
import net.liftweb.http.js.JsCmds.SetHtml
import net.liftweb.http.{S, RequestVar, TemplateFinder, SHtml}
import code.model.{User, Order, Curry, Heat}
import java.util.Calendar

class OrderSnippet {
  val curryList = Curry.findAll.filterNot(_.deprecated.is).sortWith( (a,b) => {
     if (a.price.is == b.price.is) {
        a.name.is.compareTo(b.name.is) < 0
     }
     else {
        a.price.is.compare(b.price.is) < 0
     }
  })
  var ta = false
  var rf = false
  var orderedFor = "";
  object heat extends RequestVar[Heat.Heat](Heat.mildMedium)
  object dish extends net.liftweb.http.SessionVar[Box[Curry]](Full(curryList.head))

  def order = ".hotness" #> SHtml.select(Heat.values.toSeq.map(e => (e.toString, e.toString)), Full(heat.is.toString), (s: String) => heat(Heat.withName(s))) &
          ".dish" #> SHtml.ajaxSelect(curryList.toSeq.map(c => (c.id.is.toString, c.info)), dish.is.map(_.id.is.toString), updateDescription _) &
          ".takeAway" #> SHtml.ajaxCheckbox(false, getTakeAway _) &
          ".orderFor" #> SHtml.ajaxText("", getOrderForPerson _) &
          ".sendOrder" #> SHtml.submit("Submit Order", processOrder _) & description(dish.is)

  def getTakeAway(b: Boolean) = {
    ta = b
    JsCmds.Noop
  }

  def getOrderFor(b: Boolean) = {
    rf = b
    JsCmds.Noop
  }

  def getOrderForPerson(s: String) = {
    orderedFor = s
    JsCmds.Noop

  }


  def updateDescription(s: String): JsCmd = {
    Curry.find(s) match {
      case Full(aCurry) =>
        dish(Full(aCurry))
        val result = description(Full(aCurry)).apply(descriptionPart)
        SetHtml("descriptionTable", result)
      case _ => JsCmds.Noop
    }
  }

  def description(c: Box[Curry]) = c match {
    case Full(aCurry) => "#pic [src]" #> aCurry.picUrl &
      "#pic [alt]" #> aCurry.name &
      "#desc *" #> aCurry.description &
      "#curryName *" #> aCurry.name
    case _ => ClearClearable 
  }

  def processOrder(): JsCmd = {

    val calendar: Calendar = Calendar.getInstance
    if (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.WEDNESDAY || calendar.get(Calendar.HOUR_OF_DAY) > 13)
    {
      S.notice(Props.get("order.not-today").openOr("Not today!"))
      S.redirectTo("/index")
    }
    else if (calendar.get(Calendar.HOUR_OF_DAY) > 11 || (calendar.get(Calendar.HOUR_OF_DAY) == 11 && calendar.get(Calendar.MINUTE) > 28 ))
    {
      S.notice(Props.get("order.cutoff").openOr("Missed cutoff time!"))
      S.redirectTo("/order")
    }
    else
    {
      val order: Order = Order.create
      order.timeStamp(new java.util.Date())
      order.user(User.currentUser)
      order.curry(dish.is)
      order.heat(heat)
      order.takeAway(ta)
      order.orderFor(orderedFor)
      order.save
      code.comet.OrderServer ! "new"

      S.notice("Order accepted")
      S.redirectTo("/currentorder")
    }
  }

  lazy val descriptionPart: NodeSeq = TemplateFinder.findAnyTemplate("order" :: Nil) match {
    case Full(t) => ("#descriptionTable ^*" #> "ignored").apply(t)
    case _ => NodeSeq.Empty
  }
}
