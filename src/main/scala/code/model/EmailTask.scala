package code.model

import java.util.{Date, Calendar, TimerTask}

class EmailTask extends TimerTask {
    def run {
      Em
    }
  }

object EmailTask
{
  def nextOrderReminder(now:Calendar):Date =
  {
    val newCal:Calendar= now.clone.asInstanceOf[Calendar]
    newCal.set(Calendar.DAY_OF_WEEK,Calendar.WEDNESDAY)
    newCal.set(Calendar.HOUR_OF_DAY,6)
    newCal.set(Calendar.MINUTE,0)
    newCal.set(Calendar.SECOND,0)
    newCal.set(Calendar.MILLISECOND,0)
    while(now.after(newCal))
    {
      newCal.add(Calendar.WEEK_OF_YEAR,1)
    }
    newCal.getTime
  }
}