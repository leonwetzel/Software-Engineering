package model.robot;

import model.virtualmap.OccupancyMap;

import java.io.PipedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.PipedOutputStream;
import java.io.IOException;

import java.util.StringTokenizer;

/**
 * Title    :   The Mobile Robot Explorer Simulation Environment v2.0
 * Copyright:   GNU General Public License as published by the Free Software Foundation
 * Company  :   Hanze University of Applied Sciences
 *
 * @author Dustin Meijer        (2012)
 * @author Alexander Jeurissen  (2012)
 * @author Davide Brugali       (2002)
 * @version 2.0
 */

public class MobileRobotAI implements Runnable {

	private final OccupancyMap map;
	private final MobileRobot robot;

	private boolean running;

	public MobileRobotAI(MobileRobot robot, OccupancyMap map) {
		this.map = map;
		this.robot = robot;
	}

	/**
	 * In this method the gui.controller sends commands to the robot and its devices.
	 * At the moment all the commands are hardcoded.
	 * The exercise is to let the gui.controller make intelligent decisions based on
	 * what has been discovered so far. This information is contained in the OccupancyMap.
	 */
	public void run() {
		String result;
		this.running = true;
		double position[] = new double[3];
		double measures[] = new double[360];
		int i = 0;
		while (running) {
			try {
				System.out.println("");
				PipedInputStream pipeIn = new PipedInputStream();
				BufferedReader input = new BufferedReader(new InputStreamReader(pipeIn));
				PrintWriter output = new PrintWriter(new PipedOutputStream(pipeIn), true);
				robot.setOutput(output);

				//ases where a variable value is never used after its assignment, i.e.:
				System.out.println("intelligence running");

				robot.sendCommand("R1.GETPOS");
				result = input.readLine();
				parsePosition(result, position);

				robot.sendCommand("L1.SCAN");


				System.out.println(running);
				result = input.readLine();
				System.out.println("READ LINE DONE");
				try
				{
					if(result.substring(0, 4).equalsIgnoreCase("SCAN"))
					{
						parseMeasures(result, measures);
						addCommandToRobot(result,measures);
						System.out.println("ADDED COMMANDS");
					}
				}catch(Exception e)
				{
					System.err.println(e.getMessage());
				}
				map.drawLaserScan(position, measures);
				System.out.println("END OF LOOP");
			} catch (IOException ioe) {
				System.err.println("execution stopped");
				running = false;
			}
		}

	}
	private void oldCode()
	{
		/*
				robot.sendCommand("P1.MOVEFW 10");
				result = input.readLine();

				robot.sendCommand("R1.GETPOS");
				result = input.readLine();
				parsePosition(result, position);
				System.out.println("Before Crash");
				robot.sendCommand("L1.SCAN");
				result = input.readLine();
				parseMeasures(result, measures);
				map.drawLaserScan(position, measures);
				System.out.println("After Crash");
				robot.sendCommand("P1.ROTATERIGHT 90");
				result = input.readLine();

				robot.sendCommand("P1.MOVEFW 100");
				result = input.readLine();

				robot.sendCommand("R1.GETPOS");
				result = input.readLine();
				parsePosition(result, position);

				robot.sendCommand("L1.SCAN");
				result = input.readLine();
				parseMeasures(result, measures);
				map.drawLaserScan(position, measures);

				//robot.sendCommand("P1.ROTATELEFT 45");
				result = input.readLine();

				//robot.sendCommand("P1.MOVEFW 70");
				result = input.readLine();

				robot.sendCommand("R1.GETPOS");
				result = input.readLine();
				parsePosition(result, position);

				robot.sendCommand("L1.SCAN");
				result = input.readLine();
				parseMeasures(result, measures);
				map.drawLaserScan(position, measures);

				//robot.sendCommand("P1.MOVEFW 70");
				result = input.readLine();

				//robot.sendCommand("P1.ROTATERIGHT 45");
				result = input.readLine();

				robot.sendCommand("R1.GETPOS");
				result = input.readLine();
				parsePosition(result, position);

				robot.sendCommand("L1.SCAN");
				result = input.readLine();
				parseMeasures(result, measures);
				map.drawLaserScan(position, measures);

				//robot.sendCommand("P1.MOVEFW 90");
				result = input.readLine();

				robot.sendCommand("R1.GETPOS");
				result = input.readLine();
				parsePosition(result, position);

				robot.sendCommand("L1.SCAN");
				result = input.readLine();
				parseMeasures(result, measures);
				map.drawLaserScan(position, measures);

				//robot.sendCommand("P1.ROTATERIGHT 45");
				result = input.readLine();

				//robot.sendCommand("P1.MOVEFW 90");
				result = input.readLine();

				//robot.sendCommand("R1.GETPOS");
				result = input.readLine();
				parsePosition(result, position);

				//robot.sendCommand("L1.SCAN");
				result = input.readLine();
				parseMeasures(result, measures);
				map.drawLaserScan(position, measures);

				//robot.sendCommand("P1.ROTATERIGHT 45");
				result = input.readLine();

				//robot.sendCommand("P1.MOVEFW 100");
				result = input.readLine();

				//robot.sendCommand("R1.GETPOS");
				result = input.readLine();
				parsePosition(result, position);

				robot.sendCommand("L1.SCAN");
				result = input.readLine();
				parseMeasures(result, measures);
				map.drawLaserScan(position, measures);

				//robot.sendCommand("P1.ROTATERIGHT 90");
				result = input.readLine();

				//robot.sendCommand("P1.MOVEFW 80");
				result = input.readLine();

				robot.sendCommand("R1.GETPOS");
				result = input.readLine();
				parsePosition(result, position);

				robot.sendCommand("L1.SCAN");
				result = input.readLine();
				parseMeasures(result, measures);
				map.drawLaserScan(position, measures);

				robot.sendCommand("P1.MOVEFW 10");
				result = input.readLine();

				robot.sendCommand("R1.GETPOS");
				result = input.readLine();
				parsePosition(result, position);

				robot.sendCommand("L1.SCAN");
				result = input.readLine();
				parseMeasures(result, measures);
				map.drawLaserScan(position, measures);
				//this.running = false; */
	}

