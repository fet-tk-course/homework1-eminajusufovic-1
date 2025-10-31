interface Person {
    fun fullName() : String
    fun country(): String
}


open class Developer (val firstName : String, val lastName : String, val yearsOfExp : Int,
                      val country : String, val lang : List<String>) : Person {

    init {
        if (firstName.isBlank() || lastName.isBlank()) {
            throw Throwable("First name and last name can not be blank!")
        }
        if (yearsOfExp < 0) {
            throw Throwable("Years of experience can not be negative!")
        }
        if (lang.isEmpty()) {
            throw Throwable("List of languages can not be empty!")
        }
    }

    val languages: List<String> = lang.map { it.lowercase() }

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
    return developers
        .flatMap { it.languages }
        .groupingBy { it }
        .eachCount()

}

fun countDeveloperManual(developers: List<Developer>): Map<String, Int> {
    val map = mutableMapOf<String, Int>()
    for (dev in developers){
        for (lang in dev.languages){
            map[lang] = map.getOrDefault(lang,0)+1
        }
    }
    return map
}

fun avgExpeByLanguage(developers: List<Developer>): Map<String, Double> {
    return developers
        .groupBy { dev -> dev.languages }
        .flatMap { (langs, devs) ->
            langs.map { lang ->
                lang to devs.map { it.yearsOfExp }.average()
            }
        }
        .toMap()
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

    val framework : String
    if (dev is BackendDeveloper){
        framework = dev.backendFramework
    }else if (dev is FrontendDeveloper){
        framework = dev.frontendFramework
    }else {
        framework = "N/A"
    }

    println("${dev.fullName()} — $role — jezici: ${dev.lang.joinToString(", ")} — framework: $framework")



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

    println("Counting languages (groupBy)")
    val countGroupBy = countDeveloper(developers)
    for (entry in countGroupBy) {
        println("${entry.key}: ${entry.value} developers")
    }

    println("Counting languages (manual)")
    val manualDev= countDeveloperManual(developers)
    for (entry in manualDev){
        println("${entry.key}: ${entry.value} developers")
    }
    print("Average experience by language (groupBy)")
    val avgExp= avgExpeByLanguage(developers)
    for (entry in avgExp){
        println("${entry.key}: ${entry.value} years")
    }






}


