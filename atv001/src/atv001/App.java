package atv001;

import java.util.LinkedList;
import java.util.Queue;

public class App {
    
    private static Queue<Processo> filaProcessos = new LinkedList<>();
    private static Queue<Processo> finalizados = new LinkedList<>();

    public static void main(String[] args) {

        // Inicializando os processos...
        filaProcessos.add(new Processo(0, 10000));
        filaProcessos.add(new Processo(1, 5000));
        filaProcessos.add(new Processo(2, 7000));
        filaProcessos.add(new Processo(3, 3000));
        filaProcessos.add(new Processo(4, 3000));
        filaProcessos.add(new Processo(5, 8000));
        filaProcessos.add(new Processo(6, 2000));
        filaProcessos.add(new Processo(7, 5000));
        filaProcessos.add(new Processo(8, 4000));
        filaProcessos.add(new Processo(9, 10000));

        while (!filaProcessos.isEmpty()) {
            Processo processo = filaProcessos.poll();

            processo.executar();
            
            if(processo.getEp() == Estado.PRONTO)
                filaProcessos.add(processo);
            else if(processo.getEp() == Estado.FINALIZADO)
                finalizados.add(processo);
        }
    }
}
