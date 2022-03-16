import org.junit.Test

import org.junit.Assert.*

class ContactsComponentTest {

    /**
     * todo : returned list must
     *  1. hold only unique entries (data NOT id)
     *  2. hold max three entries
     *  3. sorted by "last_used" (if you use a custom sort, i'd suggest to use the unix timestamp)
     */
    @Test
    fun getRecentContacts() {

        val requiredList = ContactsComponent().getRecentContacts()

        val expectedList = listOf(
            Contact(id = 5, data = "C", last_used = Time(unix = "1647469990366")),
            Contact(id = 4, data = "A", last_used = Time(unix = "1647469980365")),
            Contact(id = 1, data = "B", last_used = Time(unix = "1647469950362")),
        )

        assertEquals(expectedList, requiredList)
    }
}