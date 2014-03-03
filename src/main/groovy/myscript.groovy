println "hello world from groovy version ${GroovySystem.version}"
def task = "cf apps".execute()
//println "cf apps="+task.text.toString();
task.in.eachLine { line -> println line }
println "-- the end -- "