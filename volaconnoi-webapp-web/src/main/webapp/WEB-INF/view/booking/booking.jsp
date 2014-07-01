<%-- 
    Document   : booking
    Created on : 18-giu-2014, 11.22.34
    Author     : Mazzy
--%>

<article class="container_12"> 
    <section class="grid_9 omega rectangle_area_booking">
        <div class="info_route_user">
            <form action="confirm" method="POST">
                <div class="float_left_info_route_user">
                    <h4>Dati utente</h4>

                    <div>  
                        <input type="text" name="username" placeholder="Username" <c:if test="${!empty pageContext.request.userPrincipal.name}">value="${user.username}" readonly</c:if>>
                        <c:if test="${empty pageContext.request.userPrincipal.name}">
                            <input type="password" name="password" placeholder="Password">
                        </c:if>
                    </div>
                    <div>
                        <input type="email" name="email" placeholder="Email" <c:if test="${!empty pageContext.request.userPrincipal.name}">value="${user.email}" readonly</c:if>>
                    </div>
                    <div>
                        <input type="text" name="name" placeholder="Nome" <c:if test="${!empty pageContext.request.userPrincipal.name}">value="${user.name}" readonly</c:if>>
                        <input type="text" name="surname" placeholder="Cognome" <c:if test="${!empty pageContext.request.userPrincipal.name}">value="${user.surname}" readonly</c:if>>
                    </div>

                    <div>
                        <input type="text" name="address" placeholder="Indirizzo" <c:if test="${!empty pageContext.request.userPrincipal.name}">value="${user.address}" readonly</c:if>>
                    </div>

                    <div>
                        <input type="text" name="city" placeholder="Città" <c:if test="${!empty pageContext.request.userPrincipal.name}">value="${user.city}" readonly</c:if>>
                        <input style="width:65px;" type="text" name="zip_code" placeholder="CAP" <c:if test="${!empty pageContext.request.userPrincipal.name}">value="${user.zip_code}" readonly</c:if>>
                    </div>

                    <div>
                        <select name="country">
                            <c:choose>
                                <c:when test="${empty pageContext.request.userPrincipal.name}">
                                    <option value="">Seleziona un paese</option>
                                    <c:forEach items="${CountriesList}" var="country">
                                        <option value="${country.nicename}">${country.nicename}</option>
                                    </c:forEach>
                                </c:when>
                                <c:otherwise>
                                    <option value="${user.country}">${user.country}</option>
                                </c:otherwise>
                            </c:choose>        
                        </select>     
                    </div>

                    <div>
                        <select name="mobilenumber_code">
                            <c:choose>
                                <c:when test="${empty pageContext.request.userPrincipal.name}">
                                    <option value="">Seleziona un prefisso internazionale</option>
                                    <c:forEach items="${CountriesList}" var="country">
                                        <option value="${country.phonecode}">${country.nicename} (+${country.phonecode})</option>
                                    </c:forEach>
                                </c:when>
                                <c:otherwise>
                                    <c:forEach items="${user.phoneNumbers}" var="phoneNumber">
                                        <c:if test="${phoneNumber.type == 'Mobile'}">
                                            <c:set var="mobile" scope="page" value="${phoneNumber.phone_number}"/>
                                            <option value="${phoneNumber.area_code}">+${phoneNumber.area_code}</option>
                                        </c:if>
                                    </c:forEach>
                                </c:otherwise>
                            </c:choose>
                        </select> 
                    </div>

                    <div>
                        <input type="text" name="mobilenumber" placeholder="Mobile" <c:if test="${!empty pageContext.request.userPrincipal.name}">value="${mobile}" readonly</c:if>>
                    </div>

                    <div>
                        <label>Passeggeri</label><br/>
                        <input id="passengers" type="number" min="1" max="${route.seats}" name="passengers" placeholder="Passeggeri"  value="1" style="width:50px; margin-right: 10px">
                        
                        <label>Bagagli*</label>
                        <select id="luggages" name="luggages">
                            <option value="0">0</option>
                            <option value="1">1</option>
                            <option value="2">2</option>
                            <option value="3">3</option>
                            <option value="4">4</option>
                            <option value="1">5</option>
                        </select><br/><br/>
                        <p style='font-size: 0.8em'>(*) Ogni bagaglio comporta una sovratassa di <fmt:formatNumber type="currency"
                                          currencySymbol="&euro;"
                                          value="${tax}"/></p>
                    </div>
                    
                    <div>
                        <input type="submit" value="Prosegui"/>     
                    </div>
                </div>
                    
                <div class="float_right_info_route_user">
                    <h4>Dati di pagamento</h4>
                    
                    <img src="${pageContext.servletContext.contextPath}/resources/img/credit-card.png" width="220px" />
                    
                    <input type="text" name="credit_number" placeholder="Numero carta">
                    
                    <div>
                        <label>Data Scadenza</label><br/>
                        <select>
                            <c:forEach var="i" begin="1" end="12">
                                <option>${i}</option>
                            </c:forEach>
                        </select>
                        <select>
                            <c:forEach var="i" begin="2014" end="2022">
                                <option>${i}</option>
                            </c:forEach>                    
                        </select>
                        </div>
                    <div>
                        <input type="text" name="cv2" placeholder="Codice CV2">
                    </div>
                </div>
            </form>
        </div>
    </section>
                
    <aside class="grid_3 rectangle_area_booking_aside">
        <div class="info_route_aside">
            <p class="info_route">Info volo</p>
            <p id="id_route" style="display: none">${route.id_route}</p>
            <p><strong>Compagnia:</strong> ${route.airlane}</p>
            <p><strong>Partenza da: </strong> ${route.airport_city_source.city}</p>
            <p><strong>Arrivo a:</strong> ${route.airport_city_dest.city}</p>
            <p><strong>Ora partenza:</strong> <fmt:formatDate value="${route.departure_date}" pattern="dd/MM/yyyy hh:mm"/></p>
            <p><strong>Ora arrivo:</strong> <fmt:formatDate value="${route.arrival_date}" pattern="dd/MM/yyyy hh:mm"/></p>
            <p><strong>Prezzo: </strong><span id="price">${route.price}</span> &euro;</p>
        </div>
    </aside>
        
</article>