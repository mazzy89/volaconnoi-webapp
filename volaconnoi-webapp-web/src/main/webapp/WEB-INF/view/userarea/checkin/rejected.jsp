<%-- 
    Document   : rejected
    Created on : 25-giu-2014, 10.43.56
    Author     : Mazzy
--%>

<section class="grid_10 area_principal">
    <h4>I tuoi voli rifiutati</h4>
    <div class="body_area_principal">
        <c:choose>
            <c:when test="${!empty flights_rejected}">
                <table class="show_reservations">
                    <tr>
                        <th>PNR</th>
                        <th>Compagnia</th>
                        <th>ID Veivolo</th>
                        <th>Partenza</th>
                        <th>Arrivo</th>
                        <th>Ora partenza (ora locale)</th>
                        <th>Ora arrivo (ora locale)</th>
                        <th>Passeggeri<th>
                        <th>Classe</th>
                    </tr>
                    <c:forEach items="${flights_rejected}" var="flights">
                        <tr>
                            <td>${flights.id}</td>
                            <td>${flights.airlane}</td>
                            <td>${flights.route.aircraft_id}</td>
                            <td>${flights.route.airport_city_source.city}</td>
                            <td>${flights.route.airport_city_dest.city}</td>
                            <td><fmt:formatDate value="${flights.route.departure_date}" pattern="dd/MM/yyyy HH:mm"/></td>
                            <td><fmt:formatDate value="${flights.route.arrival_date}" pattern="dd/MM/yyyy HH:mm"/></td>
                            <td>${flights.passengers}</td>
                            <td>${flights.route.travel_class}</td>
                        </tr>
                    </c:forEach>
                </table>
            </c:when>
            <c:otherwise>
                <p>Non hai rifiutato alcun volo.</p>
            </c:otherwise>
        </c:choose>
    </div>
 </section>
</article>
