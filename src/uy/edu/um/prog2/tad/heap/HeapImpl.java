package uy.edu.um.prog2.tad.heap;

public class HeapImpl<K extends Comparable<K>, T> implements Heap<K, T>{
    private int size;
    private HeapNode<K, T>[] heap;
    private boolean isMin;

    public HeapImpl(boolean isMin) {
        this.isMin = isMin;
        this.size = 0;
        this.heap = new HeapNode[1];
    }

    public HeapImpl(boolean isMin, K[] array){
        this.isMin = isMin;
        this.size = 0;
        heap = new HeapNode[1];


        for(int i = 0; i < array.length; i++){
            insert(array[i],null);
        }
        orderHeap();
    }

    public void insert(K key, T value) {
        if (size == 0) {
            heap[0] = new HeapNode(key, value);
            return;
        }

        if (size == heap.length) {
            resize();
        }

        HeapNode<K, T> nodoNuevo = new HeapNode<>(key, value);
        heap[size] = nodoNuevo;
        size++;

        int ultValor = 0;
        for (int i = size; i > 0; i = (i-1)/2){
            // Se compara con el padre, tiene metodos en este para isMin y !isMin
            if (compare(heap[i].getKey(), heap[(i-1)/2].getKey()) < 0){
                swap(size,(size-1)/2);
            }
            else {return;} // Si no pasa no es necesario cambiar ya que mantiene el ordenamiento
             ultValor = i;
        }

        // Si llega hasta aca, el nodo llego a la posicion 1 o 2
        if (compare(heap[0].getKey(), heap[ultValor].getKey()) < 0){
            swap(0,ultValor);
        }
    }

    public void resize(){
        HeapNode<K, T>[] newHeap = new HeapNode[size*2 +1];
        for (int i = 0; i < size; i++){
            newHeap[i] = this.heap[i];
        }
        this.heap = newHeap;
        this.size = size*2 + 1;
    }


    public T delete() {
        // Catch de size 0
        if (size == 0){
            if (heap[0].getKey() == null){
                return null;
            }
            return heap[0].getValue();
        }

        // Se busca el ultimo y se mueve a la raiz
        T valorADevolver = heap[0].getValue();
        heap[0] = heap[size -1];
        size--;

        // Se mueve para abajo
        for (int i = 0; i < heap.length;){
            // Se ve que este en dentro del array los hijos
            if (i*2+1 > heap.length){
                return valorADevolver;
            }

            //Revisa si el izquierdo no es nulo
            if (heap[i*2+1] != null){
                if (heap[i*2+2] != null){ // Revisa el derecho
                    //Hay que compararlos para ordenar
                    int cualHijo = compare(heap[i*2+1].getKey(), heap[i*2 +2].getKey());
                    switch (cualHijo){
                        case 1: /*
                            Is min y 1, iz > der, como es min, se cambiaria por el derecho
                            !isMin y 1 der > iz, como es max, se cambiaria por el derecho
                            */
                            int cualEsMayor = compare(heap[i*2+2].getKey(), heap[i].getKey());
                            switch (cualEsMayor){
                                case 1, 0: // Estan bien termina
                                    return valorADevolver;
                                case -1: // Hay que cambiarlos
                                    swap(i, i*2+2);
                                    i = i*2+2;
                                    break;
                            }
                        case 0: // No importa  cual se usa, por defecto el izquierdo
                        case -1: /*
                            Is min y -1, iz < der, como es min, se cambiaria por el izquierdo
                            !isMin y -1 der < iz, como es max, se cambiaria por el izquierdo
                            */
                            int cualesMayor = compare(heap[i*2+1].getKey(), heap[i].getKey());
                            switch (cualesMayor) {
                                case 1, 0: // Estan bien termina
                                    return valorADevolver;
                                case -1: // Hay que cambiarlos
                                    swap(i, i * 2 + 1);
                                    i = i * 2 + 1;
                                    break;
                                }
                    }
                } else {
                    if (heap[i*2+1] != null) {
                    // Se revisa si estan ordenados el izquierdo y el padre
                    int cualEsMayor = compare(heap[i*2+1].getKey(), heap[i].getKey());
                    switch (cualEsMayor) {
                        case 1, 0: // Estan bien termina
                            return valorADevolver;
                        case -1: // Hay que cambiarlos
                            swap(i, i * 2 + 1);
                            i = i * 2 + 1;
                            break;
                    }
                }
                }
            }
            else{
                if (heap[i*2+2] != null){
                //Se revisa si estan ordenados
                int cualEsMayor = compare(heap[i*2+2].getKey(), heap[i].getKey());
                switch (cualEsMayor){
                    case 1, 0: // Estan bien termina
                        return valorADevolver;
                    case -1: // Hay que cambiarlos
                        swap(i, i*2+2);
                        i = i*2+2;
                        break;
                }
            } else {return valorADevolver;} // Los dos son null, es hoja
            }
        }
        return null;
    }

    public HeapNode<K,T> get(){
        return this.heap[0];
    }

    public int size(){
        return size;
    }

    private void orderHeap() {
        // Para la ordenacion usa la mitad del tamaño, dividiendo por el largo de un solo subarbol (iz. o der.)
        for (int i = size / 2 - 1; i >= 0; i--) {
            heapify(i); // Inicida la ordenacion para el ultimo valor
        }
    }

    private void heapify(int index) {
         /*
         El compare tiene metodo para isMin true and false. De esta forma solo se necesita una impl.
         Cambiar mayor por menor al leer si isMin es false
         */
        int hijoIz = 2 * index + 1; // Hijo izquierdo
        int hijoDer = 2 * index + 2; // Hijo derecho
        int menor = index; // Se supone que el valor del index es el minimo (isMin true tipo de heap)

        if (hijoIz < size && compare(heap[hijoIz].getKey(), heap[menor].getKey()) < 0) { // Si la posicion de la izquieda es menor
            menor = hijoIz; // Se cambia el valor del minimo
        }
        if (hijoDer < size && compare(heap[hijoDer].getKey(), heap[menor].getKey()) < 0) { // Si la posicion de la derecha es menor
            menor = hijoDer; // Se cambia el valor del minimo
        }

        if (menor != index) { // Si el padre no es el minimo (No tiene ordenamiento minimo)
            swap(index, menor); // Los cambia y vuelve a revisar
            heapify(menor); // Vuelve a revisar
        }
    }

    private void swap(int i, int j) {
        HeapNode<K, T> temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    private int compare(K key1, K key2) {
        if (isMin) {
            return key1.compareTo(key2);
        } else {

            return key2.compareTo(key1);
        }
    }
}
