
import ast.NodoPrograma;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;

import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;

import java.awt.Desktop;

public class IDECompilador extends JFrame {

	private JPanel contentPane;
	private JTextField txtNombre;
	private FileReader fr;
	private File archivo;
	private String path;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IDECompilador frame = new IDECompilador();
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
	public IDECompilador() {
		setResizable(false);
		setTitle("Compilador - Tercera Entrega");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 450);
		contentPane = new JPanel();
		contentPane.setBackground(Color.lightGray);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] {30, 245, 30, 113, 30, 0};
		gbl_contentPane.rowHeights = new int[]{40, 20, 23, 23, 16, 160, 23, 0, 16, 115, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblNombreDelArchivo = new JLabel("Nombre del archivo");
		GridBagConstraints gbc_lblNombreDelArchivo = new GridBagConstraints();
		gbc_lblNombreDelArchivo.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblNombreDelArchivo.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombreDelArchivo.gridx = 1;
		gbc_lblNombreDelArchivo.gridy = 2;
		lblNombreDelArchivo.setForeground(Color.BLACK);
		lblNombreDelArchivo.setFont(new Font("Courier", Font.PLAIN, 15));
		contentPane.add(lblNombreDelArchivo, gbc_lblNombreDelArchivo);
		
		final TextArea txaArchivo = new TextArea();
		GridBagConstraints gbc_txaArchivo = new GridBagConstraints();

		gbc_txaArchivo.fill = GridBagConstraints.HORIZONTAL;
		gbc_txaArchivo.insets = new Insets(0, 0, 5, 5);
		gbc_txaArchivo.gridwidth = 6;
		
		gbc_txaArchivo.gridx = 1;
		gbc_txaArchivo.gridy = 5;
		contentPane.add(txaArchivo, gbc_txaArchivo);
		txaArchivo.setBackground(Color.DARK_GRAY);
		txaArchivo.setForeground(Color.WHITE);
		txaArchivo.setFont(new Font("Courier", Font.PLAIN, 13));
		JButton btnGuardarArchivo = new JButton("Guardar");
		btnGuardarArchivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveFile(txaArchivo,true);
			}
		});
		JButton btnAbrirArchivo = new JButton("Abrir archivo");
		btnAbrirArchivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				fc.showOpenDialog(null);
				archivo = fc.getSelectedFile();
				path = archivo.getAbsolutePath();
				String nombre = archivo.getName();
				txtNombre.setText(nombre);
				
				try {
					fr = new FileReader(archivo);
					BufferedReader br = new BufferedReader(fr);
					String texto= "";
					String linea= "";
					while((linea=br.readLine()) != null) {
						texto += linea+"\n";					
					}
					txaArchivo.setText(texto);
					fr.reset();
				}catch(Exception ex) {
					
				}
			}
		});
		GridBagConstraints gbc_btnAbrirArchivo = new GridBagConstraints();
		gbc_btnAbrirArchivo.gridwidth = 2;
		gbc_btnAbrirArchivo.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAbrirArchivo.insets = new Insets(0, 0, 5, 5);
		gbc_btnAbrirArchivo.gridx = 4;
		gbc_btnAbrirArchivo.gridy = 2;
		contentPane.add(btnAbrirArchivo, gbc_btnAbrirArchivo);
		GridBagConstraints gbc_btnGuardarArchivo = new GridBagConstraints();
		gbc_btnGuardarArchivo.gridwidth = 2;
		gbc_btnGuardarArchivo.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnGuardarArchivo.insets = new Insets(0, 0, 5, 5);
		gbc_btnGuardarArchivo.gridx = 4;
		gbc_btnGuardarArchivo.gridy = 3;
		contentPane.add(btnGuardarArchivo, gbc_btnGuardarArchivo);
		
		txtNombre = new JTextField();
		GridBagConstraints gbc_txtNombre = new GridBagConstraints();
		gbc_txtNombre.gridwidth = 3;
		gbc_txtNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtNombre.insets = new Insets(0, 0, 5, 5);
		gbc_txtNombre.gridx = 1;
		gbc_txtNombre.gridy = 3;
		contentPane.add(txtNombre, gbc_txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblCodigoDePrueba = new JLabel("Codigo a analizar");
		lblCodigoDePrueba.setFont(new Font("Courier", Font.PLAIN, 15));
		lblCodigoDePrueba.setForeground(Color.BLACK);
		GridBagConstraints gbc_lblCodigoDePrueba = new GridBagConstraints();
		gbc_lblCodigoDePrueba.anchor = GridBagConstraints.WEST;
		gbc_lblCodigoDePrueba.insets = new Insets(0, 0, 5, 5);
		gbc_lblCodigoDePrueba.gridx = 1;
		gbc_lblCodigoDePrueba.gridy = 4;
		contentPane.add(lblCodigoDePrueba, gbc_lblCodigoDePrueba);

		JButton btnAnalisisSintactico = new JButton("Analisis Sint\u00E1ctico");
		btnAnalisisSintactico.setBackground(Color.WHITE);
		btnAnalisisSintactico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fr == null) {
					JOptionPane.showMessageDialog(null,"No hay archivo cargado");
				}else {
					String txt = txaArchivo.getText();
					Reader in = new StringReader(txt);
					saveFile(txaArchivo,false);
					Lexer lexer = new Lexer(in);
					parser sintactico = new parser(lexer);
					try {
						sintactico.parse();					
						JFrame  sintacticoFrame= new JFrame("Resultado de analisis sintáctico");  
						sintacticoFrame.setSize(500,700);  
						sintacticoFrame.setVisible(true);  
					    //crea area de texto
						JTextArea area=new JTextArea(sintactico.s,100,200);   
						area.setEditable(false);
						//agrega scrollbar
				        JScrollPane scroll = new JScrollPane(area);
				        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
				        //los agrega en la nueva ventana
				        sintacticoFrame.add(scroll); 
				        
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null,e1.getMessage());
					} catch (Error e1) {
						JOptionPane.showMessageDialog(null,e1.getMessage());
					}
					path = null;
				}	
			}
		});
		GridBagConstraints gbc_btnAnalisisSintactico = new GridBagConstraints();
		gbc_btnAnalisisSintactico.gridwidth = 5;
		gbc_btnAnalisisSintactico.insets = new Insets(0, 0, 5, 5);
		gbc_btnAnalisisSintactico.gridx = 2;
		gbc_btnAnalisisSintactico.gridy = 7;
		contentPane.add(btnAnalisisSintactico, gbc_btnAnalisisSintactico);


		JButton btnRealizarAnalisis = new JButton("Analisis Lexicogr\u00E1fico");
		btnRealizarAnalisis.setBackground(Color.WHITE);
		btnRealizarAnalisis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fr == null) {
					JOptionPane.showMessageDialog(null,"No hay archivo cargado");
				}else {
					String txt = txaArchivo.getText();
					Reader in = new StringReader(txt);
//					saveFile(txaArchivo,false);
					Lexer lexer = new Lexer(in);
					parser sintactico = new parser(lexer);
					try {
						sintactico.parse();
						fr = new FileReader(archivo);
						
						JFrame analisisFrame= new JFrame("Resultado del analisis lexicografico");  
						analisisFrame.setSize(500,700);  
						analisisFrame.setVisible(true);  
					    //crea area de texto
						JTextArea area=new JTextArea(lexer.s,100,200);   
						area.setEditable(false);
						//agrega scrollbar
				        JScrollPane scroll = new JScrollPane(area);
				        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
				        //los agrega en la nueva ventana
				        analisisFrame.add(scroll); 	
				        
					}  catch (Exception e1) {
						JOptionPane.showMessageDialog(null,e1.getMessage());
					} catch (Error e1) {
						JOptionPane.showMessageDialog(null,e1.getMessage());
					}
					path = null;
					
				}	
			}
		});
		GridBagConstraints gbc_btnRealizarAnalisis = new GridBagConstraints();
		gbc_btnRealizarAnalisis.gridwidth = 2;
		gbc_btnRealizarAnalisis.insets = new Insets(0, 2, 5, 5);
		gbc_btnRealizarAnalisis.gridx = 0;
		gbc_btnRealizarAnalisis.gridy = 7;
		contentPane.add(btnRealizarAnalisis, gbc_btnRealizarAnalisis);

		JButton btnTablaSimb = new JButton("Ver Tabla de Simbolos");
		btnTablaSimb.setBackground(Color.WHITE);
		btnTablaSimb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fr == null) {
					JOptionPane.showMessageDialog(null,"No hay archivo cargado");
				}else {
					String fileName= "ts.txt";
					File file= new File(fileName);

					// this gives you a 2-dimensional array of strings
					Object[][] lines = new Object[200][5];
					Object[] header = new Object[5];
					Scanner inputStream;

					try{
						inputStream = new Scanner(file);
						String line= inputStream.next();
						String[] values = line.split(",");
						header = values;
						int i = 0;
						while(inputStream.hasNext()){
							line= inputStream.next();
							values = line.split(",");
							// this adds the currently parsed line to the 2-dimensional string array
							lines[i++] = values;
						}
						inputStream.close();
					}catch (FileNotFoundException ex) {
						ex.printStackTrace();
					}
					JFrame tsFrame= new JFrame("Tabla de Simbolos");
					//crea area de texto
					JTable table = new JTable(lines, header);
					table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
					table.add(table.getTableHeader(), BorderLayout.PAGE_START);
//					table.setFillsViewportHeight(true);

					tsFrame.setLayout(new GridBagLayout());
					GridBagConstraints gbc = new GridBagConstraints();
					gbc.anchor = GridBagConstraints.NORTHWEST;
					gbc.fill = GridBagConstraints.BOTH;
					gbc.gridx = 0;
					gbc.gridy = 0;
					gbc.gridheight = 1;
					gbc.gridwidth = 3;
					gbc.insets = new Insets(5, 5, 5, 5);
					gbc.ipadx = 2;
					gbc.ipady = 2;
					gbc.weightx = 1;
					gbc.weighty = 1;
					tsFrame.add(new JScrollPane(table), gbc);
					tsFrame.setVisible(true);
					tsFrame.setSize(500,700);
				}
			}
		});
		GridBagConstraints gbc_btnTablaSimb = new GridBagConstraints();
		gbc_btnTablaSimb.gridwidth = 1;
		gbc_btnTablaSimb.insets = new Insets(0, 0, 5, 5);
		gbc_btnTablaSimb.gridx = 1;
		gbc_btnTablaSimb.gridy = 8;
		contentPane.add(btnTablaSimb, gbc_btnTablaSimb);


		JButton btnGenerarArbolAST = new JButton("Generar Arbol AST");
		btnGenerarArbolAST.setBackground(Color.WHITE);
		btnGenerarArbolAST.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fr == null) {
					JOptionPane.showMessageDialog(null,"No hay archivo cargado");
				}else {
					String txt = txaArchivo.getText();
					Reader in = new StringReader(txt);
					saveFile(txaArchivo,false);
					Lexer lexer = new Lexer(in);
					parser sintactico = new parser(lexer);
					try {
						NodoPrograma programa = (NodoPrograma) sintactico.parse().value;

						try {
							FileWriter archivo = new FileWriter("arbol.dot");
							PrintWriter pw = new PrintWriter(archivo);
							pw.println(programa.graficar());
							archivo.close();
						} catch (Exception ee) {
							ee.printStackTrace();
						}
						String cmd = "dot -Tpng arbol.dot -o arbol.png";
						Runtime.getRuntime().exec(cmd);
						JOptionPane.showMessageDialog(null,"Arbol AST Generado!");
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null,e1.getMessage());
					} catch (Error e1) {
						JOptionPane.showMessageDialog(null,e1.getMessage());
					}
					path = null;
				}
			}
		});
		GridBagConstraints gbc_btnGenerarArbolAST = new GridBagConstraints();
		gbc_btnGenerarArbolAST.gridwidth = 1;
		gbc_btnGenerarArbolAST.insets = new Insets(0, 0, 5, 5);
		gbc_btnGenerarArbolAST.gridx = 2;
		gbc_btnGenerarArbolAST.gridy = 7;
		contentPane.add(btnGenerarArbolAST, gbc_btnGenerarArbolAST);
		
		
		JButton btnGenerarAssembler = new JButton("Generar assembler");
		btnGenerarAssembler.setBackground(Color.WHITE);
		btnGenerarAssembler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fr == null) {
					JOptionPane.showMessageDialog(null,"No hay archivo cargado");
				}else {
					String txt = txaArchivo.getText();
					Reader in = new StringReader(txt);
					saveFile(txaArchivo,false);
					Lexer lexer = new Lexer(in);
					parser sintactico = new parser(lexer);
					String codigo = "";
					try {
						NodoPrograma programa = (NodoPrograma) sintactico.parse().value;
						try {
					      FileWriter archivo = new FileWriter("data.asm");
					      PrintWriter pw = new PrintWriter(archivo);
					      codigo = programa.generarAssembler();
					      pw.println(codigo);
					      archivo.close();
						} catch (Exception ee) {
							JOptionPane.showMessageDialog(null,ee.getMessage());
						}
						JFrame assemblerFrame= new JFrame("data.asm");  
						assemblerFrame.setSize(500,700);  
					    assemblerFrame.setVisible(true);  
					    //crea area de texto
						JTextArea area=new JTextArea(codigo,100,200);   
						area.setEditable(false);
						//agrega scrollbar
				        JScrollPane scroll = new JScrollPane(area);
				        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
				        //los agrega en la nueva ventana
				        assemblerFrame.add(scroll); 	
				        
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null,e1.getMessage());
					} catch (Error e1) {
						JOptionPane.showMessageDialog(null,e1.getMessage());
					}
					path = null;
				}
			}
		});
		GridBagConstraints gbc_btnGenerarAssembler = new GridBagConstraints();
		 gbc_btnGenerarAssembler.gridwidth = 2;
		 gbc_btnGenerarAssembler.insets = new Insets(0, 4, 5, 5);
		 gbc_btnGenerarAssembler.gridx = 5;
		 gbc_btnGenerarAssembler.gridy = 7;
		contentPane.add(btnGenerarAssembler,  gbc_btnGenerarAssembler);
	}
	
	public void saveFile(TextArea txaArchivo, Boolean jopt) {
		String nombreArchivo = txtNombre.getText();
		if (archivo == null) {
			String carpeta = System.getProperty("user.dir");
			path = carpeta + "/" + nombreArchivo;
		}else {
			if (!archivo.getName().equals(nombreArchivo)) {
				String carpeta = System.getProperty("user.dir");
				path = carpeta + "/" + nombreArchivo;
			}else {
				path = archivo.getAbsolutePath();
			}
		}
		
		FileWriter fw = null;
		try {
			fw = new FileWriter(path);
		}catch(IOException ex) {
			JOptionPane.showMessageDialog(null, "No hay ningun archivo");
		}
		try { 
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(txaArchivo.getText());
			bw.close();
			fr = new FileReader(path);
			if(jopt) {
				JOptionPane.showMessageDialog(null, "Archivo guardado correctamente");
			}
		}catch (Exception ex){
			
		}
		path = null;
	}

}
