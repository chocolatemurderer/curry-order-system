package code.model

import java.util.{Timer, Date, Calendar, TimerTask}
import xml.NodeSeq
import net.liftweb.http.TemplateFinder
import net.liftweb.common._
import net.liftweb.util.Helpers._
import net.liftweb.util.Mailer._
import java.net.{Inet4Address, InetAddress, NetworkInterface}
import net.liftweb.util.{Props, Mailer}

class EmailTask(taskType: TaskType.Value, t: Timer) extends TimerTask
{

  if (t != null)
  {
    t.scheduleAtFixedRate(this, this.getRunTime(Calendar.getInstance), 7 * 24 * 60 * 60000)
  }


  def run()
  {
    if (taskType == TaskType.REMINDER)
    {
      createReminder()
    }
    else
    {
      createOrder()
    }
  }

  def getRunTime(now: Calendar): Date =
  {
    val newCal: Calendar = now.clone.asInstanceOf[Calendar]
    newCal.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY)
    if (taskType == TaskType.REMINDER)
    {

      newCal.set(Calendar.HOUR_OF_DAY, 6)
      newCal.set(Calendar.MINUTE, 0)
    }
    else
    {
      newCal.set(Calendar.HOUR_OF_DAY, 11)
      newCal.set(Calendar.MINUTE, 30)
    }

    newCal.set(Calendar.SECOND, 0)
    newCal.set(Calendar.MILLISECOND, 0)
    while (!now.before(newCal))
    {
      newCal.add(Calendar.WEEK_OF_YEAR, 1)
    }
    newCal.getTime

  }

  def createReminder()
  {
    val m = <div>
      <p>Hi</p>

      <p>Come to curry lunch</p>

      <p>Please order by 11:30</p>

      <p>
        <a href={Props.get("server.uri").openOr("/")}>Place Order</a>
      </p>
    </div>

    val myRecips: List[String] = User.findAll().map(_.email.is)

    Mailer.sendMail(From(Props.get("email.from").openOr("reminder@localhost")), Subject(Props.get("reminder.subject").openOr("Curry!")),
      (XHTMLMailBodyType(m) :: myRecips.map(To(_))): _*)
  }

  def createOrder()
  {
    val emailTo: List[To] = Props.get("order.to").openOr("shop@localhost").split(' ').toList.map(To(_))

    TemplateFinder.findAnyTemplate("currentOrderEmail" :: Nil) match
    {
      case Full(ns) =>
        val m = (new code.snippet.CurrentOrders).email.apply(ns)
        val myRecips: List[String] = Order.findCurrent.flatMap(_.user.obj).map(_.email.is).distinct
        Mailer.sendMail(From(Props.get("email.from").openOr("order@localhost")), Subject(Props.get("order.subject").openOr("Curry Order")),
          (emailTo ::: (XHTMLMailBodyType(m) :: myRecips.map(CC(_)))): _*)
      case _ =>
    }
  }
}