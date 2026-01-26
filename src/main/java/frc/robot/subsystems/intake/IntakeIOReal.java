package frc.robot.subsystems.intake;

import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import org.littletonrobotics.junction.Logger;

public class IntakeIOReal implements IntakeIO {

    public final TalonSRX intakeMotor;

    public IntakeIOReal() {
        intakeMotor = new TalonSRX(IntakeConstants.MotorIDs.ROLLER_MOTOR_ID);
    }

    @Override
    public void setSpeed(double speed) {
        intakeMotor.set(TalonSRXControlMode.PercentOutput, speed);
    }

    @Override
    public void stop() {
        intakeMotor.set(TalonSRXControlMode.PercentOutput, 0);
    }

    @Override
    public void periodic() {
        Logger.recordOutput("Intake/RollerMotorVoltage", intakeMotor.getBusVoltage());
        Logger.recordOutput("Intake/RollerMotorSpeedPercentile", intakeMotor.getMotorOutputPercent());
    }
}
