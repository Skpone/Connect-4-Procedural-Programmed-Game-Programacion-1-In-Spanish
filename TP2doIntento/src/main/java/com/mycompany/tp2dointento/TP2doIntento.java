/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.tp2dointento;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.Random;
/**
 *
 * @author mulat
 */
public class TP2doIntento {
    public static int MAXFILAS;
    public static int MAXCOLUMNAS;
    public static void main(String[] args) {
        //msj del juego
        System.out.println("Juego 4 en fila!");
        //crear buffered reader, pedir input de cuantas filas y columnas quiere el usuario que sea de tamaño el tablero
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        boolean tableroCreado = false;
        while (tableroCreado == false) {
            MAXFILAS = obtener_filas_tablero();
            if(MAXFILAS > 4 && MAXFILAS < 20){
                MAXCOLUMNAS = obtener_columnas_tablero();
                if (MAXCOLUMNAS > 4 && MAXCOLUMNAS < 20) {
                    tableroCreado = true;
                    //crear tablero
                    char[][] tablero = new char[MAXFILAS][MAXCOLUMNAS];
                    inicializar_tablero(tablero);
                    imprimir_tablero(tablero);
                    
                    //modo de juego
                    boolean modoDeJuego = obtener_modo_juego();
                    if(modoDeJuego/*1*/){
                        System.out.println("1.vs.COM COMIENZA");
                        String jugador = new String("jugador");
                        char ficha = 'R';
                        int columna; //donde se va a posicionar la ficha
                        int fila;

                        //iniciamos juego
                        boolean juegoSigue = true;
                        while (juegoSigue) {
                            columna = obtener_columna_tablero(tablero, jugador);
                            if(columna != -1){
                                fila = obtener_fila_vacia(tablero, columna);//fila vacia en la columna elegida
                                if (fila != -1) {
                                    //colocamos la ficha
                                    tablero[fila][columna] = ficha;
                                    imprimir_tablero(tablero);


                                    //comprobar si juegoSigue
                                    juegoSigue = check_juegoSigue(tablero, fila, columna, ficha, jugador);
                                    //si la fila no está llena entonces cambio de jugador
                                    jugador = cambiar_jugador(jugador);
                                    ficha = cambiar_ficha(ficha);

                                    //COM
                                    if (juegoSigue) {
                                        //cambiamos de ficha y jugador
                                        columna = obtener_columna_random_tablero(tablero, jugador);
                                        fila = obtener_fila_vacia(tablero, columna);//fila vacia en la columna elegida //pd: el com nunca va a agarrar una columna llena esto en este caso es para que se elija la primer fila de mas abajo vacia
                                        if (fila != -1) {
                                            //colocamos la ficha
                                            tablero[fila][columna] = ficha;
                                            imprimir_tablero(tablero);

                                            //comprobar si juegoSigue
                                            juegoSigue = check_juegoSigue(tablero, fila, columna, ficha, jugador);

                                            //si la fila no está llena entonces cambio de jugador
                                            jugador = cambiar_jugador(jugador);
                                            ficha = cambiar_ficha(ficha);
                                        }
                                    }
                                }else{
                                    System.out.println("ésta fila está llena, inténtelo de nuevo..");
                                }
                            }else{
                                System.out.println("valor ingresado incorrecto, vuelva a ingresar...");
                            }
                        }
                    }else/*2*/{
                        System.out.println("1.vs.1 COMIENZA");
                        String jugador = new String("jugador 1");
                        char ficha = 'R';
                        int columna; //donde se va a posicionar la ficha
                        int fila;

                        //iniciamos juego
                        boolean juegoSigue = true;
                        while (juegoSigue) {
                            columna = obtener_columna_tablero(tablero, jugador);
                            if(columna != -1){
                                fila = obtener_fila_vacia(tablero, columna);//fila vacia en la columna elegida
                                if (fila != -1) {
                                    //colocamos la ficha
                                    tablero[fila][columna] = ficha;
                                    imprimir_tablero(tablero);


                                    //comprobar si juegoSigue
                                    juegoSigue = check_juegoSigue(tablero, fila, columna, ficha, jugador);
                                    //si la fila no está llena entonces cambio de jugador
                                    jugador = cambiar_jugador(jugador);
                                    ficha = cambiar_ficha(ficha);

                                    if (juegoSigue) {
                                        //cambiamos de ficha y jugador
                                        columna = obtener_columna_tablero(tablero, jugador);
                                        if(columna != -1){
                                            fila = obtener_fila_vacia(tablero, columna);//fila vacia en la columna elegida
                                            if (fila != -1) {
                                                //colocamos la ficha
                                                tablero[fila][columna] = ficha;
                                                imprimir_tablero(tablero);

                                                //comprobar si juegoSigue
                                                juegoSigue = check_juegoSigue(tablero, fila, columna, ficha, jugador);

                                                //si la fila no está llena entonces cambio de jugador
                                                jugador = cambiar_jugador(jugador);
                                                ficha = cambiar_ficha(ficha);
                                            }else{
                                                System.out.println("ésta fila está llena, inténtelo de nuevo..");
                                            }
                                        }else{
                                            System.out.println("valor ingresado incorrecto, vuelva a ingresar...");
                                        }
                                    }
                                }else{
                                    System.out.println("ésta fila está llena, inténtelo de nuevo..");
                                }
                            }else{
                                System.out.println("valor ingresado incorrecto, vuelva a ingresar...");
                            }
                        }
                    }
                }else{
                    System.out.println("valor inválido, por favor inténtelo de nuevo");
                }   
            }else{
                System.out.println("valor inválido, por favor inténtelo de nuevo");
            }

        }



    }
    public static int obtener_filas_tablero(){
        int filas = 0;
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        try{
            System.out.println("ingrese la cantidad de filas que tiene el tablero... entre 5 y 19");
            filas = Integer.valueOf(input.readLine());
        }catch(Exception e){
        }
        return filas;
    }
    public static int obtener_columnas_tablero(){
        int columnas = 0;
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        try{
            System.out.println("ingrese la cantidad de columnas que tiene el tablero... entre 5 y 19");
            columnas = Integer.valueOf(input.readLine());
        }catch(Exception e){
        }
        return columnas;
    }
    public static void inicializar_tablero(char mat[][]){
        for (int fila = 0; fila < mat.length; fila++) {
            for (int columna = 0; columna < mat[fila].length; columna++) {
               mat[fila][columna] = ' '; 
            }
        }
    }
    public static void imprimir_tablero(char mat[][]){
        for (int fila = 0; fila < mat.length; fila++) {
            System.out.print("| ");
            for (int columna = 0; columna < mat[fila].length; columna++) {
                System.out.print(mat[fila][columna]+" | ");
            }
            System.out.println("");
        }
        System.out.println("");
    }
    public static boolean obtener_modo_juego(){
        boolean modoJuego = true; //true == modo de juego 1
        int nro = 1;
        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Elija modo de juego, 1 para 1.vs.COM o 2 para 1.vs.1.");
            nro = Integer.valueOf(input.readLine());
            if(nro == 1){
               modoJuego = true;  
            }else if(nro == 2){
                modoJuego = false;
            }else{
                System.out.println("número inválido, se eligirá modo de juego 1");
            }
        }catch (Exception e){
            System.out.println("valor inválido, se elegirá modo de juego 1");
        }
        return modoJuego;
    }
    public static String cambiar_jugador(String jugador){
        //modo de juego 1
        if(jugador.equals("jugador 1")){
            jugador = "jugador 2";
        }else if(jugador.equals("jugador 2")){
            jugador = "jugador 1";
        }
        //modo de juego 2
        if(jugador.equals("jugador")){
            jugador = "COM";
        }else if(jugador.equals("COM")){
            jugador = "jugador";
        }
        return jugador;
    }
    public static char cambiar_ficha(char ficha){
        if (ficha == 'R') {
            ficha = 'A';
        }else{
            ficha = 'R';
        }
        return ficha;
    }
    public static int obtener_columna_random_tablero(char[][] tablero ,String jugador){
        int columna;
        Random r = new Random();
        columna = r.nextInt(MAXCOLUMNAS); //numero entre 0 y maxcolumnas-1
        while(tablero[0][columna] != ' '){
            columna = r.nextInt(MAXCOLUMNAS);
        }//fila vacia en la columna elegida
        System.out.println(jugador+":");

        return columna;
    }
    public static int obtener_columna_tablero(char mat[][], String jugador){
        int columna;
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("Turno de "+jugador+", por favor ingresa nro de columna, entre 0 y "+(MAXCOLUMNAS-1));
            columna = Integer.valueOf(input.readLine());
            if ((columna < MAXCOLUMNAS) && (columna >= 0)) {
                return columna;
            }else{
                return -1;
            }
        } catch (Exception e) {
            return -1;
        }
    }
    public static int obtener_fila_vacia(char mat[][], int columna){
        for (int fila = MAXFILAS-1; fila >= 0; fila--) {
            if (mat[fila][columna] == ' ') {
                return fila;
            }
        }
        return -1;
    }
    public static boolean check_juegoSigue(char tablero[][], int fila, int columna, char ficha, String jugador){
        int x = fila;//declaramos para que no cambien los valores al volver a recorrer
        int y = columna;
        int fichasEnLinea = 0;
        boolean ladoEncontrado = false;
        //comprobamos si hay 4 en fila diagonalmente orientado hacia la derecha y la izq
        while(!ladoEncontrado){
            if(y == 0 || x == 0){
                ladoEncontrado = true; //si encontro un lado
            }else {
                x = x - 1; //recorremos subiendo diagonalmente hacia la izquierda
                y = y - 1;
            }
        }
        while((x < MAXFILAS && x >= 0) && (y < MAXCOLUMNAS && y >= 0)) {
            if (tablero[x][y] == ficha) { //vamos chequeando que hayan cuatro fichas iguales en linea
                fichasEnLinea++;
                if (fichasEnLinea == 4) {
                    System.out.println(jugador+" Gana!!");
                    return false; //juego deja de seguir
                }
            } else {
                fichasEnLinea = 0;
            }
            x = x + 1; //vamos recorriendo bajando en direccion a la derecha
            y = y + 1;
        }


        //redefinimos valores para recorrer correctamente
        x = fila;
        y = columna;
        fichasEnLinea = 0;
        ladoEncontrado = false;
        //check si hay 4 en fila recorriendo diagonalmente al reves
        while(!ladoEncontrado){
            if(y == (MAXCOLUMNAS-1) || x == 0){
                ladoEncontrado = true; //si encontro un lado
            }else {
                x = x - 1; //recorremos subiendo diagonalmente hacia la derecha
                y = y + 1;
            }
        }
        while((x < MAXFILAS && x >= 0) && (y < MAXCOLUMNAS && y >= 0)) {
            if (tablero[x][y] == ficha) { //vamos chequeando que hayan cuatro fichas iguales en linea
                fichasEnLinea++;
                if (fichasEnLinea == 4) {
                    System.out.println(jugador+" Gana!!");
                    return false;
                }
                } else {
                    fichasEnLinea = 0;
                }
            x = x + 1; //vamos recorriendo bajando en direccion a la derecha
            y = y - 1;
        }


        //comprobamos si hay 4 en fila vertical y horizontalmente
        x = 0;
        y = columna;
        fichasEnLinea = 0;
        //recorremos verticalmente de arriba hacia abajo
        while(x < MAXFILAS){
            if (tablero[x][y] == ficha){
                fichasEnLinea++;
                if (fichasEnLinea == 4) {
                    System.out.println(jugador+" Gana!!");
                    return false;
                }
            }else{
                fichasEnLinea = 0;
            }
            x++;
        }


        x = fila;
        y = 0;
        fichasEnLinea = 0;
        //recorremos verticalmente de arriba hacia abajo
        while(y < MAXCOLUMNAS){
            if (tablero[x][y] == ficha){
                fichasEnLinea++;
                if (fichasEnLinea == 4) {
                    System.out.println(jugador+" Gana!!");
                    return false;
                }
            }else{
                fichasEnLinea = 0;
            }
            y++;
        }

        //check empate
        int espaciosEnTablero = 0;
        for (x = 0; x < MAXFILAS; x++) {
            for (y = 0; y < MAXCOLUMNAS; y++) {
                if(tablero[x][y] == ' '){//si en alguna posicion del tablero hay espacio entonces no esta lleno el tablero
                    espaciosEnTablero++;
                }
            }
        }
        if (espaciosEnTablero == 0) {
            System.out.println("Empate"); 
           return false;
        }
        return true;//si no hay ganador juegoSigue
    }
}
