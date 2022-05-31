import javax.swing.*;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class ThreadProdutor extends Thread{

    ArrayList<Integer> bufferCircular = new ArrayList<Integer>(100);
    int valor = 0;

    public Semaphore Mutex = new Semaphore(1); // Mutual Exclusion
    public boolean Encerrar = false;
    JTextArea ListaInterfaceFrafica;
    Thread objetoThreadProdutor;

    ThreadProdutor(JTextArea lista){
        //Inicializa os atributos
        ListaInterfaceFrafica = lista;

        //Criar a Thread
        objetoThreadProdutor = new Thread(this);

        //Iniciar a Thread
        objetoThreadProdutor.start();
    }

    public void run(){
        while (Encerrar == false){ //Será FALSE qnd o usuário clicar em parar

            try {
                //Região crítica, necessário verificar o Mutex
                Mutex.acquire();
                if (bufferCircular.size() < 100){
                    bufferCircular.add(valor); //Adiciona o valor no buffer circular
                    ListaInterfaceFrafica.append(Integer.toString(valor) + "\n"); //Mostrar o valor na interface
                    valor++;
                }
                //Liberar o acesso a região crítica
                Mutex.release();
                Thread.sleep(10); //Faz a Thread aguardar 10mls para continuar a execução
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
