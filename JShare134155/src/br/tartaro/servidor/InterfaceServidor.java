package br.tartaro.servidor;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.dagostini.jshare.comum.pojos.Arquivo;
import br.dagostini.jshare.comun.Cliente;
import br.dagostini.jshare.comun.IServer;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Color;

public class InterfaceServidor extends JFrame implements IServer {

	private JPanel contentPane;
	private JTextField txtPorta;
	private JButton btnParar;
	private JButton btnIniciar;
	private JTextArea txtPainel;
	private JTextField txtIp;
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy H:mm:ss:SSS");
	private HashMap<Cliente, List<Arquivo>> mapaServidor;
	private Registry registry;
	private IServer servidor;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfaceServidor frame = new InterfaceServidor();
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
	public InterfaceServidor() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblNewLabel = new JLabel("IP");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);
		
		txtIp = new JTextField();
		GridBagConstraints gbc_txtIp = new GridBagConstraints();
		gbc_txtIp.gridwidth = 6;
		gbc_txtIp.insets = new Insets(0, 0, 5, 5);
		gbc_txtIp.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtIp.gridx = 1;
		gbc_txtIp.gridy = 0;
		contentPane.add(txtIp, gbc_txtIp);
		txtIp.setColumns(10);
		
		JLabel lblPorta = new JLabel("Porta");
		GridBagConstraints gbc_lblPorta = new GridBagConstraints();
		gbc_lblPorta.insets = new Insets(0, 0, 5, 5);
		gbc_lblPorta.anchor = GridBagConstraints.EAST;
		gbc_lblPorta.gridx = 0;
		gbc_lblPorta.gridy = 1;
		contentPane.add(lblPorta, gbc_lblPorta);
		
		txtPorta = new JTextField();
		txtPorta.setText("1818");
		GridBagConstraints gbc_txtPorta = new GridBagConstraints();
		gbc_txtPorta.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtPorta.gridwidth = 6;
		gbc_txtPorta.insets = new Insets(0, 0, 5, 5);
		gbc_txtPorta.gridx = 1;
		gbc_txtPorta.gridy = 1;
		contentPane.add(txtPorta, gbc_txtPorta);
		txtPorta.setColumns(10);
		
		btnIniciar = new JButton("Iniciar Serviço");
		btnIniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				iniciarServico();
			}
		});
		GridBagConstraints gbc_btnIniciar = new GridBagConstraints();
		gbc_btnIniciar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnIniciar.gridwidth = 2;
		gbc_btnIniciar.insets = new Insets(0, 0, 5, 5);
		gbc_btnIniciar.gridx = 7;
		gbc_btnIniciar.gridy = 1;
		contentPane.add(btnIniciar, gbc_btnIniciar);
		
		btnParar = new JButton("Parar Serviço");
		btnParar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pararServico();
			}
		});
		GridBagConstraints gbc_btnParar = new GridBagConstraints();
		gbc_btnParar.insets = new Insets(0, 0, 5, 0);
		gbc_btnParar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnParar.gridwidth = 5;
		gbc_btnParar.gridx = 9;
		gbc_btnParar.gridy = 1;
		contentPane.add(btnParar, gbc_btnParar);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 14;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 2;
		contentPane.add(scrollPane, gbc_scrollPane);
		
		txtPainel = new JTextArea();
		txtPainel.setEnabled(true);
		txtPainel.setBackground(Color.BLACK);
		txtPainel.setForeground(Color.GREEN);
		scrollPane.setViewportView(txtPainel);
	}

	protected void pararServico() {
	

			mostrar("SERVIDOR PARANDO O SERVIÇO.");

			fecharTodosClientes();

			try {
				UnicastRemoteObject.unexportObject(this, true);
				UnicastRemoteObject.unexportObject(registry, true);

				btnIniciar.setEnabled(true);
				btnParar.setEnabled(false);
				txtIp.setEnabled(true);
				txtPorta.setEnabled(true);

				mostrar("Serviço encerrado.");

			} catch (RemoteException e) {
				e.printStackTrace();
			}

		}

		/**
		 * Desconecta todos os clientes.
		 */
		

	
		private void fecharTodosClientes() {
			mostrar("DESCONECTANDO TODOS OS CLIENTES.");
		}
		
		private void mostrar(String string) {
			txtPainel.append(sdf.format(new Date()));
			txtPainel.append(" -> ");
			txtPainel.append(string);
			txtPainel.append("\n");
		}

	protected void iniciarServico() {
		
		String ip = txtIp.getText().trim();
		if (!ip.matches("[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}")) {
			JOptionPane.showMessageDialog(this, "Digite um IP Valido.");
			return;
		}
		
		String strPorta = txtPorta.getText().trim();

		if (!strPorta.matches("[0-9]+") || strPorta.length() > 5) {
			JOptionPane.showMessageDialog(this, "A porta deve ser um valor numérico de no máximo 5 dígitos!");
			return;
		}
		int intPorta = Integer.parseInt(strPorta);
		if (intPorta < 1024 || intPorta > 65535) {
			JOptionPane.showMessageDialog(this, "A porta deve estar entre 1024 e 65535");
			return;
		}

		try {

			servidor = (IServer) UnicastRemoteObject.exportObject((IServer) this, 0);
			registry = LocateRegistry.createRegistry(intPorta);
			registry.rebind(IServer.NOME_SERVICO, servidor);

			mostrar("Serviço iniciado.");

			btnIniciar.setEnabled(false);
			btnParar.setEnabled(true);
			txtIp.setEnabled(false);
			txtPorta.setEnabled(false);

		} catch (RemoteException e) {
			JOptionPane.showMessageDialog(this, "Erro criando registro, verifique se a porta já não está sendo usada.");
			e.printStackTrace();
		}


	}

	@Override
	public void registrarCliente(Cliente c) throws RemoteException {
		mostrar(c.getNome()+" registrado como cliente.");
	}

	@Override
	public void publicarListaArquivos(Cliente c, List<Arquivo> lista) throws RemoteException {
			
		mapaServidor = new HashMap<>();
		mapaServidor.put(c, lista);
		
		for (int i = 0; i < lista.size(); i++) {
			System.out.println(lista.get(i).getNome() + lista.get(i).getTamanho());
		}
		mostrar(c.getNome()+" publicou lista de arquivos.");
	}

	@Override
	public Map<Cliente, List<Arquivo>> procurarArquivo(String nome) throws RemoteException {
		HashMap<Cliente, List<Arquivo>> resultado = new HashMap<>();
		List<Arquivo> arquivosCliente = new ArrayList<>();

		for (Map.Entry<Cliente, List<Arquivo>> lista : mapaServidor.entrySet()) {
			
			for(Arquivo arquivo : mapaServidor.get(lista.getKey())){
				if (arquivo.getNome().contains(nome)) {
					arquivosCliente.add(arquivo);
				}
			}
			
			if (!arquivosCliente.isEmpty()) {
				Cliente c = new Cliente(lista.getKey().getNome(), lista.getKey().getIp(), lista.getKey().getPorta());
				System.out.println(c.getNome());
				for(Arquivo ar : arquivosCliente){
					System.out.println(c.getNome());
				}
				resultado.put(c, arquivosCliente);
			}
		}
		
		return resultado;
	}

	@Override
	public byte[] baixarArquivo(Arquivo arq) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void desconectar(Cliente c) throws RemoteException {
		mostrar(c.getNome()+" Saiu.");
	}
}