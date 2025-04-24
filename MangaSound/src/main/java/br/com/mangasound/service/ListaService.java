package br.com.mangasound.service;

import br.com.mangasound.model.ListaReproducao;
import br.com.mangasound.model.Musica;
import br.com.mangasound.model.NodoMusica;
import br.com.mangasound.util.AudioPlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ListaService {
    private final List<ListaReproducao> listas = new ArrayList<>();
    private final MusicaService musicaService;

    public ListaService(MusicaService musicaService) {
        this.musicaService = musicaService;
    }

    public void criarLista(Scanner scanner) {
        System.out.print("Digite o nome da nova lista: ");
        String nome = scanner.nextLine();
        listas.add(new ListaReproducao(nome));
        System.out.println("Lista \"" + nome + "\" criada com sucesso!");
    }

    public void editarLista(Scanner scanner) {
        if (listas.isEmpty()) {
            System.out.println("Nenhuma lista criada.");
            return;
        }

        exibirListas();
        System.out.print("Escolha o número da lista: ");
        int index = scanner.nextInt(); scanner.nextLine();

        if (index < 0 || index >= listas.size()) {
            System.out.println("Lista inválida.");
            return;
        }

        ListaReproducao lista = listas.get(index);
        lista.exibirLista();

        System.out.print("Digite o índice da música que quer mover: ");
        int de = scanner.nextInt();
        System.out.print("Digite a nova posição: ");
        int para = scanner.nextInt(); scanner.nextLine();

        lista.moverMusica(de, para);
        System.out.println("Música movida com sucesso!");
    }

    public void executarLista(Scanner scanner) {
        if (listas.isEmpty()) {
            System.out.println("Nenhuma lista criada.");
            return;
        }

        exibirListas();
        System.out.print("Escolha o número da lista: ");
        int index = scanner.nextInt(); scanner.nextLine();

        if (index < 0 || index >= listas.size()) {
            System.out.println("Lista inválida.");
            return;
        }

        ListaReproducao lista = listas.get(index);
        NodoMusica atual = lista.getPrimeiro();
        NodoMusica anterior = null;

        Scanner input = new Scanner(System.in);
        while (atual != null) {
            System.out.println("Reproduzindo: " + atual.getMusica().getNome());
            AudioPlayer player = new AudioPlayer(atual.getMusica().getCaminho());
            player.play();

            System.out.println("Comandos: [p]arar | [n]ext | [v]oltar");
            String comando = input.nextLine();

            switch (comando.toLowerCase()) {
                case "p" -> {
                    player.stop();
                    return;
                }
                case "n" -> {
                    player.stop();
                    anterior = atual;
                    atual = atual.getProximo();
                }
                case "v" -> {
                    player.stop();
                    atual = anterior;
                }
                default -> System.out.println("Comando inválido.");
            }
        }
    }

    private void exibirListas() {
        for (int i = 0; i < listas.size(); i++) {
            System.out.println(i + " - " + listas.get(i).getNome());
        }
    }
}
