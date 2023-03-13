
public class Main {

    // Sleeps the "main" Thread for input seconds
    private static void wait(int seconds) {
        try {
            Thread.sleep(1000L * seconds);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Interrupts the input Thread
    private static void kill_thread(Thread thread) {
        try {
            thread.interrupt();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Stops execution of the current Thread (main) until the input Thread completes
    private static void wait_for_thread_to_finish(Thread thread) {
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        // Sound samples
        Player drums = new Player("rock_drums.wav");
        Player trumpets = new Player("incredibles-trumpet.wav");
        Player jaz_piano = new Player("latin-jaz-piano.wav");

        // Play sounds without multithreading (one after the other)
        System.out.println("Playing drums not in a thread");
        drums.play();
        System.out.println("Playing trumpet not in a thread");
        trumpets.play();

        // Loop for playing sounds using multithreading
        for (int i = 0; i < 10; i++) {
            // Creating the Threads
            Thread drumsThread = new Thread(drums);
            Thread trumpetThread = new Thread(trumpets);
            Thread pianoThread = new Thread(jaz_piano);

            System.out.println("Starting Drum Thread");
            drumsThread.start();

            // Tell the Main Thread to wait before bringing in piano
            wait(3);

            System.out.println("Starting Piano Thread");
            pianoThread.start();

            // Tell the Main Thread to wait before bringing in trumpets
            wait(5);

            System.out.println("Playing Trumpet thread");
            trumpetThread.start();

            // Tell the Main Thread to wait before stopping the trumpets
            wait(3);

            System.out.println("Killing Trumpet thread");
            kill_thread(trumpetThread);

            // Tell the Main Thread to stop until the piano is finished
            wait_for_thread_to_finish(pianoThread);
        }


    }

}
