package com.a3_backend.TAD;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class TADFilaEncadeada<T> implements Iterable<T> {
    private No<T> inicio;
    private No<T> fim;
    private int tamanho;

    private static class No<T> {
        T elemento;
        No<T> proximo;

        No(T elemento) {
            this.elemento = elemento;
        }
    }
    public void enfileirar(T elemento) {
        No<T> novoNo = new No<>(elemento);
        if (estaVazia()) {
            inicio = novoNo;
        } else {
            fim.proximo = novoNo;
        }
        fim = novoNo;
        tamanho++;
    }

    public T desenfileirar() {
        if (estaVazia()) {
            throw new NoSuchElementException("A fila está vazia");
        }
        T elemento = inicio.elemento;
        inicio = inicio.proximo;
        if (inicio == null) {
            fim = null;
        }
        tamanho--;
        return elemento;
    }

    public T primeiro() {
        if (estaVazia()) {
            throw new NoSuchElementException("A fila está vazia");
        }
        return inicio.elemento;
    }

    public boolean estaVazia() {
        return tamanho == 0;
    }

    public int tamanho() {
        return tamanho;
    }

    @Override
    public Iterator<T> iterator() {
        return new IteradorFila();
    }

    private class IteradorFila implements Iterator<T> {
        private No<T> atual = inicio;

        @Override
        public boolean hasNext() {
            return atual != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T elemento = atual.elemento;
            atual = atual.proximo;
            return elemento;
        }
    }
}
