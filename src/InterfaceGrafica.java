import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Container;

public class InterfaceGrafica extends JFrame implements ActionListener {

    private JButton botaoINICIAR;
    private JButton botaoPARAR;
    private JLabel labelNumerosGerados;
    private JLabel labelNumerosProcessados;
    private JLabel labelRelogio;
    static private JTextArea listaNumerosGerados;
    static private JTextArea listaNumerosProcessados;
    private JScrollPane scrollListaNumerosGerados;
    private JScrollPane scrollListaNumerosProcessados;
    private Container janelaPrincipal;

    //Atrivutos para criar as Threads
    ThreadProdutor objetoThreadProdutor; //Thread que vai gerar os números
    ThreadConsumidor objetoThreadConsumidor; //Thread que vai processar os números

    public InterfaceGrafica(){
        setSize(300, 640); //Tamanho da janela principal
        setTitle("Multi Threads"); //Titulo janela
        janelaPrincipal = getContentPane(); //Ref para a janela principal
        janelaPrincipal.setLayout(null); // limpar o conteudo da janela

        //Criando os componentes da interface gráfica
        botaoINICIAR = new JButton("Iniciar");
        botaoPARAR = new JButton("Parar");
        labelRelogio = new JLabel("Relógio");
        labelNumerosGerados = new JLabel("Gerados");
        labelNumerosProcessados = new JLabel("Processados");
        listaNumerosGerados = new JTextArea();
        listaNumerosProcessados = new JTextArea();
        scrollListaNumerosGerados = new JScrollPane(listaNumerosGerados);
        scrollListaNumerosProcessados = new JScrollPane(listaNumerosProcessados);

        //Configurando o posicionamento dos componentes
        botaoINICIAR.setBounds(70,550,80,40);
        botaoPARAR.setBounds(160,550,80,40);
        labelNumerosGerados.setBounds(50,3,100,20);
        labelNumerosProcessados.setBounds(180,3,100,20);
        scrollListaNumerosGerados.setBounds(30,20,100,500);
        scrollListaNumerosProcessados.setBounds(160,20,100,500);
        labelRelogio.setBounds(120,520,80,40);

        //Adicionar componentes na tela
        janelaPrincipal.add(botaoINICIAR);
        janelaPrincipal.add(botaoPARAR);
        janelaPrincipal.add(labelNumerosGerados);
        janelaPrincipal.add(labelNumerosProcessados);
        janelaPrincipal.add(scrollListaNumerosGerados);
        janelaPrincipal.add(scrollListaNumerosProcessados);
        janelaPrincipal.add(labelRelogio);
        setVisible(true);

        //Inserir tratamento dos eventos de clique
        botaoINICIAR.addActionListener(this);
        botaoPARAR.addActionListener(this);

        //Inserir relógio
        Temporizador relogio = new Temporizador(labelRelogio);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Verificar se o usuario clicou no botão
        if(e.getActionCommand().equals("Iniciar")){
            //Limpar a lista
            listaNumerosGerados.setText("");
            listaNumerosProcessados.setText("");

            //Criando as Threads Produtor e Consumidor
            objetoThreadProdutor = new ThreadProdutor(listaNumerosGerados);
            objetoThreadConsumidor = new ThreadConsumidor(objetoThreadProdutor, listaNumerosProcessados);
        }

        if (e.getActionCommand().equals("Parar")){
            //Setar o atributo "Encerrar" para true, fazendo com que as threads se encerrem
            objetoThreadProdutor.Encerrar = true;
            objetoThreadConsumidor.Encerrar = true;
        }
    }

    public static void main(String[] args) {
        InterfaceGrafica tela = new InterfaceGrafica();
    }

}
