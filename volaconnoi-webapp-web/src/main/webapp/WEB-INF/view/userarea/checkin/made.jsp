<%-- 
    Document   : valid
    Created on : 25-giu-2014, 9.34.03
    Author     : Mazzy
--%>


<section class="grid_10 area_principal">
    <h4>I tuoi voli effettuati</h4>
    <div class="body_area_principal">
        <c:choose>
            <c:when test="${!empty flights_made}">
                <table class="show_reservations">
                    <tr>
                        <th>PNR</th>
                        <th>Compagnia</th>
                        <th>ID Veivolo</th>
                        <th>Partenza</th>
                        <th>Arrivo</th>
                        <th>Ora partenza (ora locale)</th>
                        <th>Ora arrivo (ora locale)</th>
                        <th>Passeggeri</th>
                        <th>Bagagli</th>
                        <th>Classe</th>
                    </tr>
                    <c:forEach items="${flights_made}" var="flights">
                        <tr>
                            <td><span style="font-weight: 600">${flights.id}</span></td>
                            <td>${flights.route.airlane}</td>
                            <td>${flights.route.aircraft_id}</td>
                            <td>${flights.route.airport_city_source.city}</td>
                            <td>${flights.route.airport_city_dest.city}</td>
                            <td><fmt:formatDate value="${flights.route.departure_date}" pattern="dd/MM/yyyy HH:mm"/></td>
                            <td><fmt:formatDate value="${flights.route.arrival_date}" pattern="dd/MM/yyyy HH:mm"/></td>
                            <td>${flights.passengers}</td>
                            <td>${flights.luggages}</td>
                            <td>${flights.route.travel_class}</td>
                        </tr>
                    </c:forEach>
                </table>
            </c:when>
            <c:otherwise>
                <p>Non hai effettuato ancora alcun volo</p>
            </c:otherwise>
        </c:choose>
    </div>
 </section>
</article>