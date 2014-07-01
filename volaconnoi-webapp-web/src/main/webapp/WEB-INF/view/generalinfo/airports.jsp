<%-- 
    Document   : airports
    Created on : 25-giu-2014, 12.49.53
    Author     : Mazzy
--%>

<article class="container_12">
    
    <aside class="grid_12">
        <h4>Cerca areoporto</h4>
        
        <select id="airportscountries">
            <option>Seleziona nazione</option>
            <c:forEach items="${CountriesList}" var="country">
                <option>${country}</option>
            </c:forEach>        
        </select>
        
        <select id="airportscities">
            <option>Seleziona una città</option>
        </select>
        
    </aside>
    
    <div class="clear"></div>
    
    <section class="grid_12">
        
        <div id="map_canvas">
        
        </div>
        
    </section>
    
</article>
        
<script src="${pageContext.servletContext.contextPath}/resources/js/map.js"></script>
