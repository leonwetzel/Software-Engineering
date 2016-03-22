package model.device;

import model.environment.Environment;
import model.environment.Position;
import model.robot.MobileRobot;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Robert on 22-3-2016.
 */
public class Sonar extends Device {

    private final int range = 25;
    private ArrayList<SonarMeasurement> sonarMeasurement;

    public Sonar(String name, MobileRobot robot, Position localPos, Environment environment) {
        super(name, robot, localPos, environment);
        this.sonarMeasurement = new ArrayList<SonarMeasurement>();



        this.addPoint(0, 2);
        this.addPoint(100, 2);
        this.addPoint(100, -2);
        this.addPoint(0, -2);

    }
    public void  executeCommand(String command)
    {

    }
    public void nextStep()
    {

    }

}
