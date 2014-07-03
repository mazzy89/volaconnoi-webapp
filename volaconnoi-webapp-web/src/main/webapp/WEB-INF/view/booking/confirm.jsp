<%-- 
    Document   : confim
    Created on : 19-giu-2014, 20.13.34
    Author     : Mazzy
--%>

<article class="container_12">
    <section class="grid_9 confirmation">
        
        <div id="commit_body">   
            <h4>Confermi qui la sua prenotazione</h4>
            <h5>Ecco qui le sue info per il suo volo ${user.name} ${user.surname}</h5>

            <div class="body_confirmation">
                <p class="info_route">Info volo</p>
                <p id="id_route" style="display: none">${route.id_route}</p>
                <p><strong>Compagnia:</strong> ${route.airlane}</p>
                <p><strong>Partenza da: </strong> ${route.airport_city_source.city}</p>
                <p><strong>Arrivo a:</strong> ${route.airport_city_dest.city}</p>
                <p><strong>Ora partenza:</strong> <fmt:formatDate value="${route.departure_date}" pattern="dd/MM/yyyy HH:mm"/></p>
                <p><strong>Ora arrivo:</strong> <fmt:formatDate value="${route.arrival_date}" pattern="dd/MM/yyyy HH:mm"/></p>
                <p><strong>Prezzo: </strong><span id="price_final"><fmt:formatNumber type="currency" 
                value="${price}" pattern="####.##" /></span> &euro;</p>

                <p><strong>N. Passeggeri:</strong> ${passengers}</p>
                <p><strong>N. Bagagli:</strong> ${luggages}</p>
            </div>

            <form id="commit_form" action="commit" method="POST">
                <c:if test="${user.fidelity_points != 0}">
                    <p>Hai a disposizione ${user.fidelity_points} punti fedeltà</label>
                    <p>Puoi riscattare i tuoi punti ora</p>
                    <p>Quanti punti vuoi riscattare?</p>
                    <input type="number" value="0" min="0" max="${user.fidelity_points}" id="points" name="points">
                </c:if><br/><br/>
                <input type="submit" value="Conferma">
            </form>
            <br/>
            <p>Proseguendo acquisterà il volo e Le sarà addebitato il costo totale nella sua carta di credito</p>
        </div>
            
    </section>
</article>
            
<script src="${pageContext.servletContext.contextPath}/resources/js/commitform.js"></script>
