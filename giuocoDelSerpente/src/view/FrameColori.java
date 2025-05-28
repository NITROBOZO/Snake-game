package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;

public class FrameColori extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JButton cancelButton;
	private JButton okButton;
	private Color[] colors;

	public Color[] getColors() {
		return colors;
	}

	public JButton getCancelButton() {
		return cancelButton;
	}

	public JButton getOkButton() {
		return okButton;
	}

	public FrameColori() {
		setResizable(false);
		colors = new Color[4];
		colors[0] = Color.red;
		colors[1] = Color.blue;
		colors[2] = Color.green;
		colors[3] = Color.cyan;
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 37, 72, 0, 0, 37, 62, 0, 53, 52, 0, 111, 0 };
		gbl_contentPanel.rowHeights = new int[] { 23, 180, 0 };
		gbl_contentPanel.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		contentPanel.setLayout(gbl_contentPanel);

		JCheckBox testaP1 = new JCheckBox("Testa P1");
		testaP1.setForeground(new Color(255, 255, 255));
		testaP1.setFocusPainted(false);
		GridBagConstraints gbc_testaP1 = new GridBagConstraints();
		gbc_testaP1.anchor = GridBagConstraints.NORTHWEST;
		gbc_testaP1.insets = new Insets(0, 0, 5, 5);
		gbc_testaP1.gridx = 4;
		gbc_testaP1.gridy = 0;
		contentPanel.add(testaP1, gbc_testaP1);

		JCheckBox testaP2 = new JCheckBox("Testa P2");
		testaP2.setForeground(new Color(255, 255, 255));
		testaP2.setFocusPainted(false);
		GridBagConstraints gbc_testaP2 = new GridBagConstraints();
		gbc_testaP2.anchor = GridBagConstraints.NORTHWEST;
		gbc_testaP2.insets = new Insets(0, 0, 5, 5);
		gbc_testaP2.gridx = 5;
		gbc_testaP2.gridy = 0;
		contentPanel.add(testaP2, gbc_testaP2);

		JCheckBox corpoP1 = new JCheckBox("Corpo P1");
		corpoP1.setForeground(new Color(255, 255, 255));
		corpoP1.setFocusPainted(false);
		GridBagConstraints gbc_corpoP1 = new GridBagConstraints();
		gbc_corpoP1.anchor = GridBagConstraints.NORTHWEST;
		gbc_corpoP1.insets = new Insets(0, 0, 5, 5);
		gbc_corpoP1.gridx = 7;
		gbc_corpoP1.gridy = 0;
		contentPanel.add(corpoP1, gbc_corpoP1);

		JCheckBox corpoP2 = new JCheckBox("Corpo P2");
		corpoP2.setForeground(new Color(255, 255, 255));
		corpoP2.setFocusPainted(false);
		GridBagConstraints gbc_corpoP2 = new GridBagConstraints();
		gbc_corpoP2.anchor = GridBagConstraints.NORTHWEST;
		gbc_corpoP2.insets = new Insets(0, 0, 5, 5);
		gbc_corpoP2.gridx = 8;
		gbc_corpoP2.gridy = 0;
		contentPanel.add(corpoP2, gbc_corpoP2);
		testaP1.setBackground(colors[0]);
		testaP2.setBackground(colors[1]);
		corpoP1.setBackground(colors[2]);
		corpoP2.setBackground(colors[3]);

		JButton btnScegli = new JButton("SCEGLI");
		btnScegli.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// color chooser integrato in swing
				Color color = JColorChooser.showDialog(null, "scegli il colore", Color.red);
				if (testaP1.isSelected()) {
					colors[0] = color;
					testaP1.setBackground(colors[0]);
				}
				if (testaP2.isSelected()) {
					colors[1] = color;
					testaP2.setBackground(colors[1]);
				}
				if (corpoP1.isSelected()) {
					colors[2] = color;
					corpoP1.setBackground(colors[2]);
				}
				if (corpoP2.isSelected()) {
					colors[3] = color;
					corpoP2.setBackground(colors[3]);
				}
			}
		});
		btnScegli.setFont(new Font("Tahoma", Font.PLAIN, 64));
		GridBagConstraints gbc_btnScegli = new GridBagConstraints();
		gbc_btnScegli.insets = new Insets(0, 0, 0, 5);
		gbc_btnScegli.fill = GridBagConstraints.BOTH;
		gbc_btnScegli.gridwidth = 5;
		gbc_btnScegli.gridx = 3;
		gbc_btnScegli.gridy = 1;
		contentPanel.add(btnScegli, gbc_btnScegli);

		JButton btnReset = new JButton("default");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				colors[0] = Color.red;
				colors[1] = Color.blue;
				colors[2] = Color.green;
				colors[3] = Color.cyan;
				testaP1.setBackground(colors[0]);
				testaP2.setBackground(colors[1]);
				corpoP1.setBackground(colors[2]);
				corpoP2.setBackground(colors[3]);

			}
		});
		GridBagConstraints gbc_btnReset = new GridBagConstraints();
		gbc_btnReset.insets = new Insets(0, 0, 0, 5);
		gbc_btnReset.gridx = 8;
		gbc_btnReset.gridy = 1;
		contentPanel.add(btnReset, gbc_btnReset);
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
