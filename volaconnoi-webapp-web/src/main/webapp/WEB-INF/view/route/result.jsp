<%-- 
    Document   : result
    Created on : 17-giu-2014, 10.57.13
    Author     : Mazzy
--%>

<article class="container_12">
    <section class="grid_12 result_box">
        <h4>Risultati ricerca voli</h4>
        <p>Cliccando sul volo inizier� la fase di acquisto.</p>
        <p class="new_search"><a href="<c:url value='/search' />"><img class="glass" src="${pageContext.servletContext.contextPath}/resources/img/glass.png" width="20px" height="20px"><strong>Nuova ricerca</strong></a></p>
        <c:choose>
            <c:when test="${!empty routes_list}">
                <table class="show_reservations">
                    <c:forEach items="${routes_list}" var="route" varStatus="iter">
                        <tr class="tableRow"
                            onclick="document.location.href='${pageContext.servletContext.contextPath}/booking?id=${route.id_route}'">
                            <td>${route.airlane}</td>
                            <td>${route.aircraft_id}</td>
                            <td><strong>${route.airport_city_source.city} (${route.airport_city_source.name})</strong></td>
                            <td><strong>${route.airport_city_dest.city} (${route.airport_city_dest.name})</strong></td>
                            <td><fmt:formatDate value="${route.departure_date}" pattern="dd/MM/yyyy hh:mm"/></td>
                            <td><fmt:formatDate value="${route.arrival_date}" pattern="dd/MM/yyyy hh:mm"/></td>
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