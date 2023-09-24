package atv001;

import java.util.Random;

public class Processo {
    private int pid, exec, tp, cp, nes, nCpu;
    public int getExec() {
        return exec;
    }

    public void setExec(int exec) {
        this.exec = exec;
    }

    private int quantum;
    private Estado ep;
    private Random rdm;
    private GravaProcesso gp;

    public Processo(int pid, int exec) {
        this.pid = pid;
        this.exec = exec;
        this.tp = 0;
        this.cp = this.tp+1;
        this.ep = Estado.PRONTO;
        this.nes = 0;
        this.nCpu = 0;
        this.quantum = 0;
        this.rdm = new Random();
        this.gp = GravaProcesso.getInstancia();/**  fiz um singleton para evitar que cada objeto Processo
                                                *   tivesse que carregar uma instancia de GravaProcesso
                                                *   e consumir memoria desnecessaria, com o singleton todo objeto
                                                *   Processo terá a mesma instancia de GravaProcesso na memoria!
                                                */
    }

    public void executar() {
        this.mudaEstado(Estado.EXECUTANDO);

        this.tp++;//aqui ja contabilizei um ciclo, os proximos serão contabilizados no For
        this.cp++;

        this.nCpu++; //o uso da CPU so contabilizo quando o processo entra em Execucao
        this.quantum = 1000; //recebeu o valor de quantum do SO

        boolean houveIO = false;

        for(int i=0; i<this.quantum;i++){
            this.tp++;
            this.cp++;

            if(this.cp > this.exec){
                this.mudaEstado(Estado.FINALIZADO);
                break;
            }

            if (rdm.nextInt(0,101) <= 5) {
                this.mudaEstado(Estado.BLOQUEADO);
                this.nes++;
                houveIO = true;
                
                /**
                 * durante um processo de IO em um SO single thread como estamos simulando nesta atividade
                 * eu nao posso contabilizar ciclos pois o processo esta bloqueado aguardando
                 * o IO finalizar para continuar executando os ciclos do processo, vale ressaltar que
                 * tomei a liberdade de nao contabilizar os ciclos do IO, e sim apenas os ciclos daquele processo,
                 * tomei esta decisao pois do contrário poderia haver uma discrepancia entre os valores de TP e CP
                 */
                while(this.ep == Estado.BLOQUEADO){
                    if(rdm.nextInt(0,101) <= 30){
                        this.mudaEstado(Estado.PRONTO);
                        return;
                    }
                }
            }
        }

        if(houveIO == false && this.getEp() != Estado.FINALIZADO){
            this.mudaEstado(Estado.PRONTO);
        }
        
    }

    private void mudaEstado(Estado e) {
        gp.gravarProcesso(this);

        this.imprimir();

        System.out.println("Mudando de estado: " + this.ep + " >>> " + e);

        this.ep = e;
    }

    public void imprimir() {

        System.out.println("\n\nPID: " + this.pid);
        System.out.println("Tempo de processamento: " + this.tp);
        System.out.println("Contador de programa: " + this.cp);
        System.out.println("Estado: " + this.ep);
        System.out.println("Número de vezes que realizou operação de I/O: " + this.nes);
        System.out.println("Número de vezes que usou a CPU: " + this.nCpu);
    }

    public int getPid() {
        return this.pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getTp() {
        return this.tp;
    }

    public void setTp(int tp) {
        this.tp = tp;
    }

    public int getCp() {
        return this.cp;
    }

    public void setCp(int cp) {
        this.cp = cp;
    }

    public Estado getEp(){
        return this.ep;
    }
    
    public void setEp(Estado ep){
        this.ep = ep;
    }

    public int getNes() {
        return this.nes;
    }

    public void setNes(int nes) {
        this.nes = nes;
    }

    public int getnCpu() {
        return this.nCpu;
    }

    public void setnCpu(int nCpu) {
        this.nCpu = nCpu;
    }
    
}