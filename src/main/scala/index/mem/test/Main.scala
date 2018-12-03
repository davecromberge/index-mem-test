package index.mem.test

import scala.util.Random

import com.netflix.atlas.core.index.{IndexStats, RoaringTagIndex}
import com.netflix.atlas.core.model.BasicTaggedItem
import com.netflix.spectator.api.NoopRegistry

object Main extends App {

  private val numMetrics = args.headOption.map(_.toInt).getOrElse(10000)
  private val node = 1
  private val nodeId = f"i-$node%017x"
  private val values = (0 until 7919).map(_ => Random.nextDouble()).toArray
  private val stats = new IndexStats(new NoopRegistry)

  private def metric(i: Int): BasicTaggedItem = {
    val mname = f"$i%08x"
    BasicTaggedItem(
      Map(
        "nf.app"     -> "foo",
        "nf.cluster" -> "foo-bar",
        "nf.asg"     -> "foo-bar-v000",
        "nf.stack"   -> "bar",
        "nf.node"    -> "$nodeId",
        "nf.region"  -> "us-east-1",
        "nf.zone"    -> "us-east-1a",
        "nf.vmtype"  -> "r3.2xlarge",
        "name"       -> s"test.metric.$mname"))
  }

  lazy val items =
    (1 until numMetrics).map(metric).toArray

  val index = new RoaringTagIndex(items, stats)

  println("Press enter to exit...")
  scala.io.StdIn.readLine()
}

