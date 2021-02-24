package xyx.game.mask.Date;

public class Planet {



    private String planetName;
    private Long distanceFromSun;
    private int gravity;
    private int diameter;private String info;
    private boolean isRead;
    public Planet(String planetName, Long distanceFromSun, int gravity, int diameter,String info,boolean isRead) {
        this.planetName = planetName;
        this.distanceFromSun = distanceFromSun;
        this.gravity = gravity;
        this.diameter = diameter;
        this.info=info;
        this.isRead=isRead;
    }
    public String getPlanetName() {
        return planetName;
    }
    public void setPlanetName(String planetName) {
        this.planetName = planetName;
    }
    public Long getDistanceFromSun() {
        return distanceFromSun;
    }
    public void setDistanceFromSun(Long distanceFromSun) {
        this.distanceFromSun = distanceFromSun;
    }
    public int getGravity() {
        return gravity;
    }
    public void setGravity(int gravity) {
        this.gravity = gravity;
    }
    public int getDiameter() {
        return diameter;
    }
    public void setDiameter(int diameter) {
        this.diameter = diameter;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }
}