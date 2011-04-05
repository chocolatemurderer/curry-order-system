package code.model

import java.util.{Timer, Date, Calendar, TimerTask}
import net.liftweb.util.Mailer
import xml.NodeSeq
import net.liftweb.http.TemplateFinder
import net.liftweb.common._
import net.liftweb.util.Helpers._
import net.liftweb.util.Mailer
import Mailer._
import java.net.{Inet4Address, InetAddress, NetworkInterface}

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
        <a href={"http://" + getIp}>Place Order</a>
      </p>
    </div>

    val myRecips: List[String] = User.findAll().map(_.email.is)

    Mailer.sendMail(From("***REMOVED***"), Subject("Wednesday curry 12:15 at ***REMOVED*** Lower Hutt (***REMOVED***)"),
      (XHTMLMailBodyType(m) :: myRecips.map(To(_))): _*)
  }

  def createOrder()
  {
    var emailTo = "***REMOVED***"
    if (getIp.equals("***REMOVED***"))
    {
      emailTo = "hemantsharma98@gmail.com"
    }

    TemplateFinder.findAnyTemplate("currentOrderEmail" :: Nil) match
    {
      case Full(ns) =>
        val m = (new code.snippet.CurrentOrders).email.apply(ns)
        val myRecips: List[String] = Order.findCurrent.flatMap(_.user.obj).map(_.email.is).distinct
        Mailer.sendMail(From("***REMOVED***"), Subject("Curry Order"),
          (To(emailTo) :: XHTMLMailBodyType(m) :: myRecips.map(CC(_))): _*)
      case _ =>
    }
  }

  def getIp: String =
  {
    var ipAddress = "127.0.0.1"
    val ipadd = NetworkInterface.getNetworkInterfaces
    while (ipadd.hasMoreElements)
    {
      val networkInterface: NetworkInterface = ipadd.nextElement
      var inetAddr = networkInterface.getInetAddresses
      while (inetAddr.hasMoreElements)
      {
        val inetAddress: InetAddress = inetAddr.nextElement
        if (inetAddress.isInstanceOf[Inet4Address])
        {
          val hostAddress: String = inetAddress.getHostAddress
          if (networkInterface.getName.toLowerCase.startsWith("eth"))
          {
            if (networkInterface.getName.toLowerCase.endsWith(":0"))
            {
              return hostAddress
            }
            ipAddress = hostAddress
          }
        }
      }
    }
    return ipAddress
  }

}