package com.czw.设计模式.命令模式.command;

import com.czw.设计模式.命令模式.model.Garage;

/**
 * @Author: czw
 * @CreateTime: 2020-01-16 14:25
 * @UpdeteTime: 2020-01-16 14:25
 * @Description:
 */
public class GarageDoorOpenCommand implements Command{
	Garage garage;
	public GarageDoorOpenCommand(Garage garage){
		this.garage=garage;
	}
	@Override
	public void execute() {
		garage.up();
	}
}
