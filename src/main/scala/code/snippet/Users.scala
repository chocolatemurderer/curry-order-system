package code.snippet

import code.model.User
import xml.NodeSeq

import net.liftweb.http.{S, RequestVar, TemplateFinder, SHtml}

import _root_.net.liftweb.util._
import _root_.net.liftweb.common._
import Helpers._

class Users {

  val users = User.findAll

  def list = ".user" #> rows & ClearClearable

  def rows: (NodeSeq => NodeSeq) = (ns: NodeSeq) => users.flatMap(row(_).apply(ns))

  def row(o: User) = ".name *" #> o.realName &
          ".email *" #> o.email


}