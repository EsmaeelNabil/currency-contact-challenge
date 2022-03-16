fun main(args: Array<String>) {

    val requiredList = ContactsComponent().getRecentContacts()

    val expectedList = listOf(
        Contact(id = 5, data = "C", last_used = Time(unix = "1647469990366")),
        Contact(id = 4, data = "A", last_used = Time(unix = "1647469980365")),
        Contact(id = 1, data = "B", last_used = Time(unix = "1647469950362")),
    )

    val condition = requiredList == expectedList

    println(condition)

}