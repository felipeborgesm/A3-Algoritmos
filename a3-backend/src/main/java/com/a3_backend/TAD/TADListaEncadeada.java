package com.a3_backend.TAD;

import com.a3_backend.dto.ProdutoMaisVendidoResponse;
import java.util.*;

public class TADListaEncadeada<T> implements Iterable<T> {
    private No<T> inicio;
    private int tamanho;

    public class No<T> {
        public T dado;
        public No<T> proximo;

        public No(T dado) {
            this.dado = dado;
            this.proximo = null;
        }
    }

    public TADListaEncadeada() {
        this.inicio = null;
        this.tamanho = 0;
    }

    public boolean add(T dado) {
        No<T> novoNo = new No<>(dado);
        if (isEmpty()) {
            inicio = novoNo;
        } else {
            No<T> atual = inicio;
            while (atual.proximo != null) {
                atual = atual.proximo;
            }
            atual.proximo = novoNo;
        }
        tamanho++;
        return true;
    }

    public boolean remove(Object dado) {
        if (inicio == null) {
            return false;
        }

        if (inicio.dado.equals(dado)) {
            inicio = inicio.proximo;
            tamanho--;
            return true;
        }

        No<T> anterior = inicio;
        No<T> atual = inicio.proximo;
        while (atual != null && !atual.dado.equals(dado)) {
            anterior = atual;
            atual = atual.proximo;
        }

        if (atual != null) {
            anterior.proximo = atual.proximo;
            tamanho--;
            return true;
        }

        return false;
    };

    public int size() {
        return tamanho;
    }

    public boolean isEmpty() {
        if (inicio == null) {
            return true;
        };
        return false;
    }

    public boolean contains(Object o) {
        No<T> atual = inicio;
        while (atual != null) {
            if (atual.dado.equals(o)) {
                return true;
            }
            atual = atual.proximo;
        }
        return false;
    }

    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private No<T> atual = inicio;

            @Override
            public boolean hasNext() {
                return atual != null;
            }

            @Override
            public T next() {
                if (hasNext()) {
                    T dado = atual.dado;
                    atual = atual.proximo;
                    return dado;
                } else {
                    throw new NoSuchElementException();
                }
            }
        };
    }

    // Método para serialização JSON
    public Object[] getItens() {
        return toArray();
    }

    public T[] toArray() {
        @SuppressWarnings("unchecked")
        T[] array = (T[]) new Object[tamanho];
        No<T> atual = inicio;
        for (int i = 0; i < tamanho; i++) {
            array[i] = atual.dado;
            atual = atual.proximo;
        }
        return array;
    }

    public void sort() {
        if (inicio == null || inicio.proximo == null) {
            return; // Nada para ordenar
        }
        inicio = mergeSort(inicio);
    }

    private No<T> encontrarMeio(No<T> head) {
        if (head == null) {
            return null;
        }

        No<T> lento = head;
        No<T> rapido = head;

        while (rapido.proximo != null && rapido.proximo.proximo != null) {
            lento = lento.proximo;
            rapido = rapido.proximo.proximo;
        }
        return lento;
    }

    private No<T> mergeSort(No<T> head) {
        if (head == null || head.proximo == null) {
            return head;
        }

        No<T> meio = encontrarMeio(head);
        No<T> segundaMetade = meio.proximo;
        meio.proximo = null;

        No<T> esquerda = mergeSort(head);
        No<T> direita = mergeSort(segundaMetade);

        return merge(esquerda, direita);
    }

    private No<T> merge(No<T> esquerda, No<T> direita) {
        No<T> dummyHead = new No<>(null); // Nó auxiliar para facilitar a construção
        No<T> atual = dummyHead;

        while (esquerda != null && direita != null) {
            ProdutoMaisVendidoResponse pedidoEsquerda = (ProdutoMaisVendidoResponse) esquerda.dado;
            ProdutoMaisVendidoResponse pedidoDireita = (ProdutoMaisVendidoResponse) direita.dado;

            // Invertendo a comparação para ordenar em ordem decrescente
            int compareQuantidade = pedidoEsquerda.getQuantidadeTotal().compareTo(pedidoDireita.getQuantidadeTotal());
            if (compareQuantidade < 0 || (compareQuantidade == 0 &&
                    pedidoEsquerda.getValorTotal().compareTo(pedidoDireita.getValorTotal()) <= 0)) {
                atual.proximo = esquerda;
                esquerda = esquerda.proximo;
            } else {
                atual.proximo = direita;
                direita = direita.proximo;
            }
            atual = atual.proximo;
        }

        // Adiciona os elementos restantes
        if (esquerda != null) {
            atual.proximo = esquerda;
        }
        if (direita != null) {
            atual.proximo = direita;
        }

        return dummyHead.proximo;
    }
}
