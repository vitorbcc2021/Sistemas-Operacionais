public class App {

    public static void main(String[] args) {
        MemoryPage mp = new MemoryPage();
        NRUPagination nru = new NRUPagination(mp);
        FIFOPagination fifo = new FIFOPagination(mp);
        FIFOSCPagination sc = new FIFOSCPagination(mp);
        ClockPagination clock = new ClockPagination(mp);
        WSClockPagination wsclock = new WSClockPagination(mp);

        mp.printMatrix();

        //imprimindo após NRU
        nru.execInstructions();
        mp.printMatrix();

        //imprimindo após FIFO
        fifo.execInstructions();
        mp.printMatrix();

        //imprimindo após FIFO-SC
        sc.execInstructions();
        mp.printMatrix();

        //imprimindo após Relogio
        clock.execInstructions();
        mp.printMatrix();

        //imprimindo após WS-Clock
        wsclock.execInstructions();
        mp.printMatrix();

    }
}
