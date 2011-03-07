package code.util

import org.specs._
import org.specs.runner.JUnit4
import org.specs.runner.ConsoleRunner
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

class SchedulerTestSpecsAsTest extends JUnit4(SchedulerTestSpecs)
object SchedulerTestSpecsRunner extends ConsoleRunner(SchedulerTestSpecs)
object SchedulerTestSpecs extends Specification {
  "Scheduler" should {
    "find next wednesday 6am for order reminder" >> {
      val task = new EmailTask(TaskType.REMINDER,null)
      val cal=Calendar.getInstance()
      cal.set(2011,1,8,4,23,7)
      task.getRunTime(cal) must be_==(new java.util.Date(111,1,9,6,0))
      cal.set(2011,1,9,6,1,7)
      task.getRunTime(cal) must be_==(new java.util.Date(111,1,16,6,0))
      cal.set(2011,1,9,6,0,0)
      task.getRunTime(cal) must be_==(new java.util.Date(111,1,16,6,0))
      val task2 = new EmailTask(TaskType.ORDER, null)
      task2.getRunTime(cal) must be_==(new java.util.Date(111,1,9,11,30))
    }
    "cope with the next wednesday being next year" >> {
      val task = new EmailTask(TaskType.REMINDER, null)
      val cal=Calendar.getInstance()
      cal.set(2010,11,29,8,23,7)
      task.getRunTime(cal) must be_==(new java.util.Date(111,0,5,6,0))
    }
  }
//  "Artifact" should {
//    val (c: Cultist, c2: Cultist, x: Artifact) = transaction {
//      (cultists.lookup(1L) getOrElse null,
//      cultists.lookup(2L) getOrElse null,
//      artifacts.lookup(1L) getOrElse null)
//    }
//    "resolve it's owning cultist" >> {
//      transaction {
//        x.owner must beSome(c)
//        x.owner must beSome(c)
//      }
//    }
//    "be mine if owning cultist is logged in" >> {
//      transaction {
//        x.stateFor(c) must beSome(ArtifactState.mine)
//      }
//    }
//    "be available if not mine" >> {
//      transaction {
//        x.stateFor(c2) must beSome(ArtifactState.available)
//      }
//    }
//    "be waiting if waiting clone exists" >> {
//      transaction {
//        clones.delete(clones.where(cl => cl.artifactId === x.id))
//        clones.insert(new Clone(x.id, c2.id, CloneState.waiting, 0))
//        x.stateFor(c2) must beSome(ArtifactState.waiting)
//      }
//    }
//    "be progressing if progressing clone exists" >> {
//      transaction {
//        clones.delete(clones.where(cl => cl.artifactId === x.id))
//        clones.insert(new Clone(x.id, c2.id, CloneState.progressing, 0))
//        x.stateFor(c2) must beSome(ArtifactState.progressing)
//      }
//    }
//    "be done if done clone exists" >> {
//      transaction {
//        clones.delete(clones.where(cl => cl.artifactId === x.id))
//        clones.insert(new Clone(x.id, c2.id, CloneState.done, 0))
//        x.stateFor(c2) must beSome(ArtifactState.done)
//      }
//    }
//    "start waiting clone requests from available state" >> {
//      transaction {
//        clones.delete(clones.where(cl => cl.artifactId === x.id))
//        x.stateFor(c2) must beSome(ArtifactState.available)
//        x.clone(c2) must beEqual(true)
//        x.stateFor(c2) must beSome(ArtifactState.waiting)
//      }
//    }
//    "cancel existing clone requests" >> {
//      transaction {
//        clones.delete(clones.where(cl => cl.artifactId === x.id))
//        clones.insert(new Clone(x.id, c2.id, CloneState.waiting, 0))
//        x.stateFor(c2) must beSome(ArtifactState.waiting)
//
//        x.cancelClone(c2) must beEqual(true)
//        x.stateFor(c2) must beSome(ArtifactState.available)
//
//        clones.delete(clones.where(cl => cl.artifactId === x.id))
//        clones.insert(new Clone(x.id, c2.id, CloneState.progressing, 0))
//        x.stateFor(c2) must beSome(ArtifactState.progressing)
//
//        x.cancelClone(c2) must beEqual(true)
//        x.stateFor(c2) must beSome(ArtifactState.available)
//      }
//    }
//    "have a local path based on the gateway local path" >> {
//      transaction {
//        val g: Gateway = gateways.insert(new Gateway(TestDb.c1.id, "foo", "bar", "/tmp/g/it", "password", GateMode.rw, GateState.open))
//        val a: Artifact = artifacts.insert(new Artifact(g.id, "folder/file.ext", T.now, T.now))
//        a.localPath must beSome("/tmp/g/it/folder/file.ext")
//
//        g.localPath = "/var/cache/gates/a/"
//        gateways.update(g)
//        a.localPath must beSome("/var/cache/gates/a/folder/file.ext")
//
//        a.path = "/x/y/z/readme"
//        artifacts.update(a)
//        a.localPath must beSome("/var/cache/gates/a/x/y/z/readme")
//      }
//    }
//  }
}

