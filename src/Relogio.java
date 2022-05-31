import javax.swing.*;
import java.util.TimerTask;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


public class Relogio extends TimerTask {
    JLabel interfaceGrafica;

    Relogio(JLabel label){
        interfaceGrafica = label;
    }


    @Override
    public void run() {
        try{ //Formatar a hora
            DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm:ss");
            LocalTime localTime = LocalTime.now(); //Pega horario atual do sistema
            interfaceGrafica.setText(formatoHora.format(localTime)); //Exibir na tela

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
