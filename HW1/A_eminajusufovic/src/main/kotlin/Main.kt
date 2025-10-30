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

fun devInfo(dev : Developer){
    val role : String
    if (dev is BackendDeveloper){
        role = "Backend developer"
    }else if (dev is FrontendDeveloper){
        role = "Frontend developer"
    }else {
        role = "N/A"
    }


}

fun main (){
    val developers = listOf(
        BackendDeveloper("Emina", "Jusufovic", 5, "BA",listOf("Java", "Python"), "Ktor"),
        FrontendDeveloper("Amila", "Residovic", 4, "US", listOf("JavaScript", "TypeScript"), "React"),
        BackendDeveloper("Amina", "Hasic", 2, "UK",listOf("HTML", "CSS", "JavaScript"), "Vue.js" ),
        FrontendDeveloper("Armin", "Coralic", 8, "BA", listOf("Java", "Kotlin"), "Ktor"),
        BackendDeveloper("Adnan", "Hasic", 3, "DE", listOf("Python", "Java"), "Django"))

    println("All developers")
    developers.forEach { devInfo(it) }






}


