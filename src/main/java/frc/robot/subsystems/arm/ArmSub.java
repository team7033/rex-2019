package frc.robot.subsystems.arm;

import frc.robot.Robot;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Potentiometer;
import frc.robot.commands.ArmControlWithJoystick;

public class ArmSub extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private SpeedControllerGroup motors;
  public Potentiometer pot;

  public ArmSub(SpeedControllerGroup motors, Potentiometer pot) {
    this.motors = motors;
    this.pot = pot;
    new Thread(() -> {
      while (true)
      if(Robot.debug) {
        System.out.println("[ArmSub] current angle=" + this.pot.get());
        Timer.delay(1);
      }
    }).start();
  }
  public void set(double value) {
    motors.set(value);
  }
  
  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new ArmControlWithJoystick());
  }
}
