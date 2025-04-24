package br.com.mangasound.service;

import br.com.mangasound.model.Musica;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MusicaService {
    private final List<Musica> musicas = new ArrayList<>();
    private final File pastaRepositorio = new File("repositorio");

    public MusicaService() {
        if (!pastaRepositorio.exists()) {
            pastaRepositorio.mkdir();
        }
    }

    public void adicionarMusica(Scanner scanner) {
        System.out.print("Digite o caminho do arquivo .wav: ");
        String caminhoOriginal = scanner.nextLine();
        File arquivoOriginal = new File(caminhoOriginal);

        if (!arquivoOriginal.exists() || !arquivoOriginal.getName().endsWith(".wav")) {
            System.out.println("Arquivo inválido.");
            return;
        }

        File destino = new File(pastaRepositorio, arquivoOriginal.getName());
        try {
            Files.copy(arquivoOriginal.toPath(), destino.toPath(), StandardCopyOption.REPLACE_EXISTING);
            Musica nova = new Musica(arquivoOriginal.getName(), destino.getAbsolutePath());
            musicas.add(nova);
            System.out.println("Música adicionada com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao copiar o arquivo: " + e.getMessage());
        }
    }

    public List<Musica> listarMusicas() {
        return musicas;
    }

    public void exibirMusicas() {
        for (int i = 0; i < musicas.size(); i++) {
            System.out.println(i + " - " + musicas.get(i).getNome());
        }
    }

    public Musica selecionarMusica(int index) {
        if (index >= 0 && index < musicas.size()) {
            return musicas.get(index);
        }
        return null;
    }
}
