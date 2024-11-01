package com.a3_backend.TAD;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

@Component
public class ListaEncadeada<T> implements Collection<T> {
    private No<T> inicio;
    private int tamanho;

    public ListaEncadeada() {
        this.inicio = null;
        this.tamanho = 0;
    }

    @Override
    public boolean add(T dado) {
        No<T> novoNo = new No<>(dado);
        if (inicio == null) {
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
    @Override
    public boolean remove(Object dado) {
        if (inicio == null) {
            return false;
        }

        if (inicio.dado.equals(dado)) {
            inicio = inicio.proximo;
            tamanho--;
            return false;
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
        }

        return true;
    };

    @Override
    public int size() {
        return tamanho;
    }

    @Override
    public boolean isEmpty() {
        if (inicio == null) {
            return true;
        };
        return false;
    }

    @Override
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

    @Override
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

    @Override
    public Object[] toArray() {
        Object[] array = new Object[tamanho];
        No<T> atual = inicio;
        for (int i = 0; i < tamanho; i++) {
            array[i] = atual.dado;
            atual = atual.proximo;
        }
        return array;
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        if (a.length < tamanho) {
            a = (T1[]) Arrays.copyOf(toArray(), tamanho, a.getClass());
        } else {
            System.arraycopy(toArray(), 0, a, 0, tamanho);
            if (a.length > tamanho) {
                a[tamanho] = null;
            }
        }
        return a;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c) {
            if (!contains(o)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        boolean modificado = false;
        for (T elemento : c) {
            if (add(elemento)) {
                modificado = true;
            }
        }
        return modificado;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean modificado = false;
        for (Object o : c) {
            if (remove(o)) {
                modificado = true;
            }
        }
        return modificado;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean modificado = false;
        No<T> atual = inicio;
        No<T> anterior = null;
        while (atual != null) {
            if (!c.contains(atual.dado)) {
                if (anterior == null) {
                    inicio = atual.proximo;
                } else {
                    anterior.proximo = atual.proximo;
                }
                tamanho--;
                modificado = true;
            } else {
                anterior = atual;
            }
            atual = atual.proximo;
        }
        return modificado;
    }

    @Override
    public void clear() {
        inicio = null;
        tamanho = 0;
    }
}
