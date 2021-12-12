package GUI;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileSystemView;

import com.jogamp.newt.event.KeyEvent;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;

import JOGL.models.CanvasFontJOGL;
import JOGL.models.FontClassJOGL;
import Models.CellsBox;
import Utility.FileUtility;
import java.awt.Font;

public class FontWindow {

	private JFrame frmFontGenerater;
	private JPanel JOGLInputPanel;
	private FontClassJOGL inputLisenner;
	private JTextField txtCharacter;
	private JPanel JOGLOutputPanel;
	private CanvasFontJOGL canvasFontJOGL;
	private JLabel errorLabel;
	private JList<String> list;
	private DefaultListModel<String> ListModel;
	private String currentChar = "";
	private String currenSelectedChar = "";
	private JLabel lineLabel;
	private int currentLinePos;

	public JFrame getFrame() {
		return frmFontGenerater;
	}

	public FontWindow() {
		initialize();
	}

	private void initialize() {
		frmFontGenerater = new JFrame();
		frmFontGenerater.setTitle("Font Generator");
		frmFontGenerater.setBounds(100, 100, 653, 597);
		frmFontGenerater.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmFontGenerater.getContentPane().setLayout(null);

		JOGLInputPanel = new JPanel();
		JOGLInputPanel.setBounds(10, 10, 400, 400);
		JOGLInputPanel.setBackground(Color.WHITE);
		frmFontGenerater.getContentPane().add(JOGLInputPanel);

		JButton drawButton = new JButton("Draw");
		drawButton.setBounds(10, 420, 173, 39);
		drawButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inputLisenner.setIsEraseFlase();
			}
		});
		frmFontGenerater.getContentPane().add(drawButton);

		JButton eraseButton = new JButton("Erase");
		eraseButton.setBounds(10, 469, 173, 39);
		eraseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inputLisenner.setIsEraseTrue();
			}
		});
		frmFontGenerater.getContentPane().add(eraseButton);

		JButton clearButton = new JButton("Clear");
		clearButton.setBounds(207, 420, 192, 39);
		clearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inputLisenner.clear();
			}
		});
		frmFontGenerater.getContentPane().add(clearButton);

		JButton addToListButton = new JButton("Add");
		addToListButton.setBounds(470, 439, 80, 30);
		addToListButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addAction();
			}
		});
		frmFontGenerater.getContentPane().add(addToListButton);

		JButton RemoveFromButton = new JButton("Remove");
		RemoveFromButton.setBounds(550, 439, 80, 30);
		RemoveFromButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeAction();
			}
		});
		frmFontGenerater.getContentPane().add(RemoveFromButton);

		txtCharacter = new JTextField();
		txtCharacter.setBounds(470, 474, 160, 29);
		txtCharacter.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyTyped(java.awt.event.KeyEvent evt) {
				if (txtCharacter.getText().length() >= 2
						&& !(evt.getKeyChar() == KeyEvent.VK_DELETE || evt.getKeyChar() == KeyEvent.VK_BACK_SPACE)) {
					Toolkit toolkit = Toolkit.getDefaultToolkit();
					toolkit.beep();
					evt.consume();
				}
			}
		});
		txtCharacter.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				textCharInputAction();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
			}
		});
		txtCharacter.setToolTipText("");
		frmFontGenerater.getContentPane().add(txtCharacter);
		txtCharacter.setColumns(10);

		JButton backButton = new JButton("back");
		backButton.setBounds(207, 469, 192, 39);
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inputLisenner.stepBack();
			}
		});
		frmFontGenerater.getContentPane().add(backButton);

		JOGLOutputPanel = new JPanel();
		JOGLOutputPanel.setBounds(420, 159, 40, 40);
		JOGLOutputPanel.setBackground(Color.WHITE);
		frmFontGenerater.getContentPane().add(JOGLOutputPanel);

		errorLabel = new JLabel("");
		errorLabel.setBounds(420, 367, 210, 30);
		errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
		errorLabel.setForeground(Color.RED);
		frmFontGenerater.getContentPane().add(errorLabel);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(470, 10, 159, 348);
		frmFontGenerater.getContentPane().add(scrollPane);

		ListModel = new DefaultListModel<String>();
		list = new JList<String>(ListModel);
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				selectElementAction();
			}
		});
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setVisibleRowCount(5);
		scrollPane.setViewportView(list);

		JButton NewButton = new JButton("New");
		NewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newButtonAction();
			}
		});
		NewButton.setBounds(470, 407, 160, 29);
		frmFontGenerater.getContentPane().add(NewButton);

		JFileChooser fileChooser = new JFileChooser(
				FileSystemView.getFileSystemView().getHomeDirectory().getAbsoluteFile());
		fileChooser.setDialogTitle("Export Font");
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

		JButton saveButton = new JButton("save");
		saveButton.setBounds(470, 506, 160, 44);
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveToFile(fileChooser, saveButton);
			}
		});
		frmFontGenerater.getContentPane().add(saveButton);


		JLabel lblNewLabel = new JLabel("Line on");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(45, 518, 67, 32);
		frmFontGenerater.getContentPane().add(lblNewLabel);
		
		lineLabel = new JLabel("0");
		lineLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lineLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lineLabel.setBounds(125, 518, 58, 32);
		frmFontGenerater.getContentPane().add(lineLabel);
		
		JButton upButton = new JButton("up");
		upButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(currentLinePos<canvasFontJOGL.getMaxLineCellNumber()) {
					currentLinePos++;
					updateLineNumber(currentLinePos);
				}
			}
		});
		upButton.setBounds(207, 518, 85, 32);
		frmFontGenerater.getContentPane().add(upButton);
		
		JButton downButton = new JButton("down");
		downButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(currentLinePos>0) {
					currentLinePos--;
					updateLineNumber(currentLinePos);
				}
			}
		});
		downButton.setBounds(314, 518, 85, 32);
		frmFontGenerater.getContentPane().add(downButton);

		try {
			JOGLSetup();
			custmeCurser();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	private void JOGLSetup() throws Exception {
		canvasFontJOGL = new CanvasFontJOGL(new GLCapabilities(GLProfile.get(GLProfile.GL2)),
				new CellsBox(16, 0, 0, JOGLInputPanel.getWidth(), JOGLInputPanel.getHeight()),
				JOGLOutputPanel.getWidth(), JOGLOutputPanel.getHeight(), JOGLInputPanel.getWidth(),
				JOGLInputPanel.getHeight());

		JOGLOutputPanel.add(canvasFontJOGL.getOutputCanvas());
		JOGLInputPanel.add(canvasFontJOGL.getInputCanvas());

		canvasFontJOGL.getInAnimator().start();
		canvasFontJOGL.getOutAnimator().start();

		inputLisenner = canvasFontJOGL.getInputLisenner();
	}

	private void saveToFile(JFileChooser fileChooser, JButton saveButton) {
		// reset error label
		resetErrorLabel();
		// check list size
		if (this.ListModel.getSize() == 0) {
			this.errorLabel.setText("List is Empty");
			return;
		}

		try {
			// get file path
			if (fileChooser.showOpenDialog(saveButton) == JFileChooser.APPROVE_OPTION) {
				String path = fileChooser.getSelectedFile().getAbsolutePath();
				// get file name
				path += "/Hussein";
				// get file format
				String extention = "font";
				// get values
				var values = this.canvasFontJOGL.toString();
				// save to file
				FileUtility.WriteToFile(values, path, extention);
				// reset Program
				ListModel.clear();
				canvasFontJOGL.resetAll();
			}
		} catch (IOException e) {
			e.getStackTrace();
			this.errorLabel.setText(e.getMessage());
		}

	}

	private void custmeCurser() {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image image = toolkit.getImage("resources/paint-brush-10855.png");
		Cursor c = toolkit.createCustomCursor(image, new Point(0, 25), "custom cursor");
		JOGLInputPanel.setCursor(c);
	}

	private void newButtonAction() {
		resetErrorLabel();
		canvasFontJOGL.newBox();
		canvasFontJOGL.setLineCellNumber(currentLinePos);
	}

	private void selectElementAction() {
		try {
			currenSelectedChar = ListModel.get(list.getSelectedIndex());
			canvasFontJOGL.setCurrent(currenSelectedChar);
		} catch (Exception e) {
		}

	}

	private void removeAction() {
		resetErrorLabel();
		String str = currenSelectedChar;
		if (canvasFontJOGL.isInList(str)) {
			ListModel.removeElement(str);
			canvasFontJOGL.removeFromList(str);
		}
	}

	private void addAction() {
		resetErrorLabel();
		String str = this.currentChar;
		if (isValedChar(str)) {
			ListModel.addElement(str);
			canvasFontJOGL.addToList(str,this.currentLinePos);
		}
	}

	private boolean isValedChar(String str) {
		if (str == "") {
			errorLabel.setText("Please Enter Char");
			return false;
		} else if (canvasFontJOGL.isInList(str)) {
			errorLabel.setText("This Character Aldready Exist");
			return false;
		}
		return true;
	}

	private void textCharInputAction() {
		var str = removeWhiteSpace(txtCharacter.getText());
		if (str.length() == 0) {
			clearTxtCharacter();
		} else if (str.charAt(0) == '^' && str.length() == 2) {// math char
			this.currentChar = str;
		} else if (str.charAt(0) != '^') {// alphabet and numbers
			this.currentChar = str;
		}
	}

	private void clearTxtCharacter() {
		Runnable doHighlight = new Runnable() {
			@Override
			public void run() {
				txtCharacter.setText("");
			}
		};
		SwingUtilities.invokeLater(doHighlight);
	}

	private String removeWhiteSpace(String str) {
		return str.replaceAll("\\s+", "");
	}

	private void resetErrorLabel() {
		this.errorLabel.setText("");
	}
	
	private void updateLineNumber(int i) {
		lineLabel.setText(i+"");
		canvasFontJOGL.setLineCellNumber(i);
	}
}
