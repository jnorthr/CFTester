<% include '/WEB-INF/includes/header.gtpl' %>

<h1>VCAP_SERVICES</h1>
<pre>
<%= request.getAttribute('vs') %>
</pre>

<% include '/WEB-INF/includes/footer.gtpl' %>

