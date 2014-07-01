
    <main class="container_12">
        <article id="search" class="grid_7">
            <h5><img class="glass" src="${pageContext.servletContext.contextPath}/resources/img/glass.png" width="20px" height="20px"> Cerca volo</h5>
                <form action="result" method="GET">
                    <div class="grid_3 alpha omega left_search">
                        <input type="text" name="source" placeholder="Partenza da"><br/><br/>
                        <input type="text" class="from" name="from" placeholder="Data partenza"><br/><br/>
                        <input type="submit" value="Cerca">
                    </div>
                    <div class="grid_3 alpha right_search">
                    <input type = "text" name="destination" placeholder="Arrivo a"><br/><br/>
                        <select name="travel_class">
                           <option value="Economy">Economy</option>
                           <option value="Business">Business</option>
                           <option value="First">First</option>
                        </select><br/><br/>
                        <label>Data flessibile? </label><input type="checkbox" name="date_flexi" value="on">
                    </div>       
               </form>
        </article>
    </main>