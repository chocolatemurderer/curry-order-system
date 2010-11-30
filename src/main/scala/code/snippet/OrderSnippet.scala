package code.snippet

import _root_.net.liftweb.util._
import _root_.net.liftweb.common._
import Helpers._
import net.liftweb.http.js.{JsCmd, JsCmds}
import xml.NodeSeq
import net.liftweb.http.js.JsCmds.Replace
import code.model.{Order, Curry, Heat}
import net.liftweb.http.{S, RequestVar, TemplateFinder, SHtml}

class OrderSnippet {
  val curryList = Curry.findAll()
  object heat extends RequestVar[Heat.Heat](Heat.mildMedium)
  object dish extends net.liftweb.http.SessionVar[Box[Curry]](Empty)

  def order = ".hotness" #> SHtml.select(Heat.values.toSeq.map(e => (e.toString, e.toString)), Empty, (s: String) => heat(Heat.withName(s))) &
          ".dish" #> SHtml.ajaxSelect(curryList.toSeq.map(e => (e.id.is.toString, e.name.is)), Empty, updateDescription _) &
          ".sendOrder" #> SHtml.submit("order", processOrder _)


  def updateDescription(s: String): JsCmd = {

    Curry.find(s) match {
      case Full(aCurry) =>
        dish(Full(aCurry))
        val result = ("#pic [src]" #> aCurry.picUrl &
                "#pic [alt]" #> aCurry.name &
                "#desc *" #> aCurry.description &
                "#curryName *" #> aCurry.name).apply(descriptionPart)
        Replace("descriptionTable", result)
      case _ => JsCmds.Noop
    }
  }

  def processOrder(): JsCmd = {
    val order: Order = Order.create
    order.timeStamp(new java.util.Date())
    order.user(Empty)
    order.curry(dish.is)
    order.heat(heat)

    order.save

    S.notice("Order processed")
    S.redirectTo("/")
  }

  lazy val descriptionPart: NodeSeq = TemplateFinder.findAnyTemplate("order" :: Nil) match {
    case Full(t) => t \\ "table"
    case _ => NodeSeq.Empty
  }

}