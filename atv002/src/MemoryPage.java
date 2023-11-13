import java.util.Random;

public class MemoryPage {
    private int[][] swap;
    private int[][] ram;
    private Random rdm;

    public MemoryPage(){
        swap = new int[100][6];
        ram = new int[10][6];
        rdm = new Random();

        fillMemories();
    }

    private void fillMemories(){
        for(int i=0; i<100; i++){ // swap
            swap[i][0] = i; // N 0 a 99
            swap[i][1] = i+1; // I 1 a 100
            swap[i][2] = rdm.nextInt(1, 51); // D 1 a 50
            swap[i][3] = 0; // R 
            swap[i][4] = 0; // M
            swap[i][5] = rdm.nextInt(100, 10_000); // T 100 a 9999
        }

        for(int i=0; i<10; i++){ // ram
            int rdmNumber = rdm.nextInt(0, 100);
            ram[i][0] = rdmNumber; /*utilizei o rdmNumber como numero da pagina
                                    pois ele remete ao numero da linha na matriz swap,
                                    isto vai facilitar a troca de páginas posteriormente
                                    */
            ram[i][1] = swap[rdmNumber][1];
            ram[i][2] = swap[rdmNumber][2];
            ram[i][3] = swap[rdmNumber][3];
            ram[i][4] = swap[rdmNumber][4];
            ram[i][5] = swap[rdmNumber][5];
        }
    }

    public void swapToRam(){
        for(int i=0; i<10; i++){ // ram
            int rdmNumber = rdm.nextInt(0, 100);
            ram[i][0] = rdmNumber; /*utilizei o rdmNumber como numero da pagina
                                    pois ele remete ao numero da linha na matriz swap,
                                    isto vai facilitar a troca de páginas posteriormente*/
            ram[i][1] = swap[rdmNumber][1];
            ram[i][2] = swap[rdmNumber][2];
            ram[i][3] = swap[rdmNumber][3];
            ram[i][4] = swap[rdmNumber][4];
            ram[i][5] = swap[rdmNumber][5];
        }

    }

    public void printMatrix(){
        System.out.println("\n\n====== SWAP ======");
        System.out.println("no.  N   |   I   |   D   |  R  |  M  |   T");

        for (int i = 0; i < 100; i++) {
            System.out.printf("%02d.  %02d  |  %03d  |  %03d  |  %d  |  %d  |  %04d\n", i, swap[i][0], swap[i][1], 
                            swap[i][2], swap[i][3], swap[i][4], swap[i][5]);
        }

        System.out.println("\n\n====== RAM ======");
        System.out.println("no.  N   |   I   |   D   |  R  |  M  |   T");

        for (int i = 0; i < 10; i++) {
            System.out.printf("%02d.  %02d  |  %03d  |  %03d  |  %d  |  %d  |  %04d\n", i, ram[i][0], ram[i][1], 
                            ram[i][2], ram[i][3], ram[i][4], ram[i][5]);
        }
    }

    public int[][] getSwap() {
        return swap;
    }

    public void setSwap(int[][] swap) {
        this.swap = swap;
    }

    public int[][] getRam() {
        return ram;
    }

    public void setRam(int[][] ram) {
        this.ram = ram;
    }
}
