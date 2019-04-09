package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.arm.SetPoints;

public class ArmPIDControlWithJoystick extends Command {

  private int i = 5;
  private double kDefaultPosition = 95;
  private double m_angle;
  
  public ArmPIDControlWithJoystick() {
    requires(Robot.m_PIDarm);
    requires(Robot.m_switch);
    new Thread(() -> {
      while (Robot.debug && this.isRunning()) {
        System.out.println("[ArmPIDControlWithJoystick] angle=" + m_angle);
        Timer.delay(1);
      }
    }).start(); 
    this.m_angle = kDefaultPosition;
  }

  public void setM_angle(double m_angle) {
    this.m_angle = m_angle;

  }

  public double getM_angle() {
    return m_angle;
  }

  public void increment(double increment) {
    this.m_angle += increment;
  }
  
  public void decrement(double decrement) {
    this.m_angle -= decrement;
    if (m_angle <= 2);
    m_angle = 2;
  } 

  public void nextSetPoint() {
    i += 1;
    if (i >= SetPoints.getSetPoints().length) i = SetPoints.getSetPoints().length - 1;
    m_angle = SetPoints.getSetPoints()[i];
  }  
  
  public void previousSetPoint() {
    i -= 1;
    if (i <= 0) i = 0;
    m_angle = SetPoints.getSetPoints()[i];
  }

  @Override
  protected void execute() {
    Robot.m_PIDarm.setSetpoint(m_angle);

    if (Robot.m_oi.getExtreme().getRawButton(2)) {
      if(Robot.m_oi.getExtreme().getY()>=0)
      setM_angle((Math.pow(Robot.m_oi.getExtreme().getY(), 2) * Math.signum(Robot.m_oi.getExtreme().getY())) * 270);
    }
    if (Robot.m_oi.getExtreme().getRawButton(12)){
      ((ArmPIDControlWithJoystick) Robot.m_PIDarm.getDefaultCommand()).setM_angle(SetPoints.getSetPoints()[0]);
    }
    if (Robot.m_oi.getExtreme().getRawButton(11)){
      ((ArmPIDControlWithJoystick) Robot.m_PIDarm.getDefaultCommand()).setM_angle(SetPoints.getSetPoints()[4]);
    }
    if (Robot.m_oi.getExtreme().getRawButton(10)){
      ((ArmPIDControlWithJoystick) Robot.m_PIDarm.getDefaultCommand()).setM_angle(SetPoints.getSetPoints()[6]);
    }
    if (Robot.m_oi.getExtreme().getRawButton(9)){
      ((ArmPIDControlWithJoystick) Robot.m_PIDarm.getDefaultCommand()).setM_angle(SetPoints.getSetPoints()[9]);
    }

    if (Robot.m_oi.getExtreme().getRawButton(8)){
      ((ArmPIDControlWithJoystick) Robot.m_PIDarm.getDefaultCommand()).setM_angle(SetPoints.getSetPoints()[10
      
      
      ]);
    }

    if (m_angle == SetPoints.getSetPoints()[0] && Robot.m_switch.isPressed()) {
      Robot.m_PIDarm.disable();
    }
    else {
      Robot.m_PIDarm.enable();
    }
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
  }

  @Override
  protected void interrupted() {
  }
}
