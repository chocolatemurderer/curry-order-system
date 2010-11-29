package code.snippet

import _root_.net.liftweb.util._
import _root_.net.liftweb.common._
import Helpers._
import net.liftweb.http.SHtml
import code.model.{Curry, Heat}
import net.liftweb.http.js.JsCmds

class OrderSnippet {
  val curryList = Curry.findAll()

  def order = ".hotness" #> SHtml.select(Heat.values.toSeq.map(e => (e.toString, e.toString)), Empty, (s: String) => _) &
          ".dish" #> SHtml.radio(curryList.map(e => (e.name.is)), Empty, (s: String) => _) &
          ClearClearable &
          ".sendOrder" #> SHtml.submit("order", () => JsCmds.Noop)


}