package frc.robot.subsystems.arm;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class ArmSwitch extends Subsystem {
  
  private DigitalInput limitSwitch = new DigitalInput(RobotMap.armSwitch);

  public boolean isPressed() {
    return !limitSwitch.get();
  }

  @Override
  public void initDefaultCommand() {
  }
}
