package com.company;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import lib.ArrayUtils;
import lib.JTableUtils;


public class Window extends JFrame {

    private JPanel panelMain;
    private JTable tableOutput;
    private JButton buttonRun;
    private JButton buttonLoadInputFromFile;
    private JButton buttonSaveOutputIntoFile;
    private JTable tableInput;
    private JScrollPane scrollPaneTableInput;
    private JScrollPane scrollPaneTableOutput;

    private JFileChooser fileChooserOpen;
    private JFileChooser fileChooserSave;

    public Window() {
        this.setTitle("Task 4");
        this.setContentPane(panelMain);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        setLocationRelativeTo(null); //запуск окна в центре

        JTableUtils.initJTableForArray(tableInput, 400, true, false, true, false);
        JTableUtils.initJTableForArray(tableOutput, 400, true, false, true, false);
        //tableOutput.setEnabled(false);
        scrollPaneTableInput.setPreferredSize(new Dimension(500, 200));
        scrollPaneTableOutput.setPreferredSize(new Dimension(500, 200));
        tableInput.setRowHeight(25);
        tableOutput.setRowHeight(25);

        fileChooserOpen = new JFileChooser();
        fileChooserSave = new JFileChooser();
        fileChooserOpen.setCurrentDirectory(new File("."));
        fileChooserSave.setCurrentDirectory(new File("."));
        FileFilter filter = new FileNameExtensionFilter("Text files", "txt");
        fileChooserOpen.addChoosableFileFilter(filter);
        fileChooserSave.addChoosableFileFilter(filter);

        fileChooserSave.setAcceptAllFileFilterUsed(false);
        fileChooserSave.setDialogType(JFileChooser.SAVE_DIALOG);
        fileChooserSave.setApproveButtonText("Save");

        JTableUtils.writeArrayToJTable(tableInput, new String[][] {
                {"abcdef ghijklmn opqrstu vwxyz"},
                {"hello!"},
                {"I love you..."}
        });


        this.pack();

        buttonRun.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    String[][] matrix = JTableUtils.readStringMatrixFromJTable(tableInput);
                    String[][] arr = Main.process(matrix);
                    String[][] arrOut = CombSort.arrOut;
                    JTableUtils.writeArrayToJTable(tableOutput, arrOut);

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Ошибка");
                }
            }
        });

        buttonLoadInputFromFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    if (fileChooserOpen.showOpenDialog(panelMain) == JFileChooser.APPROVE_OPTION) {
                        String[] arr = ArrayUtils.readLinesFromFile(fileChooserOpen.getSelectedFile().getPath());
                        JTableUtils.writeArrayToJTable(tableInput, arr);
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Ошибка");
                }
            }
        });

        buttonSaveOutputIntoFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    if (fileChooserSave.showSaveDialog(panelMain) == JFileChooser.APPROVE_OPTION) {
                        String[][] matrix = JTableUtils.readStringMatrixFromJTable(tableOutput);
                        String file = fileChooserSave.getSelectedFile().getPath();
                        if (!file.toLowerCase().endsWith(".txt")) {
                            file += ".txt";
                        }
                        ArrayUtils.writeArrayToFile(file, CombSort.arrOut, null);
                    }
                } catch (Exception e) {
                    //SwingUtils.showErrorMessageBox(e);
                }
            }
        });

    }
}

