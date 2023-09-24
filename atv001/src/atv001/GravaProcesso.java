package atv001;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class GravaProcesso {
    private static GravaProcesso singleton;
    
    private GravaProcesso(){
        String cabecalhoTabela = "Aluno: Vitor Allace Marques Costa\t\t6 periodo - BCC 2023\n\nPID\t|\tEXEC\t|\tTP\t|\tCP\t|\tNES\t|\tnCPU\n";

        byte[] bytesCabecalho = cabecalhoTabela.getBytes();

        Path url = Paths.get("C:/users/vitor/Desktop/processos.txt");
        
        try {
            Files.write(url, bytesCabecalho);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static GravaProcesso getInstancia(){
        return singleton==null? new GravaProcesso() : singleton;
    }

    public void gravarProcesso(Processo p){
        Path url = Paths.get("C:/users/vitor/Desktop/processos.txt");

        String dados = String.format("%d\t\t%d\t\t%d\t\t%d\t\t%d\t\t%d\n", p.getPid(), p.getExec(), p.getTp(), p.getCp(), p.getNes(), p.getnCpu());

        byte[] byteDados = dados.getBytes();

        try {
            Files.write(url, byteDados, StandardOpenOption.APPEND);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
