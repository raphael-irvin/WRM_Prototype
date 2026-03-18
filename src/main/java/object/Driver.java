package object;

import utilclass.UtilClass;

public class Driver {

    //General Stats
    private String name;
    private int age;
    private double weight;
    private double height;

    //Driving Stats
    private int handling;
    private int braking;
    private int reaction;
    private int efficiency;
    private int focus;
    private int vision;
    private int feedback;

    //Derivative Stats
    private int overtaking;
    private int cornering;
    private int defending;

    private Driver() {
        //Prevent Manual Instantiation
    }

    public static Driver createRandomLowDriver() {
        Driver driver = new Driver();
        // Generate Random Attributes
        driver.name = "Driver " + UtilClass.generateRandomInt(1, 1000);

        driver.handling = UtilClass.generateRandomInt(1, 10);
        driver.braking = UtilClass.generateRandomInt(1, 10);
        driver.reaction = UtilClass.generateRandomInt(1, 10);
        driver.efficiency = UtilClass.generateRandomInt(1, 10);
        driver.focus = UtilClass.generateRandomInt(1, 10);
        driver.vision = UtilClass.generateRandomInt(1, 10);
        driver.feedback = UtilClass.generateRandomInt(1, 10);
        driver.calculateDerivativeStats();

        System.out.println("DRIVER CREATED");;

        return driver;
    }

    public void calculateDerivativeStats() {
        overtaking = (int) Math.floor(0.375*handling + 0.3125*vision + 0.1875*reaction + 0.125*braking);
        cornering = (int) Math.floor(0.375*handling + 0.375*braking + 0.125*vision + 0.125*reaction);
        defending = (int) Math.floor(0.375*handling + 0.3125*reaction + 0.3125*braking);
    }

    public void showDriverInfo() {
        System.out.printf("Driver Name: %s\n", name);
        System.out.printf("Handling: %s\n", handling);
        System.out.printf("Braking: %s\n", braking);
        System.out.printf("Reaction: %s\n", reaction);
        System.out.printf("Efficiency: %s\n", efficiency);
        System.out.printf("Focus: %s\n", focus);
        System.out.printf("Vision: %s\n", vision);
        System.out.printf("Feedback: %s\n", feedback);
        System.out.printf("Overtaking: %s\n", overtaking);
        System.out.printf("Cornering: %s\n", cornering);
        System.out.printf("Defending: %s\n", defending);
        System.out.printf("Overall Technique: %s\n", (overtaking + cornering + defending) / 3);
        System.out.printf("Overall Skill: %s\n", (handling + braking + reaction + efficiency + focus + vision + feedback) / 7);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public int getHandling() {
        return handling;
    }

    public void setHandling(int handling) {
        this.handling = handling;
    }

    public int getBraking() {
        return braking;
    }

    public void setBraking(int braking) {
        this.braking = braking;
    }

    public int getReaction() {
        return reaction;
    }

    public void setReaction(int reaction) {
        this.reaction = reaction;
    }

    public int getEfficiency() {
        return efficiency;
    }

    public void setEfficiency(int efficiency) {
        this.efficiency = efficiency;
    }

    public int getFocus() {
        return focus;
    }

    public void setFocus(int focus) {
        this.focus = focus;
    }

    public int getVision() {
        return vision;
    }

    public void setVision(int vision) {
        this.vision = vision;
    }

    public int getFeedback() {
        return feedback;
    }

    public void setFeedback(int feedback) {
        this.feedback = feedback;
    }

    public int getOvertaking() {
        return overtaking;
    }

    public void setOvertaking(int overtaking) {
        this.overtaking = overtaking;
    }

    public int getCornering() {
        return cornering;
    }

    public void setCornering(int cornering) {
        this.cornering = cornering;
    }

    public int getDefending() {
        return defending;
    }

    public void setDefending(int defending) {
        this.defending = defending;
    }
}
