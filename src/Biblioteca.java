import java.util.Scanner;

public class Biblioteca {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[][] biblioteca = new String[100][5];
        int totalLibros = 0;
        int opcion;

        do {
            System.out.println("\n--- Menú Biblioteca ---");
            System.out.println("1. Registrar libro");
            System.out.println("2. Actualizar libro");
            System.out.println("3. Eliminar libro");
            System.out.println("4. Buscar libro");
            System.out.println("5. Ordenar libros");
            System.out.println("0. Salir");
            System.out.print("Opción: ");
            opcion = sc.nextInt(); sc.nextLine();

            switch (opcion) {
                case 1:
                    registrarLibro(biblioteca, totalLibros, sc);
                    totalLibros++;
                    break;
                case 2:
                    actualizarLibro(biblioteca, totalLibros, sc);
                    break;
                case 3:
                    totalLibros = eliminarLibro(biblioteca, totalLibros, sc);
                    break;
                case 4:
                    buscarLibro(biblioteca, totalLibros, sc);
                    break;
                case 5:
                    ordenarLibros(biblioteca, totalLibros, sc);
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        } while (opcion != 0);
    }

    public static void registrarLibro(String[][] biblio, int index, Scanner sc) {
        System.out.print("Código: ");
        biblio[index][0] = sc.nextLine();
        System.out.print("Nombre: ");
        biblio[index][1] = sc.nextLine();
        System.out.print("Autor: ");
        biblio[index][2] = sc.nextLine();
        System.out.print("Materia: ");
        biblio[index][3] = sc.nextLine();
        System.out.print("Número de páginas: ");
        biblio[index][4] = sc.nextLine();
    }

    public static void actualizarLibro(String[][] biblio, int total, Scanner sc) {
        System.out.print("Código del libro a actualizar: ");
        String codigo = sc.nextLine();
        int pos = buscarPorCodigo(biblio, total, codigo);
        if (pos != -1) {
            registrarLibro(biblio, pos, sc);
            System.out.println("Libro actualizado.");
        } else {
            System.out.println("Libro no encontrado.");
        }
    }

    public static int eliminarLibro(String[][] biblio, int total, Scanner sc) {
        System.out.print("Código del libro a eliminar: ");
        String codigo = sc.nextLine();
        int pos = buscarPorCodigo(biblio, total, codigo);
        if (pos != -1) {
            for (int i = pos; i < total - 1; i++) {
                biblio[i] = biblio[i + 1];
            }
            System.out.println("Libro eliminado.");
            return total - 1;
        } else {
            System.out.println("Libro no encontrado.");
            return total;
        }
    }

    public static void buscarLibro(String[][] biblio, int total, Scanner sc) {
        System.out.print("Código del libro a buscar: ");
        String codigo = sc.nextLine();
        int pos = buscarPorCodigo(biblio, total, codigo);
        if (pos != -1) {
            mostrarLibro(biblio, pos);
        } else {
            System.out.println("Libro no encontrado.");
        }
    }

    public static int buscarPorCodigo(String[][] biblio, int total, String codigo) {
        for (int i = 0; i < total; i++) {
            if (biblio[i][0].equals(codigo)) {
                return i;
            }
        }
        return -1;
    }

    public static void mostrarLibro(String[][] biblio, int index) {
        System.out.println("Código: " + biblio[index][0]);
        System.out.println("Nombre: " + biblio[index][1]);
        System.out.println("Autor: " + biblio[index][2]);
        System.out.println("Materia: " + biblio[index][3]);
        System.out.println("Páginas: " + biblio[index][4]);
    }

    public static void ordenarLibros(String[][] biblio, int total, Scanner sc) {
        System.out.println("Seleccione campo para ordenar:");
        System.out.println("0. Código");
        System.out.println("1. Nombre");
        System.out.println("2. Autor");
        System.out.println("3. Materia");
        System.out.println("4. Páginas");
        int campo = sc.nextInt(); sc.nextLine();

        System.out.println("Método: burbuja / seleccion");
        String metodo = sc.nextLine();

        if (metodo.equalsIgnoreCase("burbuja")) {
            for (int i = 0; i < total - 1; i++) {
                for (int j = 0; j < total - i - 1; j++) {
                    if (biblio[j][campo].compareTo(biblio[j + 1][campo]) > 0) {
                        String[] temp = biblio[j];
                        biblio[j] = biblio[j + 1];
                        biblio[j + 1] = temp;
                    }
                }
            }
        } else if (metodo.equalsIgnoreCase("seleccion")) {
            for (int i = 0; i < total - 1; i++) {
                int minIdx = i;
                for (int j = i + 1; j < total; j++) {
                    if (biblio[j][campo].compareTo(biblio[minIdx][campo]) < 0) {
                        minIdx = j;
                    }
                }
                String[] temp = biblio[minIdx];
                biblio[minIdx] = biblio[i];
                biblio[i] = temp;
            }
        } else {
            System.out.println("Método no válido.");
        }

        System.out.println("Libros ordenados:");
        for (int i = 0; i < total; i++) {
            System.out.println((i + 1) + ". " + biblio[i][campo]);
        }
    }
}

