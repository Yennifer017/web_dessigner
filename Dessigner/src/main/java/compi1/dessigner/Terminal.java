package compi1.dessigner;

/**
 *
 * @author yenni
 */
import compi1.dessigner.requests.Requester;
import compi1.dessigner.util.NumberLine;
import java.awt.event.WindowEvent;

public class Terminal extends javax.swing.JFrame {

    //FIELDS
    private NumberLine numConsole, numDisplayFile;
    private Requester requester;

    /**
     * Creates new form Fronted
     */
    public Terminal() {
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
        jLabel1 = new javax.swing.JLabel();
        columnaDisplay = new javax.swing.JLabel();
        clearEditorBtn = new javax.swing.JButton();
        ExecuteConsulta = new javax.swing.JButton();
        menu = new javax.swing.JMenuBar();

        jRadioButton1.setText("jRadioButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
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
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, interfazPanelLayout.createSequentialGroup()
                        .addGap(0, 240, Short.MAX_VALUE)
                        .addGroup(interfazPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, interfazPanelLayout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(columnaDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, interfazPanelLayout.createSequentialGroup()
                                .addComponent(clearEditorBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(ClearConsoleBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(ExecuteConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        interfazPanelLayout.setVerticalGroup(
            interfazPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(interfazPanelLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(interfazPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(columnaDisplay))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(displayScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(interfazPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(clearEditorBtn)
                    .addComponent(ClearConsoleBtn)
                    .addComponent(ExecuteConsulta))
                .addGap(18, 18, 18)
                .addComponent(consoleScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

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

    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized

    }//GEN-LAST:event_formComponentResized

    private void formWindowStateChanged(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowStateChanged

    }//GEN-LAST:event_formWindowStateChanged

    private void ClearConsoleBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClearConsoleBtnActionPerformed
        console.setText("");
    }//GEN-LAST:event_ClearConsoleBtnActionPerformed

    private void clearEditorBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearEditorBtnActionPerformed
        display.setText("");
    }//GEN-LAST:event_clearEditorBtnActionPerformed

    private void ExecuteConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExecuteConsultaActionPerformed
        requester.request(display.getText(), Requester.SQcms_EXECUTOR_URL, Requester.POST_METHOD);
    }//GEN-LAST:event_ExecuteConsultaActionPerformed

    private void displayCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_displayCaretUpdate
        numDisplayFile.updateColumna(columnaDisplay);
    }//GEN-LAST:event_displayCaretUpdate

    @Override
    protected void processWindowEvent(WindowEvent e) {
        if (e.getID() == WindowEvent.WINDOW_CLOSING) {
            dispose();
        } else {
            super.processWindowEvent(e);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ClearConsoleBtn;
    private javax.swing.JButton ExecuteConsulta;
    private javax.swing.JButton clearEditorBtn;
    private javax.swing.JLabel columnaDisplay;
    private javax.swing.JTextPane console;
    private javax.swing.JScrollPane consoleScroll;
    private javax.swing.JTextPane display;
    private javax.swing.JScrollPane displayScroll;
    private javax.swing.JPanel interfazPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JMenuBar menu;
    // End of variables declaration//GEN-END:variables
}