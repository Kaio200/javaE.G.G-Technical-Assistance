package view;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.DAO;

import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.Cursor;
import javax.swing.JScrollPane;
import javax.swing.JList;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.border.BevelBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;

public class Usuarios extends JDialog {

	//Instanciar objetos JDBC
		DAO dao = new DAO();
		private Connection con;
		private PreparedStatement pst;
		private ResultSet rs;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtID;
	private JTextField txtNome;
	private JTextField txtLogin;
	private JPasswordField txtSenha;
	private JButton btnEditar;
	private JButton btnExcluir;
	private JButton btnAdicionar;
	private JList listUsuarios;
	private JScrollPane scrollPane;
	private JComboBox cboPerfil;
	private JCheckBox chckSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Usuarios dialog = new Usuarios();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public Usuarios() {
		getContentPane().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//clicar no painel do JDialog
				scrollPane.setVisible(false);
				txtNome.setText(null);
			}
		});
		setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		setIconImage(Toolkit.getDefaultToolkit().getImage(Usuarios.class.getResource("/img/DR Eggman.png")));
		getContentPane().setBackground(new Color(170, 0, 0));
		setTitle("E.G.G Users");
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 515, 339);
		getContentPane().setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setVisible(false);
		scrollPane.setBorder(null);
		scrollPane.setBounds(82, 96, 263, 93);
		getContentPane().add(scrollPane);
		
		listUsuarios = new JList();
		listUsuarios.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				buscarUsuarioLista();
			}
		});
		listUsuarios.setBackground(new Color(255, 255, 0));
		listUsuarios.setBorder(null);
		scrollPane.setViewportView(listUsuarios);
		
		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setForeground(SystemColor.text);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(26, 26, 46, 14);
		getContentPane().add(lblNewLabel);
		
		txtID = new JTextField();
		txtID.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		txtID.setForeground(new Color(255, 255, 255));
		txtID.setBackground(new Color(0, 0, 0));
		txtID.setBounds(82, 23, 263, 20);
		getContentPane().add(txtID);
		txtID.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Nome");
		lblNewLabel_1.setForeground(SystemColor.text);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(26, 67, 46, 14);
		getContentPane().add(lblNewLabel_1);
		
		txtNome = new JTextField();
		txtNome.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtNome.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				listarUsuarios();
			}
		});
		txtNome.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		txtNome.setForeground(new Color(0, 0, 0));
		txtNome.setBackground(new Color(255, 255, 0));
		txtNome.setBounds(82, 67, 263, 20);
		getContentPane().add(txtNome);
		txtNome.setColumns(10);
		
		JButton btnBuscar = new JButton("");
		btnBuscar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnBuscar.setForeground(new Color(0, 0, 0));
		btnBuscar.setBackground(new Color(255, 255, 0));
		btnBuscar.setIcon(new ImageIcon(Usuarios.class.getResource("/img/pesquisar.png")));
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscarUsuario();
			}
		});
		btnBuscar.setBounds(26, 229, 71, 60);
		getContentPane().add(btnBuscar);
		
		JLabel lblNewLabel_2 = new JLabel("Login");
		lblNewLabel_2.setForeground(SystemColor.text);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2.setBounds(26, 113, 46, 14);
		getContentPane().add(lblNewLabel_2);
		
		txtLogin = new JTextField();
		txtLogin.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		txtLogin.setForeground(new Color(255, 255, 255));
		txtLogin.setBackground(new Color(0, 0, 0));
		txtLogin.setBounds(82, 110, 263, 20);
		getContentPane().add(txtLogin);
		txtLogin.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Senha");
		lblNewLabel_3.setForeground(SystemColor.text);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_3.setBounds(26, 162, 46, 14);
		getContentPane().add(lblNewLabel_3);
		
		JButton btnLimpar = new JButton("");
		btnLimpar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLimpar.setBackground(new Color(0, 0, 0));
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparCampos();
				
			}
		});
		btnLimpar.setIcon(new ImageIcon(Usuarios.class.getResource("/img/eraser.png")));
		btnLimpar.setBounds(120, 229, 73, 60);
		getContentPane().add(btnLimpar);
		
		txtSenha = new JPasswordField();
		txtSenha.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		txtSenha.setForeground(new Color(0, 0, 0));
		txtSenha.setBackground(new Color(255, 255, 0));
		txtSenha.setBounds(82, 159, 263, 20);
		getContentPane().add(txtSenha);
		
		btnAdicionar = new JButton("");
		btnAdicionar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAdicionar.setEnabled(false);
		btnAdicionar.setBackground(new Color(255, 255, 0));
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionar();
			}
		});
		btnAdicionar.setIcon(new ImageIcon(Usuarios.class.getResource("/img/adicionar.png")));
		btnAdicionar.setBounds(215, 229, 71, 60);
		getContentPane().add(btnAdicionar);
		
		btnEditar = new JButton("");
		btnEditar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEditar.setEnabled(false);
		btnEditar.setBackground(new Color(0, 0, 0));
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//evento que vai verificar se o checkbox foi selecionado
				if (chckSenha.isSelected()) {
					editarUsuario();
				} else {
					editarUsuarioExcetoSenha();
				}
			}
		});
		btnEditar.setIcon(new ImageIcon(Usuarios.class.getResource("/img/editar.png")));
		btnEditar.setBounds(310, 229, 73, 60);
		getContentPane().add(btnEditar);
		
		btnExcluir = new JButton("");
		btnExcluir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnExcluir.setEnabled(false);
		btnExcluir.setBackground(new Color(255, 255, 0));
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirUsuario();
			}
		});
		btnExcluir.setIcon(new ImageIcon(Usuarios.class.getResource("/img/excluir.png")));
		btnExcluir.setBounds(405, 229, 73, 60);
		getContentPane().add(btnExcluir);
		
		JLabel lblNewLabel_4 = new JLabel("Perfil");
		lblNewLabel_4.setForeground(new Color(255, 255, 255));
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_4.setBounds(355, 159, 46, 30);
		getContentPane().add(lblNewLabel_4);
		
		cboPerfil = new JComboBox();
		cboPerfil.setForeground(new Color(255, 255, 255));
		cboPerfil.setBackground(new Color(0, 0, 0));
		cboPerfil.setModel(new DefaultComboBoxModel(new String[] {"", "admin", "user"}));
		cboPerfil.setBounds(405, 158, 80, 31);
		getContentPane().add(cboPerfil);
		
		chckSenha = new JCheckBox("Alterar Senha");
		chckSenha.setBackground(new Color(170, 0, 0));
		chckSenha.setForeground(new Color(255, 255, 255));
		chckSenha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckSenha.isSelected()) {
					txtSenha.setText(null);
					txtSenha.requestFocus();
					txtSenha.setBackground(Color.YELLOW);
				} else {
					txtSenha.setBackground(Color.WHITE);
				}				
				
			}
		});
		chckSenha.setBounds(26, 196, 125, 23);
		getContentPane().add(chckSenha);

	}// FIM DO CONSTRUTOR
	
	/**
	 * Método usado para listar o nome dos usuários na lista
	 */
	private void listarUsuarios() {
		//System.out.println("teste");
		//a linha abaixo cria um objeto usando como referência um vetor dinâmico, este objeto irá temporariamente armazenar os nomes
		DefaultListModel<String> modelo = new DefaultListModel<>();
		//setar o modelo (vetor na lista)
		listUsuarios.setModel(modelo);
		//Query (instrução sql)
		String readLista = "select * from usuarios where nome like '" + txtNome.getText() + "%'" + "order by nome";
		try {
			//abrir a conexão
			con = dao.conectar();
			//preparar a query (instrução sql
			pst = con.prepareStatement(readLista);
			//executar a query e trazer o resultado para lista
			rs = pst.executeQuery();
			//uso do while para trazer os usuários enquanto existir
			while(rs.next()) {
				//mostrar a barra de rolagem (scrollpane)
				scrollPane.setVisible(true);
				//adicionar os usuarios no vetor -> lista
				modelo.addElement(rs.getString(2));
				//esconder o scrollpane se nenhuma letra for digitada
				if(txtNome.getText().isEmpty()) {
					scrollPane.setVisible(false);
				}
				
			}
			//fechar a conexão
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * Método para Adicionar um Usuário pelo nome
	 */
	private void adicionar() {
		//System.out.println("teste");
		//validação de campos obrigatórios
		if (txtNome.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "prencha o Nome do Usuário");
			txtNome.requestFocus();
		}else
			if (txtLogin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "prencha o Login do Usuário");
			txtLogin.requestFocus();
		} else {

			
			//lógica principal
			String create = "insert into usuarios(Nome,Login,Senha) values (?,?,md5(?))";
			//tratamento de exceções
			try {
				//abrir a conexão
				con = dao.conectar();
				//preparar a execução da query(instrução sql - CRUD Create)
				pst = con.prepareStatement(create);
				pst.setString(1, txtID.getText());
				pst.setString(2, txtNome.getText());
				pst.setString(3, txtLogin.getText());
				pst.setString(4, txtSenha.getText());
				cboPerfil.setSelectedItem(rs.getString(5));
				//validação (liberação dos botões alterar e excluir)
				//executa a query(instrução sql (CRUD - Create))
				pst.executeUpdate();
				//confirmar
				JOptionPane.showMessageDialog(null, "Usuário adicionado");
				//limpar os campos
				limparCampos();
				//fechar a conexão
				con.close();
			}catch (Exception e) {
					System.out.println(e);
		}
	
		}
	}
	/**
	 *  Método responsável por limpar os campos
	 */
	private void limparCampos() {
		txtID.setText(null);
		txtNome.setText(null);
		txtLogin.setText(null);
		txtSenha.setText(null);
	}//fim do método limparCampos()
	
	/**
	 * Método usado para buscar um Usuário no banco
	 */
	private void Usuario() {
		System.out.println("teste do botão buscar");
		String read = "select * from usuarios where nome = ?";
		try {
			con = dao.conectar();
			pst = con.prepareStatement(read);
			pst.setString(1, txtNome.getText());
			rs = pst.executeQuery();
			if (rs.next()) {
				txtID.setText(rs.getString(1));
				txtNome.setText(rs.getString(2));
				txtLogin.setText(rs.getString(3));
				txtSenha.setText(rs.getString(4));
				btnEditar.setEnabled(true);
				btnExcluir.setEnabled(true);
			} else {
				//se não existir um contato no banco
				JOptionPane.showMessageDialog(null, "Usuário inexistente");
				//validação (liberação do Botão adicionar)
				btnAdicionar.setEnabled(true);
				System.out.println("Usuário inexistente");
				
				JOptionPane.showMessageDialog(null, "Usuário inexistente");
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		}//fim do método buscar
		
		private void editarUsuario() {
			//System.out.println("Teste do botão editar");
			if(txtNome.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Digite o nome do usuario");
				txtNome.requestFocus();
			} else if (txtLogin.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Digite o login do usuario");
				txtLogin.requestFocus();
			} else {
				//lógica principal
				//CRUD - update
				String update = "update usuarios set nome=?,login=?,senha=? where id=?";
				//tratamento de exeções
				try {
					//abrir a conexão
					con = dao.conectar();
					//preparar a query (instrução sql)
					pst = con.prepareStatement(update);
					pst.setString(1, txtNome.getText());
					pst.setString(2, txtLogin.getText());
					pst.setString(3, txtSenha.getText());
					pst.setString(4, txtID.getText());
					//executar a query
					pst.executeUpdate();
					//confirmar para o usuario
					JOptionPane.showMessageDialog(null, "Dados do usúario editados com sucesso.");
					//limpar os campos
					 limparCampos();
					 con.close();				 
				}catch (Exception e) {
					System.out.println(e);
				}
			}
			
		}// fim do método editar
		
		private void editarUsuarioExcetoSenha() {
			//System.out.println("Teste do botão editar");
			if(txtNome.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Digite o nome do usuario");
				txtNome.requestFocus();
			} else if (txtLogin.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Digite o login do usuario");
				txtLogin.requestFocus();
			} else {
				//lógica principal
				//CRUD - update
				String update = "update usuarios set nome=?,login=? where id=?";
				//tratamento de exeções
				try {
					//abrir a conexão
					con = dao.conectar();
					//preparar a query (instrução sql)
					pst = con.prepareStatement(update);
					pst.setString(1, txtNome.getText());
					pst.setString(2, txtLogin.getText());
					pst.setString(3, txtID.getText());
					//executar a query
					pst.executeUpdate();
					//confirmar para o usuario
					JOptionPane.showMessageDialog(null, "Dados do usúario editados com sucesso.");
					//limpar os campos
					 limparCampos();
					 con.close();				 
				}catch (Exception e) {
					System.out.println(e);
				}
			}
			
		}// fim do método editar
		
		/**
		 * Método usado para excluir um Usuário
		 */
		private void excluirUsuario() {
			//System.out.println("teste do botão excluir");
			//validação de exclusão - a variável confirma captura a opção escolhida
			int confirma = JOptionPane.showConfirmDialog(null, "Confirma a exclusão deste contato?","Atenção!",JOptionPane.YES_NO_OPTION);
			if (confirma == JOptionPane.YES_OPTION) {
				//CRUD - Delete
				String delete = "delete from usuarios where id=?";
				//tratamento de exceções
				try {
					//abrir a conexão
					con = dao.conectar();
					//preparar a query (instrução sql)
					pst = con.prepareStatement(delete);
					//substituir a ? pelo id do usuário
					pst.setString(1, txtID.getText());
					//executar a query
					pst.executeUpdate();
					//limpar campos
					limparCampos();
					//exibir uma mensagem ao usuário
					JOptionPane.showMessageDialog(null, "Usuário excluído");
					//fechar a conexão
					con.close();
				} catch (Exception e) {
					System.out.println(e);
				}
			}		
		} //fim do método excluirUsuarios
		
		/**
		 * Método que busca o usuário selecionado na lista
		 */
		private void buscarUsuarioLista() {
		 System.out.println("teste");
			// variável que captura o índice da linha da lista
			int linha = listUsuarios.getSelectedIndex();
			if (linha >= 0) {
				// Query (instrução sql)
				// limit (0,1) -> seleciona o índice 0 e 1 usuário da lista
				String readListaUsuario = "select * from usuarios where nome like '" + txtNome.getText() + "%'"	+ "order by nome limit " + (linha) + " , 1";
				try {
					//abrir a conexão
					con = dao.conectar();
					//preparar a query para execução
					pst = con.prepareStatement(readListaUsuario);
					//executar e obter o resultado
					rs = pst.executeQuery();
					if (rs.next()) {
						//esconder a lista
						scrollPane.setVisible(false);
						//setar os campos
						txtID.setText(rs.getString(1));
						txtNome.setText(rs.getString(2));
						txtLogin.setText(rs.getString(3));
						txtSenha.setText(rs.getString(4));
						cboPerfil.setSelectedItem(rs.getString(5));
					} else {
						JOptionPane.showMessageDialog(null, "Usuário inexistente");
					}
					//fechar a conexão
					con.close();
				} catch (Exception e) {
					System.out.println(e);
				}
			} else {
				// se não existir no banco um usuário da lista
				scrollPane.setVisible(false);
			}
		}
		/**
		 * Método usado para buscar um usuário no banco pelo login
		 */
		private void buscarUsuario() {
			// System.out.println("teste do botão buscar");
			String read = "select * from usuarios where login = ?";
			try {
				con = dao.conectar();
				pst = con.prepareStatement(read);
				pst.setString(1, txtLogin.getText());
				rs = pst.executeQuery();
				if (rs.next()) {
					txtID.setText(rs.getString(1));
					txtNome.setText(rs.getString(2));
					txtLogin.setText(rs.getString(3));
					txtSenha.setText(rs.getString(4));
					cboPerfil.setSelectedItem(rs.getString(5));
					btnEditar.setEnabled(true);
				} else {
					JOptionPane.showMessageDialog(null, "Usuário inexistente");
				}
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}

		/**
		 * Método para adicionar um novo usuário
		 */
		private void adicionarUsuario() {
			// Criar uma variável/objeto para capturar a senha
			String capturaSenha = new String(txtSenha.getPassword());
			// System.out.println("teste");
			// validação de campos obrigatórios
			if (txtNome.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Preencha o nome do usuário");
				txtNome.requestFocus();
			} else if (txtLogin.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Preencha o login do usuário");
				txtLogin.requestFocus();
			} else if (capturaSenha.length() == 0) {
				JOptionPane.showMessageDialog(null, "Preencha a senha do usuário");
				txtLogin.requestFocus();
			} else {
				// lógica principal
				// CRUD Create
				String create = "insert into usuarios(nome,login,senha) values (?,?,md5(?))";
				// tratamento de exceções
				try {
					// abrir a conexão
					con = dao.conectar();
					// preparar a execução da query(instrução sql - CRUD Create)
					pst = con.prepareStatement(create);
					pst.setString(1, txtNome.getText());
					pst.setString(2, txtLogin.getText());
					pst.setString(3, txtSenha.getText());
					pst.setString(4, cboPerfil.getSelectedItem().toString());
					pst.setString(5, txtID.getText());
					// executa a query(instrução sql (CRUD - Create))
					pst.executeUpdate();
					// confirmar
					JOptionPane.showMessageDialog(null, "Usuário adicionado");
					// limpar os campos
					limparCampos();
					// fechar a conexão
					con.close();
				} catch (Exception e) {
					System.out.println(e);
				}
			}
		}// fim do método adicionar

		/**
		 * Limpar campos
		 */
		private void limpar() {
			txtID.setText(null);
			txtNome.setText(null);
			txtLogin.setText(null);
			txtSenha.setText(null);
			scrollPane.setVisible(false);
			cboPerfil.setSelectedItem("");
		}
}//fim do código
