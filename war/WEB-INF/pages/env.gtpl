<% include '/WEB-INF/includes/header.gtpl' %>

<h1>VCAP_APPLICATION</h1>
<pre>
<%= request.getAttribute('va') %>
</pre>
<% include '/WEB-INF/includes/footer.gtpl' %>

