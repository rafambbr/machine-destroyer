package com.destroyer.machinedestroyer.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/destroy")
public class DestroyController {

	@GetMapping("/memory")
	public String destroyMemory() {
		return "memory";
	}

	@GetMapping("/cpu")
	public String destroyCpu() {
		return "cpu";
	}
}
