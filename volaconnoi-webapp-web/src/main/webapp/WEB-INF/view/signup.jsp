<%-- 
    Document   : signup
    Created on : 8-giu-2014, 12.18.42
    Author     : Mazzy
--%>

    <article class="container_12">
        <section class="grid_5 form_input">
            <h4>Registrazione membri</h4>
            <p class="fieldsrequired">(*) Campi richiesti</p>
            <h6>I tuoi dati d'accesso su VolaConNoi.it</h6>
            <form action="signup" method="POST">
                <div>
                    <label>Username*</span></label>
                    <input type="text" name="username" autofocus>
                    <c:if test="${!empty usernameInvalid}">
                        <p class="errorSignUp">Errore: inserisci un username valido</p>
                    </c:if>
                    <c:if test="${!empty usernameAlreadyExist}">
                        <p class="errorSignUp">Errore: l'username è già in uso</p>
                    </c:if>
                </div>
                <div>
                    <label>Password*</label>
                    <input type="password" name="password">
                    <c:if test="${!empty passwordInvalid}">
                        <p class="errorSignUp">Errore: inserisci una password</p>
                    </c:if>
                </div>
                <div>
                    <label>Conferma Password*</label>
                    <input type="password" name="confirm_password">
                    <c:if test="${!empty confirmPasswordError}">
                        <p class="errorSignUp">Errore: le password non coincidono</p>
                    </c:if>
                </div>
                <div>
                    <label>E-mail*</label>
                    <input type="email" name="email">
                    <c:if test="${!empty emailErrorInvalid}">
                        <p class="errorSignUp">Errore: inserisci una e-mail valida</p>
                    </c:if>
                </div>
                
                <br/>
                
                <h6>Dati Personali</h6>
                    <div>
                        <label>Nome*</label>
                        <input type="text" name="name">
                        <c:if test="${!empty nameErrorInvalid}">
                            <p class="errorSignUp">Errore: inserisci un nome</p>
                        </c:if>
                    </div>
                    <div>
                        <label>Cognome/i*</label>
                        <input type="text" name="surname">
                        <c:if test="${!empty surnameErrorInvalid}">
                            <p class="errorSignUp">Errore: inserisci un cognome</p>
                        </c:if>
                    </div>
                    <div>
                        <label>Indirizzo*</label>
                        <input type="text" name="address">
                        <c:if test="${!empty addressErrorInvalid}">
                            <p class="errorSignUp">Errore: inserisci un indirizzo</p>
                        </c:if>
                    </div>
                    <div>
                        <label>Città*</label>
                        <input type="text" name="city">
                        <c:if test="${!empty cityErrorInvalid}">
                            <p class="errorSignUp">Errore: inserisci una città</p>
                        </c:if>
                    </div>
                    <div>
                        <label>CAP*</label>
                        <input type="text" name="zip_code">
                        <c:if test="${!empty zipCodeErrorInvalid}">
                            <p class="errorSignUp">Errore: inserisci un CAP</p>
                        </c:if>
                    </div>
                    <div>
                        <label>Paese*</label>
                        <select name="country">
                            <option value="">Seleziona un paese</option>
                            <c:forEach items="${CountriesList}" var="country">
                                <option value="${country.nicename}">${country.nicename}</option>
                            </c:forEach>
                        </select>
                        <c:if test="${!empty countryErrorInvalid}">
                            <p class="errorSignUp">Errore: seleziona un paese</p>
                        </c:if>
                    </div>
                    <div>
                        <label>Fisso</label>
                        <select name="homenumber_code">
                            <option value="">Seleziona un prefisso internazionale</option>
                            <c:forEach items="${CountriesList}" var="country">
                                <option value="${country.phonecode}">${country.nicename} (+${country.phonecode})</option>
                            </c:forEach>
                        </select>
                        <input type="text" name="homenumber">

                    </div>
                    <div>
                        <label>Mobile*</label>
                        <select name="mobilenumber_code">
                            <option value="">Seleziona un prefisso internazionale</option>
                            <c:forEach items="${CountriesList}" var="country">
                                <option value="${country.phonecode}">${country.nicename} (+${country.phonecode})</option>
                            </c:forEach>
                        </select>
                        <input type="text" name="mobilenumber">
                         <c:if test="${!empty mobileNumberErrorInvalid}">
                            <p class="errorSignUp">Errore: inserisci un numero mobile</p>                            
                        </c:if>
                    </div>
                    
                    <div>
                        <input type="submit" value="Registrati"/>
                    </div>
            </form>
            <div class="form_footer"></div>
        </section>
    </article>