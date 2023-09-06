package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import model.DAO;
import utils.Validador;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Font;
import java.awt.Cursor;

@SuppressWarnings("unused")
public class Login extends JFrame {

	DAO dao = new DAO();
	private Connection con;
	private PreparedStatement pst;
	private ResultSet rs;
	Principal principal = new Principal();

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtLogin;
	private JPasswordField txtSenha;
	private JButton btnAcessar;
	private AbstractButton lblStatus;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Login() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				status();
			}
		});
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/img/DR Eggman.png")));
		setResizable(false);
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 860, 630);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("Welcome");
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_3.setFont(new Font("Comic Sans MS", Font.BOLD, 25));
		lblNewLabel_3.setBounds(377, 45, 106, 49);
		contentPane.add(lblNewLabel_3);

		JLabel lblNewLabel = new JLabel("Login:");
		lblNewLabel.setBounds(166, 180, 64, 49);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		contentPane.add(lblNewLabel);

		txtLogin = new JTextField();
		txtLogin.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		txtLogin.setBounds(240, 180, 378, 49);
		txtLogin.setBackground(new Color(255, 255, 0));
		txtLogin.setForeground(new Color(0, 0, 0));
		txtLogin.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(txtLogin);
		txtLogin.setColumns(10);
		txtLogin.setDocument(new Validador(15));

		JLabel lblNewLabel_1 = new JLabel("Senha:");
		lblNewLabel_1.setBounds(166, 270, 64, 49);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		contentPane.add(lblNewLabel_1);

		txtSenha = new JPasswordField();
		txtSenha.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		txtSenha.setBounds(240, 270, 378, 49);
		txtSenha.setBackground(new Color(255, 255, 0));
		txtSenha.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtSenha.setDocument(new Validador(250));
		contentPane.add(txtSenha);

		btnAcessar = new JButton("Acessar");
		btnAcessar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAcessar.setBounds(365, 448, 129, 49);
		btnAcessar.setBackground(new Color(255, 255, 0));
		btnAcessar.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnAcessar.setForeground(new Color(255, 0, 0));
		btnAcessar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logar();
			}
		});
		contentPane.add(btnAcessar);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBounds(0, 0, 844, 591);
		lblNewLabel_2.setBackground(new Color(149, 0, 0));
		lblNewLabel_2.setIcon(new ImageIcon(Login.class.getResource("/img/DrEggman.png")));
		contentPane.add(lblNewLabel_2);

		getRootPane().setDefaultButton(btnAcessar);

	}

	/**
	 * Método responsável por verificar o status de conexão com o banco
	 */
	private void status() {
		try {
			con = dao.conectar();
			if (con == null) {
				lblStatus.setIcon(new ImageIcon(Login.class.getResource("/img/dbloff.png")));
			} else {
				lblStatus.setIcon(new ImageIcon(Login.class.getResource("/img/dblon.png")));
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * Método para autenticar um usuário
	 */
	private void logar() {
		String capturaSenha = new String(txtSenha.getPassword());
		if (txtLogin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o login");
			txtLogin.requestFocus();
		} else if (capturaSenha.length() == 0) {
			JOptionPane.showMessageDialog(null, "Preencha a senha");
			txtSenha.requestFocus();
		} else {
			String read = "select * from usuarios where login=? and senha=md5(?)";
			try {
				con = dao.conectar();
				pst = con.prepareStatement(read);
				pst.setString(1, txtLogin.getText());
				pst.setString(2, capturaSenha);
				rs = pst.executeQuery();
				if (rs.next()) {
					String perfil = rs.getString(5);
					if (perfil.equals("admin")) {
						principal.setVisible(true);
						principal.lblUsuario.setText(rs.getString(2));
						principal.btnRelatorios.setEnabled(true);
						principal.btnUsuarios.setEnabled(true);
						this.dispose();
					} else {
						principal.setVisible(true);
						principal.panelRodape.setBackground(Color.DARK_GRAY);
						principal.lblUsuario.setText(rs.getString(2));
						this.dispose();
					}
				} else {
					JOptionPane.showMessageDialog(null, "usuário e/ou senha inválido(s)");
				}
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}
}