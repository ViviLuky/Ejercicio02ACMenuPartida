import modelos.EstadoPartida;
import streams.MyOOs;

import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
      File  partidas=new File("Partidas.dat");
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        do {
            opcion = menu(scanner);
            switch (opcion){
                case 1:
                    AgregarPartida(scanner,partidas);
                break;
                case 2:
                    MostrarPartida(partidas);
                    break;
                case 3:
                    BorrarPartida(scanner,partidas);
                break;
                case 4:
                    System.out.println("Adios");
                    break;
            }


        } while (opcion != 4);
            System.out.println("Error Elige una Opcion");

    }

    private static void BorrarPartida(Scanner scanner,File partidas) {
        System.out.println("Estas seguro eliminar la partida(si/no)");
        String respuestas=scanner.nextLine().toLowerCase().trim();
        if(respuestas.equals("si")){
            if(partidas.delete()){
                System.out.println("a tomar por culo la copia");
            }else {
                System.out.println("el juego mno me deja Borrar");
            }
        }else {
            System.out.println("buena eleccion");
        }
    }

    private static void MostrarPartida(File partidas)  {
        if(partidas.exists()){
            try {
                FileInputStream inputStream=new FileInputStream(partidas);
                ObjectInputStream ois=new ObjectInputStream(inputStream);
                while (true){
                    EstadoPartida p= (EstadoPartida) ois.readObject();
                    System.out.println(p.toString());
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

        }else {
            System.out.println("No tengo partidas guardadas");
        }
    }

    private static void AgregarPartida(Scanner scanner,File partidas) {
        try{

            EstadoPartida partida= datosPartida(scanner);
        ObjectOutputStream oos;
       if(partidas.exists()){
           oos=new MyOOs(new FileOutputStream(partidas,true));
       }else {
           oos=new ObjectOutputStream(new FileOutputStream(partidas));
       }
       oos.writeObject(partida);

       oos.close();
    }catch (InputMismatchException ex){
            System.out.println("Error al elejir");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static EstadoPartida datosPartida(Scanner scanner){
      return ;
}

    private static int menu(Scanner scanner) {
        int opcion=0;
        do {
            System.out.println("Menu----Elige las opciones.");
            //opcion=leer.nextInt();
            System.out.println("1-Agregar Partida");
            // opcion= leer.nextInt();
            System.out.println("2-Mostrar Partida");
            //opcion=leer.nextInt();
            System.out.println("3-Borrar Partida");
            //opcion= leer.nextInt();
            System.out.println("4-Salir");
            try {
                opcion=scanner.nextInt();


            }catch (InputMismatchException exception){
                opcion=0;
                System.out.println("ERROR");
                scanner.nextLine();
            }

        }while (opcion<1 || opcion>4);
        scanner.nextLine();
        return opcion;
    }
}