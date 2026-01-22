package frc.robot.subsystems.intake;

import static edu.wpi.first.units.Units.Volts;

import com.ctre.phoenix6.hardware.TalonFX;

public class IntakeIOReal implements IntakeIO {

    public final TalonFX intakeMotor;

    public IntakeIOReal() {
        intakeMotor = new TalonFX(IntakeConstants.MotorIDs.ROLLER_MOTOR_ID);
    }

    @Override
    public void setRollerSpeed(double speed) {
        intakeMotor.set(speed);
    }

    @Override
    public void stop() {
        intakeMotor.stopMotor();
    }

    @Override
    public void updateInputs(IntakeIO.IntakeIOInputs inputs) {
        inputs.rollerSpeedPercentile = intakeMotor.get();
        inputs.rollerAppliedVolts = Volts.of(intakeMotor.getMotorVoltage().getValueAsDouble());
    }
}
