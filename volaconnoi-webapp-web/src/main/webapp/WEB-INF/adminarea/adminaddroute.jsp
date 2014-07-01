<%-- 
    Document   : adminaddflights
    Created on : 13-giu-2014, 21.42.52
    Author     : Mazzy
--%>

    <section class="grid_9 area_principal">
        <h4>Aggiungi volo</h4>
        <div class="body_area_principal add_route rectangle">
            <p><strong>${addSuccess}</strong></p>

            <form action="${pageContext.servletContext.contextPath}/admin/addroute" method="POST">
                
                <label>Compagnia area</label>
                <select name="airlane">
                    <c:forEach items="${airlanes_active_list}" var="airlane">
                        <option value="${airlane.name}">${airlane.name} <c:if test="${!empty airlane.country}">(${airlane.country})</c:if></option>
                    </c:forEach>
                </select>
                
                <label>ID Aeromobile</label><input type="text" name="aircraft_id">

                <label>Aeroporto di partenza</label>
                <select name="airport_city_source">
                    <c:forEach items="${airports_list}" var="airport">
                        <option value="${airport.id}">${airport.city} (${airport.name})</option>
                    </c:forEach>
                </select>

                <label>Aeroporto di arrivo</label>
                <select name="airport_city_dest">
                    <c:forEach items="${airports_list}" var="airport">
                        <option value="${airport.id}">${airport.city} (${airport.name})</option>
                    </c:forEach>
                </select>

                <label for="from">Data partenza</label><input type="text" class="from" name="from">
                <label for="to">Data arrivo</label><input type="text" class="to" name="to">


                <label>Ora partenza <span style="font-size: 10px; font-weight: 300">(ora locale)</span></label>
                hh
                <select name="departure_hour">
                    <c:forEach begin="0" end="23" var="hour">
                        <option value="${hour}">
                            <fmt:formatNumber value="${hour}" type="number" minIntegerDigits="2"/>
                        </option>
                    </c:forEach>
                </select>
                mm
                 <select name="departure_minutes">
                    <c:forEach begin="0" end="55" step="5" var="minutes">
                        <option value="${minutes}">
                            <fmt:formatNumber value="${minutes}" type="number" minIntegerDigits="2"/>
                        </option>
                    </c:forEach>
                </select>

                <label>Ora arrivo  <span style="font-size: 10px; font-weight: 300">(ora locale)</span></label>
                hh
               <select name="arrival_hour">
                    <c:forEach begin="0" end="23" var="hour">
                        <option value="${hour}">
                            <fmt:formatNumber value="${hour}" type="number" minIntegerDigits="2"/>
                        </option>
                    </c:forEach>
                </select>
                mm
                 <select name="arrival_minutes">
                    <c:forEach begin="0" end="55" step="5" var="minutes">
                        <option value="${minutes}">
                            <fmt:formatNumber value="${minutes}" type="number" minIntegerDigits="2"/>
                        </option>
                    </c:forEach>
                </select>

                <label>Classe</label>
                <select name="class_travel">
                    <option value="Economy">Economy</option>
                    <option value="Business">Business</option>
                    <option value="First">First</option>
                </select>

                <label>Posti disponibili</label>
                <input type="number" name="seats">

                <label>Tariffa <span style="font-size: 10px; font-weight: 300">(in Euro)</span></label>
                <input type="number" step="10" name="rate">

                <div>
                    <input type="submit" value="Aggiungi">
                </div>
            </form>
        </div>
    </section>
</article>

