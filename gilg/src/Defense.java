public class Defense extends Building{
    private int level = 1;
    private int workers = 30;

    public void addLevel() {
        this.level += 1;
    }

    public int getLevel() {
        return level;
    }

    public int getNumWorker() {
        return workers;
    }
}