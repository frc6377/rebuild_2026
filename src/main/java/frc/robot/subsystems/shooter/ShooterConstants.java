package frc.robot.subsystems.shooter;

import com.ctre.phoenix6.configs.MotorOutputConfigs;
import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import edu.wpi.first.math.controller.PIDController;

public final class ShooterConstants {
    public static final InvertedValue shooterOuttakeDirection = InvertedValue.Clockwise_Positive;
    public static final NeutralModeValue shooterNeutralMode = NeutralModeValue.Coast;
    public static final MotorOutputConfigs shooterMotorOutputConfigs =
            new MotorOutputConfigs().withInverted(shooterOuttakeDirection).withNeutralMode(shooterNeutralMode);

    // PID gains for velocity control (Slot0)
    // kP: Proportional gain - output per unit of error in velocity
    // kI: Integral gain - output per unit of integrated error
    // kD: Derivative gain - output per unit of error derivative
    // kS: Static feedforward - output to overcome static friction
    // kV: Velocity feedforward - output per unit of requested velocity
    public static final Slot0Configs shooterGains =
            new Slot0Configs().withKP(0.1).withKI(0).withKD(0).withKS(0).withKV(0.12);
    public static final PIDController shooterSimPIDController = new PIDController(0.1, 0.0, 0.0);
    public static final TalonFXConfiguration shooterTalonFXConfiguration = new TalonFXConfiguration()
            .withMotorOutput(shooterMotorOutputConfigs)
            .withSlot0(shooterGains);

    // Simulation Constants
    public static final double MOI = 0.01;
    public static final double gearRatio = 1.0;
    public static final int motorCount = 1;
}
