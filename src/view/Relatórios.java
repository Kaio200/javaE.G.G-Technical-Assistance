package view;

import java.awt.Desktop;
import java.awt.EventQueue;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.JDialog;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import model.DAO;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.Cursor;

@SuppressWarnings("unused")
public class Relatórios extends JDialog {

	DAO dao = new DAO();
	private Connection con;
	private PreparedStatement pst;
	private ResultSet rs;

	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Relatórios dialog = new Relatórios();
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
	public Relatórios() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Relatórios.class.getResource("/img/DR Eggman.png")));
		getContentPane().setBackground(new Color(0, 0, 0));
		setTitle("Reports");
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 520, 400);
		getContentPane().setLayout(null);
		
		JButton btnClientes = new JButton("");
		btnClientes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnClientes.setBackground(new Color(255, 255, 0));
		btnClientes.setIcon(new ImageIcon(Relatórios.class.getResource("/img/icons8-chamada-em-conferência-50.png")));
		btnClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				relatorioClientes();
			}
			
		});
		
		JButton btnValidade = new JButton("");
		btnValidade.setBackground(new Color(255, 255, 255));
		btnValidade.setForeground(new Color(255, 255, 255));
		btnValidade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				relatorioValidade();
			}
		});
		
		JButton btnPatrimonio = new JButton("");
		btnPatrimonio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					relatoriopatrimonio();
			}
		});
		btnPatrimonio.setBackground(new Color(255, 255, 0));
		btnPatrimonio.setIcon(new ImageIcon(Relatórios.class.getResource("/img/dinheiro.png")));
		btnPatrimonio.setBounds(206, 125, 89, 79);
		getContentPane().add(btnPatrimonio);
		btnValidade.setIcon(new ImageIcon(Relatórios.class.getResource("/img/expirado.png")));
		btnValidade.setBounds(290, 39, 89, 75);
		getContentPane().add(btnValidade);
		
		JButton btnReposicao = new JButton("");
		btnReposicao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				relatorioReposicao();
			}
		});
		btnReposicao.setIcon(new ImageIcon(Relatórios.class.getResource("/img/pecas-de-reposicao.png")));
		btnReposicao.setBackground(new Color(255, 255, 255));
		btnReposicao.setBounds(120, 215, 89, 75);
		getContentPane().add(btnReposicao);
		btnClientes.setBounds(120, 39, 89, 75);
		getContentPane().add(btnClientes);
		
		JButton btnServicos = new JButton("");
		btnServicos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnServicos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				relatorioServicos();
			}
		});
		btnServicos.setBackground(new Color(255, 255, 0));
		btnServicos.setIcon(new ImageIcon(Relatórios.class.getResource("/img/icons8-serviço-50.png")));
		btnServicos.setBounds(290, 215, 89, 75);
		getContentPane().add(btnServicos);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Relatórios.class.getResource("/img/DR Eggman.png")));
		lblNewLabel.setBounds(0, 0, 504, 361);
		getContentPane().add(lblNewLabel);

	}
	
	private void relatorioClientes() {
		Document document = new Document();
		try {
			PdfWriter.getInstance(document, new FileOutputStream("clientes.pdf"));
			document.open();
			Date dataRelatorio = new Date();
			DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);
			document.add(new Paragraph(formatador.format(dataRelatorio)));
			document.add(new Paragraph("Clientes:"));
			document.add(new Paragraph(" "));
			String readClientes = "select nome,fone from clientes order by nome";
			try {
				con = dao.conectar();
				pst = con.prepareStatement(readClientes);
				rs = pst.executeQuery();
				PdfPTable tabela = new PdfPTable(2);
				PdfPCell col1 = new PdfPCell(new Paragraph("Cliente"));
				PdfPCell col2 = new PdfPCell(new Paragraph("Fone"));
				tabela.addCell(col1);
				tabela.addCell(col2);
				while (rs.next()) {
					tabela.addCell(rs.getString(1));
					tabela.addCell(rs.getString(2));
				}
				document.add(tabela);
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		document.close();
		try {
			Desktop.getDesktop().open(new File("clientes.pdf"));
		} catch (Exception e) {
			System.out.println(e);
		  }
	}	
	private void relatorioServicos() {
		Document document = new Document();
		try {
			PdfWriter.getInstance(document, new FileOutputStream("servicos.pdf"));
			document.open();
			Date dataRelatorio = new Date();
			DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);
			document.add(new Paragraph(formatador.format(dataRelatorio)));
			document.add(new Paragraph("Servicos:"));
			document.add(new Paragraph(" "));
			String readServicos = "select os,equipamento,defeito,valor from servicos order by equipamento";
			try {

				con = dao.conectar();
				pst = con.prepareStatement(readServicos);
				rs = pst.executeQuery();

				PdfPTable tabela = new PdfPTable(4);
				PdfPCell col1 = new PdfPCell(new Paragraph("OS"));
				PdfPCell col2 = new PdfPCell(new Paragraph("Equipamento"));
				PdfPCell col3 = new PdfPCell(new Paragraph("Defeito"));
				PdfPCell col4 = new PdfPCell(new Paragraph("Valor"));
				tabela.addCell(col1);
				tabela.addCell(col2);
				tabela.addCell(col3);
				tabela.addCell(col4);
				while (rs.next()) {
					tabela.addCell(rs.getString(1));
					tabela.addCell(rs.getString(2));
					tabela.addCell(rs.getString(3));
					tabela.addCell(rs.getString(4));
				}				
				document.add(tabela);
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		
		document.close();
		try {
			Desktop.getDesktop().open(new File("servicos.pdf"));
		} catch (Exception e) {
			System.out.println(e);
		  }
			
		}
		
		private void relatorioValidade() {
			Document document = new Document();
			try {
				PdfWriter.getInstance(document, new FileOutputStream("Validade.pdf"));
				document.open();
				Date dataRelatorio = new Date();
				DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);
				document.add(new Paragraph(formatador.format(dataRelatorio)));
				document.add(new Paragraph("Validade:"));
				document.add(new Paragraph(" "));
				String readClientes = "select codigo,produto,date_format(dataval,'%d/%m/%y') as validade from produtos where dataval < dataent";
				try {
					con = dao.conectar();
					pst = con.prepareStatement(readClientes);
					rs = pst.executeQuery();
					PdfPTable tabela = new PdfPTable(2);
					PdfPCell col1 = new PdfPCell(new Paragraph("Codigo"));
					PdfPCell col2 = new PdfPCell(new Paragraph("Validade"));
					tabela.addCell(col1);
					tabela.addCell(col2);
					while (rs.next()) {
						tabela.addCell(rs.getString(1));
						tabela.addCell(rs.getString(2));
					}
					document.add(tabela);
					con.close();
				} catch (Exception e) {
					System.out.println(e);
				}
			} catch (Exception e) {
				System.out.println(e);
			}
			document.close();
			try {
				Desktop.getDesktop().open(new File("validade.pdf"));
			} catch (Exception e) {
				System.out.println(e);
		    }
			
		}
			
			private void relatorioReposicao() {
				Document document = new Document();
				try {
					PdfWriter.getInstance(document, new FileOutputStream("Reposicao.pdf"));
					document.open();
					Date dataRelatorio = new Date();
					DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);
					document.add(new Paragraph(formatador.format(dataRelatorio)));
					document.add(new Paragraph("Reposicao:"));
					document.add(new Paragraph(" "));
					String readClientes = "select codigo,produto,date_format(dataval, '%d/%m/%Y') as validade, estoque, estoquemin from produtos where estoque < estoquemin;";
					try {
						con = dao.conectar();
						pst = con.prepareStatement(readClientes);
						rs = pst.executeQuery();
						PdfPTable tabela = new PdfPTable(2);
						PdfPCell col1 = new PdfPCell(new Paragraph("Codigo"));
						PdfPCell col2 = new PdfPCell(new Paragraph("Produto"));
						tabela.addCell(col1);
						tabela.addCell(col2);
						while (rs.next()) {
							tabela.addCell(rs.getString(1));
							tabela.addCell(rs.getString(2));
						}
						document.add(tabela);
						con.close();
					} catch (Exception e) {
						System.out.println(e);
					}
				} catch (Exception e) {
					System.out.println(e);
				}
				document.close();
				try {
					Desktop.getDesktop().open(new File("reposicao.pdf"));
				} catch (Exception e) {
					System.out.println(e);
			 }
				
		}
			
				private void relatoriopatrimonio() {
					Document document = new Document();
					try {
						PdfWriter.getInstance(document, new FileOutputStream("Patrimonio.pdf"));
						document.open();
						Date dataRelatorio = new Date();
						DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);
						document.add(new Paragraph(formatador.format(dataRelatorio)));
						document.add(new Paragraph("Patrimonio:"));
						document.add(new Paragraph(" "));
						String readClientes = "select sum(custo * estoque) as total from produtos;";
							con = dao.conectar();
							pst = con.prepareStatement(readClientes);
							rs = pst.executeQuery();
							PdfPTable tabela = new PdfPTable(1);
							PdfPCell col1 = new PdfPCell(new Paragraph("Patrimonio"));
							tabela.addCell(col1);
							while (rs.next()) {
								tabela.addCell(rs.getString(1));
							}
							document.add(tabela);
							con.close();
						} catch (Exception e) {
							System.out.println(e);
					        }
					        document.close();
					    try {
						Desktop.getDesktop().open(new File("patrimonio.pdf"));
					    } catch (Exception e) {
						    System.out.println(e);
			 }
	}
		
}
