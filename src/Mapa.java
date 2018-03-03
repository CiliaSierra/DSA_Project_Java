package src;

public class Mapa {

    src.Celda[][] celdas;
    String nombre;

    public Mapa(int altura, int anchura){
        this.celdas = new src.Celda[altura][anchura];
    }

    public void llenarMapa(src.Celda[][] celdas){
        //if(celdas.length == this.celdas.length(0)){}

    }
    public void mostrarMapa (){
        for(int i = 0; i<celdas.length; i++){
            for (int j=0; j<celdas[0].length;j++) {
                    System.out.print(celdas[i][j].letra());
            }
            System.out.println();
        }
    }

}
