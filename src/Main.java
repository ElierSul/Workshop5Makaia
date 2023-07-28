import java.io.IOException;
import java.util.Queue;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        // Prueba de la funcionalidad del validador HTML

        // Leer el archivo HTML y obtener una cola de etiquetas HTML
        Queue<HtmlTag> htmlTagsQueue = null;
        try {
            htmlTagsQueue = HtmlReader.getTagsFromHtmlFile("C:\\Users\\HP\\IdeaProjects\\Workshop5\\src\\html\\html3.html");
        } catch (IOException e) {
            System.err.println("Error al leer el archivo HTML: " + e.getMessage());
            return;
        }

        // Validar las etiquetas HTML
        Stack<HtmlTag> invalidTagsStack = HtmlValidator.isValidHtml(htmlTagsQueue);

        // Mostrar los resultados de la validación
        if (invalidTagsStack == null || invalidTagsStack.isEmpty()) {
            System.out.println("El HTML es válido.");
        } else {
            System.out.println("El HTML es inválido. Etiquetas no coincidentes:");
            while (!invalidTagsStack.isEmpty()) {
                System.out.println(invalidTagsStack.pop());
            }
        }
    }
}
