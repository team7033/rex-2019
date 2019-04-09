package frc.robot.subsystems.arm;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.interfaces.Potentiometer;
import frc.robot.RobotMap;
import frc.robot.Robot;
import frc.robot.commands.ArmPIDControlWithJoystick;

public class ArmPIDSystem extends PIDSubsystem {

  private static double kp = 0.009; // 0.008 , 0.009 ,
  private static double kd = 0.005; // 0.005 , 0.005 ,
  private static double ki = 0.0007; //0.0005, 0.0007,

  public Potentiometer pot;
  private SpeedControllerGroup motors;
  
  public ArmPIDSystem(SpeedControllerGroup motors, Potentiometer pot) {
    super("Arm", kp, ki, kd);
    this.pot = pot;
    this.setAbsoluteTolerance(10);
    this.setInputRange(0, 330);
    this.setOutputRange(-0.3, 0.3); // -0.3, 0.5
    this.motors = motors;
    this.getPIDController().setContinuous(true);
    this.enable();
    new Thread(() -> {
      while (true)
      if(Robot.debug) {
        System.out.println("[ArmPIDSystem] current angle=" + this.pot.get());
        Timer.delay(1);
      }
    }).start();
  }

  public double getPWMValue() {
    return this.motors.get();
  }

  @Override
  protected double returnPIDInput() {
    return this.pot.get();
  }

  @Override
  protected void usePIDOutput(double output) {
    this.motors.pidWrite(-output);
  }

  @Override
  protected void initDefaultCommand() {
    this.setDefaultCommand(new ArmPIDControlWithJoystick());
  }
  
}