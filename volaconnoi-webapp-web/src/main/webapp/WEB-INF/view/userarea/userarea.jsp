<%-- 
    Document   : userarea
    Created on : 11-giu-2014, 11.50.05
    Author     : Mazzy
--%>

    
    <section class="grid_9 area_principal">
        <h4>Benvenuto nella tua sezione personale ${pageContext.request.userPrincipal.name}</h4>
        <div class="body_area_principal">
            <p>Il tuo saldo punti fedeltà raccolti alla data odierna <fmt:formatDate value="${date_daily}" pattern="dd/MM/yyyy"/> è ${fidelity_points}.</p>
            <p>Scegli una voce dal menù sulla destra.</p>
        </div>
    </section>
</article>
