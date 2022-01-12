package com.team2073.robot;

import com.team2073.robot.Commands.HalfPowerCommand;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OperatorInterface {
    private final ApplicationContext appCTX = ApplicationContext.getInstance();
    public final Joystick controller = appCTX.getController();
    // create button A
    private final JoystickButton a = new JoystickButton(controller, 1);
    // Hold A to move motor @ half power
    public void init() {
        a.whileHeld(new HalfPowerCommand());
    }
}
