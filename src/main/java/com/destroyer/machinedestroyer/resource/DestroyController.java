/*
*************************************************************
*Nome     : DestroyController.java
*Autor    : brunopedroso
*Data     : 31/10/2017
*************************************************************
*/
package com.destroyer.machinedestroyer.resource;

import static java.lang.String.join;
import static java.util.Collections.nCopies;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.destroyer.machinedestroyer.service.MonteCarloPI;

@RestController("/destroy")
public class DestroyController {
  
  private static Map<Long, String> map = new HashMap<Long, String>();
  private static long memoryCount = 0;
  private final static BigDecimal POINTS = new BigDecimal("200000000");

  @Value("${app.memory.text}")
  private String memoryText;

  @GetMapping("/memory/{power}")
  public String destroyMemory(@PathVariable int power) {
      if(power <= 0)
          power = 1000;

      if(memoryText==null) {
        StringBuilder sb = new StringBuilder();
        IntStream.range(0, 5000).forEach(i -> sb.append("0"));
        memoryText = sb.toString();
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
  
  @GetMapping("/memory/clean")
  public String clearMemory() {
      memoryCount = 0;
      map = null;
      map = new HashMap<Long, String>();

      Runtime rt = Runtime.getRuntime();
      long total = rt.totalMemory();
      long free = rt.freeMemory();
      long used = total - free;

      return "#" + memoryCount++
                      + ", Total: " + total
                      + ", Used: " + used
                      + ", Free: " + free;
  }

  @GetMapping("/cpu/{power}")
  public String destroyCpu(@PathVariable int power) {
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
//          BigDecimal pi = BigDecimal.valueOf(Math.PI);
          double error = 100 * Math.abs(result.doubleValue() - Math.PI) / Math.PI;

          info.append("\n\n pi: ").append(result.toPlainString())
                  .append("\n terror: ").append(error)
                  .append("\n in:").append( (System.currentTimeMillis() - now.getTime()));
      }

      return info.toString();
  }
  
}
