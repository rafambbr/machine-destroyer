package com.destroyer.machinedestroyer.resource;

import static java.lang.String.join;
import static java.util.Collections.nCopies;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.destroyer.machinedestroyer.service.MonteCarloPI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/destroy")
public class DestroyController {

	private static final Map<Long, String> map = new HashMap<Long, String>();
	private static long memoryCount = 0;
	private final static BigDecimal POINTS = new BigDecimal("200000000");

	@Value("${app.memory.text}")
	private String memoryText;

	@GetMapping("/memory")
	public String destroyMemory(int power) {

		if(power <= 0){
			power = 1000;
		}
		map.put(memoryCount, join("", nCopies(power, memoryText)));

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
	public String destroyCpu(int power) {

		if(power <= 0){
			power = 10;
		}

		int cores = Runtime.getRuntime().availableProcessors();
		BigDecimal points_per_thread = POINTS.divide(BigDecimal.valueOf(cores));

		StringBuilder info = new StringBuilder()
						.append("points: ").append(POINTS)
						.append("\n points_per_thread: ").append(points_per_thread)
						.append("\n number_of_executions: ").append(power);

		for (int i = 0; i < power; i++) {
			Date now = new Date();

			//high CPU usage simulation
			MonteCarloPI monteCarloPI = new MonteCarloPI(cores, points_per_thread);
			BigDecimal result = monteCarloPI.calculate();
			BigDecimal pi = BigDecimal.valueOf(Math.PI);
			double error = 100 * Math.abs(result.doubleValue() - Math.PI) / Math.PI;

			info.append("\n\n pi: ").append(result.toPlainString())
					.append("\n terror: ").append(error)
					.append("\n in:").append( (System.currentTimeMillis() - now.getTime()) );

		}

		return info.toString();
	}
}

