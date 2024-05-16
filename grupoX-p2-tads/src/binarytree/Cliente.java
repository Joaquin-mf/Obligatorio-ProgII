package binarytree;
public class Cliente {
//public class Cliente implements Comparable<Cliente>{
    String nombre;
    int edad;

    public Cliente(String nombre, int edad) {
        this.nombre = nombre ;
        this.edad =  edad ;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

  /*  @Override
    public int compareTo(Cliente otro) {
        int resul = Integer.compare(this.edad, otro.edad);
        return resul;
    }*/
}
