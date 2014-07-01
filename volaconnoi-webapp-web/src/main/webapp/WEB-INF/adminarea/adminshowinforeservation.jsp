<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html>
<html>
  
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resources/css/reset.css">
        <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resources/css/text.css">
        <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resources/css/960.css">
        <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resources/css/style.css">

        <link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
        <script src="//code.jquery.com/jquery-1.10.2.js"></script>
        <script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
    </head>
    
    <body>

        <article class="container_12">     
            
            <section class="grid_12 area_principal">
                <h4>Info volo ${route.aircraft_id} - 
                    Da: ${route.airport_city_source.city} 
                    A: ${route.airport_city_dest.city} 
                    del <fmt:formatDate value="${route.departure_date}" pattern="dd/MM/yyyy"/> 
                    ore <fmt:formatDate value="${route.departure_date}" pattern="hh:mm"/></h4>
                <div class="body_area_principal">
                    <p>Classe: ${route.travel_class}</p>
                    <p>Posti disponibili: ${route.seats}</p>
                    <p>Incasso: <fmt:formatNumber type="currency"
                                          currencySymbol="&euro;"
                                          value="${total_builin_reserv}"/></p> 
                    <c:choose>
                        <c:when test="${!empty reservations_list}">
                            <table class="show_reservations">
                                <tr>
                                    <th>Nome</th>
                                    <th>Cognome</th>
                                    <th># Passegeri</th>
                                    <th># Bagagli</th>
                                    <th>Data effettuata</th>
                                </tr>
                                <c:forEach items="${reservations_list}" var="reservation">
                                    <tr>
                                        <td>${reservation.username.name}</td>
                                        <td>${reservation.username.surname}</td>
                                        <td>${reservation.passengers}</td>
                                        <td>${reservation.luggages}</td>
                                        <td><fmt:formatDate value="${reservation.date_reservation}" pattern="dd/MM/yyyy hh:mm"/></td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </c:when>
                        <c:otherwise>
                            <p>Non ci sono prenotazioni effettuate per il seguente volo.</p>
                        </c:otherwise>
                    </c:choose>
                </div>
            </section>
                    
        </article>
                                       
    </body>
</html>
