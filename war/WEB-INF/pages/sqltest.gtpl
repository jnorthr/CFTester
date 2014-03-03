<% include '/WEB-INF/includes/header.gtpl' %>

<h1>SQL Test Using H2Mem</h1>

<p>
    <%
        log.info "calling the sqltest attributes"
		def lines = request.getAttribute('sqltest')
    %>
    The sql test: 
	<ul>
		<% lines.eachLine{ ln -> %>
		<li><%= ln %></li>
		<% } %>
	</ul>
</p>

<% include '/WEB-INF/includes/footer.gtpl' %>

