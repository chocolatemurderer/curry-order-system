/*
 * Copyright (c) 2013 Curry Order System authors.
 * This file is part of Curry Order System. Please refer to the NOTICE.txt file for license details.
 */

package code.snippet

import code.model.User
import xml.NodeSeq

import net.liftweb.http.{S, RequestVar, TemplateFinder, SHtml}

import _root_.net.liftweb.util._
import _root_.net.liftweb.common._
import Helpers._
import net.liftweb.http.js.JsCmds

class Users {

  val users = User.findAll

  def list = ".user" #> rows & ClearClearable

  def rows: (NodeSeq => NodeSeq) = (ns: NodeSeq) => users.flatMap(row(_).apply(ns))

  def row(o: User) = ".name *" #> o.realName &
          ".email *" #> o.email  &
          ".delete *" #> deleteButton (o)  &
          ".user [id]" #> ("user" + o.id)

  def deleteButton (o: User):NodeSeq = {
    val currentUser = User.currentUser openOr null
    if (currentUser != null && (o == currentUser || currentUser.isSuper))
    {
      SHtml.ajaxButton("Delete", () => {
        User.delete_!(o)
        code.comet.OrderServer ! "deleted"
        S.notice("User Deleted")
        JsCmds.Replace("user" + o.id, NodeSeq.Empty)
      })
    }
    else
    {
      NodeSeq.Empty
    }
  }

}