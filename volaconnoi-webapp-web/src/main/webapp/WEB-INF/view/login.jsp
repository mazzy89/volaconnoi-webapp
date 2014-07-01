<%-- 
    Document   : login
    Created on : 10-giu-2014, 20.22.22
    Author     : Mazzy
--%>

    <article class ="container_12">
        <section class="grid_12" >
            <p class="infoLogin">Inserisci nome utente e password per accedere alla tua sezione personale. Se non sei iscritto prosegui con la registrazione.</p>
        </section>
        <div class="clear"></div>
        <section class="grid_4" id="loginForm">
            <form action="j_security_check" method="GET">
                <div id="loginheader"></div>
                <fieldset class="logininputfields">
                    <input type="text" name="j_username" placeholder="Username" autofocus>
                    <input type="password" name="j_password" placeholder="Password">
                </fieldset>
                <fieldset class="submitfield">
                    <input type="submit" value="Log In">
                </fieldset>
            </form>
        </section> 
    </article>
                
