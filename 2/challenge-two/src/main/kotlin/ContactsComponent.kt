class ContactsComponent {
    /**
     * in the live code this is set after the view loaded; you can assume that this won't be null or empty
     * you can mock or change this if you want
     */
    private var contacts: List<Contact> = listOf(
        Contact(id = 0, data = "A", last_used = Time(unix = "1647469940361")),
        Contact(id = 1, data = "B", last_used = Time(unix = "1647469950362")),
        Contact(id = 2, data = "C", last_used = Time(unix = "1647469960363")),
        Contact(id = 3, data = "A", last_used = Time(unix = "1647469970364")),
        Contact(id = 4, data = "A", last_used = Time(unix = "1647469980365")),
        Contact(id = 5, data = "C", last_used = Time(unix = "1647469990366")),
    )

    /**
     * todo : returned list must
     *  1. hold only unique entries (data NOT id)
     *  2. hold max three entries
     *  3. sorted by "last_used" (if you use a custom sort, i'd suggest to use the unix timestamp)
     */
    fun getRecentContacts(): List<Contact> = mutableListOf<Contact>().also { result ->
        val sortedByLastUsed = contacts.sortedByDescending { contact ->
            contact.last_used.unix.toLong()
        }
        val uniqueContacts = sortedByLastUsed.distinctBy { it.data }
        val maxThree = uniqueContacts.take(3)
        result.addAll(maxThree)
    }

}