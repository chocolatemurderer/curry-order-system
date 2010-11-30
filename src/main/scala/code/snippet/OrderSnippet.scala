package code.snippet

import _root_.net.liftweb.util._
import _root_.net.liftweb.common._
import Helpers._
import code.model.{Curry, Heat}
import net.liftweb.http.js.{JsCmd, JsCmds}
import net.liftweb.http.{TemplateFinder, SHtml}
import xml.NodeSeq
import net.liftweb.http.js.JsCmds.Replace

class OrderSnippet {
  val curryList = Curry.findAll()

  def order = ".hotness" #> SHtml.select(Heat.values.toSeq.map(e => (e.toString, e.toString)), Empty, (s: String) => _) &
          ".dish" #> SHtml.ajaxSelect(curryList.toSeq.map(e => (e.id.is.toString, e.name.is)), Empty, updateDescription _) &
          ".sendOrder" #> SHtml.submit("order", () => JsCmds.Noop)


  def updateDescription(s: String): JsCmd = {
    Curry.find(s) match {
      case Full(aCurry) =>
        val result = ("#pic [src]" #> aCurry.picUrl &
                "#pic [alt]" #> aCurry.name &
                "#desc *" #> aCurry.description &
                "#curryName *" #> aCurry.name).apply(descriptionPart)
        Replace("descriptionTable", result)
      case _ => JsCmds.Noop
    }
  }

  lazy val descriptionPart: NodeSeq = TemplateFinder.findAnyTemplate("order" :: Nil) match {
    case Full(t) => t \\ "table"
    case _ => NodeSeq.Empty
  }

}