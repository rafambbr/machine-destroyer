package com.destroyer.machinedestroyer.resource;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/destroy")
public class DestroyController {

	private static final Map map = new HashMap();
	private static long memoryCount = 0;

	@Value("${app.memory.text}")
	private String memoryText;

	@GetMapping("/memory")
	public String destroyMemory(int power) {

		if(power <= 0){
			power = 10000;
		}
		map.put(memoryCount,
						String.join("",
						Collections.nCopies(power, memoryText)));

		Runtime rt = Runtime.getRuntime();
		long total = rt.totalMemory();
		long free = rt.freeMemory();
		long used = total - free;

		return "#" + memoryCount++
						+ ", Total: " + total
						+ ", Used: " + used
						+ ", Free: " + free;
	}

	@GetMapping("/cpu")
	public String destroyCpu() {

		return "cpu";
	}
}

