import groovyx.gpars.GParsPool
import groovyx.gpars.AsyncFun
import groovyx.gpars.dataflow.Promise
import groovyx.gpars.dataflow.* 
import groovyx.gpars.dataflow.SelectResult
import groovyx.gpars.group.DefaultPGroup
import groovyx.gpars.dataflow.Select

// see this page: http://www.jroller.com/vaclav/entry/add_one_function_to_my  on asynchs
// need to make sure your gpars.jar is on your classpath or you cd into a folder with the jar

// demonstrates asynch function closures - chained together into a pipeline
log.info "gpars2 is starting to build list for the template"

def before = System.currentTimeMillis()
def tab=""; 

log.info "step 1"
def range = 0..1000
def list = range.collect { it * 2 }.findAll { it % 2 == 0 && it % 7 == 0 }

log.info "step 2"
tab += "<br />"+list.toString()
tab += "<br />"

GParsPool.withPool {
    log.info "step 3"
    def list2 = range.collectParallel { it * 2 }.findAllParallel { it % 2 == 0 && it % 7 == 0 }
    tab += "<br />"
    tab += list2.toString()
}

tab += "<br /><br />collectParallel is running"

// get text from a url
GParsPool.withPool() {
    log.info "step 4"

    // use annotation rather than .async()
    @AsyncFun 
    Closure download = {String url ->
        url.toURL().text
    }

    Closure hash = {s -> s.hashCode()}.asyncFun();
    Closure sz = {s -> s.size()}.asyncFun();

    log.info "step 5"
    tab += "<br /><br />calculating result ---"
    def result = hash(download('http://www.gpars.org'))
    def caelyf = sz(download('http://www.gradle.org/'));
    tab += "<br />The result of hashing: " + result.get()+"\nand type of 'result' is "+result.class;
    tab += "<br /><br />The size of http://www.gradle.org/ page is "+caelyf.get()+" bytes"
} // end of withPool


def maxPromise // wait point of future answer

GParsPool.withPool {
    log.info "step 6"

    def numbers = 0..29999

    def comparison = {a, b -> a>b?a:b}.asyncFun()
    maxPromise = numbers.inject(0, comparison)

    tab += "<br /><br />Look here, I can talk to the user while the math is being done for me!"

    // doesn't work yet ---
    //Promise timeoutPromise = Select.createTimeout(5000)
    //final timeoutChannel = Select.createTimeout(5000)
    //def timeoutPromise = DataflowReadChannel.createTimeout(5000)
    //    def select = Dataflow.select(a, b, c)
    //final alt = Dataflow.select(maxPromise, timeoutPromise)
    //final alt = new Select(maxPromise, timeoutChannel)
    //def result2 = alt.select()
    //tab += "<br />Result: " + result2.toString()

    // show results from first promise
    tab += "<br />maxPromise.get()="+maxPromise.get()
} // end of withPool

log.info "step 7"

// compute elapsed time in sec.s
def after = ( System.currentTimeMillis() - before ) / 1000;

request.setAttribute 'time', after.toString()
request.setAttribute 'tab', tab;

log.info "gpars2 is forwarding list to the template"

forward '/WEB-INF/pages/gpars2.gtpl'
