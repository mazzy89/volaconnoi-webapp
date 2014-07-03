<%-- 
    Document   : result
    Created on : 17-giu-2014, 10.57.13
    Author     : Mazzy
--%>

<article class="container_12">
    <section class="grid_12 result_box">
        <h4>Risultati ricerca voli</h4>
        <p>Cliccando sul volo inizierà la fase di acquisto.</p>
        <p class="new_search"><a href="<c:url value='/search' />"><img class="glass" src="${pageContext.servletContext.contextPath}/resources/img/glass.png" width="20px" height="20px"><strong>Nuova ricerca</strong></a></p>
        <c:choose>
            <c:when test="${!empty routes_list}">
                <table class="show_reservations">
                    <tr>
                        <th>Compagnia</th>
                        <th>ID Aereomobile </th>
                        <th>Partenza</th>
                        <th>Arrivo</th>
                        <th>Ora partenza</th>
                        <th>Durata</th>
                        <th>Classe</th>
                        <th>Prezzo</th>
                    </tr>
                    <c:forEach items="${routes_list}" var="route" varStatus="iter">
                        <tr class="tableRow"
                            onclick="document.location.href='${pageContext.servletContext.contextPath}/booking?id=${route.id_route}'">
                            <td>${route.airlane}</td>
                            <td>${route.aircraft_id}</td>
                            <td><strong>${route.airport_city_source.city} (${route.airport_city_source.iata_faa_code})</strong></td>
                            <td><strong>${route.airport_city_dest.city} (${route.airport_city_dest.iata_faa_code})</strong></td>
                            <td><span style="font-weight: 600"><fmt:formatDate value="${route.departure_date}" pattern="dd/MM/yyyy hh:mm"/></span></td>
                            <td>${duration_list[iter.index]} h</td>
                            <td>${route.travel_class}</td>
                            <td><strong><fmt:formatNumber type="currency"
                                          currencySymbol="&euro;"
                                          value="${route.price}"/></strong></td>
                        </tr>
                    </c:forEach>
                </table>
            </c:when>
            <c:otherwise>
                <p>Ci scusiamo per l'inconveniente ma non ci sono voli corrispondenti ai parametri di ricerca da Lei inseriti.</p>
            </c:otherwise>
        </c:choose>
    </section>
</article>