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
            // Koristim getOrDefault ovdje da bih dobila vrijednost ili 0 ako ključ ne postoji.
            // Napomena: ovu funkciju sam saznala putem ChatGPT-a jer ranije nisam znala za nju.
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

fun avgExpManual (developers: List<Developer>) : Map<String, Double> {
    val manualMap = mutableMapOf<String, MutableList<Int>>()
    for(dev in developers){
        for(lang in dev.languages){
            val years = manualMap.getOrPut(lang) {mutableListOf()}
            years.add(dev.yearsOfExp)

        }
    }
    val avg = mutableMapOf<String, Double>()
    for ((lang, list) in manualMap){

        avg[lang] = list.average()
}
    return avg
}

fun filterByFramework (developers: List<Developer>, framework : String) : List<Developer>{
    return developers.filter { dev ->
        when (dev) {
            is BackendDeveloper -> dev.backendFramework == framework
            is FrontendDeveloper -> dev.frontendFramework == framework
            else -> false
        }
    }

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
    println("\n")

    println("Counting languages (groupBy)")
    val countGroupBy = countDeveloper(developers)
    for (entry in countGroupBy) {
        println("${entry.key}: ${entry.value} developers ")

    }
    println("\n")

    println("Counting languages (manual)")
    val manualDev= countDeveloperManual(developers)
    for (entry in manualDev){
        println("${entry.key}: ${entry.value} developers")
    }
    println("\n")

    println("Average experience by language (groupBy)")
    val avgExp= avgExpeByLanguage(developers)
    for (entry in avgExp){
        println("${entry.key}: ${entry.value} years")
    }
    println("\n")

    println("Average experience by language (manual)")
        val avgExpM = avgExpManual(developers)
        for (entry in avgExpM) {
            println("${entry.key}: ${entry.value} years")
        }
    println("\n")

    val filteredDevs = filterByFramework(developers, "Ktor")
    println("Developers using Ktor framework:")
    filteredDevs.forEach { devInfo(it) }
    println("\n")

    val filteredDevs1 = filterByFramework(developers, "Vue.js")
    println("Developers using Vue.js framework:")
    filteredDevs1.forEach { devInfo(it) }








}


