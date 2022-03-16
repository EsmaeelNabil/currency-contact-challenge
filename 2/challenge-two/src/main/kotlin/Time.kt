import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.*


data class Time(
    val date_iso8601: String = "",
    val unix: String = "",
) {

}

/**
 * chosen to use static values because this function would return the same time for multiple items
 */
fun getUnixNow(): String = Calendar.getInstance().timeInMillis.toString()
