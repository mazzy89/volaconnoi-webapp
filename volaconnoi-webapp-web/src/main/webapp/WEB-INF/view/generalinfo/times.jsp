<%-- 
    Document   : times
    Created on : 25-giu-2014, 22.31.39
    Author     : Mazzy
--%>

<article class="container_12">
    
    <section class="grid_12">
        
        <h4>Cerca orari orari dei voli</h4>
        
        <select id="airports_source">
            <option>Seleziona un aeroporto di partenza</option>
            <c:forEach items="${airport_source_list}" var="item">
                <option>${item}</option>
            </c:forEach>
        </select>
        
        <select id="airports_dest">
            <option>Seleziona un aeroporto di destinazione</option>  
        </select>
        
    </section>
    
    <div class='clear'></div>
    
    <section class='grid_12' style='margin-top: 32px;'>
        
        <div id='result_times'>
            
            <table class='show_reservations'>
                
                
            </table>
            
        </div>
        
        
    </section>
    
</article>

<script src="${pageContext.servletContext.contextPath}/resources/js/route.js"></script>
