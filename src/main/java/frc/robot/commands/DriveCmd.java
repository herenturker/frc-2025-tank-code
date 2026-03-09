package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Drivetrain;
import java.util.function.DoubleSupplier;

public class DriveCmd extends Command{
    private final Drivetrain m_drive;
    private final DoubleSupplier m_forward;
    private final DoubleSupplier m_rotation;
    private int command;

    public DriveCmd(Drivetrain drive, DoubleSupplier forward, DoubleSupplier rotation){
        m_drive = drive;
        m_forward = forward;
        m_rotation = rotation;
        addRequirements(drive);
    }

    public DriveCmd(Drivetrain drive, int command){
      m_forward = () -> 0;
      m_rotation = () -> 0;
      m_drive = drive;
      this.command = command;
      addRequirements(m_drive);
    }

    @Override
    public void initialize() {
      System.out.println("[Drive Run CMD has started]");
    }
  
  
    @Override
    public void execute() {

      m_drive.runMotors((m_forward.getAsDouble()), m_rotation.getAsDouble()); // Legacy
      
      switch(command){
        case 1:
          m_drive.goFwd();
          System.out.println("GO FWD");
            break;
        case 2:
            m_drive.goBckwd();
            System.out.println("GO BACKWARD");
            break;
        case 4:
            m_drive.turnRight();
            System.out.println("TURN RIGHT");
            break;
        case 3:
            m_drive.turnLeft();
            System.out.println("TURN LEFT");
            break;
        default:
            System.out.println("[Invalid Command for CMD]");
    }
  }
  
  
    @Override
    public void end(boolean interrupted) {
      System.out.println("[Drive Run CMD has ended]");
      m_drive.stop();
    }

    @Override
    public boolean isFinished() {
      return false;
    }
}
