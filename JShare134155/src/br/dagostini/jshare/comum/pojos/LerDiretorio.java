package br.dagostini.jshare.comum.pojos;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class LerDiretorio {

	public static void main(String[] args) {
		File dir = new File(".\\");
		
		List<Arquivo> listaArquivos = new ArrayList<>();
		List<Diretorio> listaDiretorios = new ArrayList<>();
		for (File file: dir.listFiles()) {
			if (file.isFile()) {
				Arquivo arq = new Arquivo();
				arq.setNome(file.getName());
				arq.setTamanho(file.length());
				listaArquivos.add(arq);
			} else {
				Diretorio diret = new Diretorio();
				diret.setNome(file.getName());
				listaDiretorios.add(diret);
			}
		}
		
		System.out.println("Diretorios");
		for (Diretorio directo : listaDiretorios) {
			System.out.println("\t" + directo.getNome());
		}
		
		System.out.println("Arquivos");
		for (Arquivo arquivo : listaArquivos) {
			System.out.println("\t" + arquivo.getTamanho() + "\t" + arquivo.getNome());
		}
	}
}
