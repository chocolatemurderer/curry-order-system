package code.model

import java.util.{Timer, Date, Calendar, TimerTask}
import javax.mail.Message
import net.liftweb.util.Mailer
import xml.NodeSeq

class EmailTask(taskType: TaskType.Value,t : Timer) extends TimerTask {

  t.schedule(this,this.getRunTime(Calendar.getInstance))


  def run {
    sendMessage( if (taskType==TaskType.REMINDER) createReminder else createOrder )
  }

  def getRunTime(now: Calendar): Date = {
    val newCal: Calendar = now.clone.asInstanceOf[Calendar]
    newCal.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY)
    if (taskType == TaskType.REMINDER) {

      newCal.set(Calendar.HOUR_OF_DAY, 6)
      newCal.set(Calendar.MINUTE, 0)
    } else {
      newCal.set(Calendar.HOUR_OF_DAY, 11)
      newCal.set(Calendar.MINUTE, 30)
    }

    newCal.set(Calendar.SECOND, 0)
    newCal.set(Calendar.MILLISECOND, 0)
    while (now.after(newCal)) {
      newCal.add(Calendar.WEEK_OF_YEAR, 1)
    }
    newCal.getTime

  }

  def createMessage:Message
  {

  }

  def sendMessage(m: Message) {
    import net.liftweb.util.Mailer
    import Mailer._

    val myRecips : List[String] = Nil
    val plainContent : String = "..."
    val xhtmlContent : NodeSeq = NodeSeq.Empty

    Mailer.sendMail(From("***REMOVED***"), Subject("Curry Order"),
                    (plainContent :: xhtmlContent :: myRecips.map(To(_))) : _*)
  }
}