package com.team2073.robot;

import com.team2073.robot.Commands.HalfPowerCommand;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import com.team2073.robot.Subsystems.SimpleSubsystem;

public class OperatorInterface {
    private final ApplicationContext appCTX = ApplicationContext.getInstance();
    public final Joystick controller = appCTX.getController();
    // create button A
    private final JoystickButton a = new JoystickButton(controller, 1);
    private final JoystickButton leftTrigger = new JoystickButton(controller, 2);
    private final JoystickButton rightTrigger = new JoystickButton(controller, 3);
    private final JoystickButton leftBumper = new JoystickButton(controller, 4);

    public void init() {
        a.whileHeld(new HalfPowerCommand());
    }


}
