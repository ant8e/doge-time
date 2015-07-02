
object dogetime_alt {

  // inspired by  haskell Data.Time http://hackage.haskell.org/package/time-1.4

  /** Julian Day is a standard count of days, with zero being the day 1858-11-17. */
  case class Day(days: Int)

  /** TBD */
  trait CalendarError

  object Day {
    def addDays(day: Day, d: Integer): Day = Day(day.days + d)
    def diffDays(d1: Day, d2: Day): Int = d1.days - d2.days

    /** Convert from gregorian date.
      *
      * Invalid values will return an error
      */
    def fromGregorian(date: Date): Either[CalendarError, Day] = ???

    /**
     * Convert from gregorian date.
     *
     * Invalid values will be clipped to the correct range, month first, then day.
     **/
    def fromGregorianClip(date: Date): Day = ???
  }

  /** Gregorian Date */
  case class Date(year: Int, month: Int, day: Int)

  object GregorianCalendar {
    /** Convert from gregorian date. */
    def toGregorian(day: Day): Date = ???

    /** show in ISO 8601 format (yyyy-mm-dd) */
    override def toString: String = ???

    /** The number of days in a given month according to the proleptic Gregorian calendar
      *
      * @return 0 on invalid values
      */
    def monthLength(year: Int, month: Int): Int = ???

    /**
     * Add months, with days past the last day of the month clipped to the last day.
     * For instance, 2005-01-30 + 1 month = 2005-02-28.
     */
    def addMonthsClip(d: Date, m: Int): Date = ???

    /**
     * Add months, with days past the last day of the month rolling over to the next month.
     * For instance, 2005-01-30 + 1 month = 2005-03-02.
     */
    def addMonthsRollOver(d: Date, m: Int): Date = ???

    /**
     * Add years, matching month and day, with Feb 29th clipped to Feb 28th if necessary.
     * For instance, 2004-02-29 + 2 years = 2006-02-28.
     */
    def addYearsClip(d: Date, y: Int): Date = ???

    /**
     * Add years, matching month and day, with Feb 29th rolled over to Mar 1st if necessary.
     * For instance, 2004-02-29 + 2 years = 2006-03-01.
     */
    def addYearsRollOver(d: Date, y: Int): Date = ???

    /** Is this year a leap year according to the proleptic Gregorian calendar? */
    def isLeapYear(year: Int) = ???
  }

  /** This is a length of time in picoseconds, as measured by a clock. */
  sealed trait DiffTime {
    def picoSeconds: Long
  }

  object DiffTime {
    /** Create a DiffTime which represents an integral number of seconds. */
    def seconds(seconds: Int): DiffTime = new DiffTime {
      val picoSeconds = seconds * 1000000000000L
    }
    /** Create a DiffTime from a number of seconds. */
    def picoSeconds(seconds: Int): DiffTime = new DiffTime {
      val picoSeconds = seconds * 1000000000000L
    }
  }

  /**
   * A TimeZone is a whole number of minutes offset from UTC, together with a name and a "just for summer" flag.
   * @param minuteOffset  The number of minutes offset from UTC. Positive means local time will be later in the day than UTC.
   * @param summerOnly    Is this time zone just persisting for the summer?
   * @param name The name of the zone, typically a three- or four-letter acronym.
   */
  case class TimeZone(minuteOffset: Int, summerOnly: Boolean, name: String)

  /** The UTC time zone */
  object UTC extends TimeZone(0, false, "UTC")

  /**
   * Time of day as represented in hour, minute ,seconds, picoseconds, typically used to express local time of day.
   * @param hour range 0 - 23
   * @param minute  range 0 - 59
   * @param second range 0 - 60  accomodating leap seconds. Any local minute may have a leap second,
   *               since leap seconds happen in all zones simultaneously
   * @param picosecond  range 0 - (10^12 -1)
   */
  case class TimeOfDay(hour: Int, minute: Int, second: Int, picosecond: Long)

  object TimeOfDay {
    /** Convert a ToD in some timezone to a ToD in UTC, together with a day adjustment. */
    def toUTC(zoneTime: TimeOfDay, timeZone: TimeZone): (Int, TimeOfDay) = ???

    /** Convert a ToD in UTC to a ToD in some timezone, together with a day adjustment. */
    def fromUTC(utcTime: TimeOfDay, timeZone: TimeZone): (Int, TimeOfDay) = ???

    /** Get a TimeOfDay given a time since midnight. Time more than 24h will be converted to leap-seconds. */
    def toTimeOfDay(time: DiffTime): TimeOfDay = ???

    /** Find out how much time since midnight a given TimeOfDay is. */
    def toTime(time: TimeOfDay): DiffTime = ???
  }


  /** A simple day and time aggregate, where the day is of the specified parameter, and the time is a TimeOfDay.
    * Conversion of this (as local civil time) to UTC depends on the time zone. */
  case class LocalTime(day: Day, timeOfDay: TimeOfDay)

  object LocalTime {
    def addDays(localTime: LocalTime, d: Integer): LocalTime = localTime.copy(day = Day.addDays(localTime.day, d))

    //TODO  add other convenience function diffDays, addTime, toUtc, fromUtc, ....
  }

  /** A local time together with a TimeZone. */
  case class ZonedTime(localTime: LocalTime, timeZone: TimeZone)

  object ZonedTime {
    //TODO  add convenience functions

  }

}