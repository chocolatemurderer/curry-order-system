package code.model

import java.util.{Date, Calendar, TimerTask}

class EmailTask(taskType: TaskType.Value) extends TimerTask {

  def run {
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
}