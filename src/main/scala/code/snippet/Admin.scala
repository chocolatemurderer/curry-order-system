package code.snippet

import _root_.net.liftweb.util._
import _root_.net.liftweb.common._
import Helpers._
import net.liftweb.http.js.{JsCmd, JsCmds}
import xml.NodeSeq
import net.liftweb.http.js.JsCmds.Replace
import net.liftweb.http.{S, RequestVar, TemplateFinder, SHtml}
import code.model.{User, Order, Curry, Heat}
import code.setup.LoadDB

class Admin {
  def reset = ".submit" #> SHtml.submit("Reset!?", () => S.redirectTo("/", () => {LoadDB.load ; S.notice("Database Reset!")} ))
}
