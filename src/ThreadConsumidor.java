import javax.swing.*;

public class ThreadConsumidor extends Thread{

    ThreadProdutor objetoThreadProdutor; //Objeto para acessar a ThreadProdutor
    Thread objetoThreadConsumidor;
    JTextArea ListaInterfaceGrafica;
    public boolean Encerrar = false;

    ThreadConsumidor(ThreadProdutor temp, JTextArea lista){
        //Inicializando atributos
        objetoThreadProdutor = temp;
        ListaInterfaceGrafica = lista;

        //Criar Thread
        objetoThreadConsumidor = new Thread(this);

        //Inicializar Thread
        objetoThreadConsumidor.start();
    }

    public void run(){
        while (Encerrar == false){ //Será FALSE qnd o usuário clicar em parar

            try {
                //Região crítica, necessário verificar o Mutex
                objetoThreadProdutor.Mutex.acquire();
                if (objetoThreadProdutor.bufferCircular.size() > 0){ // Verifica se tem dados no buffer para ser lido
                    int temp = objetoThreadProdutor.bufferCircular.remove(0); //Remover o primeiro elemento da lista
                    int processamento = temp * 100; //Processar o dado
                    ListaInterfaceGrafica.append(Integer.toString(processamento) + "\n"); //Mostrar o processamento na interface
                }
                //Liberar o acesso a região crítica
                objetoThreadProdutor.Mutex.release();
                Thread.sleep(10); //Faz a Thread aguardar 10mls para continuar a execução
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
