package br.dagostini.jshare.comum.pojos;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class LerArquivo {
	
	public LerArquivo(){
		
	}
	
	public LerArquivo(File file) {
		byte[] dados = leia(file);
		escreva(new File("Copia de " + file.getName()),dados);
	}

	private byte[] leia(File file) {
		Path path = Paths.get(file.getPath());
		try {
			byte[] dados = Files.readAllBytes(path);
			return dados;
		} catch (IOException e) {
			throw new RuntimeException();
			
		}
		
	}
	
	public void escreva(File file, byte[] dados){
		try {
			Files.write(Paths.get(file.getPath()), dados, StandardOpenOption.CREATE);
		} catch (IOException e) {
			throw new RuntimeException();
		}
	}

	public static void main(String[] args) {
		new LerArquivo(new File("logo.pgn"));
	}

}
