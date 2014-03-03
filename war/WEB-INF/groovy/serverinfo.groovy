log.info "Starting to setting attribute serverinfo.groovy"

def method = request.method

if (!session) {
    session = request.getSession(true)
}

if (!session.groovlet) {
    session.groovlet = 'Caelyf Rox !'
}

int i = 0;
def attrs="";
def attr = context.getAttributeNames();
attr.each {
  i+=1;
  attrs += "<li>${it}</li>"
}
if (i<1) 
{
	attrs+="<li>No context attribute names were found</li>"
}

def reqs=""
def at = (request.getAuthType()) ? request.getAuthType() : "unknown";
def cp = request.getContextPath()
def gm = request.getMethod()
def pi = (request.getPathInfo()) ? request.getPathInfo() : "unknown";
reqs += "<li>Authentication Type : ${at}</li>"
reqs += "<li>Context Path : ${cp}</li>"
reqs += "<li>HTTP Method : ${gm}</li>"
reqs += "<li>Path Info : ${pi}</li>"
                
                
i=0;
def hdrs=""
headers.each {
  i+=1;
  hdrs += "<li>${it.key} = ${it.value}</li>"
}
if (i<1) 
{
	hdrs+="<li>No headers were found</li>"
}

def pms=""
i = 0;
params.each {
  i+=1;
  pms += "<li>Parameter ${it.key} = ${it.value}</li>"
}
if (i<1) 
{
	pms+="<li>No client provided parameters were found</li>"
}

def cookies = request.getCookies()
i=0;
def cook=""
cookies.each {
  i+=1;
  def note = (it.comment) ? it.comment : "none";
  def domain = (it.domain) ? it.domain : "unknown";
  def age = (it.maxAge) ? it.maxAge.toString() : "unknown"
  def dir = (it.path) ? it.path : "unknown";

  cook += "<li>${it.name} = ${it.value}<br />Note : ${note}<br />Domain : ${domain} from path ${dir} and age in sec.s : ${age}<br />Protocol version:${it.version} - secure : ${it.secure}</li>"
}
if (i<1) 
{
	cook+="<li>No cookies were found</li>"
}

context.setAttribute 'version', '1.0'
request.setAttribute 'reqs', reqs.toString()
request.setAttribute 'cook', cook.toString()
request.setAttribute 'hdrs', hdrs.toString()
request.setAttribute 'pms', pms.toString()
request.setAttribute 'method', method.toString()
request.setAttribute 'app', context.getServerInfo().trim().toString()
request.setAttribute 'con', attrs.toString()

log.info "Forwarding to the serverinfo template"
forward '/WEB-INF/pages/serverinfo.gtpl'
