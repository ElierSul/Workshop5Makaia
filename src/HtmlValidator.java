import java.util.Queue;
import java.util.Stack;

public class HtmlValidator {

    // Método para validar etiquetas HTML
    public static Stack<HtmlTag> isValidHtml(Queue<HtmlTag> tags) {
        // Creamos una pila para rastrear las etiquetas de apertura
        Stack<HtmlTag> stack = new Stack<>();

        // Recorremos la cola de etiquetas HTML recibida como argumento
        for (HtmlTag tag : tags) {
            // Si la etiqueta es auto-cerrada, la ignoramos y continuamos con la siguiente etiqueta
            if (tag.isSelfClosing()) {
                continue;
            } else if (tag.isOpenTag()) { // Si es una etiqueta de apertura
                // La agregamos a la pila para mantener un seguimiento de las etiquetas de apertura encontradas
                stack.push(tag);
            } else if (!tag.isOpenTag()) { // Si es una etiqueta de cierre
                if (!stack.isEmpty()) { // Verificamos si la pila no está vacía
                    // Comprobamos si la etiqueta de cierre coincide con la última etiqueta de apertura en la pila
                    if (stack.peek().matches(tag)) {
                        // Si coinciden, eliminamos la etiqueta de apertura de la pila, ya que ahora está correctamente cerrada
                        stack.pop();
                    } else {
                        // Si no coinciden, significa que encontramos una etiqueta de cierre sin su etiqueta de apertura correspondiente
                        // En este caso, devolvemos la pila actual, que contiene todas las etiquetas de apertura no cerradas hasta este punto
                        return stack;
                    }
                } else {
                    // Si la pila está vacía y encontramos una etiqueta de cierre, significa que no hay etiqueta de apertura correspondiente
                    // Por lo tanto, el HTML no es válido, y devolvemos null para indicar que hay un error
                    return null;
                }
            }
        }
        // Al final del bucle, la pila contendrá todas las etiquetas de apertura que no se cerraron correctamente
        // Devolvemos la pila como resultado para mostrar las etiquetas no coincidentes, si las hay
        return stack;
    }
}
