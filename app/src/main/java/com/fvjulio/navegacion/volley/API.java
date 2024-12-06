package com.fvjulio.navegacion.volley;

public interface API {

    public String URL ="https://slateblue-squid-754504.hostingersite.com/Docente/";


    //Comienza el CRUD para Grupos
    public String LISTARGPO=URL + "ApiG.php?api=listar";
    public String GUARDARGPO=URL + "ApiG.php?api=guardar";
    public String ELIMINARGPO=URL + "ApiG.php?api=eliminar";
    public String EDITARGPO=URL + "ApiG.php?api=editar";
    public String BUSCARDOC = URL + "ApiD.php?api=listarDoc";
    public String LISTAR_ASI = URL + "ApiA.php?api=listar";
    public String LISTAR_PER = URL+ "ApiP.php?api=listar";
    public String BUSCARCORREO = URL + "ApiD.php?api=listarCorreo";
    public  String RECUPERA_CONTRA = URL + "ApiD.php?api=recuperaContrasenia";

}
