<%-- 
    Document   : cancelled
    Created on : 22-giu-2014, 11.23.05
    Author     : Mazzy
--%>

<section class="grid_10 area_principal">
    <h4>Prenotazioni cancellate</h4>
    <div class="body_area_principal">
        <c:choose>
            <c:when test="${!empty reservations_user_cancelled}">
                <table class="show_reservations">
                    <tr>
                        <th>PNR</th>
                        <th>Compagnia</th>
                        <th>ID Veivolo</th>
                        <th>Partenza</th>
                        <th>Arrivo</th>
                        <th>Ora partenza <span style="font-size: 0.8em">(ora locale)</span></th>
                        <th>Ora arrivo <span style="font-size: 0.8em">(ora locale)</span></th>
                        <th>Passeggeri</th>
                        <th>Classe</th>
                    </tr>
                    <c:forEach items="${reservations_user_cancelled}" var="reserv" varStatus="iter">
                        <tr>
                            <td><span style="font-weight: 600">${reserv.id}</span></td>
                            <td>${reserv.route.airlane}</td>
                            <td>${reserv.route.aircraft_id}</td>
                            <td>${reserv.route.airport_city_source.city}</td>
                            <td>${reserv.route.airport_city_dest.city}</td>
                            <td><fmt:formatDate value="${reserv.route.departure_date}" pattern="dd/MM/yyyy HH:mm"/></td>
                            <td><fmt:formatDate value="${reserv.route.arrival_date}" pattern="dd/MM/yyyy HH:mm"/></td>
                            <td>${reserv.passengers}</td>
                            <td>${reserv.route.travel_class}</td>
                        </tr>
                    </c:forEach>
                </table>
            </c:when>
            <c:otherwise>
                <p>Non ci sono prenotazioni cancellate.</p>
            </c:otherwise>
        </c:choose>
    </div>
 </section>
</article>
