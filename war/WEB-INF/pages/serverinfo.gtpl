<% include '/WEB-INF/includes/header.gtpl' %>

		
<h1>COMMS INFO</h1>

        <h2>Headers</h2>
        <ul>
        	<%= request.getAttribute('hdrs') %>
        </ul>
        
        <h2>Request Info</h2>
        <ul>
        	<%= request.getAttribute('reqs') %>
        </ul>

        <h2>Params</h2>
        <ul>
        	<%= request.getAttribute('pms') %>
        </ul>
        
		<h2>Cookies</h2>
		<pre>
			<%= request.getAttribute('cook') %>
		</pre>

<hr />
<br />
<h1>WEB SERVER &amp; CONTAINER INFO</h1>

        <h2>General Info</h2>
        <ul>
            <li>Method: <%= request.getAttribute('method') %></li>
            <li>RequestURI: <%= request.requestURI %></li>
            <li>session.groovlet: <%= session.groovlet %></li>
            <li>application.version: <%= context.version %></li>
        </ul>
        
        <h2>Server</h2>
		<pre>
			<%= request.getAttribute('app') %>
		</pre>
        
		<h2>Context</h2>
		<pre>
			<%= request.getAttribute('con') %>
		</pre>

		<h2>Redis</h2>
		<pre>
			<%= redis %>
			<%= hasRedis %>
		</pre>

<% include '/WEB-INF/includes/footer.gtpl' %>

