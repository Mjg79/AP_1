public class Defense extends Building{
    private double level = 0.2;
    private int workers = 30;

    public void addLevel() {
        Level += 0.2;
    }

    public int getLevel() {
        return Level;
    }

    public int getNumWorker() {
        return workers ;
    }
}