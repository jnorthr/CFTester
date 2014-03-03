<% include '/WEB-INF/includes/header.gtpl' %>

<h1>GPars 2 Asynch Processing</h1>


<h2>Audit Trail</h2>
<%= request.getAttribute('tab') %>

<h2>Timings - All Tests</h2>
<p>Elapsed time: <%= request.getAttribute('time') %> seconds</p>

<% include '/WEB-INF/includes/footer.gtpl' %>

<%
    log.info "finished building the gpars2 template"
%>

