package GUI;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JTextArea;

import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileSystemView;
import javax.swing.plaf.ColorUIResource;

import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLException;
import com.jogamp.opengl.GLProfile;

import Model.JOGL.CanvasEditorJOGL;
import Utility.FileUtility;

import javax.swing.event.ChangeEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;

public class EditorWindow {

	private JFrame frmEditor;
	private int R = 0;
	private int G = 0;
	private int B = 0;
	private JLabel rLabel;
	private JLabel gLabel;
	private JLabel bLabel;
	private JPanel colorPanel;
	private JPanel JoglPanel;
	private CanvasEditorJOGL editorJOGL;
	private int lineSpacing = 0;

	public EditorWindow() {
		initialize();
	}

	private void initialize() {
		frmEditor = new JFrame();
		frmEditor.setTitle("Editor");
		frmEditor.setBounds(100, 100, 833, 700);
		frmEditor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmEditor.getContentPane().setLayout(null);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(10, 10, 816, 22);
		frmEditor.getContentPane().add(menuBar);

		JMenu fileMenu = new JMenu("File");
		menuBar.add(fileMenu);

		JFileChooser fileChooser = new JFileChooser(
				FileSystemView.getFileSystemView().getHomeDirectory().getAbsoluteFile());
		fileChooser.setDialogTitle("import Font");
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		FileFilter fileFilter = new FileFilter() {
			public String getDescription() {
				return "font files";
			}

			@Override
			public boolean accept(File f) {
				if (f.isDirectory())
					return true;
				else if (f.getName().endsWith(".font"))
					return true;
				else
					return false;
			}
		};

		fileChooser.removeChoosableFileFilter(fileChooser.getAcceptAllFileFilter());
		fileChooser.setFileFilter(fileFilter);

		JButton uploadFontButton = new JButton("Font");
		uploadFontButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadFile(fileChooser, uploadFontButton);
			}
		});
		fileMenu.add(uploadFontButton);

		JMenu aboutMenu = new JMenu("About");
		menuBar.add(aboutMenu);

		JTextArea txtArea = new JTextArea();
		txtArea.setEditable(false);
		txtArea.setText(getAboutText());
		aboutMenu.add(txtArea);

		JoglPanel = new JPanel();
		JoglPanel.setBounds(20, 42, 697, 611);
		frmEditor.getContentPane().add(JoglPanel);

		JLabel lblNewLabel = new JLabel("R");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Snap ITC", Font.PLAIN, 20));
		lblNewLabel.setBounds(727, 42, 20, 47);
		frmEditor.getContentPane().add(lblNewLabel);

		JLabel lblG = new JLabel("G");
		lblG.setHorizontalAlignment(SwingConstants.CENTER);
		lblG.setFont(new Font("Snap ITC", Font.PLAIN, 20));
		lblG.setBounds(757, 42, 20, 47);
		frmEditor.getContentPane().add(lblG);

		JLabel lblB = new JLabel("B");
		lblB.setHorizontalAlignment(SwingConstants.CENTER);
		lblB.setFont(new Font("Snap ITC", Font.PLAIN, 20));
		lblB.setBounds(787, 42, 20, 47);
		frmEditor.getContentPane().add(lblB);

		rLabel = new JLabel("" + R);
		rLabel.setHorizontalAlignment(SwingConstants.CENTER);
		rLabel.setFont(new Font("Sylfaen", Font.PLAIN, 9));
		rLabel.setBounds(727, 263, 20, 29);
		frmEditor.getContentPane().add(rLabel);

		gLabel = new JLabel("" + R);
		gLabel.setHorizontalAlignment(SwingConstants.CENTER);
		gLabel.setFont(new Font("Sylfaen", Font.PLAIN, 9));
		gLabel.setBounds(757, 263, 20, 29);
		frmEditor.getContentPane().add(gLabel);

		bLabel = new JLabel("" + R);
		bLabel.setHorizontalAlignment(SwingConstants.CENTER);
		bLabel.setFont(new Font("Sylfaen", Font.PLAIN, 9));
		bLabel.setBounds(787, 263, 20, 29);
		frmEditor.getContentPane().add(bLabel);

		JSlider rSlider = new JSlider();
		rSlider.setValue(0);
		rSlider.setPaintTicks(true);
		rSlider.setMaximum(255);
		rSlider.setOrientation(SwingConstants.VERTICAL);
		rSlider.setBounds(727, 78, 20, 189);
		frmEditor.getContentPane().add(rSlider);

		JSlider gSlider = new JSlider();
		gSlider.setPaintTicks(true);
		gSlider.setValue(0);
		gSlider.setOrientation(SwingConstants.VERTICAL);
		gSlider.setMaximum(255);
		gSlider.setBounds(757, 78, 20, 189);
		frmEditor.getContentPane().add(gSlider);

		JSlider bSlider = new JSlider();
		bSlider.setPaintTicks(true);
		bSlider.setValue(0);
		bSlider.setOrientation(SwingConstants.VERTICAL);
		bSlider.setMaximum(255);
		bSlider.setBounds(787, 78, 20, 189);
		frmEditor.getContentPane().add(bSlider);

		colorPanel = new JPanel();
		colorPanel.setBounds(727, 302, 80, 100);
		frmEditor.getContentPane().add(colorPanel);

		JSlider lineSpacingSlider = new JSlider();
		lineSpacingSlider.setValue(0);
		lineSpacingSlider.setPaintTicks(true);
		lineSpacingSlider.setMaximum(30);
		lineSpacingSlider.setBounds(727, 441, 61, 22);
		frmEditor.getContentPane().add(lineSpacingSlider);

		JLabel lblNewLabel_1 = new JLabel("Line Spacing");
		lblNewLabel_1.setFont(new Font("Snap ITC", Font.PLAIN, 11));
		lblNewLabel_1.setBounds(727, 409, 82, 22);
		frmEditor.getContentPane().add(lblNewLabel_1);

		JLabel lineSpaceingLabel = new JLabel("0");
		lineSpaceingLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lineSpaceingLabel.setFont(new Font("Sylfaen", Font.PLAIN, 9));
		lineSpaceingLabel.setBounds(787, 431, 20, 29);
		frmEditor.getContentPane().add(lineSpaceingLabel);

		rSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				R = rSlider.getValue();
				rLabel.setText(R + "");
				colorSetup();
			}
		});
		gSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				G = gSlider.getValue();
				gLabel.setText(G + "");
				colorSetup();
			}
		});
		bSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				B = bSlider.getValue();
				bLabel.setText(B + "");
				colorSetup();
			}
		});

		lineSpacingSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				lineSpacing = lineSpacingSlider.getValue();
				lineSpaceingLabel.setText(lineSpacing + "");
				editorJOGL.setLineSpacing(lineSpacing);
			}
		});

		try {
			JOGLSetup();
			colorSetup();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void loadFile(JFileChooser fileChooser, JButton loadButton) {
		try {
			if (fileChooser.showOpenDialog(loadButton) == JFileChooser.APPROVE_OPTION) {
				String path = fileChooser.getSelectedFile().getAbsolutePath();
				System.out.println(path);
				editorJOGL.setPaperSheet(FileUtility.GetFontDtoBottomDown(path), lineSpacing, JoglPanel.getWidth(),
						JoglPanel.getHeight());
			}

		} catch (Exception e) {
			e.getStackTrace();
			System.out.println(e.getMessage());
		}
	}

	private void colorSetup() {
		colorPanel.setBackground(new ColorUIResource(R, G, B));
		setFontColor(R, G, B);
	}

	private void setFontColor(float r, float g, float b) {
		editorJOGL.setFontColor(r, g, b);
	}

	private void JOGLSetup() throws GLException, Exception {
		editorJOGL = new CanvasEditorJOGL(new GLCapabilities(GLProfile.get(GLProfile.GL2)),
				FileUtility.GetFontDtoBottomDown("resources/Hussein.font"), lineSpacing, JoglPanel.getWidth(),
				JoglPanel.getHeight());

		JoglPanel.add(editorJOGL.getSheetCanvas());
	}

	private String getAboutText() {
		return "  Editor Program Written by Hussein Shukri as Project for my college graphics course   \n "
			  +"  You can find The code source on my github account github.com/HusseinShukri  \n"
			  +"  Or you can follow the link below";
		
	}

	public JFrame getFrame() {
		return this.frmEditor;
	}
}
