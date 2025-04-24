package br.com.mangasound.model;

public class ListaReproducao {
    private String nome;
    private NodoMusica primeiro;

    public ListaReproducao(String nome) {
        this.nome = nome;
        this.primeiro = null;
    }

    public String getNome() {
        return nome;
    }

    public NodoMusica getPrimeiro() {
        return primeiro;
    }

    public void adicionarMusica(Musica musica) {
        NodoMusica novoNodo = new NodoMusica(musica);
        if (primeiro == null) {
            primeiro = novoNodo;
        } else {
            NodoMusica atual = primeiro;
            while (atual.getProximo() != null) {
                atual = atual.getProximo();
            }
            atual.setProximo(novoNodo);
        }
    }

    public void moverMusica(int de, int para) {
        if (de == para) return;

        NodoMusica anterior = null, atual = primeiro;
        for (int i = 0; atual != null && i < de; i++) {
            anterior = atual;
            atual = atual.getProximo();
        }

        if (atual == null) return;

        if (anterior != null) {
            anterior.setProximo(atual.getProximo());
        } else {
            primeiro = atual.getProximo();
        }

        NodoMusica destinoAnterior = null;
        NodoMusica destino = primeiro;
        for (int i = 0; destino != null && i < para; i++) {
            destinoAnterior = destino;
            destino = destino.getProximo();
        }

        if (destinoAnterior != null) {
            atual.setProximo(destinoAnterior.getProximo());
            destinoAnterior.setProximo(atual);
        } else {
            atual.setProximo(primeiro);
            primeiro = atual;
        }
    }

    public void exibirLista() {
        NodoMusica atual = primeiro;
        int index = 0;
        while (atual != null) {
            System.out.println(index + " - " + atual.getMusica().getNome());
            atual = atual.getProximo();
            index++;
        }
    }
}
