package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.ClawWithJoystick;

public class ClawSub extends Subsystem {
  private Victor claw = new Victor(RobotMap.claw);



  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new ClawWithJoystick());
  }

  public void puxar(){
    claw.set(0.5);
  }
  
  public void jogar(){
    claw.set(-0.6);
  }

  public void stop(){
    claw.set(0.0);
  }

  double limit(double value, double limit){
    if (value > limit) return limit;
    if (value < -limit) return -limit;
    return limit;

  }

  public void run(Joystick joystick) {
    claw.set(limit(joystick.getRawAxis(2) -joystick.getRawAxis(3), 0.5));

	}

}
