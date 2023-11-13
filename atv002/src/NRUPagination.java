import java.util.Random;

public class NRUPagination{
    private int[][] ram;
    private MemoryPage mp;

    public NRUPagination(MemoryPage mp){
        this.mp = mp;
        this.ram = mp.getRam();
    }

    private void replace(){
        int[][] swap = mp.getSwap();
        
        for(int i=0; i<10; i++){
            if(ram[i][4] == 1){

                swap[ram[i][0]][1] = ram[i][1];
                swap[ram[i][0]][2] = ram[i][2];
                swap[ram[i][0]][3] = ram[i][3];
                swap[ram[i][0]][4] = ram[i][4];
                swap[ram[i][0]][5] = ram[i][5];

                mp.setSwap(swap);

                mp.swapToRam();

                ram = mp.getRam();
                
            }
            else{
                for (int j = 0; j < 10; j++) {
                    ram[j][4] = 0;   
                }

                mp.swapToRam();
                ram = mp.getRam();
            }
        }
    }

    public void execInstructions(){
        Random rdm = new Random();
        int executions = 0; //numero de instruções realizadas pela CPU

        while (executions < 1000) { /*cada algoritmo deve executar 1000 instruções,
                                    assim como foi pedido na Obs.1
                                    */
            int rdmNumber = rdm.nextInt(0,100);

            for(int j=0; j<10; j++){

                if(ram[j][1] == rdmNumber){
                    ram[j][3] = 1;
                    executions += 1;
                    
                    Random rdmPercent = new Random();
                    
                    if(rdmPercent.nextInt(0, 100) <= 30){
                        ram[j][2] += 1;
                        ram[j][4] = 1;
                    }

                    break;
                }
            }

            if(executions % 10 == 0){ //Obs.4: A cada 10 Instruções, o Bit R deve ser zerado para todas as páginas na memória RAM.
                for (int i = 0; i < 10; i++) 
                    ram[i][3] = 0;
            } 
        
            mp.setRam(ram);

            replace();
        }

    }
}
