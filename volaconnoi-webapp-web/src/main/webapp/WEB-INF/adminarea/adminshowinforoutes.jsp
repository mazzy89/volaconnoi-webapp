<%-- 
    Document   : adminshowinforoute
    Created on : 15-giu-2014, 18.04.41
    Author     : Mazzy
--%>

<section class="grid_9 area_principal">
    <h4>Voli</h4>
    <div class="body_area_principal">
        <p>Clicca su una riga per accedere alle info e prenotazioni relative al volo.</p>
        <c:choose>
            <c:when test="${!empty routes_list}">
                <table class="show_reservations">
                    <tr>
                        <th>Compagnia</th>
                        <th>ID Veivolo</th>
                        <th>Partenza</th>
                        <th>Arrivo</th>
                        <th>Ora partenza (ora locale)</th>
                        <th>Ora arrivo (ora locale)</th>
                    </tr>
                    <c:forEach items="${routes_list}" var="route" varStatus="iter">
                        <tr class="${((iter.index % 2) == 1) ? 'lightBlue' : 'white'} tableRow"
                            onclick="window.open(this.href='${pageContext.servletContext.contextPath}/admin/showreservations?id=${route.id_route}',
                                                 'targetWindow','toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=1110px,height=500px');
                                                 return false;">
                            <td>${route.airlane}</td>
                            <td>${route.aircraft_id}</td>
                            <td>${route.airport_city_source.city} (${route.airport_city_source.name})</td>
                            <td>${route.airport_city_dest.city} (${route.airport_city_dest.name})</td>
                            <td><fmt:formatDate value="${route.departure_date}" pattern="dd/MM/yyyy hh:mm"/></td>
                            <td><fmt:formatDate value="${route.arrival_date}" pattern="dd/MM/yyyy hh:mm"/></td>
                        </tr>
                    </c:forEach>
                </table>
            </c:when>
            <c:otherwise>
                <p>Non ci sono voli nel sistema. Aggiungerli attraverso la voce presente nel menù sulla sinistra</p>
            </c:otherwise>
        </c:choose>
    </div>
 </section>