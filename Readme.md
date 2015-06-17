# Introduction #

While doing a project for the browser with Scala.JS, I was very surprised not to be able to
find a simple Date & Time manipulation libraries that could compile to Scala.js.



# Goals #
* Simple and practical API
* Immutable Dates 
* Discrete timeline, nano second resolution
* full support of ISO 8601
* Lenses based operations 
* Not tied to any JVM specifics
* no runtime exception
* Thoroughly tested

# Code Samples #

		trait Instant{
			def instant: Long
		}

		trait Date {
			def day: Int
			def month: Int
			def year : Int
		}

		trait Time {
			def hour: Int
			def min: Int
			def sec : Int
			def nano : Int
		}

	   type DateTime = Date with Time

		trait InstantLike[T]{
			def instant() :Instant
		}
			
		case class ZonedDateTime(datetime:DateTime, tz: TimeZone) 	

		def fromGregorian (year:Int, month:Int, day:Int) : Either[CalendarError, Date] = ???

		def parse(s:String) : Either[CalendarError,Date] = ???

		def addDays[T:InstantLike] (instant:T,days:Int) : T

		def diffDays[T:InstantLike] (instant1:T,instant2:T) : Int

		def toDate[T:InstantLike] (instant: T, tz: TimeZone) : Date

		def toDateTime[T:InstantLike] (instant: T, tz: TimeZone) : Date with Time



