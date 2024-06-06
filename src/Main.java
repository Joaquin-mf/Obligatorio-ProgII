import exceptions.ElementNotFoundException;
import exceptions.WrongOrder;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Inicializando programa \n");
        MySongStats main = new MySongStatsImpl();
        Scanner scanner = new Scanner(System.in);
        boolean flag = false;
        while (flag == false) {
            System.out.println("\n------MENU------: \n1) Top 10 canciones en un país en tal día. \n2) Top 5 canciones que aparecen en más top 50 en un día dado. \n3) Top 7 artistas que más aparecen en el top 50 para un rango de fechas dado. \n4) cantidad de veces que aparece un artista en el top 50 en una fecha dada. \n5) cantidad de canciones con un tempo en un rango especifico para un rango especifico de fechas \n6) Salir");
            int numero = scanner.nextInt();
            if (numero==1){
                System.out.println("Ingrese el nombre del pais (en formato de dos letras USA = US)");
                String NombrePais = scanner.next();
                LocalDate fecha = null;
                boolean fechaValida = false;
                while (!fechaValida) {
                    System.out.print("Por favor, ingresa una fecha en formato yyyy/MM/dd: ");
                    String fechaStr = scanner.next();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                    try {
                        fecha = LocalDate.parse(fechaStr, formatter);
                        fechaValida = true;
                    } catch (Exception e) {
                        System.out.println("Error: formato de fecha inválido. Por favor, intenta nuevamente.");
                    }
                }
                try {
                    main.Top10(fecha,NombrePais);
                } catch (ElementNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }else if(numero==2){
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                LocalDate fecha = null;
                boolean fechaValida = false;
                while (!fechaValida) {
                    System.out.print("Por favor, ingresa una fecha en formato yyyy/MM/dd: ");
                    String fechaStr = scanner.next();
                    try {
                        fecha = LocalDate.parse(fechaStr, formatter);
                        fechaValida = true;
                    } catch (DateTimeParseException e) {
                        System.out.println("Error: formato de fecha inválido. Por favor, intenta nuevamente.");
                    }
                }
                System.out.println(fecha.toString());
                try {
                    main.Top5inTop50(fecha);
                } catch (ElementNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }else if(numero==3){
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                LocalDate fechaInicio = null;
                boolean fechaValida = false;
                while (!fechaValida) {
                    System.out.print("Por favor, ingresa una fecha de Inicio en formato yyyy/MM/dd: ");
                    String fechaInicioStr = scanner.next();
                    try {
                        fechaInicio = LocalDate.parse(fechaInicioStr, formatter);
                        fechaValida = true;
                    } catch (DateTimeParseException e) {
                        System.out.println("Error: formato de fecha inválido. Por favor, intenta nuevamente.");
                    }
                }
                LocalDate fechaFin = null;
                boolean fechaValida2 = false;
                while (!fechaValida2) {
                    System.out.print("Por favor, ingresa una fecha de finalizacion en formato yyyy/MM/dd: ");
                    String fechaFinStr = scanner.next();
                    try {
                        fechaFin = LocalDate.parse(fechaFinStr, formatter);
                        fechaValida2 = true;
                    } catch (DateTimeParseException e) {
                        System.out.println("Error: formato de fecha inválido. Por favor, intenta nuevamente.");
                    }
                }
                try {
                    main.Top7inTop50(fechaInicio,fechaFin);
                } catch (WrongOrder e) {
                    System.out.println("\033[31mWorngOrder\033[0m");
                }
            }else if(numero==4){
                scanner.nextLine(); //Limpio buffer del teclado
                System.out.println("Ingrese el nombre del artista:");
                String NombreArtista = scanner.nextLine();

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                LocalDate fecha = null;
                boolean fechaValida = false;
                while (!fechaValida) {
                    System.out.print("Por favor, ingresa una fecha en formato yyyy/MM/dd: ");
                    String fechaStr = scanner.next();
                    try {
                        fecha = LocalDate.parse(fechaStr, formatter);
                        fechaValida = true;
                    } catch (DateTimeParseException e) {
                        System.out.println("Error: formato de fecha inválido. Por favor, intenta nuevamente.");
                    }
                }
                main.OccurrenciesArtistinTop50(NombreArtista,fecha);
            }else if(numero==5){
                System.out.println("Ingrese el tempo máximo: ");
                int TempoMax = scanner.nextInt();
                System.out.println("Ingrese el tempo mínimo: ");
                int TempoMin = scanner.nextInt();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                LocalDate fechaInicio = null;
                boolean fechaValida = false;
                while (!fechaValida) {
                    System.out.print("Por favor, ingresa una fecha de Inicio en formato yyyy/MM/dd: ");
                    String fechaInicioStr = scanner.next();
                    try {
                        fechaInicio = LocalDate.parse(fechaInicioStr, formatter);
                        fechaValida = true;
                    } catch (DateTimeParseException e) {
                        System.out.println("Error: formato de fecha inválido. Por favor, intenta nuevamente.");
                    }
                }
                LocalDate fechaFin = null;
                boolean fechaValida2 = false;
                while (!fechaValida2) {
                    System.out.print("Por favor, ingresa una fecha de finalizacion en formato yyyy/MM/dd: ");
                    String fechaFinStr = scanner.next();
                    try {
                        fechaFin = LocalDate.parse(fechaFinStr, formatter);
                        fechaValida2 = true;
                    } catch (DateTimeParseException e) {
                        System.out.println("Error: formato de fecha inválido. Por favor, intenta nuevamente.");
                    }
                }
                try {
                    main.SongsbetweenTempoAndDate(TempoMax, TempoMin, fechaInicio, fechaFin);
                } catch (WrongOrder e) {
                    throw new RuntimeException(e);
                }
            }else if(numero == 6){
                System.out.println("Programa Finalizado");
                System.out.println("-----Gracias-----");
                flag = true;
            }else{
                System.out.println("elija un numero entre 1 y 5");
            }
        }
        scanner.close();
    }
}
