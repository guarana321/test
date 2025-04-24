package br.com.mangasound.application;

import br.com.mangasound.service.ListaService;
import br.com.mangasound.service.MusicaService;

import java.util.Scanner;

public class MangaSoundApplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MusicaService musicaService = new MusicaService();
        ListaService listaService = new ListaService(musicaService);

        int opcao;
        do {
            System.out.println("\n--- MANGA SOUND ---");
            System.out.println("1. Adicionar Música ao Repositório");
            System.out.println("2. Criar Lista de Reprodução");
            System.out.println("3. Editar Lista de Reprodução");
            System.out.println("4. Executar Lista de Reprodução");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> musicaService.adicionarMusica(scanner);
                case 2 -> listaService.criarLista(scanner);
                case 3 -> listaService.editarLista(scanner);
                case 4 -> listaService.executarLista(scanner);
                case 5 -> System.out.println("Encerrando o MangaSound. Até logo!");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 5);
    }
}
