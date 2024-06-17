import exceptions.ElementNotFoundException;
import exceptions.WrongOrder;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Runtime runtime = Runtime.getRuntime();
        runtime.gc();

        System.out.println("Inicializando programa \n");

        long inicio = System.currentTimeMillis();

        MySongStats main = new MySongStatsImpl();

        long fin = System.currentTimeMillis();
        long tiempoTranscurrido = fin - inicio;
        System.out.println("Tiempo: " + tiempoTranscurrido + " milisegundos");

        Scanner scanner = new Scanner(System.in);
        boolean flag = false;
        while (!flag) {
            mostrarMenu();
            int numero = scanner.nextInt();
            switch (numero) {
                case 1:
                    ejecutarOpcion1(scanner, runtime, main);
                    break;
                case 2:
                    ejecutarOpcion2(scanner, runtime, main);
                    break;
                case 3:
                    ejecutarOpcion3(scanner, runtime, main);
                    break;
                case 4:
                    ejecutarOpcion4(scanner, runtime, main);
                    break;
                case 5:
                    ejecutarOpcion5(scanner, runtime, main);
                    break;
                case 6:
                    System.out.println("Programa Finalizado");
                    System.out.println("\u001B[32m-----Gracias-----\u001B[0m");
                    flag = true;
                    break;
                default:
                    System.out.println("Elija un número entre 1 y 6");
            }
        }
        scanner.close();
    }

    private static void mostrarMenu() {
        System.out.println("\n------MENU------: \n" +
                "1) Top 10 canciones en un país en tal día. \n" +
                "2) Top 5 canciones que aparecen en más top 50 en un día dado. \n" +
                "3) Top 7 artistas que más aparecen en el top 50 para un rango de fechas dado. \n" +
                "4) Cantidad de veces que aparece un artista en el top 50 en una fecha dada. \n" +
                "5) Cantidad de canciones con un tempo en un rango específico para un rango específico de fechas \n" +
                "6) Salir");
    }

    private static void ejecutarOpcion1(Scanner scanner, Runtime runtime, MySongStats main) {
        System.out.println("Ingrese el nombre del país (en formato de dos letras, por ejemplo, USA = US):");
        String nombrePais = scanner.next();
        LocalDate fecha = obtenerFecha(scanner);

        try {
            long memoriaAntesCarga = medirMemoria(runtime);
            long inicio = System.currentTimeMillis();

            main.Top10(fecha, nombrePais);

            long fin = System.currentTimeMillis();
            long tiempoTranscurrido = fin - inicio;
            long memoriaDespuesCarga = medirMemoria(runtime);
            long memoriaUsadaCarga = memoriaDespuesCarga - memoriaAntesCarga;
            System.out.println("\nMemoria usada en el método: " + memoriaUsadaCarga + " bytes");

            System.out.println("\nTiempo: " + tiempoTranscurrido + " milisegundos");
        } catch (ElementNotFoundException e) {
            System.out.println("\033[31mElementNotFound\033[0m");
        }
    }

    private static long medirMemoria(Runtime runtime) {
        runtime.gc();
        try {
            Thread.sleep(100); // Esperar un corto tiempo para asegurar que la memoria se haya liberado
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return runtime.totalMemory() - runtime.freeMemory();
    }

    private static LocalDate obtenerFecha(Scanner scanner) {
        LocalDate fecha = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
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
        return fecha;
    }

    private static void ejecutarOpcion2(Scanner scanner, Runtime runtime, MySongStats main) {
        LocalDate fecha = obtenerFecha(scanner);

        try {
            long memoriaAntesCarga = medirMemoria(runtime);
            long inicio = System.currentTimeMillis();

            main.Top5inTop50(fecha);

            long fin = System.currentTimeMillis();
            long tiempoTranscurrido = fin - inicio;
            long memoriaDespuesCarga = medirMemoria(runtime);
            long memoriaUsadaCarga = memoriaDespuesCarga - memoriaAntesCarga;
            System.out.println("\nMemoria usada en el método: " + memoriaUsadaCarga + " bytes");

            System.out.println("\nTiempo: " + tiempoTranscurrido + " milisegundos");
        } catch (ElementNotFoundException e) {
            System.out.println("\033[31mElementNotFound\033[0m");
        }
    }

    private static void ejecutarOpcion3(Scanner scanner, Runtime runtime, MySongStats main) {
        System.out.print("Por favor, ingresa una fecha de inicio y luego una fecha de finalización \n");
        LocalDate fechaInicio = obtenerFecha(scanner);
        LocalDate fechaFin = obtenerFecha(scanner);
        try {
            long memoriaAntesCarga = medirMemoria(runtime);
            long inicio = System.currentTimeMillis();

            main.Top7inTop50(fechaInicio, fechaFin);

            long fin = System.currentTimeMillis();
            long tiempoTranscurrido = fin - inicio;
            long memoriaDespuesCarga = medirMemoria(runtime);
            long memoriaUsadaCarga = memoriaDespuesCarga - memoriaAntesCarga;
            System.out.println("\nMemoria usada en el método: " + memoriaUsadaCarga + " bytes");

            System.out.println("\nTiempo: " + tiempoTranscurrido + " milisegundos");
        } catch (WrongOrder e) {
            System.out.println("\033[31mWrongOrder\033[0m");
        }
    }

    private static void ejecutarOpcion4(Scanner scanner, Runtime runtime, MySongStats main) {
        scanner.nextLine(); // Limpiar buffer del teclado
        System.out.println("Ingrese el nombre del artista:");
        String nombreArtista = scanner.nextLine();

        LocalDate fecha = obtenerFecha(scanner);

        try {
            long memoriaAntesCarga = medirMemoria(runtime);
            long inicio = System.currentTimeMillis();

            main.OccurrenciesArtistinTop50(nombreArtista, fecha);

            long fin = System.currentTimeMillis();
            long tiempoTranscurrido = fin - inicio;
            long memoriaDespuesCarga = medirMemoria(runtime);
            long memoriaUsadaCarga = memoriaDespuesCarga - memoriaAntesCarga;
            System.out.println("\nMemoria usada en el método: " + memoriaUsadaCarga + " bytes");

            System.out.println("\nTiempo: " + tiempoTranscurrido + " milisegundos");
        } catch (ElementNotFoundException e) {
            System.out.println("\033[31mElementNotFound\033[0m");
        }
    }

    private static void ejecutarOpcion5(Scanner scanner, Runtime runtime, MySongStats main) {
        System.out.println("Ingrese el tempo máximo: ");
        int tempoMax = scanner.nextInt();
        System.out.println("Ingrese el tempo mínimo: ");
        int tempoMin = scanner.nextInt();

        System.out.print("Por favor, ingresa una fecha de inicio y luego una fecha de finalización \n");
        LocalDate fechaInicio = obtenerFecha(scanner);
        LocalDate fechaFin = obtenerFecha(scanner);

        try {
            long memoriaAntesCarga = medirMemoria(runtime);
            long inicio = System.currentTimeMillis();

            System.out.println("\nCanciones con tempo entre " + tempoMin + " y " + tempoMax + " entre " + fechaInicio + " y " + fechaFin);
            main.SongsbetweenTempoAndDate(tempoMax, tempoMin, fechaInicio, fechaFin);

            long fin = System.currentTimeMillis();
            long tiempoTranscurrido = fin - inicio;
            long memoriaDespuesCarga = medirMemoria(runtime);
            long memoriaUsadaCarga = memoriaDespuesCarga - memoriaAntesCarga;
            System.out.println("\nMemoria usada en el método: " + memoriaUsadaCarga + " bytes");

            System.out.println("\nTiempo: " + tiempoTranscurrido + " milisegundos");
        } catch (WrongOrder e) {
            System.out.println("\033[31mWrongOrder\033[0m");
        }
    }
}
