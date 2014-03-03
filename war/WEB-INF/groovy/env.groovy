import groovy.json.JsonOutput

log.info "Starting to setting attribute env.groovy"

def va = System.getenv("VCAP_APPLICATION");
def tx = JsonOutput.prettyPrint(va.toString());

va = ""
tx.eachLine{ln ->
	va += ln;
	va += "<br />"
}


request.setAttribute 'va', va.toString()

log.info "Forwarding to the env template"

forward '/WEB-INF/pages/env.gtpl'
