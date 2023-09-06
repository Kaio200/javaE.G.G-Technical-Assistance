package view;

import java.awt.Desktop;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
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
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Font;
import java.awt.Cursor;
import com.toedter.calendar.JDateChooser;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@SuppressWarnings("unused")
public class Servicos extends JDialog {
	DAO dao = new DAO();
	private Connection con;
	private PreparedStatement pst;
	private ResultSet rs;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtOS;
	private JTextField txtEquipamento;
	private JTextField txtDefeito;
	private JTextField txtValor;
	private JTextField txtIDcli;
	private JTextField txtCliente;
	public JDateChooser dataOS;

	public String[] textfield;

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
		getContentPane().setForeground(new Color(255, 255, 255));
		getContentPane().setBackground(new Color(149, 0, 0));
		setIconImage(Toolkit.getDefaultToolkit().getImage(Servicos.class.getResource("/img/DR Eggman.png")));
		setTitle("Serviços");
		setModal(true);
		setBounds(100, 100, 670, 409);
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("OS");
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 11));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(10, 28, 67, 14);
		getContentPane().add(lblNewLabel);

		txtOS = new JTextField();
		txtOS.setBackground(new Color(255, 255, 0));
		txtOS.setEditable(false);
		txtOS.setBounds(105, 26, 95, 20);
		getContentPane().add(txtOS);
		txtOS.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Data");
		lblNewLabel_1.setFont(new Font("Comic Sans MS", Font.BOLD, 11));
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(10, 86, 67, 14);
		getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Equipamento");
		lblNewLabel_2.setFont(new Font("Comic Sans MS", Font.BOLD, 11));
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setBounds(10, 138, 85, 14);
		getContentPane().add(lblNewLabel_2);

		txtEquipamento = new JTextField();
		txtEquipamento.setBackground(new Color(255, 255, 0));
		txtEquipamento.setBounds(105, 135, 493, 20);
		getContentPane().add(txtEquipamento);
		txtEquipamento.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Defeito");
		lblNewLabel_3.setFont(new Font("Comic Sans MS", Font.BOLD, 11));
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_3.setBounds(10, 186, 67, 14);
		getContentPane().add(lblNewLabel_3);

		txtDefeito = new JTextField();
		txtDefeito.setForeground(new Color(255, 255, 255));
		txtDefeito.setBackground(new Color(0, 0, 0));
		txtDefeito.setBounds(105, 180, 493, 20);
		getContentPane().add(txtDefeito);
		txtDefeito.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Valor");
		lblNewLabel_4.setFont(new Font("Comic Sans MS", Font.BOLD, 11));
		lblNewLabel_4.setForeground(new Color(255, 255, 255));
		lblNewLabel_4.setBounds(10, 228, 67, 14);
		getContentPane().add(lblNewLabel_4);

		txtValor = new JTextField();
		txtValor.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				onlyNum(e);
			}
		});
		txtValor.setForeground(new Color(0, 0, 0));
		txtValor.setBackground(new Color(255, 255, 0));
		txtValor.setBounds(105, 226, 204, 20);
		getContentPane().add(txtValor);
		txtValor.setColumns(10);

		JButton btnAdicionar = new JButton("");
		btnAdicionar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionarServicos();
			}
		});
		btnAdicionar.setBackground(new Color(255, 255, 0));
		btnAdicionar.setIcon(new ImageIcon(Servicos.class.getResource("/img/adicionar.png")));
		btnAdicionar.setBounds(41, 274, 89, 84);
		getContentPane().add(btnAdicionar);

		JButton btnEditar = new JButton("");
		btnEditar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update();
			}
		});
		btnEditar.setBackground(new Color(149, 0, 0));
		btnEditar.setIcon(new ImageIcon(Servicos.class.getResource("/img/editar.png")));
		btnEditar.setBounds(165, 274, 89, 84);
		getContentPane().add(btnEditar);

		JButton btnExcluir = new JButton("");
		btnExcluir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirproduto();
			}
		});
		btnExcluir.setBackground(new Color(255, 255, 0));
		btnExcluir.setIcon(new ImageIcon(Servicos.class.getResource("/img/excluir.png")));
		btnExcluir.setBounds(290, 274, 89, 84);
		getContentPane().add(btnExcluir);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnBuscar.setBackground(new Color(0, 0, 0));
		btnBuscar.setForeground(new Color(255, 255, 255));
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscar();
			}
		});
		btnBuscar.setBounds(220, 25, 89, 23);
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
		btnLimpar.setBounds(545, 275, 89, 84);
		getContentPane().add(btnLimpar);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(149, 0, 0));
		panel.setForeground(new Color(149, 0, 0));
		panel.setBorder(new TitledBorder(null, "Cliente", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(332, 11, 266, 113);
		getContentPane().add(panel);
		panel.setLayout(null);

		txtCliente = new JTextField();
		txtCliente.setBackground(new Color(255, 255, 0));
		txtCliente.setBounds(35, 28, 180, 20);
		panel.add(txtCliente);
		txtCliente.setColumns(10);

		txtIDcli = new JTextField();
		txtIDcli.setForeground(new Color(255, 255, 255));
		txtIDcli.setBackground(new Color(0, 0, 0));
		txtIDcli.setBounds(35, 69, 63, 20);
		panel.add(txtIDcli);
		txtIDcli.setColumns(10);

		JLabel ID = new JLabel("ID");
		ID.setFont(new Font("Comic Sans MS", Font.BOLD, 11));
		ID.setForeground(new Color(255, 255, 255));
		ID.setBounds(10, 72, 67, 14);
		panel.add(ID);

		JButton btnOS = new JButton("");
		btnOS.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnOS.setBackground(new Color(149, 0, 0));
		btnOS.setIcon(new ImageIcon(Servicos.class.getResource("/img/lista-de-controle.png")));
		btnOS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				imprimirOS();
			}
		});
		btnOS.setBounds(415, 275, 89, 84);
		getContentPane().add(btnOS);

		dataOS = new JDateChooser();
		dataOS.setBounds(105, 80, 204, 20);
		getContentPane().add(dataOS);
		Validador(textfield);
	}

	private void limpar() {
		txtOS.setText(null);
		dataOS.setDate(null);
		txtEquipamento.setText(null);
		txtDefeito.setText(null);
		txtIDcli.setText(null);
		txtValor.setText(null);
	}

	private void buscar() {
		String numOS = JOptionPane.showInputDialog("Número da OS ");

		String read = "select * from servicos where os = ?";
		try {
			con = dao.conectar();
			pst = con.prepareStatement(read);
			pst.setString(1, numOS);
			rs = pst.executeQuery();
			if (rs.next()) {
				txtOS.setText(rs.getString(1));
				dataOS.setDate(rs.getDate(2));
				txtEquipamento.setText(rs.getString(3));
				txtDefeito.setText(rs.getString(4));
				txtValor.setText(rs.getString(5));
				txtIDcli.setText(rs.getString(6));
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
		Document document = new Document();
		try {
			PdfWriter.getInstance(document, new FileOutputStream("os.pdf"));
			document.open();
			String readOS = "select * from servicos where os = ?";
			try {
				con = dao.conectar();
				pst = con.prepareStatement(readOS);
				pst.setString(1, txtOS.getText());
				rs = pst.executeQuery();
				if (rs.next()) {
					Paragraph os = new Paragraph("OS: " + rs.getString(1));
					os.setAlignment(Element.ALIGN_RIGHT);
					document.add(os);

					Paragraph equipamento = new Paragraph("Equipamento: " + rs.getString(3));
					equipamento.setAlignment(Element.ALIGN_LEFT);
					document.add(equipamento);

					Paragraph defeito = new Paragraph("Defeito: " + rs.getString(4));
					defeito.setAlignment(Element.ALIGN_LEFT);
					document.add(defeito);

					Image imagem = Image.getInstance(Servicos.class.getResource("/img/os.png"));
					imagem.scaleToFit(128, 128);
					imagem.setAbsolutePosition(20, 20);
					document.add(imagem);
				}
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		document.close();
		try {
			Desktop.getDesktop().open(new File("os.pdf"));
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * Método responsável por limpar os campos
	 */
	@SuppressWarnings("unused")
	private void limparCampos() {
		txtOS.setText(null);
		dataOS.setDate(null);
		txtEquipamento.setText(null);
		txtDefeito.setText(null);
		txtValor.setText(null);
		txtIDcli.setText(null);
	}

	/**
	 * Método para adicionar um Serviços
	 */
	private void adicionarServicos() {
		if (txtCliente.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "O campo do cliente não pode estar vazio");
		} else if (txtOS.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "O campo do OS não pode estar vazio");
		} else if (dataOS.getDate().equals(null)) {
			JOptionPane.showMessageDialog(null, "O campo do calendario não pode estar vazio");
		} else if (txtEquipamento.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "O campo do Equipamento não pode estar vazio");
		} else if (txtDefeito.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "O campo do cliente não pode estar vazio");
		} else if (txtValor.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "O campo do valor não pode estar vazio");
		} else {
			try {
				String insert = "insert into servicos (dataOS,equipamento,defeito,valor,idcli) values (?,?,?,?,?)";
				con = dao.conectar();
				pst = con.prepareStatement(insert);
				SimpleDateFormat formatador = new SimpleDateFormat("yyyyMMdd");
				String dataFormatada = formatador.format(dataOS.getDate());

				pst.setString(1, dataFormatada);
				pst.setString(2, txtEquipamento.getText());
				pst.setString(3, txtDefeito.getText());
				pst.setString(4, txtValor.getText());
				pst.setString(5, txtIDcli.getText());

				pst.executeUpdate();
				JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso");
				con.close();
			} catch (SQLException SQLe) {
				SQLe.printStackTrace();
			}

			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Método para excluir um produto
	 */
	private void excluirproduto() {
		int confirmaExcluir = JOptionPane.showConfirmDialog(null, "Confirma a exclusão deste servico ?", "Atenção!",
				JOptionPane.YES_NO_OPTION);
		if (confirmaExcluir == JOptionPane.YES_OPTION) {
			String delete = "delete from produtos where cliente=?";
			try {
				con = dao.conectar();
				pst = con.prepareStatement(delete);
				pst.setString(1, txtCliente.getText());
				pst.executeUpdate();
				JOptionPane.showMessageDialog(null, "servico excluído");
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	private void Validador(String[] jlist) {
		jlist = new String[5];
		int ui = jlist.length;
		System.out.println(ui);

		int O = 10;
		for (int i = 0; i < O; i++) {

		}
	}
	private void update() {
		if (txtCliente.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "O campo do cliente não pode estar vazio");
		} else if (txtOS.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "O campo do OS não pode estar vazio");
		} else if (dataOS.getDate().equals(null)) {
			JOptionPane.showMessageDialog(null, "O campo do calendario não pode estar vazio");
		} else if (txtEquipamento.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "O campo do Equipamento não pode estar vazio");
		} else if (txtDefeito.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "O campo do cliente não pode estar vazio");
		} else if (txtValor.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "O campo do valor não pode estar vazio");
		} else {
			try {
				
				String command = "update servicos set dataOS=?, equipamento=?, defeito=?, valor=?, idcli=?";
				con = dao.conectar();
				pst = con.prepareStatement(command);
				
				SimpleDateFormat formatador = new SimpleDateFormat("yyyyMMdd");
				String dataFormatada = formatador.format(dataOS.getDate());
				
				pst.setString(1, dataFormatada);
				pst.setString(2, txtEquipamento.getText());
				pst.setString(3, txtDefeito.getText());
				pst.setString(4, txtValor.getText());
				pst.setString(5, txtIDcli.getText());
	
				con.close();
			} catch (SQLException SQLe) {
				SQLe.printStackTrace();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	private void onlyNum(KeyEvent e) {
		if(!Character.isDigit(e.getKeyChar())) {
		e.consume();
		}
	}
}