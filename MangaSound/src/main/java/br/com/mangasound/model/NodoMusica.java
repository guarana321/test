package br.com.mangasound.model;

public class NodoMusica {
    private Musica musica;
    private NodoMusica proximo;

    public NodoMusica(Musica musica) {
        this.musica = musica;
    }

    public Musica getMusica() {
        return musica;
    }

    public NodoMusica getProximo() {
        return proximo;
    }

    public void setProximo(NodoMusica proximo) {
        this.proximo = proximo;
    }
}