	private void parsePosition(String value, double position[]) {
		int indexInit;
		int indexEnd;
		String parameter;

		indexInit = value.indexOf("X=");
		parameter = value.substring(indexInit + 2);
		indexEnd = parameter.indexOf(' ');
		position[0] = Double.parseDouble(parameter.substring(0, indexEnd));

		indexInit = value.indexOf("Y=");
		parameter = value.substring(indexInit + 2);
		indexEnd = parameter.indexOf(' ');
 		position[1] = Double.parseDouble(parameter.substring(0, indexEnd));

		indexInit = value.indexOf("DIR=");
		parameter = value.substring(indexInit + 4);
		position[2] = Double.parseDouble(parameter);
	}

	private void parseMeasures(String value, double measures[]) {
		for (int i = 0; i < 360; i++) {
			measures[i] = 100.0;
		}
		if (value.length() >= 5) {
			value = value.substring(5);  // removes the "SCAN " keyword

			StringTokenizer tokenizer = new StringTokenizer(value, " ");

			double distance;
			int direction;
			while (tokenizer.hasMoreTokens()) {
				distance = Double.parseDouble(tokenizer.nextToken().substring(2));
				direction = (int) Math.round(Math.toDegrees(Double.parseDouble(tokenizer.nextToken().substring(2))));
				if (direction == 360) {
					direction = 0;
				}
				measures[direction] = distance;
				// Printing out all the degrees and what it encountered.
				System.out.println("direction = " + direction + " distance = " + distance);
			}
		}
	}

	private void addCommandToRobot(String value, double measures[]) {
		for (int i = 0; i < 360; i++) {
			measures[i] = 100.0;
		}
		if (value.length() >= 5) {
			value = value.substring(5);  // removes the "SCAN " keyword
			StringTokenizer tokenizer = new StringTokenizer(value, " ");
			boolean somethingInFrontOfRobot = false;
			double distance;
			int direction;
			while (tokenizer.hasMoreTokens()) {
				distance = Double.parseDouble(tokenizer.nextToken().substring(2));
				direction = (int) Math.round(Math.toDegrees(Double.parseDouble(tokenizer.nextToken().substring(2))));
				if (direction == 360) {
					direction = 0;
				}
				measures[direction] = distance;
				// Printing out all the degrees and what it encountered.
				//System.out.println("direction = " + direction + " distance = " + distance);
				if(distance <=10 && !somethingInFrontOfRobot)
				{
					if((direction >= 0  && direction <= 20)|| (direction > 340) )
					{
						somethingInFrontOfRobot = true;

					}
				}else if(somethingInFrontOfRobot)
				{

						if(direction >= 45 && direction <= 135 && distance <=10)
						{
							//Go left
							System.out.println("Going Left");
							robot.sendCommand("P1.ROTATELEFT 45");
							break;
						}else if(direction >= 225 && direction <= 305 && distance <=10)
						{
							//Go Right
							System.out.println("Going Right");
							robot.sendCommand("P1.ROTATERIGHT 45");
							break;
						}
						else
						{
							System.out.println("GO RIGHT");
							robot.sendCommand("P1.ROTATERIGHT 45");
							break;
						}



				}
			}
			if(!somethingInFrontOfRobot)
			{
				System.out.print("Moving Forward");
				robot.sendCommand("P1.MOVEFW 10");
			}
		}
	}

}
