<%-- 
    Document   : checkin
    Created on : 22-giu-2014, 12.23.34
    Author     : Mazzy
--%>

<article class="container_12">
    
    <section class="grid_6" id="checkin">
        <form action="checkin" method="POST">
            <label>PNR (Codice Prenotazione utente)</label>
            <input type="text" name="reserv_id">
            <input type="submit" value="Cerca">
        </form>
    </section>
       
    <div class="clear"></div>  
        
    <section id="result_check_in" class="grid_12">
        
        <p>${notfoundreserv}</p>
        
        <c:if test="${!empty reserv}">
            <p>Clicca sulla prenotazione per effettuare il check-in</p>

            <p id="successCheckin"></p>

            <table class="show_reservations">
                <tr id="checkinRow" class="tableRow">
                    <td id="reservid"><span style="font-weight: 600">${reserv.id}</span></td>
                    <td>${reserv.username.name}</td>
                    <td>${reserv.username.surname}</td>
                    <td>${reserv.route.airport_city_source.city} (${reserv.route.airport_city_source.name})</td>
                    <td>${reserv.route.airport_city_dest.city} (${reserv.route.airport_city_dest.name})</td>
                    <td><fmt:formatDate value="${reserv.route.departure_date}" pattern="dd/MM/yyyy hh:mm"/></td>
                    <td><fmt:formatDate value="${reserv.route.arrival_date}" pattern="dd/MM/yyyy hh:mm"/></td>
                    <td>${reserv.passengers}</td>
                    <td>${reserv.luggages}</td>
                </tr>
            </table>
        </c:if>
    </section>
        
</article>
