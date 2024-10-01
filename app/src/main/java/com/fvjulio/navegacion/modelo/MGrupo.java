package com.fvjulio.navegacion.modelo;

public class MGrupo {
    private String claveGrupo;
    private String nombreAsignatura;
    private String nombreDocente;
    private int periodo;
    private int op;

    public MGrupo() {
    }

    public MGrupo(String claveGrupo, String nombreAsignatura, String nombreDocente, int periodo, int op) {
        this.claveGrupo = claveGrupo;
        this.nombreAsignatura = nombreAsignatura;
        this.nombreDocente = nombreDocente;
        this.periodo = periodo;
        this.op = op;
    }

    public String getClaveGrupo() {
        return claveGrupo;
    }

    public void setClaveGrupo(String claveGrupo) {
        this.claveGrupo = claveGrupo;
    }

    public String getNombreAsignatura() {
        return nombreAsignatura;
    }

    public void setNombreAsignatura(String nombreAsignatura) {
        this.nombreAsignatura = nombreAsignatura;
    }

    public String getNombreDocente() {
        return nombreDocente;
    }

    public void setNombreDocente(String nombreDocente) {
        this.nombreDocente = nombreDocente;
    }

    public int getPeriodo() {
        return periodo;
    }

    public void setPeriodo(int periodo) {
        this.periodo = periodo;
    }

    public int getOp() {
        return op;
    }

    public void setOp(int op) {
        this.op = op;
    }



}
