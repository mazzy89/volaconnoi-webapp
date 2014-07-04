<%-- 
    Document   : valid
    Created on : 22-giu-2014, 10.11.24
    Author     : Mazzy
--%>

<section class="grid_10 area_principal">
    
    <h4>Le tue prenotazioni</h4>
    
    <div class="body_area_principal">
        <c:choose>
            <c:when test="${!empty reservations_user_valid}">
                <table class="show_reservations">
                    <tr>
                        <th>PNR</th>
                        <th>Compagnia</th>
                        <th>ID Veivolo</th>
                        <th>Partenza</th>
                        <th>Arrivo</th>
                        <th>Ora partenza <br/>(ora locale)</th>
                        <th>Ora arrivo <br/>(ora locale)</th>
                        <th>Passeggeri</th>
                        <th>Bagagli</th>
                        <th>Cancella</th>
                    </tr>
                    <c:forEach items="${reservations_user_valid}" var="reserv">
                        <tr>
                            <td><span style="font-weight: 600">${reserv.id}</span></td>
                            <td>${reserv.route.airlane}</td>
                            <td>${reserv.route.aircraft_id}</td>
                            <td>${reserv.route.airport_city_source.city} (${reserv.route.airport_city_source.name})</td>
                            <td>${reserv.route.airport_city_dest.city} (${reserv.route.airport_city_dest.name})</td>
                            <td><fmt:formatDate value="${reserv.route.departure_date}" pattern="dd/MM/yyyy HH:mm"/></td>
                            <td><fmt:formatDate value="${reserv.route.arrival_date}" pattern="dd/MM/yyyy HH:mm"/></td>
                            <td>${reserv.passengers}</td>
                            <td>${reserv.luggages}</td>
                            <td><a href="<c:url value="cancel?id=${reserv.id}"/>">Cancella</td>
                        </tr>
                    </c:forEach>
                </table>
            </c:when>
            <c:otherwise>
                <p>Non hai effettuato ancora alcuna prenotazioni.</p>
            </c:otherwise>
        </c:choose>
    </div>
 </section>
</article>