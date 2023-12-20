
import arbol.arboles.NodoArbol;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;

public class ArbolBinario {

    private JFrame frame;
    private JPanel panel;
    NodoArbol raiz;
    // Mapa para almacenar las posiciones de los nodos
    private Map<NodoArbol, Point> positions = new HashMap<>();

    public ArbolBinario() {
        raiz = null;
        frame = new JFrame("Representación Gráfica del Árbol Binario");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 600);

        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                dibujarArbol(g, raiz, getWidth() / 2, 30, getWidth() / 4);
            }
        };
        frame.add(panel);
    }

    public void agregarDato(int dato) {
        NodoArbol nuevo = new NodoArbol();
        nuevo.setDato(dato);
        nuevo.setPadre(null);
        nuevo.setHijoDerecho(null);
        nuevo.setHijoIzquierdo(null);
        if (raiz == null) {
            raiz = nuevo;
            JOptionPane.showMessageDialog(null, "Se agregó el dato: " + dato);
        } else if (raiz.getDato() == dato) {
            JOptionPane.showMessageDialog(null, "Ese dato ya existe");
        } else {
            NodoArbol temporal = raiz;
            NodoArbol padre;
            while (temporal != null) {
                padre = temporal;
                if (dato < temporal.getDato()) {
                    temporal = temporal.getHijoIzquierdo();
                    if (temporal == null) {
                        padre.setHijoIzquierdo(nuevo);
                        nuevo.setPadre(padre);
                        JOptionPane.showMessageDialog(null, "Se agregó el dato: " + dato);
                        return;
                    }
                } else {
                    temporal = temporal.getHijoDerecho();
                    if (temporal == null) {
                        padre.setHijoDerecho(nuevo);
                        nuevo.setPadre(padre);
                        JOptionPane.showMessageDialog(null, "Se agregó el dato: " + dato);
                        return;
                    }
                }
            }
        }
    }

    public void mostrarGrafico() {
        frame.setVisible(true);
    } 
    
    public void cerrarVentana() {
    frame.dispose();
    }
  
    public void actualizarVista() {
        panel.repaint();
    }

    private void dibujarArbol(Graphics g, NodoArbol nodo, int x, int y, int offsetX) {
        if (nodo != null) {
            // Se dibuja el nodo
            g.drawOval(x - 15, y - 15, 30, 30);
            g.drawString(Integer.toString(nodo.getDato()), x - 5, y + 5);

            // Se almacena la posición del nodo
            positions.put(nodo, new Point(x, y));

            // Se dibujan las conexiones con los hijos izquierdo y derecho
            dibujarArbol(g, nodo.getHijoIzquierdo(), x - offsetX, y + 60, offsetX / 2);
            dibujarArbol(g, nodo.getHijoDerecho(), x + offsetX, y + 60, offsetX / 2);

            // Se dibujan líneas que conectan el nodo actual con sus hijos
            if (nodo.getHijoIzquierdo() != null) {
                Point start = positions.get(nodo);
                Point end = positions.get(nodo.getHijoIzquierdo());
                g.drawLine(start.x, start.y, end.x, end.y);
            }
            if (nodo.getHijoDerecho() != null) {
                Point start = positions.get(nodo);
                Point end = positions.get(nodo.getHijoDerecho());
                g.drawLine(start.x, start.y, end.x, end.y);
            }
        }
    }

    public void consultarInorden(NodoArbol recorrer) {
        if (recorrer != null) {
            consultarInorden(recorrer.getHijoIzquierdo());
            JOptionPane.showMessageDialog(null, recorrer.getDato());
            consultarInorden(recorrer.getHijoDerecho());
        }
    }

    public void consultarPreorden(NodoArbol recorrer) {
        if (recorrer != null) {
            JOptionPane.showMessageDialog(null, recorrer.getDato());
            consultarPreorden(recorrer.getHijoIzquierdo());
            consultarPreorden(recorrer.getHijoDerecho());
        }
    }

    public void consultarPosorden(NodoArbol recorrer) {
        if (recorrer != null) {
            consultarPosorden(recorrer.getHijoIzquierdo());
            consultarPosorden(recorrer.getHijoDerecho());
            JOptionPane.showMessageDialog(null, recorrer.getDato());
        }
    }

    public NodoArbol buscarDato(int dato) {
        NodoArbol temporal = raiz;

        while (temporal != null) {
            if (temporal.getDato() == dato) {
                return temporal;
            } else if (dato < temporal.getDato()) {
                temporal = temporal.getHijoIzquierdo();
            } else {
                temporal = temporal.getHijoDerecho();
            }
        }
        return null;
    }

    public int obtenerNivelDeUnNodo(NodoArbol raiz, int valor, int nivel) {
        if (raiz == null) {
            return 0;
        }
        if (raiz.getDato() == valor) {
            return nivel;
        }

        int resultado = obtenerNivelDeUnNodo(raiz.getHijoIzquierdo(), valor, nivel + 1);
        if (resultado != 0) {
            return resultado;
        }
        resultado = obtenerNivelDeUnNodo(raiz.getHijoDerecho(), valor, nivel + 1);

        return resultado;
    }

    public NodoArbol suPadre(NodoArbol raiz, int dato) {
        if (raiz == null) {
            return null;
        }
        NodoArbol obtenerPadre = null;
        while (raiz != null) {
            if (dato < raiz.getDato()) {
                obtenerPadre = raiz;
                raiz = raiz.getHijoIzquierdo();
            } else if (dato > raiz.getDato()) {
                obtenerPadre = raiz;
                raiz = raiz.getHijoDerecho();
            } else {
                break;
            }
        }
        return obtenerPadre;
    }
}
