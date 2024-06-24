import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.TreeMap;

public class CadastraCliente {
    private JPanel mainPanel;
    private JTextField tfCodigo;
    private JTextField tfNome;
    private JTextArea taMensagens;
    private JButton btnCadastrar;
    private JButton btnLimpar;
    private JButton btnMostrarClientes;
    private JButton btnFechar;
    private Map<Integer, Cliente> clientes;

    public CadastraCliente() {
        clientes = new TreeMap<>();

        btnCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarCliente();
            }
        });

        btnLimpar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limparCampos();
            }
        });

        btnMostrarClientes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarClientes();
            }
        });

        btnFechar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    private void cadastrarCliente() {
        try {
            int codigo = Integer.parseInt(tfCodigo.getText().trim());
            String nome = tfNome.getText().trim();

            if (clientes.containsKey(codigo)) {
                taMensagens.setText("Erro: Código de cliente já existente.");
            } else {
                Cliente cliente = new Cliente(codigo, nome);
                clientes.put(codigo, cliente);
                taMensagens.setText("Cliente cadastrado com sucesso.");
            }
        } catch (NumberFormatException e) {
            taMensagens.setText("Erro: Código deve ser um número inteiro.");
        }
    }

    private void limparCampos() {
        tfCodigo.setText("");
        tfNome.setText("");
        taMensagens.setText("");
    }

    private void mostrarClientes() {
        StringBuilder sb = new StringBuilder("Clientes cadastrados:\n");
        for (Cliente cliente : clientes.values()) {
            sb.append(cliente).append("\n");
        }
        taMensagens.setText(sb.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("Cadastrar Cliente");
                CadastraCliente cadastraCliente = new CadastraCliente();
                frame.setContentPane(cadastraCliente.mainPanel);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });
    }
}