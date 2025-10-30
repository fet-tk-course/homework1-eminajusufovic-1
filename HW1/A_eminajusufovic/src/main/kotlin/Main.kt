interface Person {
    fun fullName() : String
    fun country(): String
}

open class Developer (val firstName : String, val lastName : String, val yearsOfExp : Int,
    val country : String, val lang : List<String>) : Person {

    override fun fullName() = "$firstName $lastName"
    override fun country() = country
}
class BackendDeveloper (firstName: String, lastName: String, yearsOfExp: Int,
                        country: String, lang: List<String>, val backendFramework : String) : Developer(
                            firstName, lastName, yearsOfExp, country, lang
                        ){

}

class FrontendDeveloper (firstName: String, lastName: String, yearsOfExp: Int,
                        country: String, lang: List<String>, val frontendFramework : String) : Developer(
                            firstName, lastName, yearsOfExp, country, lang
                        ){

}

fun countDeveloper(developers: List<Developer>): Map<String, Int> {
    val allLanguages = developers.flatMap { dev -> dev.lang }
    val languageGrouping = allLanguages.groupingBy { language -> language }
    val languageCount = languageGrouping.eachCount()
    return languageCount

}




