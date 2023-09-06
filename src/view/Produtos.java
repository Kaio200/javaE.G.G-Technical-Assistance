package view;

import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.toedter.calendar.JDateChooser;

import model.DAO;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.Color;

@SuppressWarnings({ "serial", "unused" })
public class Produtos extends JDialog {

	DAO dao = new DAO();
	private Connection con;
	private PreparedStatement pst;
	private ResultSet rs;
	JFileChooser jfc = new JFileChooser();
	private FileInputStream fis;
	private int tamanho;

	private JTextField txtBarcode;
	private JTextField txtCodigo;
	private JTextField txtFornecedor;
	private JTextField txtId;
	private JTextField txtProduto;
	private JTextField txtCusto;
	private JTextField txtLucro;
	private JTextField txtFabricante;
	private JTextField txtEstoque;
	private JTextField txtEstoquemin;
	private JTextField txtLocal;
	private JTextArea txtaDescricao;
	@SuppressWarnings("rawtypes")
	private JComboBox cboUnidade;
	private JTextField txtLote;
	private JDateChooser dateEntrada;
	private JDateChooser dateValidade;
	private JLabel lblFoto;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Produtos dialog = new Produtos();
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
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Produtos() {
		getContentPane().setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		getContentPane().setBackground(new Color(149, 0, 0));
		getContentPane().setForeground(new Color(149, 0, 0));
		setIconImage(Toolkit.getDefaultToolkit().getImage(Produtos.class.getResource("/img/DR Eggman.png")));
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				txtBarcode.requestFocus();
			}

		});
		setTitle("Produtos");
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 782, 577);
		getContentPane().setLayout(null);
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Produtos.class.getResource("/img/produto-de-codigo-de-barras (3).png")));
		lblNewLabel.setBounds(22, 25, 64, 51);
		getContentPane().add(lblNewLabel);

		txtBarcode = new JTextField();
		txtBarcode.setBackground(new Color(255, 255, 0));
		txtBarcode.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					pesquisarProdutoBarcode();
				}
			}
		});
		txtBarcode.setBounds(96, 43, 240, 20);
		getContentPane().add(txtBarcode);
		txtBarcode.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Código:");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBackground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		lblNewLabel_1.setBounds(22, 90, 64, 14);
		getContentPane().add(lblNewLabel_1);

		txtCodigo = new JTextField();
		txtCodigo.setForeground(new Color(255, 255, 255));
		txtCodigo.setBackground(new Color(0, 0, 0));
		txtCodigo.setBounds(90, 88, 103, 20);
		getContentPane().add(txtCodigo);
		txtCodigo.setColumns(10);

		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnPesquisar.setBackground(new Color(255, 255, 0));
		btnPesquisar.setFont(new Font("Comic Sans MS", Font.BOLD, 11));
		btnPesquisar.setForeground(new Color(149, 0, 0));
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pesquisarProduto();
			}
		});
		btnPesquisar.setBounds(203, 87, 95, 23);
		getContentPane().add(btnPesquisar);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(149, 0, 0));
		panel.setBorder(new TitledBorder(null, "Fornecedor", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(370, 25, 385, 67);
		getContentPane().add(panel);
		panel.setLayout(null);

		txtFornecedor = new JTextField();
		txtFornecedor.setBackground(new Color(255, 255, 0));
		txtFornecedor.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
			}
		});
		txtFornecedor.setBounds(10, 26, 196, 20);
		panel.add(txtFornecedor);
		txtFornecedor.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Produtos.class.getResource("/img/search.png")));
		lblNewLabel_2.setBounds(216, 22, 24, 24);
		panel.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("ID");
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_3.setBackground(new Color(255, 255, 255));
		lblNewLabel_3.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		lblNewLabel_3.setBounds(263, 29, 24, 14);
		panel.add(lblNewLabel_3);

		txtId = new JTextField();
		txtId.setForeground(new Color(255, 255, 255));
		txtId.setBackground(new Color(0, 0, 0));
		txtId.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				
			}
			@Override
			public void focusLost(FocusEvent e) {
			}
		});
		txtId.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
			}
			@Override
			public void keyTyped(KeyEvent e) {
				
			}
		});
		txtId.setBounds(297, 26, 57, 20);
		panel.add(txtId);
		txtId.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Produto:");
		lblNewLabel_4.setForeground(new Color(255, 255, 255));
		lblNewLabel_4.setBackground(new Color(255, 255, 255));
		lblNewLabel_4.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		lblNewLabel_4.setBounds(22, 139, 64, 14);
		getContentPane().add(lblNewLabel_4);

		txtProduto = new JTextField();
		txtProduto.setBackground(new Color(255, 255, 0));
		txtProduto.setBounds(90, 137, 360, 20);
		getContentPane().add(txtProduto);
		txtProduto.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("Descrição:");
		lblNewLabel_5.setForeground(new Color(255, 255, 255));
		lblNewLabel_5.setBackground(new Color(255, 255, 255));
		lblNewLabel_5.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		lblNewLabel_5.setBounds(22, 189, 64, 14);
		getContentPane().add(lblNewLabel_5);

		txtaDescricao = new JTextArea();
		txtaDescricao.setForeground(new Color(255, 255, 255));
		txtaDescricao.setBackground(new Color(0, 0, 0));
		txtaDescricao.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtaDescricao.setBounds(90, 185, 360, 74);
		getContentPane().add(txtaDescricao);

		JLabel lblNewLabel_6 = new JLabel("Entrada:");
		lblNewLabel_6.setForeground(new Color(255, 255, 255));
		lblNewLabel_6.setBackground(new Color(255, 255, 255));
		lblNewLabel_6.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		lblNewLabel_6.setBounds(336, 359, 58, 14);
		getContentPane().add(lblNewLabel_6);

		JLabel lblNewLabel_7 = new JLabel("Validade:");
		lblNewLabel_7.setForeground(new Color(255, 255, 255));
		lblNewLabel_7.setBackground(new Color(255, 255, 255));
		lblNewLabel_7.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		lblNewLabel_7.setBounds(336, 410, 64, 14);
		getContentPane().add(lblNewLabel_7);

		JLabel lblNewLabel_8 = new JLabel("Custo:");
		lblNewLabel_8.setForeground(new Color(255, 255, 255));
		lblNewLabel_8.setBackground(new Color(255, 255, 255));
		lblNewLabel_8.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		lblNewLabel_8.setBounds(22, 381, 46, 14);
		getContentPane().add(lblNewLabel_8);

		txtCusto = new JTextField();
		txtCusto.setBackground(new Color(255, 255, 0));
		txtCusto.setBounds(89, 379, 103, 20);
		getContentPane().add(txtCusto);
		txtCusto.setColumns(10);

		JLabel lblNewLabel_9 = new JLabel("Lucro:");
		lblNewLabel_9.setForeground(new Color(255, 255, 255));
		lblNewLabel_9.setBackground(new Color(255, 255, 255));
		lblNewLabel_9.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		lblNewLabel_9.setBounds(203, 381, 46, 14);
		getContentPane().add(lblNewLabel_9);

		txtLucro = new JTextField();
		txtLucro.setForeground(new Color(255, 255, 255));
		txtLucro.setBackground(new Color(0, 0, 0));
		txtLucro.setText("0");
		txtLucro.setBounds(260, 379, 38, 20);
		getContentPane().add(txtLucro);
		txtLucro.setColumns(10);

		JLabel lblNewLabel_10 = new JLabel("%");
		lblNewLabel_10.setForeground(new Color(255, 255, 255));
		lblNewLabel_10.setBackground(new Color(255, 255, 255));
		lblNewLabel_10.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		lblNewLabel_10.setBounds(303, 381, 28, 14);
		getContentPane().add(lblNewLabel_10);

		JLabel lblNewLabel_11 = new JLabel("Fabricante:");
		lblNewLabel_11.setForeground(new Color(255, 255, 255));
		lblNewLabel_11.setBackground(new Color(255, 255, 255));
		lblNewLabel_11.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		lblNewLabel_11.setBounds(221, 285, 77, 14);
		getContentPane().add(lblNewLabel_11);

		txtFabricante = new JTextField();
		txtFabricante.setForeground(new Color(255, 255, 255));
		txtFabricante.setBackground(new Color(0, 0, 0));
		txtFabricante.setBounds(303, 283, 147, 20);
		getContentPane().add(txtFabricante);
		txtFabricante.setColumns(10);

		JLabel lblNewLabel_12 = new JLabel("Estoque:");
		lblNewLabel_12.setForeground(new Color(255, 255, 255));
		lblNewLabel_12.setBackground(new Color(255, 255, 255));
		lblNewLabel_12.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		lblNewLabel_12.setBounds(22, 331, 58, 14);
		getContentPane().add(lblNewLabel_12);

		txtEstoque = new JTextField();
		txtEstoque.setForeground(new Color(255, 255, 255));
		txtEstoque.setBackground(new Color(0, 0, 0));
		txtEstoque.setBounds(89, 329, 51, 20);
		getContentPane().add(txtEstoque);
		txtEstoque.setColumns(10);

		JLabel lblNewLabel_13 = new JLabel("Estoque mínimo:");
		lblNewLabel_13.setForeground(new Color(255, 255, 255));
		lblNewLabel_13.setBackground(new Color(255, 255, 255));
		lblNewLabel_13.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		lblNewLabel_13.setBounds(146, 331, 103, 14);
		getContentPane().add(lblNewLabel_13);

		txtEstoquemin = new JTextField();
		txtEstoquemin.setBackground(new Color(255, 255, 0));
		txtEstoquemin.setColumns(10);
		txtEstoquemin.setBounds(247, 329, 51, 20);
		getContentPane().add(txtEstoquemin);

		JLabel lblNewLabel_14 = new JLabel("Unidade:");
		lblNewLabel_14.setForeground(new Color(255, 255, 255));
		lblNewLabel_14.setBackground(new Color(255, 255, 255));
		lblNewLabel_14.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		lblNewLabel_14.setBounds(22, 429, 64, 14);
		getContentPane().add(lblNewLabel_14);

		cboUnidade = new JComboBox();
		cboUnidade.setForeground(new Color(255, 255, 255));
		cboUnidade.setBackground(new Color(0, 0, 0));
		cboUnidade.setModel(new DefaultComboBoxModel(new String[] { "", "UN", "PC", "CX", "g", "M", "CM" }));
		cboUnidade.setBounds(89, 426, 51, 22);
		getContentPane().add(cboUnidade);

		JLabel lblNewLabel_15 = new JLabel("Local:");
		lblNewLabel_15.setForeground(new Color(255, 255, 255));
		lblNewLabel_15.setBackground(new Color(255, 255, 255));
		lblNewLabel_15.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		lblNewLabel_15.setBounds(151, 429, 46, 14);
		getContentPane().add(lblNewLabel_15);

		txtLocal = new JTextField();
		txtLocal.setBackground(new Color(255, 255, 0));
		txtLocal.setBounds(203, 427, 116, 20);
		getContentPane().add(txtLocal);
		txtLocal.setColumns(10);

		JButton btnAdicionar = new JButton("");
		btnAdicionar.setContentAreaFilled(false);
		btnAdicionar.setToolTipText("Adicionar");
		btnAdicionar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAdicionar.setIcon(new ImageIcon(Produtos.class.getResource("/img/adicionar.png")));
		btnAdicionar.setBorder(null);
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionarProduto();
			}
		});
		btnAdicionar.setBounds(456, 470, 64, 64);
		getContentPane().add(btnAdicionar);

		JButton btnEditar = new JButton("");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editarProduto();
			}
		});
		btnEditar.setToolTipText("Editar");
		btnEditar.setIcon(new ImageIcon(Produtos.class.getResource("/img/editar.png")));
		btnEditar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEditar.setContentAreaFilled(false);
		btnEditar.setBorder(null);
		btnEditar.setBounds(534, 470, 64, 64);
		getContentPane().add(btnEditar);

		JButton btnExcluir = new JButton("");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirproduto();
			}
		});
		btnExcluir.setToolTipText("Excluir");
		btnExcluir.setIcon(new ImageIcon(Produtos.class.getResource("/img/excluir.png")));
		btnExcluir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnExcluir.setBorder(null);
		btnExcluir.setContentAreaFilled(false);
		btnExcluir.setBounds(611, 470, 64, 64);
		getContentPane().add(btnExcluir);

		JLabel lblNewLabel_4_1 = new JLabel("Lote:");
		lblNewLabel_4_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_4_1.setBackground(new Color(255, 255, 255));
		lblNewLabel_4_1.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		lblNewLabel_4_1.setBounds(22, 286, 46, 14);
		getContentPane().add(lblNewLabel_4_1);

		txtLote = new JTextField();
		txtLote.setBackground(new Color(255, 255, 0));
		txtLote.setColumns(10);
		txtLote.setBounds(90, 283, 121, 20);
		getContentPane().add(txtLote);

		lblFoto = new JLabel("");
		lblFoto.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		lblFoto.setIcon(new ImageIcon(Produtos.class.getResource("/img/camera.png")));
		lblFoto.setBounds(496, 129, 256, 256);
		getContentPane().add(lblFoto);

		JButton btnCarregar = new JButton("Carregar imagem");
		btnCarregar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCarregar.setBackground(new Color(255, 255, 0));
		btnCarregar.setFont(new Font("Comic Sans MS", Font.BOLD, 11));
		btnCarregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				carregarImagem();
			}
		});
		btnCarregar.setForeground(new Color(149, 0, 0));
		btnCarregar.setBounds(496, 389, 256, 23);
		getContentPane().add(btnCarregar);

		JButton btnLimpar = new JButton("");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparCampos();
			}
		});
		btnLimpar.setIcon(new ImageIcon(Produtos.class.getResource("/img/eraser.png")));
		btnLimpar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLimpar.setToolTipText("Limpar");
		btnLimpar.setContentAreaFilled(false);
		btnLimpar.setBorder(null);
		btnLimpar.setBounds(691, 470, 64, 64);
		getContentPane().add(btnLimpar);

		dateEntrada = new JDateChooser();
		dateEntrada.setBackground(new Color(255, 255, 0));
		dateEntrada.setBounds(336, 379, 141, 20);
		getContentPane().add(dateEntrada);

		dateValidade = new JDateChooser();
		dateValidade.setForeground(new Color(255, 255, 255));
		dateValidade.setBackground(new Color(0, 0, 0));
		dateValidade.setBounds(336, 428, 141, 20);
		getContentPane().add(dateValidade);

	}

	/**
	 * Método que cria um explorador próprio de arquivos
	 */
	private void carregarImagem() {
		jfc.setDialogTitle("Selecionar arquivo de imagem");
		jfc.setFileFilter(new FileNameExtensionFilter("Arquivo de imagens (*.PNG,*.JPG,*.JPEG)", "png", "jpg", "jpeg"));
		int arquivoSelecionado = jfc.showOpenDialog(this);
		if (arquivoSelecionado == JFileChooser.APPROVE_OPTION) {
			try {
				fis = new FileInputStream(jfc.getSelectedFile());
				tamanho = (int) jfc.getSelectedFile().length();
				Image foto = ImageIO.read(jfc.getSelectedFile()).getScaledInstance(lblFoto.getWidth(),
						lblFoto.getHeight(), Image.SCALE_SMOOTH);
				lblFoto.setIcon(new ImageIcon(foto));
				lblFoto.updateUI();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	/**
	 * Método pesquisar pelo código do produto
	 */
	private void pesquisarProduto() {
		String readCodigo = "select * from produtos inner join fornecedores on produtos.id = fornecedores.id where codigo=?";  
		
		try {
			con = dao.conectar();
			pst = con.prepareStatement(readCodigo);
			pst.setString(1, txtCodigo.getText());
			rs = pst.executeQuery();
			if (rs.next()) {
				txtBarcode.setText(rs.getString(2));
				txtProduto.setText(rs.getString(3));
				txtLote.setText(rs.getString(4));
				txtaDescricao.setText(rs.getNString(5));
				

				Blob blob = (Blob) rs.getBlob(6);
				
				byte[] img = blob.getBytes(1, (int) blob.length());
				BufferedImage imagem = null;
				try {
					imagem = ImageIO.read(new ByteArrayInputStream(img));
				} catch (Exception e) {
					System.out.println(e);
				}
				ImageIcon icone = new ImageIcon(imagem);
				Icon foto = new ImageIcon(icone.getImage().getScaledInstance(lblFoto.getWidth(), lblFoto.getHeight(),
						Image.SCALE_SMOOTH));
				lblFoto.setIcon(foto);	
				dateEntrada.setDate(rs.getDate(8));
				dateValidade.setDate(rs.getDate(9));
				txtEstoque.setText(rs.getString(10));
				txtEstoquemin.setText(rs.getString(11));
				cboUnidade.setSelectedItem(rs.getString(12));
				txtFabricante.setText(rs.getString(13));
				txtCusto.setText(rs.getString(14));
				txtLucro.setText(rs.getString(15));
				txtLocal.setText(rs.getString(16));
				txtId.setText(rs.getString(17));
				txtFornecedor.setText(rs.getString(18));
			} else {
				JOptionPane.showMessageDialog(null, "Produto não encontrado");
			}
			con.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Método pesquisar pelo código de barras do produto
	 */
	private void pesquisarProdutoBarcode() {
		String readBarcode = "select * from produtos inner join fornecedores on produtos.id = fornecedores.id where barcode=?";		
		try {
			con = dao.conectar();
			pst = con.prepareStatement(readBarcode);
			pst.setString(1, txtBarcode.getText());
			rs = pst.executeQuery();
			if (rs.next()) {
				txtCodigo.setText(rs.getString(1));
				txtProduto.setText(rs.getString(3));
				txtLote.setText(rs.getString(4));
				txtaDescricao.setText(rs.getNString(5));
				Blob blob = (Blob) rs.getBlob(6);
				byte[] img = blob.getBytes(1, (int) blob.length());
				BufferedImage imagem = null;
				try {
					imagem = ImageIO.read(new ByteArrayInputStream(img));
				} catch (Exception e) {
					System.out.println(e);
				}
				ImageIcon icone = new ImageIcon(imagem);
				Icon foto = new ImageIcon(icone.getImage().getScaledInstance(lblFoto.getWidth(), lblFoto.getHeight(),
						Image.SCALE_SMOOTH));
				lblFoto.setIcon(foto);
				String setarDataEnt = rs.getString(8);
				Date dataEntrada = new SimpleDateFormat("yyyy-MM-dd").parse(setarDataEnt);
				dateEntrada.setDate(dataEntrada);

				String setarDataVal = rs.getString(9);
				Date dataValidade = new SimpleDateFormat("yyyy-MM-dd").parse(setarDataVal);
				dateValidade.setDate(dataValidade);
				txtEstoque.setText(rs.getString(10));
				txtEstoquemin.setText(rs.getString(11));
				cboUnidade.setSelectedItem(rs.getString(12));
				txtFabricante.setText(rs.getString(7));
				txtCusto.setText(rs.getString(14));
				txtLucro.setText(rs.getString(15));
				txtLocal.setText(rs.getString(13));
				txtId.setText(rs.getString(16));
				txtFornecedor.setText(rs.getString(19));
			} else {
				JOptionPane.showMessageDialog(null, "Produto não encontrado");
			}
			con.close();
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * Método para adicionar um produto
	 */
	private void adicionarProduto() {
		String insert = "insert into produtos (barcode, produto, lote, descricao, foto, dataval, estoque, estoquemin, unidade, custo, fabricante, lucro, localarm, id) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			con = dao.conectar();
			pst = con.prepareStatement(insert);
			pst.setString(1, txtBarcode.getText());
			pst.setString(2, txtProduto.getText());
			pst.setString(3, txtLote.getText());
			pst.setString(4, txtaDescricao.getText());
			pst.setBlob(5, fis, tamanho);
			SimpleDateFormat formatador = new SimpleDateFormat("yyyyMMdd");
			String dataFormatada = formatador.format(dateValidade.getDate());
			pst.setString(6, dataFormatada);		
			pst.setString(7, txtEstoque.getText());
			pst.setString(8, txtEstoquemin.getText());
			pst.setString(9, cboUnidade.getSelectedItem().toString());
			pst.setString(10, txtCusto.getText());
			pst.setString(11, txtFabricante.getText());
			pst.setString(12, txtLucro.getText());
			pst.setString(13, txtLocal.getText());
			pst.setString(14, txtId.getText());
			pst.executeUpdate();
			JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso");
			con.close();
			limparCampos();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * Método para editar um produto
	 */
	private void editarProduto() {
		String update = "update produtos set produto=?, lote=?, descricao=?, foto=?, estoque=?, estoquemin=?, unidade=?, custo=? where codigo=?, fabricante=?, Lucro=?, Local=? ";
		try {
			con = dao.conectar();
			pst = con.prepareStatement(update);
			pst.setString(1, txtProduto.getText());
			pst.setString(2, txtBarcode.getText());
			pst.setString(3, txtLote.getText());
			pst.setString(4, txtaDescricao.getText());
			pst.setBlob(5, fis, tamanho);
			pst.setString(6, txtEstoque.getText());
			pst.setString(7, txtEstoquemin.getText());
			pst.setString(8, cboUnidade.getSelectedItem().toString());
			pst.setString(9, txtCusto.getText());
			pst.setString(10, txtCodigo.getText());
			pst.setString(11, txtFabricante.getText());
			pst.setString(12, txtLucro.getText());
			pst.setString(13, txtLocal.getText());
			pst.setString(14, txtId.getText());
			pst.executeUpdate();
			JOptionPane.showMessageDialog(null, "dados do produto alterados");
			con.close();
			limparCampos();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * Método para excluir um produto
	 */
	private void excluirproduto() {
		int confirmaExcluir = JOptionPane.showConfirmDialog(null, "Confirma a exclusão deste produto ?", "Atenção!",
				JOptionPane.YES_NO_OPTION);
		if (confirmaExcluir == JOptionPane.YES_OPTION) {
			String delete = "delete from produtos where codigo=?";
			try {
				con = dao.conectar();
				pst = con.prepareStatement(delete);
				pst.setString(1, txtCodigo.getText());
				pst.executeUpdate();
				JOptionPane.showMessageDialog(null, "Produto excluído");
				con.close();
				limparCampos();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}
	
	/**
	 *  Método responsável por limpar os campos
	 */

	private void limparCampos() {
txtId.setText(null);
txtFornecedor.setText(null);
lblFoto.setIcon(new ImageIcon(Principal.class.getResource("/img/camera.png")));
txtBarcode.setText(null);
txtCodigo.setText(null);
txtProduto.setText(null);
txtaDescricao.setText(null);
txtLote.setText(null);
txtFabricante.setText(null);
txtEstoque.setText(null);
txtEstoquemin.setText(null);
txtCusto.setText(null);
txtLucro.setText(null);
cboUnidade.setSelectedItem(null);
txtLocal.setText(null);
dateEntrada.setDate(null);
dateValidade.setDate(null);		
	}
}