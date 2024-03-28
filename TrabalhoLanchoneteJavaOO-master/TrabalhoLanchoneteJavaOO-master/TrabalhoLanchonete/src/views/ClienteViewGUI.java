package views;

import models.Cliente;
import banco.BancoDadosLanchonete;
import controllers.ClientesController;
import javax.swing.*;
import java.awt.*;

public class ClienteViewGUI {
    public static void main(String[] args) {
        ClienteViewGUI view = new ClienteViewGUI();
        view.show();
    }

    public void show() {
        JFrame frame = new JFrame("ClienteViewGUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.getContentPane().add(panel);
        placeComponents(panel);

        frame.pack();
        frame.setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));

        JLabel titleLabel = new JLabel("GERENCIAR CLIENTES");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        panel.add(titleLabel);

        JButton cadastrarButton = new JButton("Cadastrar Cliente");
        cadastrarButton.setPreferredSize(new Dimension(180, 40));
        cadastrarButton.addActionListener(e -> cadastrarCliente());
        panel.add(cadastrarButton);

        JButton exibirButton = new JButton("Exibir Clientes");
        exibirButton.setPreferredSize(new Dimension(180, 40));
        exibirButton.addActionListener(e -> exibirClientes());
        panel.add(exibirButton);

        JButton alterarButton = new JButton("Alterar Cliente");
        alterarButton.setPreferredSize(new Dimension(180, 40));
        alterarButton.addActionListener(e -> alterarCliente());
        panel.add(alterarButton);

        JButton excluirButton = new JButton("Excluir Cliente");
        excluirButton.setPreferredSize(new Dimension(180, 40));
        excluirButton.addActionListener(e -> excluirCliente());
        panel.add(excluirButton);

        JButton pesquisarButton = new JButton("Pesquisar Cliente");
        pesquisarButton.setPreferredSize(new Dimension(180, 40));
        pesquisarButton.addActionListener(e -> pesquisarClientePorCodigo());
        panel.add(pesquisarButton);
    }

    private void cadastrarCliente() {
        int cod = Integer.parseInt(JOptionPane.showInputDialog("Digite o código do cliente: "));
        String nome = JOptionPane.showInputDialog("Digite o nome do cliente: ");
        String cpf = JOptionPane.showInputDialog("Digite o CPF do cliente: ");

        ClientesController.adicionarCliente(cod, nome, cpf);
    }

    private void exibirClientes() {
        StringBuilder clientesText = new StringBuilder("---\n");
        for (Cliente cliente : BancoDadosLanchonete.getTabelaCliente()) {
            clientesText.append("Código cliente: ").append(cliente.getCodigo()).append("\n");
            clientesText.append("Nome cliente: ").append(cliente.getNome()).append("\n");
            clientesText.append("CPF cliente: ").append(cliente.getCpf()).append("\n\n");
        }
        clientesText.append("---");
        JOptionPane.showMessageDialog(null, clientesText.toString(), "Clientes Cadastrados", JOptionPane.PLAIN_MESSAGE);
    }

    private void pesquisarClientePorCodigo() {
        int cod = Integer.parseInt(JOptionPane.showInputDialog("Digite o código do cliente: "));

        Cliente cliente = ClientesController.buscarPorCodigo(cod);

        if (cliente != null) {
            StringBuilder clienteInfo = new StringBuilder();
            clienteInfo.append("Código: ").append(cliente.getCodigo()).append("\n");
            clienteInfo.append("Nome: ").append(cliente.getNome()).append("\n");
            clienteInfo.append("CPF: ").append(cliente.getCpf());
            JOptionPane.showMessageDialog(null, clienteInfo.toString(), "Informações do Cliente", JOptionPane.PLAIN_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Cliente não encontrado", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void alterarCliente() {
        int cod = Integer.parseInt(JOptionPane.showInputDialog("Informe o código do cliente: "));
        Cliente cliente = ClientesController.buscarPorCodigo(cod);

        if (cliente != null) {
            int newCod = Integer.parseInt(JOptionPane.showInputDialog("Informe o novo código do cliente (anterior: " + cliente.getCodigo() + "): "));
            String newNome = JOptionPane.showInputDialog("Informe o novo nome do cliente (anterior: " + cliente.getNome() + "): ");
            String newCpf = JOptionPane.showInputDialog("Informe o novo CPF do cliente (anterior: " + cliente.getCpf() + "): ");

            cliente.setCodigo(newCod);
            cliente.setNome(newNome);
            cliente.setCpf(newCpf);
            JOptionPane.showMessageDialog(null, "Cliente alterado com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Cliente não encontrado", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void excluirCliente() {
        int cod = Integer.parseInt(JOptionPane.showInputDialog("Digite o código do cliente: "));
        Cliente cliente = ClientesController.buscarPorCodigo(cod);

        if (cliente != null) {
            BancoDadosLanchonete.getTabelaCliente().remove(cliente);
            JOptionPane.showMessageDialog(null, "Cliente removido com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Cliente não encontrado", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
