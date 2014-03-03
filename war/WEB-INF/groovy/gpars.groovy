//@Grab(group='org.codehaus.gpars', module='gpars', version='1.1.0')   <-- didn't work ! had to put gpars.jar in /lib
// read more here: http://gpars.org/guide/guide/single.html#introduction

import groovyx.gpars.dataflow.DataflowQueue
import groovyx.gpars.dataflow.DataflowVariable
import  java.util.concurrent.Future;
import static groovyx.gpars.dataflow.Dataflow.task
import groovyx.gpars.GParsExecutorsPool
import groovyx.gpars.GParsPool
//import groovyx.gpars.dataflow.Promise
import jsr166y.ForkJoinPool

// measure runtime : get start time here
def before = System.currentTimeMillis()

log.info "gpars is collecting a list for the template"

def ans = 0;
def tab="<table><tr><th>Domain</th><th>Front Page<br />Byte Size</th><th>Sec.s to Read</th></tr>";

GParsPool.withPool() {
    def urls = ['http://www.dzone.com', 'http://www.theserverside.com', 'http://www.infoq.com','http://caelyf.de.a9sapp.eu/','http://www.gradle.org/', 'http://gpars.codehaus.org/', 'http://groovy.codehaus.org/']
    Closure download = {url ->
	    def start = System.currentTimeMillis()
            def tx = url.toURL().text
	    def fini = ( System.currentTimeMillis() - start ) / 1000;

            def tx2 = "<tr><td><a href=\"${url}\">${url}</a></td><td class=\"ar\">${tx.size()}</td><td class=\"ar\">${fini}</td></tr>"
	    tab += tx2;
            return tx;
    } // end of download
    
    Closure cachingDownload = download.gmemoize()
    
    Closure calculate = {def a = 1.7393f / 0.00414f; }
    
    Closure longLastingCalculation = {calculate();}

    Closure fastCalculation = longLastingCalculation.async()  
    //create a new closure, which starts the original closure on a thread pool

    //returns almost immediately
    Future result5=fastCalculation()
    
    //do stuff while calculation performs
    sleep(1000);
    
    // grab front pages from several websites and measure their size plus time to access
    def tx = urls.findAllParallel {url -> cachingDownload(url).contains('GROOVY')}
    tab +="</table>";

    // now ask for promised answer
    ans = result5.get()
} // withPool


// compute elapsed time for all tasks in seconds
def after = ( System.currentTimeMillis() - before ) / 1000;

request.setAttribute 'time', after.toString()
request.setAttribute 'ans', ans.toString()
request.setAttribute 'tab', tab;

log.info "gpars is forwarding list to the template"

forward '/WEB-INF/pages/gpars.gtpl'
