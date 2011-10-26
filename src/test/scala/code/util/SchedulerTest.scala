package code.util

import org.specs._
import net.liftweb._
import http._
import net.liftweb.util._
import net.liftweb.common._
import org.specs.matcher._
import org.specs.specification._
import Helpers._
import mapper._
import scala.actors.Actor
import scala.actors.Actor._
import java.util.{Calendar, Date}
import code.model.{TaskType, EmailTask}

object SchedulerTest extends Specification
{
  "Scheduler" should
  {
    "find next wednesday 6am for order reminder" >>
    {
      val task = new EmailTask(TaskType.REMINDER,null)
      val cal=Calendar.getInstance()
      cal.set(2011,1,8,4,23,7)
      cal.getTime
      task.getRunTime(cal) must be_==(new java.util.Date(111,1,9,6,0))
      cal.set(2011,1,9,6,1,7)
      cal.getTime
      task.getRunTime(cal) must be_==(new java.util.Date(111,1,16,6,0))
      cal.set(2011,1,9,6,0,0)
      cal.getTime
      task.getRunTime(cal) must be_==(new java.util.Date(111,1,16,6,0))
      val task2 = new EmailTask(TaskType.ORDER, null)
      task2.getRunTime(cal) must be_==(new java.util.Date(111,1,9,11,30))
    }

    "cope with the next wednesday being next year" >>
    {
      val task = new EmailTask(TaskType.REMINDER, null)
      val cal=Calendar.getInstance()
      cal.set(2010,11,29,8,23,7)
      cal.getTime
      task.getRunTime(cal) must be_==(new java.util.Date(111,0,5,6,0))
    }
  }
}

