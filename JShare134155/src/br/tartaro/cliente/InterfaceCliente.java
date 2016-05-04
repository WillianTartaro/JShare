package br.tartaro.cliente;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import br.dagostini.jshare.comum.pojos.Arquivo;
import br.dagostini.jshare.comum.pojos.Diretorio;
import br.dagostini.jshare.comum.pojos.LerArquivo;
import br.dagostini.jshare.comun.Cliente;
import br.dagostini.jshare.comun.IServer;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class InterfaceCliente extends JFrame implements IServer {

	private JPanel contentPane;
	private JTextField txtIP;
	private JTextField txtNome;
	private JLabel lblPorta_1;
	private JButton btnConectar;
	private JButton btnSair;
	private JLabel lblPorta;
	private JTextField txtPorta;
	private IServer servidor;
	private Registry registry;
	private Cliente cliente;
	private JLabel lblPesquisar;
	private JTextField txtBusca;
	private JScrollPane scrollPane;
	private JTable table;
	private JButton btnDown;
	private JButton btnPesquisar;
	private ModelArq modelArquivo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfaceCliente frame = new InterfaceCliente();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public InterfaceCliente() {
		setTitle("");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 503, 471);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblNome = new JLabel("IP");
		GridBagConstraints gbc_lblNome = new GridBagConstraints();
		gbc_lblNome.anchor = GridBagConstraints.EAST;
		gbc_lblNome.insets = new Insets(0, 0, 5, 5);
		gbc_lblNome.gridx = 0;
		gbc_lblNome.gridy = 0;
		contentPane.add(lblNome, gbc_lblNome);
		
		txtIP = new JTextField();
		GridBagConstraints gbc_txtIP = new GridBagConstraints();
		gbc_txtIP.gridwidth = 6;
		gbc_txtIP.insets = new Insets(0, 0, 5, 5);
		gbc_txtIP.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtIP.gridx = 1;
		gbc_txtIP.gridy = 0;
		contentPane.add(txtIP, gbc_txtIP);
		txtIP.setColumns(10);
		
		lblPorta = new JLabel("Porta");
		GridBagConstraints gbc_lblPorta = new GridBagConstraints();
		gbc_lblPorta.anchor = GridBagConstraints.EAST;
		gbc_lblPorta.insets = new Insets(0, 0, 5, 5);
		gbc_lblPorta.gridx = 7;
		gbc_lblPorta.gridy = 0;
		contentPane.add(lblPorta, gbc_lblPorta);
		
		txtPorta = new JTextField();
		GridBagConstraints gbc_txtPorta = new GridBagConstraints();
		gbc_txtPorta.gridwidth = 4;
		gbc_txtPorta.insets = new Insets(0, 0, 5, 5);
		gbc_txtPorta.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtPorta.gridx = 8;
		gbc_txtPorta.gridy = 0;
		contentPane.add(txtPorta, gbc_txtPorta);
		txtPorta.setColumns(10);
		
		lblPorta_1 = new JLabel("Nome");
		GridBagConstraints gbc_lblPorta_1 = new GridBagConstraints();
		gbc_lblPorta_1.anchor = GridBagConstraints.EAST;
		gbc_lblPorta_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblPorta_1.gridx = 0;
		gbc_lblPorta_1.gridy = 1;
		contentPane.add(lblPorta_1, gbc_lblPorta_1);
		
		txtNome = new JTextField();
		GridBagConstraints gbc_txtNome = new GridBagConstraints();
		gbc_txtNome.gridwidth = 6;
		gbc_txtNome.insets = new Insets(0, 0, 5, 5);
		gbc_txtNome.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtNome.gridx = 1;
		gbc_txtNome.gridy = 1;
		contentPane.add(txtNome, gbc_txtNome);
		txtNome.setColumns(10);
		
		btnConectar = new JButton("Conectar");
		btnConectar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				conectar();
			}
		});
		GridBagConstraints gbc_btnConectar = new GridBagConstraints();
		gbc_btnConectar.insets = new Insets(0, 0, 5, 5);
		gbc_btnConectar.gridx = 7;
		gbc_btnConectar.gridy = 1;
		contentPane.add(btnConectar, gbc_btnConectar);
		
		btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				desconectar();
			}
		});
		GridBagConstraints gbc_btnSair = new GridBagConstraints();
		gbc_btnSair.anchor = GridBagConstraints.WEST;
		gbc_btnSair.gridwidth = 3;
		gbc_btnSair.insets = new Insets(0, 0, 5, 5);
		gbc_btnSair.gridx = 8;
		gbc_btnSair.gridy = 1;
		contentPane.add(btnSair, gbc_btnSair);
		
		lblPesquisar = new JLabel("Pesquisa");
		GridBagConstraints gbc_lblPesquisar = new GridBagConstraints();
		gbc_lblPesquisar.insets = new Insets(0, 0, 5, 5);
		gbc_lblPesquisar.gridx = 0;
		gbc_lblPesquisar.gridy = 2;
		contentPane.add(lblPesquisar, gbc_lblPesquisar);
		
		txtBusca = new JTextField();
		GridBagConstraints gbc_txtBusca = new GridBagConstraints();
		gbc_txtBusca.gridwidth = 7;
		gbc_txtBusca.insets = new Insets(0, 0, 5, 5);
		gbc_txtBusca.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtBusca.gridx = 1;
		gbc_txtBusca.gridy = 2;
		contentPane.add(txtBusca, gbc_txtBusca);
		txtBusca.setColumns(10);
		
		btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				buscaArquivo();
			}
		});
		GridBagConstraints gbc_btnPesquisar = new GridBagConstraints();
		gbc_btnPesquisar.insets = new Insets(0, 0, 5, 5);
		gbc_btnPesquisar.gridx = 9;
		gbc_btnPesquisar.gridy = 2;
		contentPane.add(btnPesquisar, gbc_btnPesquisar);
		
		scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
			}
		});
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.gridwidth = 14;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 3;
		contentPane.add(scrollPane, gbc_scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		btnDown = new JButton("Download");
		btnDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				downloadArquivo();
			}
		});
		GridBagConstraints gbc_btnDown = new GridBagConstraints();
		gbc_btnDown.anchor = GridBagConstraints.EAST;
		gbc_btnDown.gridwidth = 3;
		gbc_btnDown.insets = new Insets(0, 0, 0, 5);
		gbc_btnDown.gridx = 5;
		gbc_btnDown.gridy = 4;
		contentPane.add(btnDown, gbc_btnDown);
	}

	protected void buscaArquivo() {

			String busca = txtBusca.getText();
			Map<Cliente, List<Arquivo>> arquivosBuscados = new HashMap<>();
			if (busca.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Campo vazio!");
				return;
			}
			
			try {
				arquivosBuscados = servidor.procurarArquivo(busca);
				if (arquivosBuscados.isEmpty()) {
					JOptionPane.showMessageDialog(this, "Arquivos nao encontrados!");
					return;
				} else {
					modelArquivo = new ModelArq(arquivosBuscados);
					table.setModel(modelArquivo);
				}
			} catch (RemoteException e) {
				e.printStackTrace();
			}
	}

	protected void downloadArquivo() {
		System.out.println("----------------------");
		System.out.println("Capturando arquivo");
		System.out.println("----------------------");
		String nomeArq = (String) table.getValueAt(table.getSelectedRow(), 0);
		String IP = (String) table.getValueAt(table.getSelectedRow(), 3);
		int porta = (int) table.getValueAt(table.getSelectedRow(), 4);

		Arquivo arquivo = new Arquivo();
		arquivo.setNome(nomeArq);
		
		novoServidor(IP, porta, arquivo);
	}

	private void novoServidor(String ip, int porta, Arquivo arquivo) {

		System.out.println("novo servidor");
		System.out.println("----------------------");
		LerArquivo lerArq = new LerArquivo();
		
		try {
			registry = LocateRegistry.getRegistry(ip,porta);
			//IServer clienteServidor = (IServer) registry.lookup(IServer.NOME_SERVICO);
			//NULL --> era brinks
			byte[] downArquivo = baixarArquivo(arquivo);
			System.out.println("Baixando");
			System.out.println("----------------------");
			escreverArquivo(new File("C:\\Download\\"+arquivo.getNome()), downArquivo);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
	}

	protected void desconectar() {
		try {
			desconectar(cliente);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	protected void conectar() {

		String nomeCliente = txtNome.getText().trim();
		if (nomeCliente.length() == 0) {
			JOptionPane.showMessageDialog(this, "Você precisa digitar um nome!");
			return;
		}

		// Endereço IP
		String ip = txtIP.getText().trim();
		if (!ip.matches("[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}")) {
			JOptionPane.showMessageDialog(this, "O endereço ip parece inválido!");
			return;
		}

		// Porta
		String strPorta = txtPorta.getText().trim();
		if (!strPorta.matches("[0-9]+") || strPorta.length() > 5) {
			JOptionPane.showMessageDialog(this, "A porta deve ser um valor numérico de no máximo 5 dígitos!");
			return;
		}
		int intPorta = Integer.parseInt(strPorta);

		// Iniciando objetos para conexão.
		try {
			registry = LocateRegistry.getRegistry(ip, intPorta);

			servidor = (IServer) registry.lookup(IServer.NOME_SERVICO);
			cliente = new Cliente(nomeCliente, ip, intPorta);
			//cliente = (Cliente) UnicastRemoteObject.exportObject(this, 0);

			// Avisando o servidor que está entrando no Chat.
			servidor.registrarCliente(cliente);
			servidor.publicarListaArquivos(cliente, criarListaCliente());

			btnSair.setEnabled(true);

			btnConectar.setEnabled(false);
			txtNome.setEnabled(false);
			txtIP.setEnabled(false);
			txtPorta.setEnabled(false);



		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		
	}
	
	

	private List<Arquivo> criarListaCliente() {

		File dir = new File("C:\\Uploads\\");
		List<Arquivo> listArquivo = new ArrayList<>();
		List<Diretorio> listDiretorio = new ArrayList<>();
		
		for(File file : dir.listFiles()){
			if (file.isFile()) {
				Arquivo doc = new Arquivo();
				doc.setNome(file.getName());
				System.out.println(file.getName());
				doc.setTamanho(file.length());
				listArquivo.add(doc);
			} else {
				Diretorio diretorio = new Diretorio();
				diretorio.setNome(file.getName());
				listDiretorio.add(diretorio);
			}
		}
		
		return listArquivo;
	}

	@Override
	public void registrarCliente(Cliente c) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void publicarListaArquivos(Cliente c, List<Arquivo> lista) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Map<Cliente, List<Arquivo>> procurarArquivo(String nome) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] baixarArquivo(Arquivo arq) throws RemoteException {
		List<Arquivo> arquivos = criarListaCliente();
		for (Arquivo arquivo : arquivos) {
			if (arquivo.getNome().contains(arq.getNome())) {
				byte[] dados = lerArquivo(new File("C:\\Uploads\\"+arq.getNome()));
				return dados;
			}
		}
		return null;
	}

	private byte[] lerArquivo(File file) {
		byte[] dados;
		
		Path path = Paths.get(file.getPath());
		try {
			dados = Files.readAllBytes(path);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
		return dados;
	}
	
	private void escreverArquivo(File file, byte[] dados){
		try {
			Files.write(Paths.get(file.getPath()), dados, StandardOpenOption.CREATE);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void desconectar(Cliente c) throws RemoteException {
		
		if (servidor != null) {
			servidor.desconectar(c);
			servidor = null;
		}
		
		btnSair.setEnabled(false);
		btnConectar.setEnabled(true);
		txtNome.setEnabled(true);
		txtIP.setEnabled(true);
		txtPorta.setEnabled(true);
		
	}

}
