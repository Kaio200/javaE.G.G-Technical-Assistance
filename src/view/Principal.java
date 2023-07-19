package view;

import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.DAO;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Font;

public class Principal extends JFrame {

	// Instaciar objetos JDBC
	DAO dao = new DAO();
	private Connection con;	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblStatus;
	private JLabel lblData;
	//esta label será alterada pala tela de Login (public)
	public JLabel lblUsuario;
	public JButton btnUsuarios;
	public JButton btnProdutos;
	public JPanel panelRodape;
	public JButton btnRelatorios;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
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
	public Principal() {
		setTitle("E.G.G - TECHNICAL ASSISTANCE");
		setResizable(false);
		setBackground(Color.BLACK);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Principal.class.getResource("/img/DR Eggman.png")));
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				status();
				setarData();
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1130, 499);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		btnUsuarios = new JButton("");
		btnUsuarios.setBackground(new Color(255, 255, 0));
		btnUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// abrir tela de usuários
				Usuarios usuarios = new Usuarios();
				usuarios.setVisible(true);
			}
		});
		btnUsuarios.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnUsuarios.setIcon(new ImageIcon(Principal.class.getResource("/img/icons8-eggman-robotnik-50.png")));
		btnUsuarios.setToolTipText("Usuários");
		btnUsuarios.setBounds(10, 66, 135, 113);
		contentPane.add(btnUsuarios);

		panelRodape = new JPanel();
		panelRodape.setBackground(new Color(170, 0, 0));
		panelRodape.setBounds(0, 406, 1114, 54);
		contentPane.add(panelRodape);
		panelRodape.setLayout(null);

		lblStatus = new JLabel("");
		lblStatus.setIcon(new ImageIcon(Principal.class.getResource("/img/bdoff.png")));
		lblStatus.setBounds(10, 11, 32, 32);
		panelRodape.add(lblStatus);
		
		lblData = new JLabel("New label");
		lblData.setForeground(SystemColor.text);
		lblData.setBounds(877, 11, 227, 32);
		panelRodape.add(lblData);
		
		JLabel lblNewLabel_6 = new JLabel("Usuario:");
		lblNewLabel_6.setForeground(new Color(255, 255, 255));
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_6.setBounds(68, 0, 55, 54);
		panelRodape.add(lblNewLabel_6);
		
		lblUsuario = new JLabel("");
		lblUsuario.setForeground(new Color(255, 255, 255));
		lblUsuario.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblUsuario.setBounds(133, 0, 227, 54);
		panelRodape.add(lblUsuario);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.setForeground(new Color(255, 0, 0));
		btnNewButton.setBackground(new Color(0, 0, 0));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//clicar no botão sobre
				//mostrar a janela sobre
				Sobre sobre = new Sobre();
				sobre.setVisible(true);
			
			}
		});
		btnNewButton.setIcon(new ImageIcon(Principal.class.getResource("/img/about.png")));
		btnNewButton.setBounds(1033, 11, 71, 46);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBackground(new Color(0, 0, 0));
		lblNewLabel.setIcon(new ImageIcon(Principal.class.getResource("/img/DR Eggman.png")));
		lblNewLabel.setBounds(533, 0, 500, 407);
		contentPane.add(lblNewLabel);
		
		JButton btnServiço = new JButton("");
		btnServiço.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Servicos clientes = new Servicos();
				clientes.setVisible(true);
			}
		});
		btnServiço.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnServiço.setIcon(new ImageIcon(Principal.class.getResource("/img/icons8-serviço-50.png")));
		btnServiço.setBackground(new Color(170, 0, 0));
		btnServiço.setBounds(10, 220, 135, 113);
		contentPane.add(btnServiço);
		
		JButton btnClientes = new JButton("");
		btnClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Clientes clientes = new Clientes();
				clientes.setVisible(true);
			}
		});
		btnClientes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnClientes.setIcon(new ImageIcon(Principal.class.getResource("/img/icons8-chamada-em-conferência-50.png")));
		btnClientes.setBackground(new Color(170, 0, 0));
		btnClientes.setBounds(155, 66, 135, 113);
		contentPane.add(btnClientes);
		
		btnRelatorios = new JButton("");
		btnRelatorios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Relatórios clientes = new Relatórios();
				clientes.setVisible(true);
			}
		});
		btnRelatorios.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRelatorios.setIcon(new ImageIcon(Principal.class.getResource("/img/icons8-relatório-de-negócios-50.png")));
		btnRelatorios.setBackground(new Color(255, 255, 0));
		btnRelatorios.setBounds(155, 220, 135, 113);
		contentPane.add(btnRelatorios);
		
		JButton btnFornecedor = new JButton("");
		btnFornecedor.setIcon(new ImageIcon(Principal.class.getResource("/img/acordo.png")));
		btnFornecedor.setBackground(new Color(255, 255, 0));
		btnFornecedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Fornecedores clientes = new Fornecedores();
				clientes.setVisible(true);
			}
		});
		btnFornecedor.setBounds(300, 66, 135, 113);
		contentPane.add(btnFornecedor);
		
		btnProdutos = new JButton("");
		btnProdutos.setIcon(new ImageIcon(Principal.class.getResource("/img/caixa.png")));
		btnProdutos.setBackground(new Color(149, 0, 0));
		btnProdutos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Produtos clientes = new Produtos();
				clientes.setVisible(true);
			}
		});
		btnProdutos.setBounds(300, 220, 135, 113);
		contentPane.add(btnProdutos);
		
		JLabel lblNewLabel_1 = new JLabel("      Usuarios");
		lblNewLabel_1.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		lblNewLabel_1.setForeground(new Color(255, 255, 0));
		lblNewLabel_1.setBounds(10, 190, 135, 19);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("      Clientes");
		lblNewLabel_2.setForeground(new Color(149, 0, 0));
		lblNewLabel_2.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		lblNewLabel_2.setBounds(155, 190, 135, 19);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("    Fornecedor");
		lblNewLabel_3.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		lblNewLabel_3.setForeground(new Color(255, 255, 0));
		lblNewLabel_3.setBounds(300, 190, 135, 19);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("      Serviço");
		lblNewLabel_4.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		lblNewLabel_4.setForeground(new Color(149, 0, 0));
		lblNewLabel_4.setBounds(10, 344, 135, 19);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("    Relatórios");
		lblNewLabel_5.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		lblNewLabel_5.setForeground(new Color(255, 255, 0));
		lblNewLabel_5.setBounds(155, 344, 135, 19);
		contentPane.add(lblNewLabel_5);
	}// fim do construtor

	/**
	 * Método responsável por exibir o status da conexão
	 */
	private void status() {
		try {
			// abrir a conexão
			con = dao.conectar();
			if (con == null) {
				// System.out.println("Erro de conexão");
				lblStatus.setIcon(new ImageIcon(Principal.class.getResource("/img/bdoff.png")));
			} else {
				// System.out.println("Banco conectado");
				lblStatus.setIcon(new ImageIcon(Principal.class.getResource("/img/bdon.png")));
			}
			// NUNCA esquecer de fechar a conexão
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}// fim do método status()

	/**
	 * Método responsável por setar a data no rodapé
	 */
	private void setarData() {
		// criar objeto para trazer a data do sistema
		Date data = new Date();
		// criar objeto para formatar a data
		DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);
		// alterar o texto da label pela data atual formatada
		lblData.setText(formatador.format(data));
	}
}// fim do código
