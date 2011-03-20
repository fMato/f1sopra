<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<option value="">--Selecciona un piloto--</option>
<%
    HashMap datosApuesta = (HashMap) request.getAttribute("datosApuesta");

    if(datosApuesta==null){
        datosApuesta=new HashMap();
    }
ArrayList pilotos = (ArrayList) request.getAttribute("pilotos");

if(pilotos==null){
    pilotos=new ArrayList();
}
String indCombo = request.getParameter("indSelect");
if(indCombo==null)indCombo="";
String indSelect=(String) datosApuesta.get(indCombo);
if(indSelect==null)indSelect="";
for(int i=0; i<pilotos.size(); i++){
    HashMap pilotosItem = (HashMap) pilotos.get(i);
    String numero = (String) pilotosItem.get("numero");
    String nombre = (String) pilotosItem.get("nombre");
    %>
    <option value="<%=numero%>"<%if(indSelect.equals(numero)){ %>selected="true"<%}%>>
        <%=nombre%>
    </option>
    <%
}
%>