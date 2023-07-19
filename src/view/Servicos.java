package view;

import java.awt.Desktop;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.DAO;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import java.awt.Cursor;

public class Servicos extends JDialog {

	// instanciar objetos JDBC
	DAO dao = new DAO();
	private Connection con;
	private PreparedStatement pst;
	private ResultSet rs;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtOS;
	private JTextField txtData;
	private JTextField txtEquipamento;
	private JTextField txtDefeito;
	private JTextField txtValor;
	private JTextField txtID;
	private JTextField txtCliente;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Servicos dialog = new Servicos();
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
	public Servicos() {
		getContentPane().setFont(new Font("Comic Sans MS", Font.BOLD, 11));
		getContentPane().setBackground(new Color(170, 0, 0));
		setIconImage(Toolkit.getDefaultToolkit().getImage(Servicos.class.getResource("/img/DR Eggman.png")));
		setTitle("E.G.G Services");
		setModal(true);
		setBounds(100, 100, 620, 440);
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("OS");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 11));
		lblNewLabel.setBounds(10, 37, 46, 14);
		getContentPane().add(lblNewLabel);

		txtOS = new JTextField();
		txtOS.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		txtOS.setForeground(new Color(255, 255, 255));
		txtOS.setBackground(new Color(0, 0, 0));
		txtOS.setEditable(false);
		txtOS.setBounds(125, 34, 98, 20);
		getContentPane().add(txtOS);
		txtOS.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Data");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Comic Sans MS", Font.BOLD, 11));
		lblNewLabel_1.setBounds(10, 87, 46, 14);
		getContentPane().add(lblNewLabel_1);

		txtData = new JTextField();
		txtData.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		txtData.setBackground(new Color(255, 255, 0));
		txtData.setEditable(false);
		txtData.setBounds(125, 84, 167, 20);
		getContentPane().add(txtData);
		txtData.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Equipamento");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setFont(new Font("Comic Sans MS", Font.BOLD, 11));
		lblNewLabel_2.setBounds(10, 138, 80, 14);
		getContentPane().add(lblNewLabel_2);

		txtEquipamento = new JTextField();
		txtEquipamento.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		txtEquipamento.setForeground(new Color(255, 255, 255));
		txtEquipamento.setBackground(new Color(0, 0, 0));
		txtEquipamento.setBounds(125, 135, 397, 20);
		getContentPane().add(txtEquipamento);
		txtEquipamento.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Defeito");
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_3.setFont(new Font("Comic Sans MS", Font.BOLD, 11));
		lblNewLabel_3.setBounds(10, 190, 80, 14);
		getContentPane().add(lblNewLabel_3);

		txtDefeito = new JTextField();
		txtDefeito.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		txtDefeito.setBackground(new Color(255, 255, 0));
		txtDefeito.setForeground(new Color(0, 0, 0));
		txtDefeito.setBounds(125, 187, 397, 20);
		getContentPane().add(txtDefeito);
		txtDefeito.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Valor");
		lblNewLabel_4.setForeground(new Color(255, 255, 255));
		lblNewLabel_4.setFont(new Font("Comic Sans MS", Font.BOLD, 11));
		lblNewLabel_4.setBounds(10, 246, 80, 14);
		getContentPane().add(lblNewLabel_4);

		txtValor = new JTextField();
		txtValor.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		txtValor.setText("0");
		txtValor.setForeground(new Color(255, 255, 255));
		txtValor.setBackground(new Color(0, 0, 0));
		txtValor.setBounds(125, 243, 397, 20);
		getContentPane().add(txtValor);
		txtValor.setColumns(10);

		JButton btnAdicionar = new JButton("");
		btnAdicionar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionarServicos();
			}
		});
		btnAdicionar.setBackground(new Color(0, 0, 0));
		btnAdicionar.setForeground(new Color(0, 0, 0));
		btnAdicionar.setIcon(new ImageIcon(Servicos.class.getResource("/img/adicionar.png")));
		btnAdicionar.setBounds(10, 305, 89, 85);
		getContentPane().add(btnAdicionar);

		JButton btnEditar = new JButton("");
		btnEditar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editarServicos();
			}
		});
		btnEditar.setBackground(new Color(255, 255, 0));
		btnEditar.setIcon(new ImageIcon(Servicos.class.getResource("/img/editar.png")));
		btnEditar.setBounds(109, 305, 89, 85);
		getContentPane().add(btnEditar);

		JButton btnExcluir = new JButton("");
		btnExcluir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirServicos();
			}
		});
		btnExcluir.setBackground(new Color(0, 0, 0));
		btnExcluir.setIcon(new ImageIcon(Servicos.class.getResource("/img/excluir.png")));
		btnExcluir.setBounds(208, 305, 89, 85);
		getContentPane().add(btnExcluir);

		JButton btnBuscar = new JButton("");
		btnBuscar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnBuscar.setBackground(new Color(0, 0, 0));
		btnBuscar.setIcon(new ImageIcon(Servicos.class.getResource("/img/pesquisar.png")));
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscar();
			}
		});
		btnBuscar.setBounds(406, 305, 89, 85);
		getContentPane().add(btnBuscar);

		JButton btnLimpar = new JButton("");
		btnLimpar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLimpar.setBackground(new Color(255, 255, 0));
		btnLimpar.setIcon(new ImageIcon(Servicos.class.getResource("/img/eraser.png")));
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpar();
			}
		});
		btnLimpar.setBounds(505, 305, 89, 85);
		getContentPane().add(btnLimpar);

		JPanel panel = new JPanel();
		panel.setFont(new Font("Comic Sans MS", Font.BOLD, 11));
		panel.setForeground(new Color(255, 255, 255));
		panel.setBackground(new Color(170, 0, 0));
		panel.setBorder(new TitledBorder(null, "Cliente", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(302, 11, 220, 113);
		getContentPane().add(panel);
		panel.setLayout(null);

		txtCliente = new JTextField();
		txtCliente.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		txtCliente.setBackground(new Color(255, 255, 0));
		txtCliente.setBounds(10, 28, 180, 20);
		panel.add(txtCliente);
		txtCliente.setColumns(10);

		txtID = new JTextField();
		txtID.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		txtID.setForeground(new Color(255, 255, 255));
		txtID.setBackground(new Color(0, 0, 0));
		txtID.setBounds(10, 82, 63, 20);
		panel.add(txtID);
		txtID.setColumns(10);

		JLabel ID = new JLabel("ID");
		ID.setFont(new Font("Comic Sans MS", Font.BOLD, 11));
		ID.setForeground(new Color(255, 255, 255));
		ID.setBounds(10, 59, 24, 14);
		panel.add(ID);

		JButton btnOS = new JButton("");
		btnOS.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnOS.setBackground(new Color(255, 255, 0));
		btnOS.setIcon(new ImageIcon(Servicos.class.getResource("/img/lista-de-controle.png")));
		btnOS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				imprimirOS();
			}
		});
		btnOS.setBounds(307, 305, 89, 85);
		getContentPane().add(btnOS);

	}// fim do construtor

	private void limpar() {
		txtOS.setText(null);
		txtData.setText(null);
		txtEquipamento.setText(null);
		txtDefeito.setText(null);
		txtID.setText(null);
		txtValor.setText("0");
	}

	private void buscar() {
		// captura do número da OS (sem usar a caixa de texto)
		String numOS = JOptionPane.showInputDialog("Número da OS ");

		String read = "select * from servicos where os = ?";
		try {
			// abrir a conexão
			con = dao.conectar();
			// preparar a query
			pst = con.prepareStatement(read);
			// substituir a ? (parâmetro) pelo número da OS
			pst.setString(1, numOS);
			rs = pst.executeQuery();
			if (rs.next()) {
				txtOS.setText(rs.getString(1));
				txtData.setText(rs.getString(2));
				txtEquipamento.setText(rs.getString(3));
				txtDefeito.setText(rs.getString(4));
				txtValor.setText(rs.getString(5));
				txtID.setText(rs.getString(6));
			} else {
				JOptionPane.showMessageDialog(null, "OS inexistente");
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * Impressão da OS
	 */
	private void imprimirOS() {
		// instanciar objeto para usar os métodos da biblioteca
		Document document = new Document();
		// documento pdf
		try {
			// criar um documento em branco (pdf) de nome clientes.pdf
			PdfWriter.getInstance(document, new FileOutputStream("os.pdf"));
			// abrir o documento (formatar e inserir o conteúdo)
			document.open();
			String readOS = "select * from servicos where os = ?";
			// conexão com o banco
			try {
				// abrir a conexão
				con = dao.conectar();
				// preparar a execução da query (instrução sql)
				pst = con.prepareStatement(readOS);
				pst.setString(1, txtOS.getText());
				// executar a query
				rs = pst.executeQuery();
				// se existir a OS
				if (rs.next()) {
					// document.add(new Paragraph("OS: " + rs.getString(1)));
					Paragraph os = new Paragraph("OS: " + rs.getString(1));
					os.setAlignment(Element.ALIGN_RIGHT);
					document.add(os);

					Paragraph equipamento = new Paragraph("Equipamento: " + rs.getString(3));
					equipamento.setAlignment(Element.ALIGN_LEFT);
					document.add(equipamento);

					Paragraph defeito = new Paragraph("Defeito: " + rs.getString(4));
					defeito.setAlignment(Element.ALIGN_LEFT);
					document.add(defeito);

					Paragraph valor = new Paragraph("Valor: " + rs.getString(5));
					valor.setAlignment(Element.ALIGN_LEFT);
					document.add(valor);

					// imprimir imagens
					Image imagem = Image.getInstance(Servicos.class.getResource("/img/DrEggman.png"));
					// resolução da imagem
					imagem.scaleToFit(128, 128);
					// (x,y) (referência: canto inferior esquerdo)
					imagem.setAbsolutePosition(20, 20);
					document.add(imagem);
				}
				// fechar a conexão com o banco
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		// fechar o documento (pronto para "impressão" (exibir o pdf))
		document.close();
		// Abrir o desktop do sistema operacional e usar o leitor padrão
		// de pdf para exibir o documento
		try {
			Desktop.getDesktop().open(new File("os.pdf"));
		} catch (Exception e) {
			System.out.println(e);

		}

	}

	/**
	 * Método usado para excluir um Serviço
	 */
	private void excluirServicos() {
		// System.out.println("teste do botão excluir");
		// validação de exclusão - a variável confirma captura a opção escolhida
		int confirma = JOptionPane.showConfirmDialog(null, "Confirma a exclusão deste serviço?", "Atenção!",
				JOptionPane.YES_NO_OPTION);
		if (confirma == JOptionPane.YES_OPTION) {
			// CRUD - Delete
			String delete = "delete from servicos where id=?";
			// tratamento de exceções
			try {
				// abrir a conexão
				con = dao.conectar();
				// preparar a query (instrução sql)
				pst = con.prepareStatement(delete);
				// substituir a ? pelo id do usuário
				pst.setString(1, txtID.getText());
				// executar a query
				pst.executeUpdate();
				// limpar campos
				limpar();
				// exibir uma mensagem ao usuário
				JOptionPane.showMessageDialog(null, "Serviço excluído");
				// fechar a conexão
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	/**
	 * Método para adicionar um novo Serviço
	 */
	private void adicionarServicos() {
		// String capturaSenha = new String(txtCliente.getName());

		if (txtCliente.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o Cliente do serviço");
			txtCliente.requestFocus();

		} else if (txtEquipamento.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o Equipamento do serviço");
			txtEquipamento.requestFocus();

		} else if (txtDefeito.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o Defeito do serviço");
			txtDefeito.requestFocus();

		} else if (txtValor.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o Valor do serviço");
			txtValor.requestFocus();
		} else {

			String create = "insert into servicos(equipamento, defeito, valor, id) values (?,?,?,?)";
			try {

				con = dao.conectar();

				pst = con.prepareStatement(create);
				pst.setString(1, txtEquipamento.getText());
				pst.setString(2, txtDefeito.getText());
				pst.setString(3, txtValor.getText());
				pst.setString(4, txtID.getText());

				pst.executeUpdate();
				// confirmar
				JOptionPane.showMessageDialog(null, "OS adicionado");
				// limpar os campos
				limpar();
				// fechar a conexão
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	private void editarServicos() {
		// System.out.println("Teste do botão editar");
		if (txtCliente.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o Cliente do usuario");
			txtCliente.requestFocus();

		} else if (txtEquipamento.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o Equipamento do usuario");
			txtEquipamento.requestFocus();

		} else if (txtDefeito.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o Defeito do usuario");
			txtDefeito.requestFocus();

		} else if (txtValor.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o Valor do usuario");
			txtValor.requestFocus();
		} else {

			String update = "update servicos set Equipamento=?, Defeito=?, Valor=? where OS=?";
			try {

				con = dao.conectar();
				pst = con.prepareStatement(update);
				pst.setString(1, txtEquipamento.getText());
				pst.setString(2, txtDefeito.getText());
				pst.setString(3, txtValor.getText());
				pst.setString(4, txtOS.getText());
				pst.executeUpdate();

				JOptionPane.showMessageDialog(null, "Dados do Serviço editados com sucesso.");

				limpar();
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

}// fim do código