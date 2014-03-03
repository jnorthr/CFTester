import groovy.json.JsonOutput
log.info "Starting to setting attribute services.groovy"

def vs = System.getenv("VCAP_SERVICES");
def tx = JsonOutput.prettyPrint(vs.toString());

vs = ""
tx.eachLine{ln ->
	vs += ln;
	vs += "<br />"
}

request.setAttribute 'vs', vs.toString()
log.info "Forwarding to the services template"
forward '/WEB-INF/pages/services.gtpl'
