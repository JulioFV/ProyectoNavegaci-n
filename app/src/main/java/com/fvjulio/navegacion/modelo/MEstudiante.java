package com.fvjulio.navegacion.modelo;

public class MEstudiante {
    private String matricula;
    private  String app;
    private String apm;
    private String nombre;
    private byte cal1;
    private byte cal2;
    private byte cal3;
    private byte promedio;
    private byte genero;

    public MEstudiante(String matricula, String app, String apm, String nombre, byte cal1, byte cal2, byte cal3, byte promedio, byte genero) {
        this.matricula = matricula;
        this.app = app;
        this.apm = apm;
        this.nombre = nombre;
        this.cal1 = cal1;
        this.cal2 = cal2;
        this.cal3 = cal3;
        this.promedio = promedio;
        this.genero = genero;
    }

    public MEstudiante() {

    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public String getApm() {
        return apm;
    }

    public void setApm(String apm) {
        this.apm = apm;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public byte getCal1() {
        return cal1;
    }

    public void setCal1(byte cal1) {
        this.cal1 = cal1;
    }

    public byte getCal2() {
        return cal2;
    }

    public void setCal2(byte cal2) {
        this.cal2 = cal2;
    }

    public byte getCal3() {
        return cal3;
    }

    public void setCal3(byte cal3) {
        this.cal3 = cal3;
    }

    public byte getPromedio() {
        return promedio;
    }

    public void setPromedio(byte promedio) {
        this.promedio = promedio;
    }

    public byte getGenero() {
        return genero;
    }

    public void setGenero(byte genero) {
        this.genero = genero;
    }

    @Override
    public String toString() {
        return "MEstudiante{" +
                "matricula='" + matricula + '\'' +
                ", app='" + app + '\'' +
                ", apm='" + apm + '\'' +
                ", nombre='" + nombre + '\'' +
                ", cal1=" + cal1 +
                ", cal2=" + cal2 +
                ", cal3=" + cal3 +
                ", promedio=" + promedio +
                ", genero=" + genero +
                '}';
    }
}
