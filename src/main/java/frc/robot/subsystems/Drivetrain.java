package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;

public class Drivetrain extends SubsystemBase{
    private final SparkMax m_leftMotor1 = new SparkMax(DriveConstants.kLeftMotor1Port, MotorType.kBrushed);
    private final SparkMax m_leftMotor2 = new SparkMax(DriveConstants.kLeftMotor2Port, MotorType.kBrushed);
    private final SparkMax m_rightMotor1 = new SparkMax(DriveConstants.kRightMotor1Port, MotorType.kBrushed);
    private final SparkMax m_rightMotor2 = new SparkMax(DriveConstants.kRightMotor2Port, MotorType.kBrushed);

    private SparkMaxConfig rightLeaderSparkMaxConfig_ = new SparkMaxConfig();
    private SparkMaxConfig leftLeaderSparkMaxConfig_ = new SparkMaxConfig();
    private SparkMaxConfig rightFollowerSparkMaxConfig_ = new SparkMaxConfig();
    private SparkMaxConfig leftFollowerSparkMaxConfig_ = new SparkMaxConfig();
    private SparkMaxConfig globalSparkMaxConfig_ = new SparkMaxConfig();

    //private final DifferentialDrive m_drive2 = new DifferentialDrive(m_leftMotor1, m_rightMotor1);
    private final DifferentialDrive m_drive = new DifferentialDrive(m_leftMotor2, m_rightMotor2);
    private double rightSpeed, leftSpeed = 0.0;

    public Drivetrain(){

        globalSparkMaxConfig_
            .idleMode(IdleMode.kCoast);

        rightLeaderSparkMaxConfig_
            .apply(globalSparkMaxConfig_)
            .inverted(true)
            .follow(m_rightMotor2);
        rightFollowerSparkMaxConfig_
            .apply(globalSparkMaxConfig_)
            .inverted(true);

        leftLeaderSparkMaxConfig_
            .apply(globalSparkMaxConfig_)
            .inverted(false)
            .follow(m_leftMotor2);
        leftFollowerSparkMaxConfig_
            .apply(globalSparkMaxConfig_)
            .inverted(false);
 


        m_leftMotor1.configure(leftLeaderSparkMaxConfig_, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        m_leftMotor2.configure(leftFollowerSparkMaxConfig_, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

        m_rightMotor1.configure(rightLeaderSparkMaxConfig_, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        m_rightMotor2.configure(rightFollowerSparkMaxConfig_, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

        m_drive.setSafetyEnabled(false);
        m_drive.setExpiration(0.1);

        //m_drive2.setSafetyEnabled(false);
        //m_drive2.setExpiration(0.1);
        
    }

    public void runMotors(double speed, double turn) {
        m_drive.arcadeDrive(speed, turn, true);
        //m_drive2.arcadeDrive(speed, turn, true);
      }

    public void setMotors(double forward, double rotation){

        //  Legacy code
        
        //rightSpeed = ((forward * 0.75) + rotation);
        //leftSpeed = (-(forward * 0.75) + rotation);

        // Test code (not sure)
        
        //rightSpeed = (forward * 0.75) + rotation;  // Sağ motor için hız
        //leftSpeed = (forward * 0.75) - rotation;   // Sol motor için hız

        // Another Test
        //rightSpeed = (forward * 0.75) + rotation;  // Sağ motor için hız
        //leftSpeed = -(forward * 0.75) - rotation;   // Sol motor için hız
        //          | - ekledim (test)

        /*
        m_rightMotor1.set(rightSpeed);
        m_leftMotor1.set(leftSpeed);
        m_rightMotor2.set(rightSpeed);
        m_leftMotor2.set(leftSpeed);
        */

        System.out.println("Left Speed: " + leftSpeed + ", Right Speed: " + rightSpeed);
        
        
        
        //rightSpeed = ((forward * 0.75) + rotation);
        //leftSpeed = (-(forward * 0.75) + rotation);

        rightSpeed = ((forward * 0.75) + rotation);
        leftSpeed = (-(forward * 0.75) + rotation);

        m_rightMotor2.set(rightSpeed);
        m_leftMotor2.set(leftSpeed);
        

    }

    public void stop() {
        m_leftMotor1.set(0.0);
        m_leftMotor2.set(0.0);
        m_rightMotor1.set(0.0);
        m_rightMotor2.set(0.0);
    }

    /* Test Function
    public void arcadeDriveMethod(double leftJoystickAxis, double rightJoystickAxis) {
        m_drive.arcadeDrive(leftJoystickAxis, rightJoystickAxis);
      }
    */

    public void goFwd(){
        m_leftMotor1.set(1.0);
        m_leftMotor2.set(1.0);
        m_rightMotor1.set(1.0);
        m_rightMotor2.set(1.0);
    }

    public void goBckwd(){
        m_leftMotor1.set(-1.0);
        m_leftMotor2.set(-1.0);
        m_rightMotor1.set(-1.0);
        m_rightMotor2.set(-1.0);
    }
    
    public void turnRight(){
        m_leftMotor1.set(0.5);
        m_leftMotor2.set(0.5);
        m_rightMotor1.set(-(0.5));
        m_rightMotor2.set(-(0.5));
    }

    public void turnLeft(){
        m_leftMotor1.set(-(0.5));
        m_leftMotor2.set(-(0.5));
        m_rightMotor1.set(0.5);
        m_rightMotor2.set(0.5);
    }
}
