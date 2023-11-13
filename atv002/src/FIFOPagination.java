import java.util.Random;

public class FIFOPagination{
    private int[][] ram;
    private MemoryPage mp;

    public FIFOPagination(MemoryPage mp){
        this.mp = mp;
        this.ram = mp.getRam();
    }

    public void execInstructions(){
        Random rdm = new Random();
        int[][] swap = mp.getSwap();

        int executions = 0; //numero de instruções realizadas pela CPU

        while (executions < 1000) { /*cada algoritmo deve executar 1000 instruções,
                                    assim como foi pedido na Obs.1
                                    */
            int rdmNumber = rdm.nextInt(0,100);

            if(ram[0][1] == rdmNumber){
                ram[0][3] = 1;
                executions += 1;
                    
                Random rdmPercent = new Random();
                   
                if(rdmPercent.nextInt(0, 100) <= 30){
                    ram[0][2] += 1;
                    ram[0][4] = 1;
                }

            } else{
                if(ram[0][4] == 1){
                    ram[0][4] = 0;
                    swap[ram[0][0]] = ram[0];
                    mp.setSwap(swap);
                }

                /*retira o primeiro, traz os demais uma posição a frente,
                 e busca o ultimo valor no swap utilizando aquela mesma
                 estratégia do número aleatório
                 */
                int num = rdm.nextInt(0, 100);

                for(int i=0; i<10; i++){
                    if(i!=9){
                        ram[i] = ram[i+1];
                    }
                    else{
                        ram[i] = swap[num];
                        ram[i][0] = num;
                    }
                }
            }

            if(executions % 10 == 0){ //Obs.4: A cada 10 Instruções, o Bit R deve ser zerado para todas as páginas na memória RAM.
                for (int i = 0; i < 10; i++) 
                    ram[i][3] = 0;
            } 
        
            mp.setRam(ram);
        }

    }
}
