<% include '/WEB-INF/includes/header.gtpl' %>

<h1>GPars Parallel Processing</h1>

<p>
<pre>
    Closure calculate = {def a = 1.7393f / 0.00414f; }
    
    Closure longLastingCalculation = {calculate();}

    Closure fastCalculation = longLastingCalculation.async()  
    //create a new closure, which starts the original closure on a thread pool

    //returns almost immediately
    Future result5=fastCalculation()
    
    //do stuff while calculation performs
    sleep(1000);

    list += "&lt;br /&gt;&lt;hr /&gt;&lt;br /&gt;";	    
    list += result5.get()
    list += "&lt;br /&gt;&lt;hr /&gt;&lt;br /&gt;";	    
</pre>
</p>
    
<h2>Result</h2>
<p>Using gpars features, the result of this groovy calculation is <%= request.getAttribute('ans') %></p>


<h2>URL Parallel Access</h2>
<%= request.getAttribute('tab') %>

<h2>Timings - All Tests</h2>
<p>Elapsed time: <%= request.getAttribute('time') %> seconds</p>

<% include '/WEB-INF/includes/footer.gtpl' %>

<%
    log.info "finished building the gpars template"
%>

