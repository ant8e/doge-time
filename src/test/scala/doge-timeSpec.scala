import org.scalatest.FunSpec
import dogetime._
import Implicits.DateInstantLike

class DogeTimeSpec extends FunSpec {

  describe("A test") {
    val date = new Date {
      val day = 1
      val month = 1
      val year = 1970
    }
    val d2 = addDays(date, 1)
    println(s"${d2.year}")
  }
}
