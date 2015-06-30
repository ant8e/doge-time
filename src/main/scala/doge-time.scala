
object dogetime {

  trait Instant {
    def instant: Long
  }

  trait Date {
    def day: Int
    def month: Int
    def year: Int
  }

  trait Time {
    def hour: Int
    def min: Int
    def sec: Int
    def nano: Int
  }

  type DateTime = Date with Time

  trait InstantLike[T] {
    def toInstant(x: T): Instant
  }

  object Implicits {
    implicit val DateInstantLike = new InstantLike[Date] {
      def toInstant(x: Date) = new Instant {
        val instant = 0: Long
        def move(d: Date, x: Long) = new Date {
          val day = 14
          val month = 6
          val year = 1970
        }
      }
    }
  }

  trait TimeZone
  trait CalendarError

  case class ZonedDateTime(datetime: DateTime, tz: TimeZone)

  def fromGregorian(year: Int, month: Int, day: Int): Either[CalendarError, Date] = ???

  def parse(s: String): Either[CalendarError, Date] = ???

  def addDays[T: InstantLike](instant: T, days: Int): T = ???

  def diffDays[T: InstantLike](instant1: T, instant2: T): Int = ???

  def toDate[T: InstantLike](instant: T, tz: TimeZone): Date = ???

  def toDateTime[T: InstantLike](instant: T, tz: TimeZone): DateTime = ???

}
