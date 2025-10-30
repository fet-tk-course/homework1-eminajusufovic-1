interface Person {
    fun fullName() : String
    fun country(): String
}

open class Developer (val firstName : String, val lastName : String, val yearsOfExp : Int,
    val country : String, val lang : String) : Person {

    override fun fullName() = "$firstName $lastName"
    override fun country() = country
}

