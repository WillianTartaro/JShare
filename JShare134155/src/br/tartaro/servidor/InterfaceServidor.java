package br.tartaro.servidor;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



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
import java.rmi.server.UnicastRemoteObject;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Color;

public class InterfaceServidor extends JFrame {

	private JPanel contentPane;
	private JTextField txtPorta;
	private JButton btnParar;
	private JButton btnIniciar;
	private JTextArea txtPainel;
	private JTextField txtIp;
	
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
		txtPainel.setBackground(Color.BLACK);
		txtPainel.setForeground(Color.GREEN);
		scrollPane.setViewportView(txtPainel);
	}

	protected void pararServico() {
		btnIniciar.setEnabled(true);
		btnParar.setEnabled(false);
		txtPainel.append("Serviço Parado - OLHE PARA O LADO \nSE LIGA NO MESTIÇO NA BATIDA DO CAVADO. ♪♫♪");
	}

	protected void iniciarServico() {
		btnIniciar.setEnabled(false);
		btnParar.setEnabled(true);
		txtPainel.setText(null);
		txtPainel.append("Serviço Iniciado - SIGA EM FRENTE \n");


	}
}