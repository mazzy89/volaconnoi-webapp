<%-- 
    Document   : userdatainfo
    Created on : 11-giu-2014, 13.07.40
    Author     : Mazzy
--%>

    <section class="grid_10 area_principal">
        <h4>I tuoi dati anagrafici ${pageContext.request.userPrincipal.name}</h4>
        <div class="body_area_principal user_info rectangle">
            <ul>
                <li><span>Nome: </span> ${name}</li>
                <li><span>Cognome: </span> ${surname}</li>
                <li><span>Indirizzo: </span> ${address}</li>
                <li><span>Città:</span> ${city} - <span>CAP:</span> ${zip_code}</li>
                <li><span>Paese:</span> ${country}</li>
            </ul>
            <ul>
                <c:forEach items="${phoneNumbersList}" var="phoneNumber">
                    <c:if test="${!empty phoneNumber.phone_number}">
                        <li><span>${phoneNumber.type}:</span> +${phoneNumber.area_code} ${phoneNumber.phone_number}</li>
                    </c:if>
                </c:forEach>
            </ul>
        </div>
    </section>
</article>
