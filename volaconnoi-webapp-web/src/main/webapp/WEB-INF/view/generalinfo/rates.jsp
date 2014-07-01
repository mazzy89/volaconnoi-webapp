<%-- 
    Document   : rates
    Created on : 24-giu-2014, 23.57.52
    Author     : Mazzy
--%>

<article class="cointainer_12">
    
    <section class="grid_12">
        
        <h4>Le tariffe di VolaConNoi.it</h4>
        
        <h6>Vola da:</h6>
        
        <div>
            <ul>    
                <c:forEach items="${city_airport_lowest}" var="entry">
                    <li><span style='font-weight: 600'>${entry.key}</span> a partire da <span style='font-weight: 600'><fmt:formatNumber type="currency"
                                          currencySymbol="&euro;"
                                          value="${entry.value}"/></span></li>
                </c:forEach>
            </ul>
            
        </div>
        
        
    </section>
    
</article>
