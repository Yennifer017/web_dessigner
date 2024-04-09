package compi1.dessigner;

/**
 *
 * @author yenni
 */
import compi1.dessigner.requests.Requester;
import compi1.dessigner.util.NumberLine;
import javax.swing.JOptionPane;

public class Fronted extends javax.swing.JFrame {

    //FIELDS
    private NumberLine numConsole, numDisplayFile;
    private Requester requester;


    /**
     * Creates new form Fronted
     */
    public Fronted() {
        initComponents();
        this.setLocationRelativeTo(null);
        initNumeracion();
        requester = new Requester(console);
    }


    private void initNumeracion() {
        numDisplayFile = new NumberLine(display);
        displayScroll.setRowHeaderView(numDisplayFile);
        numConsole = new NumberLine(console);
        consoleScroll.setRowHeaderView(numConsole);
        numDisplayFile.updateColumna(columnaDisplay);
    }


    private void showInesperatedError() {
        JOptionPane.showMessageDialog(null, "Ocurrio un error inesperado",
                "Error", JOptionPane.PLAIN_MESSAGE);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jRadioButton1 = new javax.swing.JRadioButton();
        interfazPanel = new javax.swing.JPanel();
        displayScroll = new javax.swing.JScrollPane();
        display = new javax.swing.JTextPane();
        consoleScroll = new javax.swing.JScrollPane();
        console = new javax.swing.JTextPane();
        ClearConsoleBtn = new javax.swing.JButton();
        archivoTxt = new javax.swing.JLabel();
        fileNameDisplay = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        columnaDisplay = new javax.swing.JLabel();
        clearEditorBtn = new javax.swing.JButton();
        ExecuteBtn = new javax.swing.JButton();
        ExecuteConsulta = new javax.swing.JButton();
        menu = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        openFileOp = new javax.swing.JMenuItem();
        newFileOp = new javax.swing.JMenuItem();
        saveOp = new javax.swing.JMenuItem();
        saveAsOp = new javax.swing.JMenuItem();
        CloseFileOp = new javax.swing.JMenuItem();
        Information = new javax.swing.JMenu();
        helpOp = new javax.swing.JMenuItem();
        creditsOp = new javax.swing.JMenuItem();

        jRadioButton1.setText("jRadioButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });
        addWindowStateListener(new java.awt.event.WindowStateListener() {
            public void windowStateChanged(java.awt.event.WindowEvent evt) {
                formWindowStateChanged(evt);
            }
        });

        interfazPanel.setBackground(new java.awt.Color(20, 20, 20));
        interfazPanel.setForeground(new java.awt.Color(13, 13, 13));

        display.setBackground(new java.awt.Color(0, 0, 43));
        display.setFont(new java.awt.Font("Dialog", 0, 19)); // NOI18N
        display.setForeground(new java.awt.Color(234, 234, 234));
        display.setCaretColor(new java.awt.Color(255, 255, 255));
        display.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                displayCaretUpdate(evt);
            }
        });
        displayScroll.setViewportView(display);

        console.setBackground(new java.awt.Color(0, 0, 43));
        console.setFont(new java.awt.Font("Dialog", 0, 19)); // NOI18N
        console.setForeground(new java.awt.Color(234, 234, 234));
        console.setCaretColor(new java.awt.Color(255, 255, 255));
        consoleScroll.setViewportView(console);

        ClearConsoleBtn.setBackground(new java.awt.Color(0, 0, 102));
        ClearConsoleBtn.setFont(new java.awt.Font("Cantarell", 0, 20)); // NOI18N
        ClearConsoleBtn.setForeground(new java.awt.Color(204, 204, 204));
        ClearConsoleBtn.setText("Limpiar Consola");
        ClearConsoleBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClearConsoleBtnActionPerformed(evt);
            }
        });

        archivoTxt.setFont(new java.awt.Font("Cantarell", 0, 19)); // NOI18N
        archivoTxt.setForeground(new java.awt.Color(255, 255, 255));
        archivoTxt.setText("Archivo actual: ");

        fileNameDisplay.setFont(new java.awt.Font("Cantarell", 0, 19)); // NOI18N
        fileNameDisplay.setForeground(new java.awt.Color(255, 255, 255));
        fileNameDisplay.setText("[none]");

        jLabel1.setFont(new java.awt.Font("Cantarell", 0, 19)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setText("Columna:");

        columnaDisplay.setFont(new java.awt.Font("Cantarell", 0, 19)); // NOI18N
        columnaDisplay.setForeground(new java.awt.Color(204, 204, 204));
        columnaDisplay.setText("0000");

        clearEditorBtn.setBackground(new java.awt.Color(0, 0, 102));
        clearEditorBtn.setFont(new java.awt.Font("Cantarell", 0, 20)); // NOI18N
        clearEditorBtn.setForeground(new java.awt.Color(204, 204, 204));
        clearEditorBtn.setText("Limpiar Editor");
        clearEditorBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearEditorBtnActionPerformed(evt);
            }
        });

        ExecuteBtn.setBackground(new java.awt.Color(0, 0, 102));
        ExecuteBtn.setFont(new java.awt.Font("Cantarell", 3, 20)); // NOI18N
        ExecuteBtn.setForeground(new java.awt.Color(204, 204, 204));
        ExecuteBtn.setText("Ejecutar XML");
        ExecuteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExecuteBtnActionPerformed(evt);
            }
        });

        ExecuteConsulta.setBackground(new java.awt.Color(0, 0, 102));
        ExecuteConsulta.setFont(new java.awt.Font("Cantarell", 0, 20)); // NOI18N
        ExecuteConsulta.setForeground(new java.awt.Color(204, 204, 204));
        ExecuteConsulta.setText("Ejecutar Consulta");
        ExecuteConsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExecuteConsultaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout interfazPanelLayout = new javax.swing.GroupLayout(interfazPanel);
        interfazPanel.setLayout(interfazPanelLayout);
        interfazPanelLayout.setHorizontalGroup(
            interfazPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(interfazPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(interfazPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(displayScroll)
                    .addComponent(consoleScroll, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(interfazPanelLayout.createSequentialGroup()
                        .addComponent(archivoTxt)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(fileNameDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(columnaDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, interfazPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(clearEditorBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(ClearConsoleBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(ExecuteBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(ExecuteConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        interfazPanelLayout.setVerticalGroup(
            interfazPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(interfazPanelLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(interfazPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(interfazPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(fileNameDisplay)
                        .addComponent(jLabel1)
                        .addComponent(columnaDisplay))
                    .addComponent(archivoTxt))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(displayScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(interfazPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(clearEditorBtn)
                    .addComponent(ClearConsoleBtn)
                    .addComponent(ExecuteBtn)
                    .addComponent(ExecuteConsulta))
                .addGap(18, 18, 18)
                .addComponent(consoleScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        fileMenu.setText("Archivo");

        openFileOp.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        openFileOp.setFont(new java.awt.Font("Cantarell", 0, 18)); // NOI18N
        openFileOp.setText("Abrir archivo");
        openFileOp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openFileOpActionPerformed(evt);
            }
        });
        fileMenu.add(openFileOp);

        newFileOp.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        newFileOp.setFont(new java.awt.Font("Cantarell", 0, 18)); // NOI18N
        newFileOp.setText("Nuevo archivo");
        newFileOp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newFileOpActionPerformed(evt);
            }
        });
        fileMenu.add(newFileOp);

        saveOp.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        saveOp.setFont(new java.awt.Font("Cantarell", 0, 18)); // NOI18N
        saveOp.setText("Guardar");
        saveOp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveOpActionPerformed(evt);
            }
        });
        fileMenu.add(saveOp);

        saveAsOp.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.SHIFT_DOWN_MASK | java.awt.event.InputEvent.CTRL_DOWN_MASK));
        saveAsOp.setFont(new java.awt.Font("Cantarell", 0, 18)); // NOI18N
        saveAsOp.setText("Guardar como");
        saveAsOp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveAsOpActionPerformed(evt);
            }
        });
        fileMenu.add(saveAsOp);

        CloseFileOp.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        CloseFileOp.setFont(new java.awt.Font("Cantarell", 0, 18)); // NOI18N
        CloseFileOp.setText("Cerrar archivo");
        CloseFileOp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CloseFileOpActionPerformed(evt);
            }
        });
        fileMenu.add(CloseFileOp);

        menu.add(fileMenu);

        Information.setText("Informacion");

        helpOp.setFont(new java.awt.Font("Cantarell", 0, 18)); // NOI18N
        helpOp.setText("Ayuda");
        helpOp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpOpActionPerformed(evt);
            }
        });
        Information.add(helpOp);

        creditsOp.setFont(new java.awt.Font("Cantarell", 0, 18)); // NOI18N
        creditsOp.setText("Creditos");
        creditsOp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                creditsOpActionPerformed(evt);
            }
        });
        Information.add(creditsOp);

        menu.add(Information);

        setJMenuBar(menu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(interfazPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(interfazPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void openFileOpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openFileOpActionPerformed

    }//GEN-LAST:event_openFileOpActionPerformed

    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
 
    }//GEN-LAST:event_formComponentResized

    private void formWindowStateChanged(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowStateChanged

    }//GEN-LAST:event_formWindowStateChanged

    private void ClearConsoleBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClearConsoleBtnActionPerformed
        console.setText("");
    }//GEN-LAST:event_ClearConsoleBtnActionPerformed

    private void helpOpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_helpOpActionPerformed
        JOptionPane.showMessageDialog(null,
                "Puedes consultar el manual de usuario en el repositorio de github",
                "Ayuda", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_helpOpActionPerformed

    private void creditsOpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_creditsOpActionPerformed
        JOptionPane.showMessageDialog(null, """
                Proyecto a base de desesperacion y lagrimas, necesito una buena nota :c apoyame con una estrella en gitHub
                                             --Yennifer""",
                "Creditos", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_creditsOpActionPerformed

    private void saveOpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveOpActionPerformed

    }//GEN-LAST:event_saveOpActionPerformed

    private void saveAsOpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveAsOpActionPerformed
        JOptionPane.showMessageDialog(null, "Sera implementado en la v 1.2");
    }//GEN-LAST:event_saveAsOpActionPerformed

    private void CloseFileOpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CloseFileOpActionPerformed

    }//GEN-LAST:event_CloseFileOpActionPerformed

    private void newFileOpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newFileOpActionPerformed

    }//GEN-LAST:event_newFileOpActionPerformed

    private void clearEditorBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearEditorBtnActionPerformed
        display.setText("");
    }//GEN-LAST:event_clearEditorBtnActionPerformed

    private void ExecuteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExecuteBtnActionPerformed
        requester.reqExecute(display.getText());
    }//GEN-LAST:event_ExecuteBtnActionPerformed

    private void ExecuteConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExecuteConsultaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ExecuteConsultaActionPerformed

    private void displayCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_displayCaretUpdate
        numDisplayFile.updateColumna(columnaDisplay);
    }//GEN-LAST:event_displayCaretUpdate


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ClearConsoleBtn;
    private javax.swing.JMenuItem CloseFileOp;
    private javax.swing.JButton ExecuteBtn;
    private javax.swing.JButton ExecuteConsulta;
    private javax.swing.JMenu Information;
    private javax.swing.JLabel archivoTxt;
    private javax.swing.JButton clearEditorBtn;
    private javax.swing.JLabel columnaDisplay;
    private javax.swing.JTextPane console;
    private javax.swing.JScrollPane consoleScroll;
    private javax.swing.JMenuItem creditsOp;
    private javax.swing.JTextPane display;
    private javax.swing.JScrollPane displayScroll;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JLabel fileNameDisplay;
    private javax.swing.JMenuItem helpOp;
    private javax.swing.JPanel interfazPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JMenuBar menu;
    private javax.swing.JMenuItem newFileOp;
    private javax.swing.JMenuItem openFileOp;
    private javax.swing.JMenuItem saveAsOp;
    private javax.swing.JMenuItem saveOp;
    // End of variables declaration//GEN-END:variables
}
