/*
 * Copyright (c) 2013 Curry Order System authors.
 * This file is part of Curry Order System. Please refer to the NOTICE.txt file for license details.
 */

package code.setup

import net.liftweb.common._
import net.liftweb.http._
import code.model.Curry
import scala.xml._

object LoadDB extends Loggable {
  lazy val menu = TemplateFinder.findAnyTemplate("templates-hidden" :: "menu" :: Nil) match {
    case Full(t) => t
    case _ => NodeSeq.Empty 
  }
  
  def load {
    var existing = Curry.findAll.map(c => c.name.is -> c).toMap
    menu \\ "main" foreach { main =>
      val name = main \ "name" text
      val c = existing.get(name) match {
        case Some(x) => 
          logger.info("Menu * " + name + " (updated)")
          x
        case None => 
          logger.info("Menu + " + name + " (added)")
          Curry.create.name(name)
      }
      c.description(main \ "description" text)
      c.picUrl(main \ "picture" text)
      c.price(java.lang.Double.parseDouble(main \ "price" text))
      c.deprecated(false)
      c.save
      existing = existing - name
    }

    existing.values.foreach {c =>
      c.deprecated(true)
      c.save
      logger.info("Menu - " + c.name.is + " (deprecated)")
    }

    /*
    val c: Curry =  Curry.create
    c.name("Lamb Vindaloo")
    c.description("A hot dish, made world famous by the Chefs of Goa. It has a Portugese influence. It is cooked with vinegar, capsicum and whole spices.")
    c.picUrl("http://orders.littleindia.co.nz/media/catalog/product/cache/1/image/340x255/9df78eab33525d08d6e5fb8d27136e95/3/3/33_vindaloo.jpg")
    c.save
    */
  }
}



