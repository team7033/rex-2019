package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.commands.TankDriveWithJoystick;

public class TankDrive extends Subsystem {
  private SpeedControllerGroup m_leftMotor = new SpeedControllerGroup(
			new Spark(0),
			new Spark(1));
	
	private SpeedControllerGroup m_rightMotor = new SpeedControllerGroup(
			new Spark(2),
      		new Spark(3));
      
  public DifferentialDrive m_drive = new DifferentialDrive(m_leftMotor, m_rightMotor); 
  
  @Override
	public void initDefaultCommand() {
		setDefaultCommand(new TankDriveWithJoystick());
	}
	
	public void drive(double right, double left)
	{
		this.m_leftMotor.set(left);
		this.m_rightMotor.set(right);
  }



  public void drive(Joystick joystick, Joystick joystick2) {
		m_drive.tankDrive(joystick.getY(), joystick2.getY(), true);
	}
	
	public void stop() {
		m_drive.tankDrive(0, 0);
	}
}
