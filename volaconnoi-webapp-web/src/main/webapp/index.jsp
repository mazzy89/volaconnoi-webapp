<%-- 
    Document   : index
    Created on : 6-giu-2014, 19.29.46
    Author     : Mazzy
--%>


<article class="container_12">

    <section id="main_page" class="grid_12">
        
                  
            <img src="resources/img/maldive.jpg" width="500px" class="img_shadow">

            <div class="wrap_text">
                <c:if test="${!empty pageContext.request.userPrincipal.name}">
                    <p>Bentornato ${pageContext.request.userPrincipal.name}!</p>  
                </c:if>

                <p>Credi che low-cost sia una brutta parola?</p>
                <p>Che stai aspettando a goderti la tua meritata vacanza?</p>
                <p><span style="color: #0099FF">VolaConNoi.it</span> ti porta in fantastiche località da sogno che hai sempre desiderato a prezzi favolosi!</p>
            </div>
        

    </section>
    
<article>