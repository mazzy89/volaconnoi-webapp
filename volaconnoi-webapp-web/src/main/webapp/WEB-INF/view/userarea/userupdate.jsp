        <section class="grid_10 update_form">
            <h4>I tuoi dati</h4>
            <p><strong>${successUpdate}</strong></p>
            <form action="${pageContext.servletContext.contextPath}/user/update" method="POST">
                <div>
                    <label>Vecchia password</label>
                    <input type="password" name="old_password">
                    <c:if test="${!empty oldPasswordError}">
                        <p class="errorSignUp">Errore: la password inserita non corrisponde con la vecchia password</p>
                    </c:if>
                </div>
                <div>
                    <label>Nuova password</label>
                    <input type="password" name="new_password">
                </div>
                <div>
                    <label>Conferma nuova password</label>
                    <input type="password" name="confirm_new_password">
                    <c:if test="${!empty newPasswordNotMatched}">
                        <p class="errorSignUp">Errore: le password inserite non corrispondono</p>
                    </c:if>
                </div>
                <div>
                    <label>E-mail</label>
                    <input type="email" name="email" value="${email}">
                    <c:if test="${!empty emailNotValidError}">
                        <p class="errorSignUp">Errore: l'email inserita non è valida</p>
                    </c:if>
                </div>
                
                <br/>
                
                <h6>Dati Personali</h6>
                    <div>
                        <label>Nome</label>
                        <input type="text" name="name" value="${name}" readonly>
                    </div>
                    <div>
                        <label>Cognome/i</label>
                        <input type="text" name="surname" value="${surname}" readonly>
                    </div>
                    <div>
                        <label>Indirizzo</label>
                        <input type="text" name="address" value="${address}">
                        <c:if test="${!empty addressErrorInvalid}">
                            <p class="errorSignUp">Errore: inserisci un indirizzo</p>
                        </c:if>
                    </div>
                    <div>
                        <label>Città</label>
                        <input type="text" name="city" value="${city}">
                         <c:if test="${!empty cityErrorInvalid}">
                            <p class="errorSignUp">Errore: inserisci una città</p>
                        </c:if>
                    </div>
                    <div>
                        <label>CAP</label>
                        <input type="text" name="zip_code" value="${zip_code}">
                        <c:if test="${!empty zipCodeErrorInvalid}">
                            <p class="errorSignUp">Errore: inserisci un CAP</p>
                        </c:if>
                    </div>
                    <div>
                        <label>Paese</label>
                        <select name="country">
                            <option value="${country_user}">${country_user}</option>
                            <c:forEach items="${CountriesList}" var="country">
                                <c:if test="${country.nicename ne country_user}" >    
                                    <option value="${country.nicename}">${country.nicename}</option>
                                </c:if>
                            </c:forEach>
                        </select>
                    </div>
                    <div>
                        
                        <label>Fisso <span style="font-size:smaller">(svuotare il campo per eliminare il numero dal sistema)</span></label>
                        <select name="homenumber_code">
                            <c:if test="${!empty home_number.phone_number}" >
                                <option value="${home_number.area_code}"> +${home_number.area_code}</option>
                            </c:if>
                            <c:forEach items="${CountriesList}" var="country">
                                    <option value="${country.phonecode}">${country.nicename} (+${country.phonecode})</option>
                            </c:forEach>
                        </select>
                        <input type="text" name="homenumber" value="${home_number.phone_number}">
                    </div>
                        
                        
                    <div>
                        <label>Mobile</label>
                        <select name="mobilenumber_code">
                            <option value="${mobile_number.area_code}"> +${mobile_number.area_code}</option>
                            
                            <c:forEach items="${CountriesList}" var="country">
                                <option value="${country.phonecode}">${country.nicename} (+${country.phonecode})</option>
                            </c:forEach>
                        </select>
                        <input type="text" name="mobilenumber" value="${mobile_number.phone_number}">
                         <c:if test="${!empty mobileNumberErrorInvalid}">
                            <p class="errorSignUp">Errore: inserisci un numero mobile</p>
                        </c:if>
                    </div>
                    
                    <div>
                        <input type="submit" value="Aggiorna"/>
                    </div>
            </form>
        </section>
    </article>