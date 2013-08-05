/*
 * Copyright (c) 2013 Curry Order System authors.
 * This file is part of Curry Order System. Please refer to the NOTICE.txt file for license details.
 */

package bootstrap.liftweb

import net.liftweb._
import util._
import Helpers._

import common._
import http._
import sitemap._
import Loc._
import mapper._

import code.model._
import java.util.{Calendar, Date, Timer}
import javax.mail.{PasswordAuthentication, Authenticator}

/**
 * A class that's instantiated early and run.  It allows the application
 * to modify lift's environment
 */
class Boot {
  def boot {
    System.setProperty("mail.smtp.host", "localhost")
    System.setProperty("mail.smtp.auth", "false")

    if (!DB.jndiJdbcConnAvailable_?) {
      val vendor = 
	new StandardDBVendor(Props.get("db.driver") openOr "org.h2.Driver",
			     Props.get("db.url") openOr 
			     "jdbc:h2:lift_proto.db;AUTO_SERVER=TRUE",
			     Props.get("db.user"), Props.get("db.password"))

      LiftRules.unloadHooks.append(vendor.closeAllConnections_! _)

      DB.defineConnectionManager(DefaultConnectionIdentifier, vendor)
    }

    val t=new Timer
    new EmailTask(TaskType.REMINDER,t)
    new EmailTask(TaskType.ORDER,t)

    // Use Lift's Mapper ORM to populate the database
    // you don't need to use Mapper to use Lift... use
    // any ORM you want
    Schemifier.schemify(true, Schemifier.infoF _, User,Curry,Order)

    // where to search snippet
    LiftRules.addToPackages("code")

    val loggedIn = If(() => User.loggedIn_?, () => RedirectResponse(User.loginPageURL))

    // Build SiteMap
    val sitemap = SiteMap(
      Menu.i("Home") / "index" >> loggedIn,
      Menu.i("Place Order") / "order" >> loggedIn,
      Menu.i("Current Orders") / "currentorder",
      Menu.i("List Users") / "user" / "list" >> Hidden,
      Menu.i("Reload") / "reload" >> Hidden
    )

    def sitemapMutators = User.sitemapMutator

    // set the sitemap.  Note if you don't want access control for
    // each page, just comment this line out.
    LiftRules.setSiteMapFunc(() => sitemapMutators(sitemap))

    // Use jQuery 1.4
    LiftRules.jsArtifacts = net.liftweb.http.js.jquery.JQuery14Artifacts

    //Show the spinny image when an Ajax call starts
    LiftRules.ajaxStart =
      Full(() => LiftRules.jsArtifacts.show("ajax-loader").cmd)
    
    // Make the spinny image go away when it ends
    LiftRules.ajaxEnd =
      Full(() => LiftRules.jsArtifacts.hide("ajax-loader").cmd)

    // Force the request to be UTF-8
    LiftRules.early.append(_.setCharacterEncoding("UTF-8"))

    // What is the function to test if a user is logged in?
    LiftRules.loggedInTest = Full(() => User.loggedIn_?)

    // Use HTML5 for rendering
    LiftRules.htmlProperties.default.set((r: Req) =>
      new Html5Properties(r.userAgent))    

    // Make a transaction span the whole HTTP request
    S.addAround(DB.buildLoanWrapper)

    code.setup.LoadDB.load

    LiftRules.unloadHooks.append(() => t.cancel())
  }
}
